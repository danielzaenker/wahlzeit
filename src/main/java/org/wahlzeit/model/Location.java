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
 * A location represented by a coordinate
 */

public class Location {
	/**
	 * The coordinate of this location
	 */
	private Coordinate coordinate;

	/**
	 * Creates a location with a coordinate
	 * @param coordinate the coordinate of the location
	 * @throws IllegalArgumentException if the coordinate is null
	 */
	public Location(Coordinate coordinate) {
		setCoordinate(coordinate);
	}

	/**
	 * Returns the coordinate of this location
	 * @return
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * Sets the coordinate of this location
	 * @param coordinate
	 * @throws IllegalArgumentException if the coordinate is null
	 */
	public void setCoordinate(Coordinate coordinate) {
		if (coordinate == null) {
			throw new IllegalArgumentException("The coordinate must not be null");
		}
		this.coordinate = coordinate;
	}
}
