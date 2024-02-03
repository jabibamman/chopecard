package com.chopecard.ui.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog

fun showAlert(message: String, context: Context) {
    AlertDialog.Builder(context).apply {
        setMessage(message)
        setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
    }.show()
}
