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

import com.googlecode.objectify.annotation.Subclass;

/**
 * Specializes the Photo class for photos of Auroras
 */
@Subclass
public class AuroraPhoto extends Photo {

	/**
	 * The main color of the aurora in this photo
	 */
	private AuroraColor mainColor = null;

	/**
	 * Default constructor
	 */
	public AuroraPhoto() {
		super();
	}

	/**
	 * Creates a AuroraPhoto from a PhotoId
	 * @param id
	 */
	public AuroraPhoto(PhotoId id) {
		super(id);
		if (id == null) {
			throw new IllegalArgumentException("The id must not be null");
		}
	}

	/**
	 * Setter method
	 * @param color
	 */
	public void setMainColor(AuroraColor color) {
		if (color == null) {
			throw new IllegalArgumentException("The color must not be null");
		}
		mainColor = color;
	}

	/**
	 * Getter method
	 * @return
	 */
	public AuroraColor getMainColor() {
		return mainColor;
	}
}

/**
 * Visible colors of auroras. See https://en.wikipedia.org/wiki/Aurora#Visual_forms_and_colors
 */
enum AuroraColor {
	Red,
	Green,
	Blue,
	Yellow,
	Pink
}
