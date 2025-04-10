package nz.co.test.transactions.repository

import nz.co.test.transactions.services.Transaction
import nz.co.test.transactions.services.TransactionsService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionRepository @Inject constructor(
    private val apiService : TransactionsService
){
    suspend fun fetchTransactions() : List<Transaction> = apiService.retrieveTransactions().toList()
}