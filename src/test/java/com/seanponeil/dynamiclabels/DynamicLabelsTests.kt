package com.seanponeil.dynamiclabels

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.containers.Neo4jContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers


@SpringBootTest
@Testcontainers
class DynamicLabelsTests @Autowired constructor(
    val plantRepository: PlantRepository,
    val birchRepository: BirchRepository,
) {

    @AfterEach
    fun tearDown() = plantRepository.deleteAll()

    @Test
    @Transactional
    fun findAllPlantsWithDynamicLabels() {

        val birchTrees = listOf(
            Birch().apply { labels = setOf("birch_1") },
            Birch().apply { labels = setOf("birch_2") },
            Birch(),
            Birch()
        )

        val pineTrees = listOf(
            Pine().apply { labels = setOf("pine_1") },
            Pine().apply { labels = setOf("pine_2") },
            Pine(),
            Pine()
        )

        val plants = plantRepository.saveAll(birchTrees + pineTrees)

        assertThat(plants).hasOnlyElementsOfTypes(Birch::class.java, Pine::class.java)

        assertThat(plantRepository.findAll()).hasOnlyElementsOfTypes(Birch::class.java, Pine::class.java)
    }

    @Test
    @Transactional
    fun findAllPlantsNoDynamicLabels() {
        val birchTrees = List(4) { Birch() }
        val pineTrees = List(4) { Pine() }

        plantRepository.saveAll(birchTrees + pineTrees)

        val plants = plantRepository.findAll()
        assertThat(plants).hasOnlyElementsOfTypes(Birch::class.java, Pine::class.java)
    }

    @Test
    fun findAllBirchTrees() {
        val birchTrees = listOf(
            Birch().apply { labels = setOf("birch_1") },
            Birch().apply { labels = setOf("birch_2") },
            Birch(),
            Birch(),
        )

        birchRepository.saveAll(birchTrees)

        val trees = birchRepository.findAll()
        assertThat(trees).hasOnlyElementsOfTypes(Birch::class.java)
    }

    companion object {
        @Container
        @JvmStatic
        val neo4j = Neo4jContainer("neo4j:4.4").apply {
            withoutAuthentication()
        }

        @JvmStatic
        @DynamicPropertySource
        fun registerDb(registry: DynamicPropertyRegistry) {
            registry.add("spring.neo4j.uri") { neo4j.boltUrl }
        }
    }
}
