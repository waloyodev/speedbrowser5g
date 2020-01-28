package net.waloyodev.speedbrowser5g.search

import net.waloyodev.speedbrowser5g.log.Logger
import net.waloyodev.speedbrowser5g.preference.UserPreferences
import net.waloyodev.speedbrowser5g.search.engine.*
import net.waloyodev.speedbrowser5g.search.suggestions.*
import android.app.Application
import okhttp3.OkHttpClient
import javax.inject.Inject

/**
 * The model that provides the search engine based
 * on the user's preference.
 */
class SearchEngineProvider @Inject constructor(
    private val userPreferences: UserPreferences,
    private val httpClient: OkHttpClient,
    private val requestFactory: RequestFactory,
    private val application: Application,
    private val logger: Logger
) {

    /**
     * Provide the [SuggestionsRepository] that maps to the user's current preference.
     */
    fun provideSearchSuggestions(): SuggestionsRepository =
        when (userPreferences.searchSuggestionChoice) {
            0 -> NoOpSuggestionsRepository()
            1 -> GoogleSuggestionsModel(httpClient, requestFactory, application, logger)
            2 -> DuckSuggestionsModel(httpClient, requestFactory, application, logger)
            3 -> BaiduSuggestionsModel(httpClient, requestFactory, application, logger)
            4 -> NaverSuggestionsModel(httpClient, requestFactory, application, logger)
            else -> GoogleSuggestionsModel(httpClient, requestFactory, application, logger)
        }

    /**
     * Provide the [BaseSearchEngine] that maps to the user's current preference.
     */
    fun provideSearchEngine(): BaseSearchEngine =
        when (userPreferences.searchChoice) {
            0 -> CustomSearch(userPreferences.searchUrl, application)
            1 -> GoogleSearch(application)
            2 -> AskSearch(application)
            3 -> BingSearch(application)
            4 -> YahooSearch(application)
            5 -> StartPageSearch(application)
            6 -> StartPageMobileSearch(application)
            7 -> DuckSearch(application)
            8 -> DuckLiteSearch(application)
            9 -> BaiduSearch(application)
            10 -> YandexSearch(application)
            11 -> NaverSearch(application)
            else -> GoogleSearch(application)
        }

    /**
     * Return the serializable index of of the provided [BaseSearchEngine].
     */
    fun mapSearchEngineToPreferenceIndex(searchEngine: BaseSearchEngine): Int =
        when (searchEngine) {
            is CustomSearch -> 0
            is GoogleSearch -> 1
            is AskSearch -> 2
            is BingSearch -> 3
            is YahooSearch -> 4
            is StartPageSearch -> 5
            is StartPageMobileSearch -> 6
            is DuckSearch -> 7
            is DuckLiteSearch -> 8
            is BaiduSearch -> 9
            is YandexSearch -> 10
            is NaverSearch -> 11
            else -> throw UnsupportedOperationException("Unknown search engine provided: " + searchEngine.javaClass)
        }

    /**
     * Provide a list of all supported search engines.
     */
    fun provideAllSearchEngines(): List<BaseSearchEngine> = listOf(
        CustomSearch(userPreferences.searchUrl, application),
        GoogleSearch(application),
        AskSearch(application),
        BingSearch(application),
        YahooSearch(application),
        StartPageSearch(application),
        StartPageMobileSearch(application),
        DuckSearch(application),
        DuckLiteSearch(application),
        BaiduSearch(application),
        YandexSearch(application),
        NaverSearch(application)
    )

}
