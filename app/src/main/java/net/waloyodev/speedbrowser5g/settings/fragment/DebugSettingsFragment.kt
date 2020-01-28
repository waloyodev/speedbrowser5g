package net.waloyodev.speedbrowser5g.settings.fragment

import net.waloyodev.speedbrowser5g.R
import net.waloyodev.speedbrowser5g.di.injector
import net.waloyodev.speedbrowser5g.extensions.snackbar
import net.waloyodev.speedbrowser5g.preference.DeveloperPreferences
import android.os.Bundle
import javax.inject.Inject

class DebugSettingsFragment : AbstractSettingsFragment() {

    @Inject internal lateinit var developerPreferences: DeveloperPreferences

    override fun providePreferencesXmlResource() = R.xml.preference_debug

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)

        togglePreference(
            preference = LEAK_CANARY,
            isChecked = developerPreferences.useLeakCanary,
            onCheckChange = { change ->
                activity?.apply { snackbar(R.string.app_restart) }
                developerPreferences.useLeakCanary = change
            }
        )
    }

    companion object {
        private const val LEAK_CANARY = "leak_canary_enabled"
    }
}
