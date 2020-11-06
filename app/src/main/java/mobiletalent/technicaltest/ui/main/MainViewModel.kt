package mobiletalent.technicaltest.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mobiletalent.technicaltest.data.Account
import mobiletalent.technicaltest.domain.AccountRepository

class MainViewModel : ViewModel() {
    private val accountRepository = AccountRepository()

    fun getAccountList(context: Context): LiveData<MutableList<Account>> {
        val mutableData = MutableLiveData<MutableList<Account>>()
        accountRepository.getAccountData(context).observeForever {
            mutableData.value = it
        }
        return mutableData
    }
}