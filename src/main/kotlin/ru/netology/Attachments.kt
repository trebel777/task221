package ru.netology

abstract class Attachments(val type: String)

class PhotoAttachments(val photo: Photo) : Attachments("photo")
class VideoAttachments(val video: Video) : Attachments("video")
class AudioAttachments(val audio: Audio) : Attachments("audio")
class StickerAttachments(val sticker: Sticker) : Attachments("sticker")
class GraffitiAttachments(val graffiti: Graffiti) : Attachments("graffiti")

class Photo(val id: Int, val albumId: Int, val ownerId: Int, val userId: Int) {
    var text: String? = null
    var date: Int = 0
    var sizes: Array<PhotoSizes> = emptyArray()
    var width: Int = 0
    var height: Int = 0
}

data class PhotoSizes(val url: String, val width: Int, val height: Int, val type: String)

class Video(val id: Int, val ownerId: Int, val title: String, val description: String)

var duration: Int? = null
var image: Array<CoverImage> = emptyArray()
var firstFrame: Array<FirstFrame> = emptyArray()
var date: Int? = null
var addingDate: Int? = null
var views: Int? = null
var localViews: Int? = null
var comments: Int? = null
var player: String? = null
var platform: String? = null
var canAdd: Boolean? = null
var isPrivate: Int = 1
var accessKey: String? = null
var processing: Int = 1
var isFavorite: Boolean? = null
var canComment: Boolean? = null
var canEdit: Boolean? = null
var canLike: Boolean? = null
var canRepost: Boolean? = null
var canSubscribe: Boolean? = null
var canAddToFaves: Boolean? = null
var canAttachLink: Boolean? = null
var width: Int = 0
var height: Int = 0
var userId: Int = 0
var converting: Boolean? = null
var added: Boolean? = null
var isSubscribed: Boolean? = null
var repeat: Int = 1
var type: String? = null
var balance: Int? = null
var liveStatus: String? = null
var live: Int = 1
var upcoming: Int = 1
var spectators: Int? = null
var likes: Array<VideoLikes> = emptyArray()
var reposts: Array<VideoReposts> = emptyArray()

data class CoverImage(val height: Int, val url: String, val width: Int, val widthPadding: Int = 1)
data class FirstFrame(val height: Int, val url: String, val width: Int)
data class VideoLikes(val count: Int, val userLikes: Boolean)
data class VideoReposts(val count: Int, val wallCount: Int, val mailCount: Int, val userReposted: Int)

class Audio(val id: Int, val ownerId: Int, val artist: String, val title: String, val duration: Int) {
    var url: String? = null
    var lyricsId: Int? = null
    var albumId: Int? = null
    var genreId: Int? = null
    var date: Int? = null
    var noSearch: Int = 1
    var isHq: Int = 1
}

class Sticker(val productId: Int, val stickerId: Int) {
    private var images: Array<ImagesSticker>? = null
    private var imagesWithBackground: Array<ImagesSticker>? = null
    private var animationUrl: String? = null
    private var isAllowed: Boolean? = null
}

data class ImagesSticker(
    var url: String,
    var width: Int,
    var height: Int
)

class Graffiti(val id: Int, val ownerId: Int, val url: String) {
    private var width: Int? = null
    private var height: Int? = null
}






