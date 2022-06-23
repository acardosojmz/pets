package edu.itvo.pets.presentation.view

import android.app.Activity
import android.app.Dialog
import android.view.Window

import edu.itvo.pets.databinding.DialogLayoutBinding

class CustomAlert {
    lateinit var binding: DialogLayoutBinding
    fun showDialog(activity: Activity, msg: String?) {
        binding =  DialogLayoutBinding.inflate(activity.layoutInflater)
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(binding.root)
        binding.message.text= msg
        binding.btnOK.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}