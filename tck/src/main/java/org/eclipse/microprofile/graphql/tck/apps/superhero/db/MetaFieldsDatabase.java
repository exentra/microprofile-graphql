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

import org.eclipse.microprofile.graphql.GraphQLMetaModel;
import org.eclipse.microprofile.graphql.MetaField;
import org.eclipse.microprofile.graphql.MetaFields;
import org.eclipse.microprofile.graphql.tck.apps.superhero.model.Villain;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
@GraphQLMetaModel
public class MetaFieldsDatabase {

    private List<MetaField> villainFields;

    public MetaFieldsDatabase() {
        initFields();
    }

    private void initFields() {
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
