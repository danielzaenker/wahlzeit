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
 * A coordinate
 */
public interface Coordinate {

	/**
	 * Converts this coordinate to a CartesianCoordinate and returns it
	 * @return the converted coordinate
	 */
	CartesianCoordinate asCartesianCoordinate();

	/**
	 * Returns the cartesian distance from this to another coordinate
	 *
	 * @param coordinate the other coordinate
	 * @return the calculated distance
	 */
	double getCartesianDistance(Coordinate coordinate);

	/**
	 * Converts this coordinate to a SphericCoordinate and returns it
	 * @return the converted coordinate
	 */
	SphericCoordinate asSphericCoordinate();

	/**
	 * Returns the central angle between this and another coordinate
	 * @param coordinate the other coordinate
	 * @return the calculated angle
	 */
	double getCentralAngle(Coordinate coordinate);

	/**
	 * Checks if this and another coordinate are the same under an epsilon value
	 *
	 * @param coordinate the other coordinate
	 * @return true if the coordinates are the same, false otherwise
	 */
	boolean isEqual(Coordinate coordinate);
}
