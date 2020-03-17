/*
 * Copyright (c) 2020 Contributors to the Eclipse Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eclipse.microprofile.graphql.tck.apps.superhero.db;

import org.eclipse.microprofile.graphql.MetaField;
import org.eclipse.microprofile.graphql.tck.apps.superhero.model.Villain;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class VillainDatabase {

    private List<MetaField> metaFields;
    private List<Villain> allVillains;

    private void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        try {
            Jsonb jsonb = JsonbBuilder.create();
            String mapJson = getInitalJson();
            allVillains = jsonb.fromJson(mapJson, new ArrayList<Villain>() {
            }.getClass().getGenericSuperclass());
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
