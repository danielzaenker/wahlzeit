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

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartesianCoordinateTest {

	private static final double DELTA = 0.0001;
	private CartesianCoordinate c1;
	private CartesianCoordinate c2;
	private CartesianCoordinate c3;

	@Before
	public void initCoordinates() {
		c1 = CartesianCoordinate.getCartesianCoordinate(1.8, -2.1, 3.9);
		c2 = CartesianCoordinate.getCartesianCoordinate(1.8, -2.1, 3.9);
		c3 = CartesianCoordinate.getCartesianCoordinate(3.5, -4.3, -5.7);
	}

	@Test
	public void testGetCartesianDistance() {
		assertEquals(9.9945, c1.getCartesianDistance(c3), DELTA);
		assertEquals(c1.getCartesianDistance(c3), c3.getCartesianDistance(c1), DELTA);
		assertEquals(0.0, c1.getCartesianDistance(c2), DELTA);
		assertEquals(0.0, c1.getCartesianDistance(c1), DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetDistanceWithException() {
		c1.getCartesianDistance(null);
	}

	@Test
	public void testIsEqual() {
		assertTrue(c1.isEqual(c2));
		assertTrue(c2.isEqual(c1));
		assertTrue(c1.isEqual(c1));
		assertFalse(c1.isEqual(c3));
		assertFalse(c3.isEqual(c2));
		assertFalse(c1.isEqual(null));
	}

	@Test
	public void testEquals() {
		Object object = new Object();
		assertTrue(c1.equals(c2));
		assertTrue(c2.equals(c2));
		assertTrue(c1.equals(c1));
		assertFalse(c1.equals(c3));
		assertFalse(c1.equals(object));
		assertFalse(c1.equals(null));
	}
}
