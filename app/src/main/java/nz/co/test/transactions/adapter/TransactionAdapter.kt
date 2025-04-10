package nz.co.test.transactions.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nz.co.test.transactions.R
import nz.co.test.transactions.TransactionDateUtils
import nz.co.test.transactions.services.Transaction

class TransactionAdapter(private val onClick: (Transaction) -> Unit) :
    ListAdapter<Transaction, TransactionAdapter.TransactionViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    // ViewHolder binds transaction item data to views
    inner class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(transaction: Transaction) {
            val summaryTextView = itemView.findViewById<TextView>(R.id.transactionSummaryText)
            val transactionTextView = itemView.findViewById<TextView>(R.id.transactionText)
            val dateTextView = itemView.findViewById<TextView>(R.id.transactionDateText)
            summaryTextView.text = transaction.summary
            transactionTextView.text = if (transaction.isCredit) buildString {
                append("$")
                append(transaction.credit.toString())
            } else buildString {
                append("$")
                append(transaction.debit.toString())
            }
            transactionTextView.setTextColor(if (transaction.isCredit) Color.GREEN else Color.RED)
            dateTextView.text = TransactionDateUtils.formatDate(transaction.transactionDate.toString())
            itemView.setOnClickListener { onClick(transaction) }
        }
    }

    // Handles item diffing for RecyclerView updates
    class DiffCallback : DiffUtil.ItemCallback<Transaction>() {
        override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction) =
            oldItem == newItem
    }
}