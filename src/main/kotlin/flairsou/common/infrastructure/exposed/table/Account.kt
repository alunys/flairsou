package flairsou.common.infrastructure.exposed.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.date

object Account: Table("account") {
    val uuid = varchar("uuid", 20).primaryKey()
    val createdAt = date("created_at")
    val updatedAt = date("updated_at")
    val name = varchar("name", 50)
    val number = varchar("number", 50)
    val description = text("description").nullable()
    val bankName = varchar("bank_name", 50).nullable()
}