package net.waloyodev.speedbrowser5g.search.engine

import net.waloyodev.speedbrowser5g.R
import android.app.Application

/**
 * The Google search engine.
 *
 * See https://www.google.com/images/srpr/logo11w.png for the icon.
 */
class GoogleSearch(application: Application?) : BaseSearchEngine(
    "file:///android_asset/google.png",
    "https://www.google.com/search?client=a5gfastbrowser&ie=UTF-8&oe=UTF-8&q=",
    R.string.search_engine_google, application
)
