package com.binarproject.tmdb.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

class LayoutUtils {
    companion object {

        fun showErrorDialog(context: Context, message: String, title: String) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setPositiveButton(
                "OK",
                { dialogInterface, _ -> dialogInterface.dismiss() })
            val dialog = builder.create()
            dialog.show()
        }
    }
}