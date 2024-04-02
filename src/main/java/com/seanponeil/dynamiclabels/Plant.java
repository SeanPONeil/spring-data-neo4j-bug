package com.seanponeil.dynamiclabels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.DynamicLabels;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Node("Plant")
public abstract class Plant {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    protected String id;

    @DynamicLabels protected Set<String> labels = Set.of();
}
