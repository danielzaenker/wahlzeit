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

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {

	@Test
	public void testLocation() {
		Coordinate c1 = new Coordinate(1.7, 81.5, -4.7);
		Coordinate c2 = new Coordinate(6.6, 5.1, -9.2);
		Coordinate zeroCoordinate = new Coordinate();

		Location location1 = new Location(c1);
		Location location2 = new Location();
		assertNotNull(location1.getCoordinate());
		assertNotNull(location2.getCoordinate());
		assertSame(location1.getCoordinate(), c1);
		assertTrue(location2.getCoordinate().isEqual(zeroCoordinate));

		location1.setCoordinate(c2);
		assertSame(location1.getCoordinate(), c2);
	}
}
