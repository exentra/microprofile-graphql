package org.eclipse.microprofile.graphql.tck.apps.superhero.db;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.eclipse.microprofile.graphql.MetaField;
import org.eclipse.microprofile.graphql.tck.apps.superhero.model.Villain;

@ApplicationScoped
public class VillainDatabase {

    private List<MetaField> metaFields;
    private List<Villain> allVillains;

    private void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
	try {
	    Jsonb jsonb = JsonbBuilder.create();
	    String mapJson = getInitalJson();
	    allVillains = jsonb.fromJson(mapJson, ArrayList.class.getGenericSuperclass());
	} catch (Exception ex) {
	    throw new RuntimeException(ex);
	}
    }

    public Villain getVillain(String name) {
	return allVillains.stream().filter(v -> v.getName().equals(name))
		.findFirst()
		.orElse(null);
    }

    private static String getInitalJson() {
	return "[{\"name\":\"Joker\"}]";
    }
}
