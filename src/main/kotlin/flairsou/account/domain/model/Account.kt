package flairsou.account.domain.model

import java.util.*

class Account(val uuid: UUID, var name: String, var number: String?, var description: String?, var bankName: String?) {

    companion object {
        fun createNew(name: String, number: String?, description: String?, bankName: String?): Account {
            return Account(UUID.randomUUID(), name, number, description, bankName)
        }
    }
}

