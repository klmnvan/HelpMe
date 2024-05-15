package com.example.matuleme.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.provider.Settings
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.example.matuleme.R
import com.example.matuleme.objects.UserStorage
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class FragmentCode: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        //----------------Добавляем код
        val imageView = ImageView(activity)
        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels
        val barcodeBitmap = BarcodeEncoder().encodeBitmap(UserStorage.profile.id, BarcodeFormat.CODE_128, width, width)
        imageView.setImageBitmap(barcodeBitmap)
        builder.setView(imageView)
        //----------------------------------
        val dialog = builder.create()
        dialog.window?.decorView?.setBackgroundResource(R.drawable.shape_white_def_10rad) // setting the background
        return dialog
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Settings.System.putInt(activity?.contentResolver, Settings.System.SCREEN_BRIGHTNESS, 0)
    }

}