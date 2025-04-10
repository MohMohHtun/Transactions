package nz.co.test.transactions.activities

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import nz.co.test.transactions.R
import nz.co.test.transactions.TransactionDateUtils
import nz.co.test.transactions.services.Transaction
import java.math.RoundingMode

class TransactionDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_detailactivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Transaction Detail"
        val transaction = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("transaction", Transaction::class.java)
        } else {
            intent.getSerializableExtra("transaction") as Transaction
        }
        if(transaction != null){
            findViewById<TextView>(R.id.summaryTextView).text = transaction.summary
            findViewById<TextView>(R.id.transactionDateTextView).text =
                TransactionDateUtils.formatDate(transaction.transactionDate)

            findViewById<TextView>(R.id.creditTextView).text = buildString {
                append("$")
                append(transaction.credit)
            }
            findViewById<TextView>(R.id.debitTextView).text = buildString {
                append("$")
                append(transaction.debit)
            }
            findViewById<TextView>(R.id.gstTextView).text =
                buildString {
                    append("$")
                    append(transaction.gst.setScale(2, RoundingMode.HALF_UP))
                }

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}