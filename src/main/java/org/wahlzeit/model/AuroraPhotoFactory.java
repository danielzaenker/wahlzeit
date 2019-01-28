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

import org.wahlzeit.annotations.PatternInstace;
import org.wahlzeit.services.AuroraManager;

@PatternInstace(
		patternName = "Abstract Factory",
		participants = {"ConcreteFactory"}
)
/**
 * Specializes the PhotoFactory for the AuroraPhoto class
 */
public class AuroraPhotoFactory extends PhotoFactory {
	@Override
	public AuroraPhoto createPhoto() {
		Aurora aurora = AuroraManager.getInstance().createAurora("Default", null);
		AuroraPhoto photo = new AuroraPhoto();
		photo.setAurora(aurora);
		return photo;
	}

	@Override
	public AuroraPhoto createPhoto(PhotoId id) {
		Aurora aurora = AuroraManager.getInstance().createAurora("Default", null);
		AuroraPhoto photo = new AuroraPhoto(id);
		photo.setAurora(aurora);
		return photo;
	}

	@Override
	public AuroraPhoto loadPhoto(PhotoId id) {
		// same behaviour as in PhotoFactory
		return null;
	}
}
