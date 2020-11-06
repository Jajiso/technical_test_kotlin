package mobiletalent.technicaltest.domain

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import mobiletalent.technicaltest.data.Account
import mobiletalent.technicaltest.data.AccountPayment
import mobiletalent.technicaltest.data.AccountSaving
import mobiletalent.technicaltest.utilities.JsonHelper
import org.json.JSONArray
import org.json.JSONObject

class AccountRepository {

    fun getAccountData(context: Context): LiveData<MutableList<Account>> {
        val data = MutableLiveData<MutableList<Account>>()
        val originalJson: String = JsonHelper.getJsonFromAssets(context)!!
        val jsonAccountArray: JSONArray = JsonHelper.getJsonArrayFromJson(originalJson, "accounts")
        val jsonObjectList: ArrayList<JSONObject> = JsonHelper.getListOfJsonObjectsFromJSONArray(jsonAccountArray)
        val accountMutableList = mutableListOf<Account>()
        for (obj in jsonObjectList){
            if (obj.get("accountType").toString().toLowerCase() == "payment") {
                val accountPayment: AccountPayment = Gson().fromJson(obj.toString(), AccountPayment::class.java)
                accountMutableList.add(accountPayment)
            } else if (obj.get("accountType").toString().toLowerCase() == "saving") {
                val accountSaving: AccountSaving = Gson().fromJson(obj.toString(), AccountSaving::class.java)
                accountMutableList.add(accountSaving)
            }
        }
        data.value = accountMutableList
        return data
    }

    fun getAccountsFromAssets(context: Context): ArrayList<Account> {
        val data = ArrayList<Account>()
        val originalJson: String = JsonHelper.getJsonFromAssets(context)!!
        val jsonAccountArray: JSONArray = JsonHelper.getJsonArrayFromJson(originalJson, "accounts")
        val jsonObjectList: ArrayList<JSONObject> = JsonHelper.getListOfJsonObjectsFromJSONArray(jsonAccountArray)
        for (obj in jsonObjectList){
            if (obj.get("accountType").toString().toLowerCase() == "payment") {
                val accountPayment: AccountPayment = Gson().fromJson(obj.toString(), AccountPayment::class.java)
                data.add(accountPayment)
            } else if (obj.get("accountType").toString().toLowerCase() == "saving") {
                val accountSaving: AccountSaving = Gson().fromJson(obj.toString(), AccountSaving::class.java)
                data.add(accountSaving)
            }
        }
        return data
    }
}