package net.waloyodev.speedbrowser5g.di

import net.waloyodev.speedbrowser5g.adblock.allowlist.AllowListModel
import net.waloyodev.speedbrowser5g.adblock.allowlist.SessionAllowListModel
import net.waloyodev.speedbrowser5g.database.allowlist.AdBlockAllowListDatabase
import net.waloyodev.speedbrowser5g.database.allowlist.AdBlockAllowListRepository
import net.waloyodev.speedbrowser5g.database.bookmark.BookmarkDatabase
import net.waloyodev.speedbrowser5g.database.bookmark.BookmarkRepository
import net.waloyodev.speedbrowser5g.database.downloads.DownloadsDatabase
import net.waloyodev.speedbrowser5g.database.downloads.DownloadsRepository
import net.waloyodev.speedbrowser5g.database.feeds.FeedsDatabase
import net.waloyodev.speedbrowser5g.database.feeds.FeedsRepository
import net.waloyodev.speedbrowser5g.database.history.HistoryDatabase
import net.waloyodev.speedbrowser5g.database.history.HistoryRepository
import net.waloyodev.speedbrowser5g.ssl.SessionSslWarningPreferences
import net.waloyodev.speedbrowser5g.ssl.SslWarningPreferences
import dagger.Binds
import dagger.Module

/**
 * Dependency injection module used to bind implementations to interfaces.
 */
@Module
abstract class AppBindsModule {

    @Binds
    abstract fun provideBookmarkModel(bookmarkDatabase: BookmarkDatabase): BookmarkRepository

    @Binds
    abstract fun provideDownloadsModel(downloadsDatabase: DownloadsDatabase): DownloadsRepository

    @Binds
    abstract fun providesHistoryModel(historyDatabase: HistoryDatabase): HistoryRepository

    @Binds
    abstract fun providesAdBlockAllowListModel(adBlockAllowListDatabase: AdBlockAllowListDatabase): AdBlockAllowListRepository

    @Binds
    abstract fun providesAllowListModel(sessionAllowListModel: SessionAllowListModel): AllowListModel

    @Binds
    abstract fun providesFeedsModel(feedsDatabase: FeedsDatabase): FeedsRepository

    @Binds
    abstract fun providesSslWarningPreferences(sessionSslWarningPreferences: SessionSslWarningPreferences): SslWarningPreferences

}
