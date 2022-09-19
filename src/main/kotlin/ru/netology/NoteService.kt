package ru.netology

data class Note(
    val id: Int,
    var title: String? = null,
    var text: String? = null,
    val date: Int = System.currentTimeMillis().toInt(),
    var comments: Int = 0,
    var readComments: Int? = null,
    var viewUrl: String? = null,
    var privacyView: String? = null,
    var canComment: Int = 1,
    var textViki: String? = null,
)

data class CommentNote(
    val cid: Int,
    val noteId: UInt,
    val replyTo: UInt,
    var message: String,
    val guid: String,
)

class Notes {
    private var notesIdCounter = 1000
    private var commentsIdCounter = 1000

    private var notesList: ArrayList<Note> = ArrayList()
    private var notesDeleted: ArrayList<Note> = ArrayList()

    private var commentsList: ArrayList<CommentNote> = ArrayList()
    private var commentsDeleted: ArrayList<CommentNote> = ArrayList()

    fun getNumberOfNotes(): Int = notesList.size
    fun getNumberOfComments(): Int = commentsList.size

    fun clearData() {
        notesList = arrayListOf()
        notesDeleted = arrayListOf()
        commentsList = arrayListOf()
        commentsDeleted = arrayListOf()

        notesIdCounter = 1000
        commentsIdCounter = 1000
    }

    fun add(
        title: String,
        text: String,
        privacy: Int = 0,
        commentPrivacy: Int = 0,
        privView: String = "all",
        privComment: String = "all",
    ): Int {
        if (privacy !in 0..3 || commentPrivacy !in 0..3) {
            return -1
        }

        val note = Note(
            id = getNextNoteId(), canComment = commentPrivacy,
            title = title, text = text,
            privacyView = privComment
        )

        if (notesList.add(note))
            return note.id
        else
            return -1
    }

    fun createComment(noteId: UInt, replyTo: UInt = 0U, message: String, guid: String): Int {
        val comment =
            CommentNote(cid = getNextCommentId(), noteId = noteId, replyTo = replyTo, message = message, guid = guid)

        if (commentsList.add(comment)) {
            val note = notesList.find { it.id == noteId.toInt() }

            if (note != null) {
                note.comments++
            }

            return comment.cid
        } else
            return -1
    }


    fun delete(noteId: UInt): Int {
        val nid: Int = noteId.toInt()

        val note = notesList.find { it.id == nid }
        if (note == null) return -1

        val commentsOfTheNote = commentsList.filter { it.noteId == noteId }
        commentsOfTheNote.forEach { deleteComment(it.cid.toUInt()) }

        val copyNote = note.copy()
        notesDeleted.add(copyNote)
        notesList.remove(note)

        return 1
    }


    fun edit(
        noteId: UInt,
        title: String,
        text: String,
        privacy: Int = 0,
        commentPrivacy: Int = 0,
        privacyView: String = "all",
        privacyComment: String = "all",
    ): Int {
        if (privacy !in 0..3 || commentPrivacy !in 0..3)
            return -1

        for ((index, note) in notesList.withIndex()) {
            if (note.id == noteId.toInt()) {
                with(notesList[index]) {
                    this.title = title
                    this.text = text
                    this.canComment = commentPrivacy
                    this.privacyView = privacyComment
                }

                return 1
            }
        }

        return -1
    }

    fun deleteComment(commentId: UInt): Int {
        val commId = commentId.toInt()

        for (comment in commentsList) {
            if (commId == comment.cid) {
                val commentCopy = comment.copy()
                commentsDeleted.add(commentCopy)
                commentsList.remove(comment)

                val noteId = commentCopy.noteId.toInt()
                val note = notesList.find { it.id == noteId }
                if (note != null) note.comments--

                return 1
            }
        }

        return -1
    }

    fun editComment(commentId: UInt, message: String): Int {
        val comment = commentsList.find { it.cid == commentId.toInt() }

        if (comment == null) return -1

        comment.message = message

        return 1
    }

    fun get(noteIds: String, offset: UInt, count: UInt, sort: UInt): List<Note>? {
        var foundNotes: MutableList<Note> = mutableListOf()

        var idStrings = noteIds.split(",", ".", " ", ":", ";")
        var idNumbers = ArrayList<Int>()
        for (id in idStrings) {
            idNumbers.add(id.toInt())
        }

        if (idNumbers.size == 0) return null

        for (id in idNumbers) {
            val note = notesList.find { it.id == id }
            if (note == null) continue

            foundNotes.add(note)
        }

        return foundNotes
    }

    fun getById(noteId: UInt): Note? {
        return notesList.find { it.id == noteId.toInt() }
    }

    fun getComments(
        noteId: UInt,
        ownerId: UInt = 0U,
        sort: UInt = 0U,
        offset: UInt = 0U,
        count: UInt = 0U
    ): List<CommentNote> {
        val nid = noteId

        var commentsOfTheNote = commentsList.filter { it.noteId == nid }

        if ((offset + count > 0U) && ((offset + count) <= commentsOfTheNote.size.toUInt())) {
            commentsOfTheNote = commentsOfTheNote.subList(offset.toInt(), (offset + count).toInt())
        }

        return commentsOfTheNote
    }

    fun restoreComment(commentId: UInt): Int {
        val comment = commentsDeleted.find { it.cid == commentId.toInt() }
        if (comment == null) return -1

        val note = notesList.find { it.id == comment.noteId.toInt() }
        if (note == null) return -1

        val restoredComment = comment.copy()

        commentsDeleted.remove(comment)
        commentsList.add(restoredComment)
        note.comments++

        return 1
    }


    private fun getNextNoteId(): Int {
        return ++notesIdCounter
    }

    private fun getNextCommentId(): Int {
        return ++commentsIdCounter
    }

}
