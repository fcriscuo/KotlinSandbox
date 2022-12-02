package org.batteryparkdev.testcontainers.neo4j

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.jupiter.api.Test
import org.neo4j.driver.AuthTokens
import org.neo4j.driver.GraphDatabase
import org.testcontainers.containers.Neo4jContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.stream.Collectors


/**
 * Created by fcriscuo on 2022Sep30
 */
@Testcontainers
internal class Neo4jExampleTest {
    @Test
    fun testSomethingUsingBolt() {

        // Retrieve the Bolt URL from the container
        val boltUrl: String = neo4jContainer.getBoltUrl()
        try {
            GraphDatabase.driver(boltUrl, AuthTokens.none()).use { driver ->
                driver.session().use { session ->
                    val one: Long = session.run("RETURN 1", emptyMap()).next().get(0).asLong()
                    assertThat(one).isEqualTo(1L)
                }
            }
        } catch (e: Exception) {
            fail(e.message)
        }
    }

    @Test
    @Throws(IOException::class)
    fun testSomethingUsingHttp() {

        // Retrieve the HTTP URL from the container
        val httpUrl: String = neo4jContainer.getHttpUrl()
        val url = URL("$httpUrl/db/data/transaction/commit")
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection
        con.setRequestMethod("POST")
        con.setRequestProperty("Content-Type", "application/json")
        con.setDoOutput(true)
        OutputStreamWriter(con.getOutputStream()).use { out ->
            out.write("{\"statements\":[{\"statement\":\"RETURN 1\"}]}")
            out.flush()
        }
        assertThat(con.getResponseCode()).isEqualTo(HttpURLConnection.HTTP_OK)
        BufferedReader(InputStreamReader(con.getInputStream())).use { buffer ->
            val expectedResponse =
                "{\"results\":[{\"columns\":[\"1\"],\"data\":[{\"row\":[1],\"meta\":[null]}]}],\"errors\":[]}"
            val response = buffer.lines().collect(Collectors.joining("\n"))
            assertThat(response).isEqualTo(expectedResponse)
        }
    }

    companion object {
        @Container
        private val neo4jContainer: Neo4jContainer<*> = Neo4jContainer(DockerImageName.parse("neo4j:4.4"))
            .withAdminPassword(null) // Disable password
    }
}