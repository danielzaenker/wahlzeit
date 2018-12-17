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

public class SphericCoordinateTest {

	private static final double DELTA = 0.0001;
	private SphericCoordinate c1;
	private SphericCoordinate c2;
	private SphericCoordinate c3;

	@Before
	public void initCoordinates() {
		c1 = SphericCoordinate.getSphericCoordinate(0.771558, -0.887605, 7.95173);
		c2 = SphericCoordinate.getSphericCoordinate(0.771558, -0.887605, 7.95173);
		c3 = SphericCoordinate.getSphericCoordinate(0.616871, -0.86217, 4.78121);
	}

	@Test
	public void testInitialization() {
		SphericCoordinate coordinate1 = SphericCoordinate.getSphericCoordinate(Math.PI, 2.0 * Math.PI, 1.0);
		SphericCoordinate coordinate2 = SphericCoordinate.getSphericCoordinate(Math.PI + 0.5, 2.0 * Math.PI + 1.2, 1.0);
		assertEquals(Math.PI, coordinate1.getTheta(), DELTA);
		assertEquals(0.0, coordinate1.getPhi(), DELTA);
		assertEquals(Math.PI - 0.5, coordinate2.getTheta(), DELTA);
		assertEquals(1.2 + Math.PI, coordinate2.getPhi(), DELTA);
		assertEquals(5.395580, c1.getPhi(), DELTA);
	}

	@Test (expected = IllegalStateException.class)
	public void testInvalidInitialization() {
		SphericCoordinate.getSphericCoordinate(0.0, 0.0, -10.0);
	}

	@Test
	public void testCentralAngle() {
		assertEquals(0.15590972861387034, c1.getCentralAngle(c3), DELTA);
		assertEquals(c1.getCentralAngle(c3), c3.getCentralAngle(c1), DELTA);
		assertEquals(0.0, c1.getCentralAngle(c2), DELTA);
		assertEquals(0.0, c1.getCentralAngle(c1), DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetCentralAngleException() {
		c1.getCentralAngle(null);
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
	public void testOriginsAreEqual() {
		Coordinate origin1 = SphericCoordinate.getSphericCoordinate(3.561, 4.1235, 0.0);
		Coordinate origin2 = SphericCoordinate.getSphericCoordinate(2.412, 3.123, 0.0);
		assertTrue(origin1.isEqual(origin2));
	}

	@Test
	public void polesAreEqual() {
		Coordinate pole1 = SphericCoordinate.getSphericCoordinate(Math.PI, 1.34, 5.0);
		Coordinate pole2 = SphericCoordinate.getSphericCoordinate(Math.PI, 3.83, 5.0);
		Coordinate pole3 = SphericCoordinate.getSphericCoordinate(0.0, 1.42, 5.0);
		Coordinate pole4 = SphericCoordinate.getSphericCoordinate(0.0, 0.145, 5.0);
		Coordinate pole5 = SphericCoordinate.getSphericCoordinate(0.0, 1.42, 3.6);
		assertTrue(pole1.isEqual(pole2));
		assertTrue(pole3.isEqual(pole4));
		assertFalse(pole1.isEqual(pole3));
		assertFalse(pole2.isEqual(pole5));
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
