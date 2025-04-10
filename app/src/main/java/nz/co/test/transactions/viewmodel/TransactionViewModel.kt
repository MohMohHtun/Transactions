package nz.co.test.transactions.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nz.co.test.transactions.repository.TransactionRepository
import nz.co.test.transactions.services.Transaction
import javax.inject.Inject

class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel(){

    private val _transactions = MutableLiveData<List<Transaction>>()
    val transactions: LiveData<List<Transaction>> = _transactions

    init {
        // Fetches and sorts transactions by date when the ViewModel is created
        viewModelScope.launch {
            val sortedTransactions = repository.fetchTransactions().sortedBy { it.id }
            _transactions.postValue(sortedTransactions)
        }
    }
}