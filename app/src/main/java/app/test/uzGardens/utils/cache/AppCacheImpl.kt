package app.test.uzGardens.utils.cache


import android.content.SharedPreferences
import app.test.uzGardens.di.modules.AppCacheQualifier
import app.test.uzGardens.utils.ktx.stringNullable
import javax.inject.Inject


class AppCacheImpl @Inject constructor(
    @AppCacheQualifier private val prefs: SharedPreferences
) : AppCache {

    override var token: String? by prefs.stringNullable()
    override var tokenType: String? by prefs.stringNullable()


}