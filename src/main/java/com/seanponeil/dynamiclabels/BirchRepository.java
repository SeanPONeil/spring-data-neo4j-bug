package com.seanponeil.dynamiclabels;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BirchRepository extends Neo4jRepository<Birch, String> {
}
