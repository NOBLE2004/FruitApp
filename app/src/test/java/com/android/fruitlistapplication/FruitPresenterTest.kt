package com.android.fruitlistapplication



import com.android.fruitlistapplication.model.FruitItem
import com.android.fruitlistapplication.network.APIEndService
import com.android.fruitlistapplication.room.AppDataBase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit


@RunWith(JUnit4::class)
class FruitPresenterTest {


    @get:Rule
    var mockitoRule = MockitoJUnit.rule()
    @Mock
    lateinit var dataBase: AppDataBase
    @Mock
    lateinit var apiEndService: APIEndService

    @Mock
    lateinit var view: FruitsContract.View


    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testSortData() {
        val presenter = FruitsPresenter(view, dataBase, apiEndService)
        val testData = getTestData()
        val sortData = presenter.sortFruitsList(testData)
        Assert.assertEquals("aa", sortData[0].type)
    }

    @Test
    fun testSortWithSameLetterData() {
        val presenter = FruitsPresenter(view, dataBase, apiEndService)
        val testData = getTestSameLetterData()
        val sortData = presenter.sortFruitsList(testData)
        Assert.assertEquals("aa", sortData[0].type)
    }

    private fun getTestSameLetterData(): List<FruitItem> {
        val fruitsDataList = ArrayList<FruitItem>()
        fruitsDataList.add(
            FruitItem(
                10f, 20f, "aaa", 45f, 2
            )
        )
        fruitsDataList.add(
            FruitItem(
                34f, 30f, "aa", 540f, 10

            )
        )
        fruitsDataList.add(
            FruitItem(
                450f, 100f, "apple", 300f, 12

            )
        )
        fruitsDataList.add(
            FruitItem(
                200f, 1000f, "banana", 200f, 12

            )
        )
        fruitsDataList.add(
            FruitItem(
                150f, 333f, "plum", 240f, 34

            )
        )
        return fruitsDataList
    }

    private fun getTestData(): List<FruitItem> {
        val fruitsDataList = ArrayList<FruitItem>()

        fruitsDataList.add(FruitItem(
                23f, 34f, "apple", 350f, 12
            )
        )
        fruitsDataList.add(
            FruitItem(
                45f, 454f, "aaa", 232f, 3

            )
        )
        fruitsDataList.add(
            FruitItem(
                233f, 121f, "aa", 121f, 4

            )
        )
        return fruitsDataList
    }
}