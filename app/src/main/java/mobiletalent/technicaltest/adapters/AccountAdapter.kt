package mobiletalent.technicaltest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import mobiletalent.technicaltest.R
import mobiletalent.technicaltest.data.Account
import mobiletalent.technicaltest.data.AccountPayment
import mobiletalent.technicaltest.data.AccountSaving

class AccountAdapter(val context: Context): RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {

    private var accountList = mutableListOf<Account>()
    private var originalAccountList = mutableListOf<Account>()

    fun setAccountList(dataList:MutableList<Account>) {
        accountList = dataList
        originalAccountList.addAll(accountList)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false)
        return AccountViewHolder(view);
    }

    override fun getItemCount(): Int {
        return if (accountList.size > 0) {
            accountList.size
        }else {
            0
        }
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val account = accountList[position]
        holder.bindView(account)
    }

    fun filter(onlyVisible: Boolean) {
        if (onlyVisible) {
            accountList.clear()
            for (account in originalAccountList) {
                if (account.isVisible) {
                    accountList.add(account)
                }
            }
        } else {
            accountList.clear()
            accountList.addAll(originalAccountList)
        }
        notifyDataSetChanged()
    }




    inner class AccountViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindView(account: Account) {

            itemView.account_type.visibility = View.GONE
            itemView.account_name.visibility = View.GONE
            itemView.account_product_name.visibility = View.GONE
            itemView.account_product_type.visibility = View.GONE
            itemView.account_alias.visibility = View.GONE
            itemView.account_linked_account_id.visibility = View.GONE

            if (account is AccountPayment) {

                val accountName = "Account Name: "+account.accountName
                val accountIban = "Account Iban: "+account.iban

                itemView.account_type.visibility = View.VISIBLE
                itemView.account_name.visibility = View.VISIBLE

                itemView.account_type.text = accountName
                itemView.account_name.text = accountIban

            }else if (account is AccountSaving) {
                val accountProductName = "Account Product Name: "+account.productName
                val accountProductType = "Account Product Type: "+account.productType
                val accountAlias = "Account Alias: "+account.alias
                val accountLinkedAccountID = "Account Linked Account ID: "+account.linkedAccountId

                itemView.account_product_name.visibility = View.VISIBLE
                itemView.account_product_type.visibility = View.VISIBLE
                itemView.account_alias.visibility = View.VISIBLE
                itemView.account_linked_account_id.visibility = View.VISIBLE

                itemView.account_product_name.text = accountProductName
                itemView.account_product_type.text = accountProductType
                itemView.account_alias.text = accountAlias
                itemView.account_linked_account_id.text = accountLinkedAccountID

            }

            val accountBalance = "Account Balance: "+account.convertBalanceInEUR()+" "+account.accountCurrency
            val accountType = "Account Type: "+account.accountType

            itemView.account_balance.text = accountBalance
            itemView.account_type.text = accountType
        }
    }
}