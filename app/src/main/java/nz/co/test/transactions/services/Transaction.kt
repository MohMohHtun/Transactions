package nz.co.test.transactions.services

import java.io.Serializable
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.OffsetDateTime

data class Transaction(
    val id: Int,
    val transactionDate: OffsetDateTime,
    val summary: String,
    val debit: BigDecimal,
    val credit: BigDecimal
) : Serializable {
    //Extract gst from positive value (debit or credit), assuming the amount including GST
    val gst: BigDecimal
        get() = when {
            debit > BigDecimal.ZERO -> debit.multiply(BigDecimal("0.15"))
                .divide(BigDecimal("1.15"), 2, RoundingMode.HALF_UP)

            credit > BigDecimal.ZERO -> credit.multiply(BigDecimal("0.15"))
                .divide(BigDecimal("1.15"), 2, RoundingMode.HALF_UP)

            else -> BigDecimal.ZERO
        }

    //Check if the transaction is credit
    val isCredit: Boolean get() = credit > debit
}