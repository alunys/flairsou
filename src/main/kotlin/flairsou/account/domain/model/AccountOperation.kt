package flairsou.account.domain.model

import java.util.*

class AccountOperation(var date: Date, var amount: Double, var comment: String?, var externalActor: String?, val createdAt: Date) {

    lateinit var updatedAt: Date

    companion object {
        fun createNew(date: Date, amount: Double, comment: String?, externalActor: String?): AccountOperation {
            return AccountOperation(date, amount, comment, externalActor, Date())
        }
    }
}