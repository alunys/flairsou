package flairsou.common.infrastructure.exposed.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.date

object AccountOperation: Table("account_operation") {
    val uuid = varchar("uuid", 20).primaryKey()
    val accountUuid = (varchar("city_uuid", 20) references Account.uuid)
    val createdAt = date("created_at")
    val updatedAt = date("updated_at")
    val amount = float("amount")
    val comment = Account.text("comment").nullable()
    val externalActor = Account.varchar("externalActor", 50)
}