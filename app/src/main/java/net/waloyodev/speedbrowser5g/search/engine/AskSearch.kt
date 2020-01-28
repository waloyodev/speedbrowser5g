package net.waloyodev.speedbrowser5g.search.engine

import net.waloyodev.speedbrowser5g.R
import android.app.Application

/**
 * The Ask search engine.
 */
class AskSearch(application: Application?) : BaseSearchEngine(
    "file:///android_asset/ask.png",
    "http://www.ask.com/web?qsrc=0&o=0&l=dir&qo=LightningBrowser&q=",
    R.string.search_engine_ask, application
)
