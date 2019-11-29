package flairsou.workspace.repository

import ch.tutteli.atrium.api.fluent.en_GB.feature
import ch.tutteli.atrium.api.fluent.en_GB.isA
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.verbs.expect
import flairsou.workspace.domain.model.Workspace
import flairsou.workspace.infrastructure.filesystem.persistence.repository.WorkspaceRepository
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import org.spekframework.spek2.Spek
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

object WorkspaceRepositoryTest : Spek({

    group("method findUniqueOne") {

        beforeEachGroup {
            mockkStatic("java.lang.System")
        }

        afterEachGroup {
            unmockkStatic("java.lang.System")
        }

        group("without an existing file for workspace") {

            val homeDirTest = "/dirThatDoesNotExist"

            test("should return null") {

                every { System.getProperty("user.home") } returns homeDirTest

                val workspace = WorkspaceRepository.findUniqueOne()

                expect(workspace).asExpect().toBe(null)
            }
        }

        group("with an existing file for workspace") {

            val homeDirTest = "${Paths.get("").toAbsolutePath().toString()}/src/test/resources/home"

            test("should return Workspace object") {

                every { System.getProperty("user.home") } returns homeDirTest

                val workspace = WorkspaceRepository.findUniqueOne()

                expect(workspace).asExpect().isA<Workspace>()
                    .feature { f(it::uuid) }.toBe("uuid-test")
            }

        }
    }

    group("method save") {

        lateinit var homeDirTest:String

        beforeEachTest{
            mockkStatic("java.lang.System")
            homeDirTest = "${Paths.get("").toAbsolutePath().toString()}/src/test/tmp/home"
            every { System.getProperty("user.home") } returns homeDirTest
        }

        afterEachTest {
            unmockkStatic("java.lang.System")

            // Clear tmp directory
            Files.walk(Paths.get(homeDirTest))
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach({it.delete()});
        }

        test("should create a json file for workspace") {

            val workspaceCreated = Workspace(UUID.randomUUID().toString())

            WorkspaceRepository.save(workspaceCreated)

            val files = File(homeDirTest).listFiles()
            expect(files?.size).asExpect().equals(1)

            expect(WorkspaceRepository.findUniqueOne()).asExpect().isA<Workspace>()
                .feature { f(it::uuid) }.toBe(workspaceCreated.uuid)
        }

        test("should create a retrievable workspace") {

            val workspaceCreated = Workspace(UUID.randomUUID().toString())

            WorkspaceRepository.save(workspaceCreated)

            expect(WorkspaceRepository.findUniqueOne()).asExpect().isA<Workspace>()
                .feature { f(it::uuid) }.toBe(workspaceCreated.uuid)
        }
    }
})