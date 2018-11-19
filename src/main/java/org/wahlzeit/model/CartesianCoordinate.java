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
 * A three dimensional cartesian coordinate
 */
public class CartesianCoordinate extends AbstractCoordinate {
	/**
	 * x, y and z component of the coordinate
	 */
	private final double x;
	private final double y;
	private final double z;

	/**
	 * Default constructor
	 */
	public CartesianCoordinate() {
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
	}

	/**
	 * Creates a coordinate from a x, y and z value
	 *
	 * @param x
	 * @param y
	 * @param z
	 */
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		assertIsNotNull(coordinate);
		return doGetCartesianDistance(coordinate.asCartesianCoordinate());
	}

	protected double doGetCartesianDistance(CartesianCoordinate other) {
		double connectionX = other.getX() - this.getX();
		double connectionY = other.getY() - this.getY();
		double connectionZ = other.getZ() - this.getZ();
		return Math.sqrt(connectionX * connectionX + connectionY * connectionY + connectionZ * connectionZ);
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		double radius = getCartesianDistance(new CartesianCoordinate());
		double theta = Math.acos(this.getZ() / radius);
		double phi = Math.atan2(this.getY(), this.getX());
		return new SphericCoordinate(theta, phi, radius);
	}

	@Override
	public boolean isEqual(Coordinate other) {
		return isValid(other) && doIsEqual(other.asCartesianCoordinate());
	}

	protected boolean doIsEqual(CartesianCoordinate other) {
		return  equalUnderEpsilon(this.getX(), other.getX()) &&
				equalUnderEpsilon(this.getY(), other.getY()) &&
				equalUnderEpsilon(this.getZ(), other.getZ());
	}
}
