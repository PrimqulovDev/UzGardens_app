package app.test.uzGardens.base

import android.graphics.Color
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import app.test.uzGardens.BuildConfig
import app.test.uzGardens.R
import app.test.uzGardens.utils.ProgressBarDialog
import app.test.uzGardens.utils.cache.AppCache
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Developed by Ilyos
 */

abstract class BaseActivity(@LayoutRes private val layoutId: Int) : AppCompatActivity() {

    @Inject
    lateinit var appCache: AppCache

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        onActivityCreated(savedInstanceState)
        progressBarDialog = ProgressBarDialog(applicationContext)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

    abstract fun onActivityCreated(savedInstanceState: Bundle?)


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        supportFragmentManager.fragments.forEach {
            it.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    fun showProgress() {
        if (progressBarDialog != null) {
            if (!progressBarDialog!!.isShowing) {
                progressBarDialog = ProgressBarDialog(this@BaseActivity)
                progressBarDialog!!.show()
            }
        } else {
            progressBarDialog = ProgressBarDialog(this@BaseActivity)
            progressBarDialog!!.show()
        }
    }


    fun message(message: String, isError: Boolean = true, duration: Int = Snackbar.LENGTH_LONG) {
        if (message.isNotEmpty()) {
            val snackbar = Snackbar.make(
                findViewById(android.R.id.content),
                "",
                duration
            )
            snackbar.setActionTextColor(Color.WHITE)
            snackbar.setText(message)
            val sbView = snackbar.view
            var color: Int = R.color.colorRed
            if (!isError) {
                color = R.color.colorGreen
            }
            sbView.setBackgroundColor(ContextCompat.getColor(this, color))
            snackbar.show()
        }
    }

    fun hideProgress() {
        if (!this.isDestroyed) {
            if (progressBarDialog != null) {
                progressBarDialog!!.dismiss()
            }
        }
    }

    var isLoading: Boolean
        get() = progressBarDialog?.isShowing ?: false
        set(value) {
            if (value) showProgress()
            else hideProgress()
        }

    private var progressBarDialog: ProgressBarDialog? = null
}