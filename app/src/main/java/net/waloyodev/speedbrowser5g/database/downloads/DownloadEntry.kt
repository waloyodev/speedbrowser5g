package net.waloyodev.speedbrowser5g.database.downloads

/**
 * An entry in the downloads database.
 */
data class DownloadEntry(
    val url: String,
    val title: String,
    val contentSize: String
)
