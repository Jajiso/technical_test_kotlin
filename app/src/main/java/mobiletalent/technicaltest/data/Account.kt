package mobiletalent.technicaltest.data

open class Account (
    val accountBalanceInCents: Int = 0,
    val accountCurrency: String = "",
    val accountId: String = "",
    val accountNumber: String = "",
    val accountType: String = "",
    val isVisible: Boolean = false ) {

    fun convertBalanceInEUR():Float {
        return accountBalanceInCents/100F
    }

    override fun toString(): String {
        return "Number: $accountNumber\nID: $accountId\nCurrency: $accountCurrency"
    }
}