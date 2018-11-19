/*
 * Copyright (c) 2006-2018 by Daniel Zänker
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

/**
 * A spherical coordinate represented by a theta and phi angle, plus a radius
 */
public class SphericCoordinate extends AbstractCoordinate {

	/**
	 * The theta and phi angle
	 */
	private final double theta;
	private final double phi;

	/**
	 * The radius
	 */
	private final double radius;

	public double getTheta() {
		return theta;
	}

	public double getPhi() {
		return phi;
	}

	public double getRadius() {
		return radius;
	}

	public SphericCoordinate() {
		this.theta = 0.0;
		this.phi = 0.0;
		this.radius = 0.0;
	}

	/**
	 * Creates a SphericCoordinate from theta, phi and radius
	 * @param theta
	 * @param phi
	 * @param radius
	 */
	public SphericCoordinate(double theta, double phi, double radius) {
		this.theta = theta;
		this.phi = phi;
		this.radius = radius;
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		double x = this.getRadius() * Math.sin(this.getTheta()) * Math.cos(this.getPhi());
		double y = this.getRadius() * Math.sin(this.getTheta()) * Math.sin(this.getPhi());
		double z = this.getRadius() * Math.cos(this.getTheta());
		return new CartesianCoordinate(x, y, z);
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		return this;
	}

	@Override
	public double getCentralAngle(Coordinate coordinate) {
		assertIsNotNull(coordinate);
		return doGetCentralAngle(coordinate.asSphericCoordinate());
	}

	protected double doGetCentralAngle(SphericCoordinate other) {
		double sinProduct = Math.sin(this.getTheta()) * Math.sin(other.getTheta());
		double cosProduct = Math.cos(this.getTheta()) * Math.cos(other.getTheta());
		double deltaPhi = Math.abs(this.getPhi() - other.getPhi());
		return Math.acos(sinProduct + cosProduct * Math.cos(deltaPhi));
	}

	@Override
	public boolean isEqual(Coordinate other) {
		return isValid(other) && doIsEqual(other.asSphericCoordinate());
	}

	protected boolean doIsEqual(SphericCoordinate other) {
		return  equalUnderEpsilon(this.getTheta(), other.getTheta()) &&
				equalUnderEpsilon(this.getPhi(), other.getPhi()) &&
				equalUnderEpsilon(this.getRadius(), other.getRadius());
	}
}
