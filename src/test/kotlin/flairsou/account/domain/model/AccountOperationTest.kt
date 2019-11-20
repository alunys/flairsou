package flairsou.account.domain.model

import ch.tutteli.atrium.api.fluent.en_GB.isA
import ch.tutteli.atrium.api.fluent.en_GB.isSameAs
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.verbs.expect
import org.spekframework.spek2.Spek
import java.util.*

object AccountOperationTest : Spek({

    group("creating a new account") {
        test("should return an instance of account") {
            val account = Account.createNew(
                name = "nom",
                bankName = "nom de la banque",
                description = "la description",
                number = "123456789"
            );

            expect(account).asExpect().isA<Account>()
        }
    }
    group("manipulations of operations") {

        val accountOperations by memoized {
            val account = Account.createNew(
                name = "nom",
                bankName = "nom de la banque",
                description = "la description",
                number = "123456789"
            )

            account.operations.add(
                AccountOperation.createNew(
                    amount = 0.00,
                    comment = "commentaire 0",
                    date = Date(),
                    externalActor = "la banque 0"
                )
            )

            account.operations
        }

        group("adding a new operation to an account") {

            val operationAdded = AccountOperation.createNew(
                amount = 1.11,
                comment = "commentaire 1",
                date = Date(),
                externalActor = "la banque 1"
            )

            beforeEachTest {
                accountOperations.add(operationAdded)
            }

            test("number of operations of the account should be increased") {
                expect(accountOperations.size).asExpect().isSameAs(2)
            }

            test("operation should be retrieved in the account") {
                expect(accountOperations.get(1)).asExpect().isSameAs(operationAdded)
            }
        }

        group("removing a new operation to an account") {

            beforeEachTest {
                accountOperations.remove(accountOperations.last())
            }

            test("number of operations of the account should be decreased") {
                expect(accountOperations.size).asExpect().isSameAs(0)
            }

            test("operation should removed should be retrievable") {
                expect(accountOperations.find { it.comment === "commentaire 0" }).asExpect().isSameAs(null)
            }
        }
    }

})