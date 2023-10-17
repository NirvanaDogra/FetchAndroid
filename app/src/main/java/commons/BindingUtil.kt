package commons

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:setNonStringText")
fun setNonStringText(view: TextView, value: Int) {
    view.text = value.toString()
    view.contentDescription = value.toString()
}