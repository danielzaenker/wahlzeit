/*
 * Copyright (c) 2006-2019 by Daniel Zänker
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

package org.wahlzeit.services;

import org.wahlzeit.model.Aurora;
import org.wahlzeit.model.AuroraColor;
import org.wahlzeit.model.AuroraType;

import java.util.HashMap;

public class AuroraManager extends ObjectManager {
	protected static AuroraManager instance = new AuroraManager();

	protected HashMap<String, AuroraType> typeMap;

	public AuroraManager getInstance() {
		return instance;
	}

	public Aurora createAurora(String auroraTypeName, AuroraColor auroraColor) {
		assertIsValidAuroraTypeName(auroraTypeName);
		AuroraType auroraType = getAuroraType(auroraTypeName);
		Aurora aurora = auroraType.createInstance(auroraColor);
		return aurora;
	}

	protected AuroraType getAuroraType(String typeName) {
		AuroraType type =  typeMap.get(typeName);
		if (type == null) {
			type = new AuroraType(typeName);
			typeMap.put(typeName, type);
		}
		return type;
	}

	protected static void assertIsValidAuroraTypeName(String name) {
		if (name == null || name.equals("")) {
			throw new IllegalArgumentException("Name of type is invalid");
		}
	}
}
