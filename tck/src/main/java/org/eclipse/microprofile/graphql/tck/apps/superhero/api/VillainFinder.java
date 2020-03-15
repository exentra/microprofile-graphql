package org.eclipse.microprofile.graphql.tck.apps.superhero.api;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.tck.apps.superhero.db.MetaFieldsDatabase;
import org.eclipse.microprofile.graphql.tck.apps.superhero.db.UnknownVillainException;
import org.eclipse.microprofile.graphql.tck.apps.superhero.db.VillainDatabase;
import org.eclipse.microprofile.graphql.tck.apps.superhero.model.Villain;

@GraphQLApi
public class VillainFinder {
    private static final Logger LOG = Logger.getLogger(VillainFinder.class.getName());

    @Inject
    private VillainDatabase villainDB;

    @Inject
    private MetaFieldsDatabase metaFieldsDB;

    @Query
    public Villain villain(@Name("name") @Description("Villain name") String name) throws UnknownVillainException {
	LOG.log(Level.INFO, "superHero invoked [{0}]", name);
	return Optional.ofNullable(villainDB.getVillain(name)).orElseThrow(() -> new UnknownVillainException(name));
    }

    @Mutation
    @Description("Add a new meta field to a villain")
    public void addMetaField(@Name("name") String name, @Name("type") String type) {
	LOG.log(Level.INFO, "addMetaField invoked [{0}],[{1}]", new Object[] { name, type });
	Class<?> typeClass;
	try {
	    typeClass = getClass().getClassLoader().loadClass(type);
	    metaFieldsDB.addVillainField(name, typeClass);
	} catch (ClassNotFoundException e) {
	    throw new IllegalArgumentException(type + " as fully qualified class name has not been found", e);
	}
    }
}
