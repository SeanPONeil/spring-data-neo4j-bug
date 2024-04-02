package com.seanponeil.dynamiclabels;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PlantRepository extends Neo4jRepository<Plant, String> {
}
