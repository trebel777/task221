package ru.netology

import Comment
import Post
import PostNotFoundException
import WallService
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun postAdd() {
        val add = WallService.add(Post(1, 5))
        assertTrue(add.id > 0)
    }

    @Test
    fun updateExisting() {
        val service = WallService
        service.add(Post(id = 1, ownerId = 2))
        service.add(Post(id= 2,ownerId = 3))
        service.add(Post(id = 3, ownerId = 22))
        val update = Post(id = 3, ownerId = 20)

        val result = service.update(update)
        assertTrue(result)
    }

    @Test
    fun updateUnExisting() {
        val service = WallService
        service.add(Post(1))
        val update = Post(78)
        val result = service.update(update)
        assertFalse(result)
    }
    @Test
    fun commentCreated() {
        val service = WallService

        service.add( Post(id = 1) )
        service.add( Post(id = 2) )
        service.add( Post(id = 3) )


        val comment: Comment = Comment(1, 15, 2)

        service.createComment(comment)

        // Assert
        assertFalse(service.comments.isEmpty())
    }

    @Test(expected = PostNotFoundException::class)
    fun createCommentException() {
        val service = WallService


        service.add( Post(id = 1) )
        service.add( Post(id = 2) )
        service.add( Post(id = 3) )

        val comment: Comment = Comment(1, 15, 12)

        service.createComment(comment)

    }

}

