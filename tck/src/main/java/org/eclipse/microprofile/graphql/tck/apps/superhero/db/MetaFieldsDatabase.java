package org.eclipse.microprofile.graphql.tck.apps.superhero.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.graphql.GraphQLMetaModel;
import org.eclipse.microprofile.graphql.MetaField;
import org.eclipse.microprofile.graphql.MetaField.MetaFields;
import org.eclipse.microprofile.graphql.tck.apps.superhero.model.Villain;

@ApplicationScoped
@GraphQLMetaModel
public class MetaFieldsDatabase {

    private List<MetaField> villainFields;

    public MetaFieldsDatabase() {
	initFields();
    }

    private final void initFields() {
	villainFields = new ArrayList<>();
	villainFields.add(new MetaField("hairColor", String.class));
    }

    @MetaFields(targetType = Villain.class)
    public List<MetaField> getVillainFields() {
	return Collections.unmodifiableList(villainFields);
    }

    public void addVillainField(String name, Class<?> type) {
	villainFields.add(new MetaField(name, type));
    }
}
