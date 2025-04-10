package nz.co.test.transactions.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import nz.co.test.transactions.R
import nz.co.test.transactions.TransactionApplication
import nz.co.test.transactions.adapter.TransactionAdapter
import nz.co.test.transactions.viewmodel.TransactionViewModel
import nz.co.test.transactions.viewmodel.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: TransactionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TransactionApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this,viewModelFactory)[TransactionViewModel::class.java]

        val recyclerView = findViewById<RecyclerView>(R.id.transaction_rv)
        val adapter = TransactionAdapter { transaction ->
            val intent = Intent(this, TransactionDetailActivity::class.java).apply {
                putExtra("transaction", transaction)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val divider = MaterialDividerItemDecoration(this, RecyclerView.VERTICAL)
        divider.isLastItemDecorated = false
        recyclerView.addItemDecoration(divider)
        divider.dividerColor = getColor(R.color.grey)

        viewModel.transactions.observe(this){
            val progressBar = findViewById<ProgressBar>(R.id.progressBar)
            progressBar.visibility = View.GONE
            adapter.submitList(it)
        }
    }
}