package org.eclipse.microprofile.graphql;

public interface MetaFieldType {

	Object getMetaFieldValue(String name);

	void setMetaFieldValue(String name, Object value);
}
