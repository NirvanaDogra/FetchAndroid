package commons


import android.app.Activity
import androidx.appcompat.app.AlertDialog

class DialogUtil {
    fun showAlertDialog(
        context: Activity,
        title: String,
        message: String,
        confirmationText: String = "ok"
    ) {
        AlertDialog.Builder(context)
            .setMessage(message)
            .setTitle(title)
            .setPositiveButton(confirmationText) { _, _ ->
                context.finish()
            }
            .create()
            .show()
    }
}