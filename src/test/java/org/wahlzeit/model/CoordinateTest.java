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

import static org.junit.Assert.assertEquals;


public class CoordinateTest {

	private static final double DELTA = 0.001;
	private Coordinate cc1;
	private Coordinate cc2;
	private Coordinate cc3;
	private Coordinate sc1;
	private  Coordinate sc2;
	private  Coordinate sc3;

	@Before
	public void initCoordinates() {
		cc1 = CartesianCoordinate.getCartesianCoordinate(1.8, -2.1, 3.9);
		cc2 = CartesianCoordinate.getCartesianCoordinate(1.8, -2.1, 3.9);
		cc3 = CartesianCoordinate.getCartesianCoordinate(3.5, -4.3, 5.7);
		sc1 = SphericCoordinate.getSphericCoordinate(0.771558, -0.887605, 7.95173);
		sc2 = SphericCoordinate.getSphericCoordinate(0.771558, -0.887605, 7.95173);
		sc3 = SphericCoordinate.getSphericCoordinate(0.616871, -0.86217, 4.78121);
	}

	private static void almostEqual(CartesianCoordinate c1, CartesianCoordinate c2) {
		assertEquals(c1.getX(), c2.getX(), DELTA);
		assertEquals(c1.getY(), c2.getY(), DELTA);
		assertEquals(c1.getZ(), c2.getZ(), DELTA);
	}

	private static void almostEqual(SphericCoordinate c1, SphericCoordinate c2) {
		assertEquals(c1.getTheta(), c2.getTheta(), DELTA);
		assertEquals(c1.getPhi(), c2.getPhi(), DELTA);
		assertEquals(c1.getRadius(), c2.getRadius(), DELTA);
	}
	@Test
	public void testConversionToCartesian() {
		almostEqual(sc3.asCartesianCoordinate(), cc1.asCartesianCoordinate());
		almostEqual(sc1.asCartesianCoordinate(), cc3.asCartesianCoordinate());
	}

	@Test
	public void testConversionToSpheric() {
		almostEqual(cc1.asSphericCoordinate(), sc3.asSphericCoordinate());
		almostEqual(cc3.asSphericCoordinate(), sc1.asSphericCoordinate());
	}

	@Test
	public void testGetCartesianDistance() {
		assertEquals(3.3121, sc1.getCartesianDistance(sc3), DELTA);
		assertEquals(3.3121, cc1.getCartesianDistance(sc1), DELTA);
		assertEquals(3.3121, sc1.getCartesianDistance(cc1), DELTA);
		assertEquals(0., sc1.getCartesianDistance(sc2), DELTA);
		assertEquals(0., sc1.getCartesianDistance(cc3), DELTA);
	}

	@Test
	public void testCentralAngle() {
		assertEquals(0.15590972861387034, cc3.getCentralAngle(sc3), DELTA);
		assertEquals(0.15590972861387034, cc1.getCentralAngle(cc3), DELTA);
		assertEquals(0.0, cc1.getCentralAngle(cc2), DELTA);
		assertEquals(0.0, cc1.getCentralAngle(sc3), DELTA);
		assertEquals(0.0, cc1.getCentralAngle(cc2), DELTA);
	}
}
