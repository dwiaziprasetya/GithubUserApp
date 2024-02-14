package com

import android.os.Build
import androidx.lifecycle.Observer
import com.example.githubuserapp.data.remote.response.ItemsItem
import com.example.githubuserapp.data.remote.retrofit.ApiService
import com.example.githubuserapp.ui.search.SearchViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
class SearchViewModelTest {
    private lateinit var viewModel: SearchViewModel
    private lateinit var apiService: ApiService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
        viewModel = SearchViewModel()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Test 1`(){
        runBlocking {
            val mockResponse = MockResponse()
                .setResponseCode(200)
                .setBody("""
                {
                    "total_count": 1,
                    "incomplete_results": false,
                    "items": [
                        {
                            "login": "dwiaziprasetya",
                            "id": 140523680,
                            "node_id": "U_kgDOCGA4oA",
                            "avatar_url": "https://avatars.githubusercontent.com/u/140523680?v=4",
                            "gravatar_id": "",
                            "url": "https://api.github.com/users/dwiaziprasetya",
                            "html_url": "https://github.com/dwiaziprasetya",
                            "followers_url": "https://api.github.com/users/dwiaziprasetya/followers",
                            "following_url": "https://api.github.com/users/dwiaziprasetya/following{/other_user}",
                            "gists_url": "https://api.github.com/users/dwiaziprasetya/gists{/gist_id}",
                            "starred_url": "https://api.github.com/users/dwiaziprasetya/starred{/owner}{/repo}",
                            "subscriptions_url": "https://api.github.com/users/dwiaziprasetya/subscriptions",
                            "organizations_url": "https://api.github.com/users/dwiaziprasetya/orgs",
                            "repos_url": "https://api.github.com/users/dwiaziprasetya/repos",
                            "events_url": "https://api.github.com/users/dwiaziprasetya/events{/privacy}",
                            "received_events_url": "https://api.github.com/users/dwiaziprasetya/received_events",
                            "type": "User",
                            "site_admin": false,
                            "score": 1.0
                        }
                    ]
                }
            """.trimIndent())

            val expected = mockWebServer.enqueue(mockResponse)


            val result = viewModel.setDataPerson("dwiaziprasetya")

            delay(1000)

            Assert.assertEquals(expected, result)
        }
    }

    @Test
    fun `Test 2`(){
        runBlocking {
            val mockResponse = MockResponse()
                .setResponseCode(404)

            mockWebServer.enqueue(mockResponse)

            val observer = Observer<List<ItemsItem>> {}
            viewModel.dataPerson.observeForever(observer)

            viewModel.setDataPerson("dwiaziprasetya")

            delay(1000)

            assert(viewModel.dataPerson.value == null)

            viewModel.dataPerson.removeObserver(observer)
        }
    }
}