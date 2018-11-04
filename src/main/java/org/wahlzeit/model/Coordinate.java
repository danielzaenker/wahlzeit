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
public class Coordinate {
	/**
	 * x, y and z component of the coordinate
	 */
	private final double x;
	private final double y;
	private final double z;

	/**
	 * Epsilon value for double comparision
	 */
	private static final double EPSILON = 0.0000001;

	private static boolean almostEqual(double a, double b) {
		return Math.abs(a - b) < EPSILON;
	}

	/**
	 * Default constructor
	 */
	public Coordinate() {
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
	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Returns the cartesian distance from this to another coordinate
	 *
	 * @param other the other coordinate
	 * @return the calculated distance
	 */
	public double getDistance(Coordinate other) {
		if (other == null) {
			throw new IllegalArgumentException("The other coordinate must not be null");
		}
		double connectionX = other.x - x;
		double connectionY = other.y - y;
		double connectionZ = other.z - z;
		double length = Math.sqrt(connectionX * connectionX + connectionY * connectionY + connectionZ * connectionZ);
		return length;
	}

	/**
	 * Checks if this and another coordinate are the same under an epsilon value
	 *
	 * @param other the other coordinate
	 * @return true if the coordinates are the same, false otherwise
	 */
	public boolean isEqual(Coordinate other) {
		if (other == null) {
			return false;
		}
		return almostEqual(x, other.x) && almostEqual(y, other.y) && almostEqual(z, other.z);
	}

	/**
	 * Forwards equals to {@link Coordinate#isEqual(Coordinate)}
	 *
	 * @param other the object to compare
	 * @return false if the other object is not an instance of {@link Coordinate},
	 *         otherwise the result of {@link Coordinate#isEqual(Coordinate)}
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Coordinate)) {
			return false;
		}
		Coordinate coordinate = (Coordinate) other;
		return isEqual(coordinate);
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
}
