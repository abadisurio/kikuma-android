package com.kikuma.kikumaapp.ui.notifications

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.kikuma.kikumaapp.R

class ConfirmationDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            AlertDialog.Builder(requireContext())
                .setMessage(getString(R.string.sign_out_confirmation))
                .setPositiveButton(getString(R.string.ok)) { _, _ -> }
                .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
                .create()

    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }
}