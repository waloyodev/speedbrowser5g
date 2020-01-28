package net.waloyodev.speedbrowser5g.database.feeds

import net.waloyodev.speedbrowser5g.database.FeedsModel

interface FeedsRepository {

    fun clearFeeds()
    fun feedEntry(item: FeedsModel)
    fun allEntries(): List<FeedsModel>

}