package mobiletalent.technicaltest.data

import mobiletalent.technicaltest.data.Account

data class AccountPayment(
    val accountName: String = "",
    val iban: String = ""
) : Account() {
    override fun toString(): String {
        return "Number: $accountNumber\nID: $accountId\nCurrency: $accountCurrency"
    }
}