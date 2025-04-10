package nz.co.test.transactions.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import nz.co.test.transactions.R

class TransactionDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_detailactivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Transaction Detail"

    }
}