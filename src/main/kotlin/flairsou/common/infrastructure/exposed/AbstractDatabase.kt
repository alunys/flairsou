package flairsou.common.infrastructure.exposed

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import flairsou.common.infrastructure.exposed.table.Account
import flairsou.common.infrastructure.exposed.table.AccountOperation
import java.sql.Connection
import java.sql.ResultSet

abstract class AbstractDatabase(open val path: String) {

    lateinit var connection: Database

    protected val driver = "org.sqlite.JDBC"

    fun connect() {
        this.connection = Database.connect("jdbc:sqlite:${this.path}", this.driver)
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE // Or Connection.TRANSACTION_READ_UNCOMMITTED
    }

    fun createSchema(): AbstractDatabase {
        transaction(this.connection) {
            SchemaUtils.create(Account, AccountOperation)
        }

        return this
    }

    fun disconnect() {

    }

    fun <T : Any> execAndMap(sql: String, transform: (ResultSet) -> T): List<T> {
        val result = arrayListOf<T>()
        transaction(this.connection) {
            TransactionManager.current().exec(sql) { rs ->
                while (rs.next()) {
                    result += transform(rs)
                }
            }
        }
        return result
    }
}