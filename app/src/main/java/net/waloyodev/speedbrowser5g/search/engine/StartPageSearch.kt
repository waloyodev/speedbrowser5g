package net.waloyodev.speedbrowser5g.search.engine

import net.waloyodev.speedbrowser5g.R
import android.app.Application

/**
 * The StartPage search engine.
 */
class StartPageSearch(application: Application?) : BaseSearchEngine(
    "file:///android_asset/startpage.png",
    "https://startpage.com/do/search?language=english&query=",
    R.string.search_engine_startpage, application
)
