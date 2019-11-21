package flairsou.account.domain.model

import java.util.*

class Account(val uuid: UUID, val createdAt: Date, var name: String, var number: String, var description: String?, var bankName: String?) {

    lateinit var updatedAt: Date
    var operations = arrayListOf<AccountOperation>()

    companion object {
        fun createNew(name: String, number: String, description: String?, bankName: String?): Account {
            return Account(UUID.randomUUID(), Date(), name, number, description, bankName)
        }
    }
}