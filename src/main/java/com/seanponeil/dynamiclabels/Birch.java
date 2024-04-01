package com.seanponeil.dynamiclabels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@Setter
@NoArgsConstructor
@Node("Birch")
public class Birch extends Tree {
}