package flairsou.workspace.domain.model

import java.io.File

data class Workspace(val uuid: String, val databasePath: String = "${System.getProperty("user.home")}${File.separator}.$workspaceName") {


    companion object{
        val workspaceName="flairsou"
        val databaseFileName="accounts.db"
    }

}