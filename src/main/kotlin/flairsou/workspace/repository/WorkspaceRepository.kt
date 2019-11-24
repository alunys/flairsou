package flairsou.workspace.repository

import com.beust.klaxon.Klaxon
import flairsou.workspace.model.Workspace
import java.io.File
import java.io.FileWriter

object WorkspaceRepository {

    private val workspacePath = "${System.getProperty("user.home")}${File.pathSeparator}${Workspace.workspaceName}"
    private val workspaceFilename = "workspace.json"
    private val filePath = "$workspacePath{${File.pathSeparator}$workspaceFilename"


    fun findOneByUuid(uuid: String): Workspace? {
        val file = File(filePath)

        if (!file.exists()) {
            return null
        }

        return Klaxon().parse<Workspace>(file)
    }

    fun save(workspace: Workspace) {
        val json = Klaxon().toJsonString(workspace)

        val fileWriter = FileWriter(filePath)
        fileWriter.write(json)
        fileWriter.close()
    }
}