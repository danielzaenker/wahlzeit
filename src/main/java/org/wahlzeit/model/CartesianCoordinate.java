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
	private CartesianCoordinate() {
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
		assertClassInvariants();
	}

	/**
	 * Creates a coordinate from a x, y and z value
	 *
	 * @param x
	 * @param y
	 * @param z
	 */
	private CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		assertClassInvariants();
	}

	public static CartesianCoordinate getCartesianCoordinate(double x, double y, double z) {
		return sharedObjectManager.get(new CartesianCoordinate(x, y, z)).asCartesianCoordinate();
	}

	protected void assertClassInvariants() {
		boolean xIsValid = Double.isFinite(this.getX());
		boolean yIsValid = Double.isFinite(this.getY());
		boolean zIsValid = Double.isFinite(this.getZ());

		if (!xIsValid || !yIsValid || !zIsValid) {
			throw new IllegalStateException("CartesianCoordinate is not valid");
		}
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
	protected double doGetCartesianDistance(CartesianCoordinate other) {
		double connectionX = other.getX() - this.getX();
		double connectionY = other.getY() - this.getY();
		double connectionZ = other.getZ() - this.getZ();
		return Math.sqrt(connectionX * connectionX + connectionY * connectionY + connectionZ * connectionZ);
	}

	@Override
	protected double doGetCentralAngle(SphericCoordinate other) {
		return this.asSphericCoordinate().doGetCentralAngle(other);
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		double radius = getCartesianDistance(new CartesianCoordinate());
		double theta = Math.acos(this.getZ() / radius);
		double phi = Math.atan2(this.getY(), this.getX());
		return SphericCoordinate.getSphericCoordinate(theta, phi, radius);
	}

	@Override
	protected boolean doIsEqual(Coordinate coordinate) {
		CartesianCoordinate other = coordinate.asCartesianCoordinate();
		return  equalUnderEpsilon(this.getX(), other.getX()) &&
				equalUnderEpsilon(this.getY(), other.getY()) &&
				equalUnderEpsilon(this.getZ(), other.getZ());
	}
}
