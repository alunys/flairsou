package shared.infrastructure.persistence.exposed

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import shared.infrastructure.persistence.exposed.table.Account
import shared.infrastructure.persistence.exposed.table.AccountOperation
import java.io.File

object Connection{

    private val memory by lazy {
        Database.connect("jdbc:sqlite::memory:", "org.sqlite.JDBC")
    }

    private val file by lazy {
        val databasePath ="jdbc:sqlite:/data/flairsou.db"
//        val createSchema = !File(databasePath).exists()

        Database.connect(databasePath, "org.sqlite.JDBC")

//        if(createSchema){
//            SchemaUtils.create(Account, AccountOperation)
//        }
    }
}