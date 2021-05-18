package app.test.uzGardens.utils.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import java.text.SimpleDateFormat
import java.util.*

fun hideKeyboard(view: View?) {
    if (view != null) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

val getCurrentTimeStamp: String
    get() = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())


fun ViewGroup.inflate(@LayoutRes layoutId: Int): View =
    LayoutInflater.from(context).inflate(layoutId, this, false)
