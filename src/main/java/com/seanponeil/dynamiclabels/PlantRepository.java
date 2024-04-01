package com.seanponeil.dynamiclabels;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends Neo4jRepository<Plant, String> {
    @Query("MATCH (p:Plant) RETURN p")
    List<Plant> findAllPlants();
}
