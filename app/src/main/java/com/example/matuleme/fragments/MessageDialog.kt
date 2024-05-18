package com.example.matuleme.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MessageDialog(private val message: String): DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(message)
        builder.create()
    }

}