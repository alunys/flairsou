package flairsou.common.infrastructure.exposed

import ch.tutteli.atrium.api.fluent.en_GB.isSameAs
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.verbs.expect
import org.spekframework.spek2.Spek
import java.nio.file.Paths

object AbstractDatabaseTest : Spek({

        val database by memoized {
            class TestDatabase:AbstractDatabase("${Paths.get("").toAbsolutePath().toString()}/src/test/tmp/testDatabase.db")
            val database  = TestDatabase()
            database
        }

        group("create database") {
            test("tables should exist") {
                database.connect()
                database.createSchema()

                val tableNames = listOf("\"account\"", "\"account_operation\"")

                expect(tableNames.count()).asExpect().isSameAs(database.execAndMap("SELECT * FROM sqlite_master where name in (${tableNames.joinToString()})", { rs -> rs}).count())
            }
        }

})