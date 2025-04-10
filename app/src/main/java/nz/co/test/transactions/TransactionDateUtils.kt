package nz.co.test.transactions

import java.text.SimpleDateFormat
import java.util.Locale

object TransactionDateUtils {

    fun formatDate(
        input: String,
        inputFormat: String = "yyyy-MM-dd'T'HH:mm:ss",
        outputFormat: String = "dd MMM yyyy hh:mm a"
    ): String {
        return try {
            val parser = SimpleDateFormat(inputFormat, Locale.getDefault())
            val formatter = SimpleDateFormat(outputFormat, Locale.getDefault())
            val date = parser.parse(input)
            formatter.format(date!!)
        } catch (e: Exception) {
            ""
        }
    }
}