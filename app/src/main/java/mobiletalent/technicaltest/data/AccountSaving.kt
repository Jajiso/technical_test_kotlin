package mobiletalent.technicaltest.data

import mobiletalent.technicaltest.data.Account

data class AccountSaving(
    val alias: String = "",
    val linkedAccountId: String = "",
    val productName: String = "",
    val productType: String = "",
    val savingsTargetReached: String = "",
    val targetAmountInCents: String = ""
) : Account() {
    override fun toString(): String {
        return "Number: $accountNumber\nID: $accountId\nCurrency: $accountCurrency"
    }
}