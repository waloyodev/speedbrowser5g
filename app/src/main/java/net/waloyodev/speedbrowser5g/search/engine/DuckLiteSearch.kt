package net.waloyodev.speedbrowser5g.search.engine

import net.waloyodev.speedbrowser5g.R
import android.app.Application

/**
 * The DuckDuckGo Lite search engine.
 *
 * See https://duckduckgo.com/assets/logo_homepage.normal.v101.png for the icon.
 */
class DuckLiteSearch(application: Application?) : BaseSearchEngine(
    "file:///android_asset/duckduckgo.png",
    "https://duckduckgo.com/lite/?t=a5gfastbrowser&q=",
    R.string.search_engine_duckduckgo_lite, application
)
