package com.example.githubuserapp.ui.search

import android.os.Build
import androidx.lifecycle.Observer
import androidx.test.platform.app.InstrumentationRegistry
import com.example.githubuserapp.data.remote.response.ItemsItem
import com.example.githubuserapp.data.remote.retrofit.ApiService
import com.example.githubuserapp.helper.JsonConverter
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
    fun `Test setData success`(){
        runBlocking {
            val mockResponse = MockResponse()
                .setResponseCode(200)
                .setBody(JsonConverter.readJSONFromAssets(
                    InstrumentationRegistry
                        .getInstrumentation()
                        .context,
                     "data.json"))

            val expected = mockWebServer.enqueue(mockResponse)
            val result = viewModel.setDataPerson("dwiaziprasetya")

            delay(1000)

            Assert.assertEquals(expected, result)
        }
    }

    @Test
    fun `Test setData failrue`(){
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