package flairsou.workspace.infrastructure.filesystem.persistence.repository

import com.beust.klaxon.Klaxon
import flairsou.workspace.domain.model.Workspace
import flairsou.workspace.domain.repository.IWorkspaceRepository
import java.io.File
import java.io.FileWriter

object WorkspaceRepository : IWorkspaceRepository {

    private const val workspaceFilename = "workspace.json"
    private fun retrieveWorkspaceDirectoryPath():String = "${System.getProperty("user.home")}${File.separator}.${Workspace.workspaceName}"

    override fun findUniqueOne(): Workspace? {
        val file = File(this.retrieveWorkspaceDirectoryPath(), this.workspaceFilename)

        if (!file.exists()) {
            return null
        }

        return Klaxon().parse<Workspace>(file)
    }

    override fun save(workspace: Workspace) {

        val workspaceDirectory = File(retrieveWorkspaceDirectoryPath())
        workspaceDirectory.mkdirs()

        val json = Klaxon().toJsonString(workspace)

        val fileWriter = FileWriter(File(workspaceDirectory, this.workspaceFilename))
        fileWriter.write(json)
        fileWriter.close()
    }
}