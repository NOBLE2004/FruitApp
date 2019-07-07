package com.android.nasaapp

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.android.fruitlistapplication.FruitApplication
import com.android.fruitlistapplication.model.FruitItem
import com.android.fruitlistapplication.room.AppDataBase
import com.android.fruitlistapplication.room.FruitDao
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {
    private var fruitsDao: FruitDao? = null
    private lateinit var db: AppDataBase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext().applicationContext as FruitApplication
        db = Room.inMemoryDatabaseBuilder(
            context, AppDataBase::class.java
        ).build()
        fruitsDao = db.FruitDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun testDBInstance() {
        Assert.assertNotNull(db)
    }

    @Test
    @Throws(Exception::class)
    fun testDAOInstance() {
        Assert.assertNotNull(fruitsDao)
    }

    @Test
    @Throws(Exception::class)
    fun dbWriteTest() {
        val fruitsData =
            FruitItem(43f, 33f, "Test",23f,1)
            db.FruitDao().insert(fruitsData)
        val data = db.FruitDao().getAll()
        data.subscribe { it ->
            assertEquals(true, !it.isEmpty())
        }
    }

    @Test
    @Throws(Exception::class)
    fun dbFetchTest() {
        val fruitsData = FruitItem(34f, 231f, "Test",43f,2)
        db.FruitDao().insert(fruitsData)
        val data = db.FruitDao().getAll()
        data.subscribe { it ->
            val fruits = it[0]
            assertEquals("Test", fruits.type)
        }
    }
}