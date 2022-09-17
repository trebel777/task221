import ru.netology.Attachments

data class Post(
    var id: Int = 0,
    var ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    var date: Int = 0,
    val text: String? = "text",
    val replyOwnerId: Int? = null,
    val replyPostId: Int? = null,
    val friendsOnly: Boolean = true,
    val comments: Comment? = null,
    val copyrights: Copyrights? = null,
    val likes: Likes? = null,
    val reposts: Reposts? = null,
    val canDelete: Boolean = true,
    val postType: String = "post",
    val postSource: PostSource? = null,
    val geo: Geo? = null,
    val signedId: Int? = null,
    val copyHistory: Array<Post>? = null,
    val markedAsAds: Boolean = true,
    val canEdit: Boolean = true,
    val canPin: Boolean = true,
    val isPinned: Int = 1,
    val isFavorite: Boolean = true,
    val postponedId: Int = 1,
    val views: Views? = null
)
data class Likes(
    var count: Int = 0,
    var userLikes: Boolean = true,
    var canLike: Boolean = true,
    var canPublish: Boolean = true
)
data class Reposts(
    var count: Int = 0,
    var userReposted: Boolean = true
)
data class PostSource(
    var type: String,
    var platform: String,
    var data: String?,
    var url: String
)
data class Geo(
    var type: String,
    var coordinates: String,
    var place: Place? = null
)
data class Place(
    val id: Int,
    val title: String,
    val lattitude: Int,
    val longitude: Int,
    val created: Int,
    var icon: String?,
    var checkins: Int? = null,
    var updated: Int? = null,
    val type: Int,
    val country: Int,
    val city: Int,
    val address: String? = null
)
data class Views(
    var count: Int = 0
)
data class Comment(
    val id: Int,
    val fromId: Int,
    val postId: Int,
    val date: Int = System.currentTimeMillis().toInt(),
    var text: String? = null,
    val donut: Donut? = null,
    var replyToUser: Int? = null,
    var replyToComment: Int? = null,
    var attachments: Array<Attachments>? = null,
    var parentsStack: Array<Int>? = null,
    var thread: CommentThread? = null
)
data class CommentThread(
    var count: Int? = null,
    var items: Array<Comment>? = null,
    var canPost: Boolean? = null,
    var showReplyButton: Boolean? = null,
    var groupsCanPost: Boolean? = null
)

data class Donut(
    val isDon: Boolean,
    val placeholder: String
)
class PostNotFoundException(message: String) : RuntimeException(message)

data class Copyrights(
    val id: Int = 0,
    val link: String = "",
    val name: String = "",
    val type: String = ""
)

object WallService {
    private var posts = emptyArray<Post>()
    var comments = emptyArray<Comment>()
    fun print() {
        for (post in posts) {
            print(post)
        }
    }
    fun createComment(comment: Comment) {
        for (post in posts){
            if (post.id == comment.postId) {
                comments += comment
                return
            }
        }

        throw PostNotFoundException("Пост с этим ID не найден")
    }

    fun clear() {
        posts = emptyArray()
    }

    fun add(post: Post): Post {
        val copy: Post
        if (posts.isEmpty()) {
            copy = post.copy(id = 1)
        } else {
            copy = post.copy(id = posts.last().id + 1)
        }
        posts += copy
        return copy
    }

    fun update(post: Post): Boolean {
        val id = post.id

        for( (index, currPost) in posts.withIndex() ){
            if(currPost.id == id){
                val currDate = currPost.date
                val currOwnerId = currPost.ownerId
                posts[index] = post.copy(date = currDate, ownerId = currOwnerId)

                return true
            }
        }

        return false
    }

}



fun main() {
}