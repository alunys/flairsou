package flairsou.workspace.infrastructure.filesystem.persistence.repository

import com.beust.klaxon.Klaxon
import flairsou.workspace.domain.model.Workspace
import flairsou.workspace.domain.repository.IWorkspaceRepository
import java.io.File
import java.io.FileWriter

object WorkspaceRepository : IWorkspaceRepository {

    private val workspacePath = "${System.getProperty("user.home")}${File.pathSeparator}${Workspace.workspaceName}"
    private val workspaceFilename = "workspace.json"
    private val filePath = "$workspacePath{${File.pathSeparator}$workspaceFilename"


    override fun findUniqueOne(): Workspace? {
        val file = File(filePath)

        if (!file.exists()) {
            return null
        }

        return Klaxon().parse<Workspace>(file)
    }

    override fun save(workspace: Workspace) {
        val json = Klaxon().toJsonString(workspace)

        val fileWriter = FileWriter(filePath)
        fileWriter.write(json)
        fileWriter.close()
    }
}