package flairsou.workspace.infrastructure.filesystem.persistence.repository

import com.beust.klaxon.Klaxon
import flairsou.workspace.domain.model.Workspace
import flairsou.workspace.domain.repository.IWorkspaceRepository
import java.io.File
import java.io.FileWriter

class WorkspaceRepository : IWorkspaceRepository {

    private var workspace: Workspace? = null

    private fun retrieveWorkspaceDirectoryPath(): String =
        "${System.getProperty("user.home")}${File.separator}.${Workspace.workspaceName}"

    companion object {
        private const val workspaceFilename = "workspace.json"
    }

    override fun findUniqueOne(): Workspace? {
        if(null === this.workspace) {
            val file = File(this.retrieveWorkspaceDirectoryPath(), workspaceFilename)

            if (!file.exists()) {
                return null
            }

            this.workspace = Klaxon().parse<Workspace>(file)
        }

        return this.workspace
    }

    override fun save(workspace: Workspace) {

        val workspaceDirectory = File(retrieveWorkspaceDirectoryPath())
        workspaceDirectory.mkdirs()

        val json = Klaxon().toJsonString(workspace)

        val fileWriter = FileWriter(File(workspaceDirectory, workspaceFilename))
        fileWriter.write(json)
        fileWriter.close()
    }
}