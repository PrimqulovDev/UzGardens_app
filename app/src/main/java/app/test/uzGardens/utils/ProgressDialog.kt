package app.test.uzGardens.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.WindowManager
import app.test.uzGardens.R
import kotlinx.android.synthetic.main.dialog_progress.*

class ProgressBarDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_progress)
        val window = window
        window!!.setBackgroundDrawable(ColorDrawable(0))
        progressBar.spin()
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        val layoutParams = window.attributes
        layoutParams.dimAmount = 0.2f
        layoutParams.gravity = Gravity.CENTER
        window.attributes = layoutParams
//
        setCanceledOnTouchOutside(false)

        initViews()

    }

    private fun initViews() {

    }


    private fun getWindowHeight(context: Context): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay
            .getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

}