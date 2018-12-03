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
 * A spherical coordinate represented by a theta and phi angle, plus a radius.
 * Theta, the latitude has the range of [0, PI)
 * Phi, the longitude has the range of [0, 2*PI)
 */
public class SphericCoordinate extends AbstractCoordinate {

	/**
	 * The theta and phi angle.
	 */
	private final double theta;
	private final double phi;

	/**
	 * The radius
	 */
	private final double radius;

	/**
	 * Constants for maximum values for normalization
	 */
	public static double MAX_THETA = Math.PI;
	public static double MAX_PHI = Math.PI * 2.0;


	public double getTheta() {
		return theta;
	}

	public double getPhi() {
		return phi;
	}

	public double getRadius() {
		return radius;
	}

	/**
	 * Default Constructor
	 */
	public SphericCoordinate() {
		this.theta = 0.0;
		this.phi = 0.0;
		this.radius = 0.0;
		assertClassInvariants();
	}

	/**
	 * Creates a SphericCoordinate from theta, phi and radius
	 * @param theta
	 * @param phi
	 * @param radius must not be negative
	 * @throws IllegalStateException if radius is negative or the angles are invalid
	 */
	public SphericCoordinate(double theta, double phi, double radius) {
		theta = getNormalizedAngle(theta, 2.0 * Math.PI);
		if (theta > MAX_THETA) {
			theta = 2.0 * Math.PI - theta;
			phi += Math.PI;
		}
		this.theta = theta;
		this.phi = getNormalizedAngle(phi, 2.0 * Math.PI);
		this.radius = radius;
		assertClassInvariants();
	}

	protected void assertClassInvariants() {
		boolean thetaIsValid = Double.isFinite(this.getTheta()) &&
				this.getTheta() >= 0.0 && this.getTheta() <= MAX_THETA;
		boolean phiIsValid = Double.isFinite(this.getPhi()) &&
				this.getPhi() >= 0.0 && this.getPhi() < MAX_PHI;
		boolean radiusIsValid = Double.isFinite(this.getRadius()) &&
				this.getRadius() >= 0.0;

		if (!thetaIsValid || !phiIsValid || !radiusIsValid) {
			throw new IllegalStateException("SphericCoordinate is not valid");
		}
	}

	protected static double getNormalizedAngle(double angle, double maxAngle) {
		double normalized = angle % maxAngle;
		if (normalized < 0) {
			normalized = maxAngle + normalized;
		}
		return normalized;
	}

	protected void assertValidRadius() {
		if (radius < 0.0) {
			throw new IllegalArgumentException("Radius must not be negative");
		}
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
	protected double doGetCentralAngle(SphericCoordinate other) {
		double sinProduct = Math.sin(this.getTheta()) * Math.sin(other.getTheta());
		double cosProduct = Math.cos(this.getTheta()) * Math.cos(other.getTheta());
		double deltaPhi = Math.abs(this.getPhi() - other.getPhi());
		return Math.acos(sinProduct + cosProduct * Math.cos(deltaPhi));
	}

	@Override
	protected double doGetCartesianDistance(CartesianCoordinate other) {
		return this.asCartesianCoordinate().doGetCartesianDistance(other);
	}

	@Override
	protected boolean doIsEqual(Coordinate coordinate) {
		SphericCoordinate other = coordinate.asSphericCoordinate();

		// check if the radius of booth coordinate is zero
		boolean bothInOrigin = equalUnderEpsilon(this.getRadius(), 0.0) &&
				equalUnderEpsilon(other.getRadius(), 0.0);
		if (bothInOrigin) {
			return true;
		}
		boolean radiusEqual = equalUnderEpsilon(this.getRadius(), other.getRadius());

		// ignore phi if booth coordinates are on the poles
		if ((equalUnderEpsilon(this.getTheta(), MAX_THETA) && equalUnderEpsilon(other.getTheta(), MAX_THETA)) ||
				(equalUnderEpsilon(this.getTheta(), 0.0) && equalUnderEpsilon(other.getTheta(), 0.0))) {
			return radiusEqual;
		}

		boolean allEqual = equalUnderEpsilon(this.getTheta(), other.getTheta()) &&
				equalUnderEpsilon(this.getPhi(), other.getPhi()) &&
				radiusEqual;
		return allEqual;
	}
}
