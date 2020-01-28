package net.waloyodev.speedbrowser5g.browser

import net.waloyodev.speedbrowser5g.database.Bookmark

interface BookmarksView {

    fun navigateBack()

    fun handleUpdatedUrl(url: String)

    fun handleBookmarkDeleted(bookmark: Bookmark)

}
