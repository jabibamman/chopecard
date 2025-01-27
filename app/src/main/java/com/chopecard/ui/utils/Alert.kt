package com.chopecard.ui.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog

fun showAlert(message: String, context: Context) {
    AlertDialog.Builder(context).apply {
        setMessage(message)
        setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
    }.show()
}

fun showConfirmationAlert(message: String, context: Context, ignoredOnPositiveClick: () -> Unit) {
    AlertDialog.Builder(context).apply {
        setMessage(message)
        setPositiveButton("Yes") { dialog, _ ->
            ignoredOnPositiveClick()
            dialog.dismiss()
        }
        setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
    }.show()
}
