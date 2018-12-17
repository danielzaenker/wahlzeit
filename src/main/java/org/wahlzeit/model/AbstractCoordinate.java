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

import org.wahlzeit.utils.SharedObjectManager;

/**
 * An abstract Coordinate
 */
public abstract class AbstractCoordinate implements Coordinate {

	protected static SharedObjectManager<Coordinate> sharedObjectManager = new SharedObjectManager<>();

	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		assertIsNotNull(coordinate);
		double distance = this.asCartesianCoordinate().doGetCartesianDistance(coordinate.asCartesianCoordinate());
		assertValidDistance(distance);
		return distance;
	}

	protected static void assertValidDistance(double distance) {
		if (!Double.isFinite(distance) || distance < 0.0) {
			throw new IllegalStateException("Invalid cartesian distance");
		}
	}

	protected abstract double doGetCartesianDistance(CartesianCoordinate other);

	@Override
	public double getCentralAngle(Coordinate coordinate) {
		assertIsNotNull(coordinate);
		double angle = this.asSphericCoordinate().doGetCentralAngle(coordinate.asSphericCoordinate());
		assertValidCentralAngle(angle);
		return angle;
	}

	protected static void assertValidCentralAngle(double angle) {
		if (!Double.isFinite(angle) || angle > Math.PI || angle < -Math.PI) {
			throw new IllegalStateException("Invalid central angle");
		}
	}

	protected abstract double doGetCentralAngle(SphericCoordinate other);

	protected static void assertIsNotNull(Coordinate coordinate) {
		if (!isValid(coordinate)) {
			throw new IllegalArgumentException("The other coordinate must not be null");
		}
	}

	@Override
	public boolean isEqual(Coordinate other) {
		return isValid(other) && doIsEqual(other);
	}

	protected abstract boolean doIsEqual(Coordinate other);

	protected static boolean isValid(Coordinate coordinate) {
		return coordinate != null;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Coordinate)) {
			return false;
		}
		Coordinate coordinate = (Coordinate) other;
		return isEqual(coordinate);
	}

	/**
	 * Epsilon value for double comparision
	 */
	public static final double EPSILON = 1e-8;

	protected static boolean equalUnderEpsilon(double a, double b) {
		return Math.abs(a - b) < EPSILON;
	}

}
