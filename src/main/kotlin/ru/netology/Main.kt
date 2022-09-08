data class Post(
    var id: Int,
    var ownerId: Int,
    val fromId: Int,
    var date: Int,
    val text: String,
    val canDelete: Boolean,
    val postType: String,
    val markedAsAds: Boolean,
    val canEdit: Boolean,
    val canPin: Boolean,
    val views: Int = 0
)

object WallService {
    private var posts = emptyArray<Post>()
    fun print () {
        for (post in posts) {
            print(post)
        }
    }
    fun clear() {
        posts = emptyArray()
    }

    fun add(post: Post): Post {
        val copy: Post
        if (posts.isEmpty()){
            copy = post.copy(id = 1)
        } else{
            copy = post.copy(id = posts.last().id + 1)
        }
        posts += copy
        return copy
    }

    fun update(updatePost: Post): Boolean {
        posts.forEachIndexed { index, onePost ->
            if (posts[index].id == updatePost.id) {
                val copy = Post(
                    id = updatePost.id,
                    ownerId = updatePost.ownerId,
                    fromId = updatePost.ownerId,
                    date = updatePost.date,
                    text = updatePost.text,
                    canDelete = updatePost.canDelete,
                    postType = updatePost.postType,
                    markedAsAds = updatePost.markedAsAds,
                    canEdit = updatePost.canEdit,
                    canPin = updatePost.canPin,
                    views = updatePost.views
                )
                posts[index] = copy
                return true
            }
        }
        return false
    }

}


fun main() {
    WallService.add(Post(1, 2, 3, 4, "text", true, "post", true, true, false))
    WallService.update(updatePost = Post(1,3, 4, 5, "text", true, "post", true, true, false))
    WallService.print()
}