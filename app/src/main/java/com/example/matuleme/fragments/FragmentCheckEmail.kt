package com.example.matuleme.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.matuleme.R

class FragmentCheckEmail: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setView(R.layout.test_frgm)
        val dialog = builder.create()
        dialog.window?.decorView?.setBackgroundResource(R.drawable.shape_white_10rad) // setting the background
        return dialog
    }

}