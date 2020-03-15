package org.eclipse.microprofile.graphql.tck.apps.superhero.model;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.microprofile.graphql.MetaFieldType;

public class Villain implements Character, MetaFieldType {

	private final Map<String, Object> metaFieldValues = new HashMap<>();
	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Object getMetaFieldValue(String name) {
		return metaFieldValues.get(name);
	}

	@Override
	public void setMetaFieldValue(String name, Object value) {
		metaFieldValues.put(name, value);
	}
}
