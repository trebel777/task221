package ru.netology

import Post
import WallService
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class WallServiceTest {
    val post = Post(0, 0, 0, 0, "text", true, "post", true, true, true)
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun postAdd() {
        val add = WallService.add(Post(1, 2, 3, 4, "text", false, "post", true, true, true))
        assertTrue(add.id > 0)
    }

    @Test
    fun updateExisting() {
        val service = WallService
        service.add(Post(1, 2, 3, 4, "text", false, "post", true, true, true, 44))
        val update = Post(1, 4, 7, 46, "notText", true, "video", false, false, false, 78)

        val result = service.update(update)
        assertTrue(result)
    }
    @Test
    fun updateUnExisting(){
        val service = WallService
        service.add(Post(3,2,3,4,"text",false,"post", false, false, false,2))
        val update = Post(78,1,1,1, "notText", true, "post", true, true,true)
        val result = service.update(update)
        assertFalse(result)
    }
}

