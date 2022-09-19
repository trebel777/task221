package ru.netology

import junit.framework.TestCase.assertEquals
import org.junit.Test

class NoteServiceTest {

    @Test
    fun add() {

        val nt = Notes()
        nt.clearData()

        val note1 = nt.add("First note", "Created first note!")
        val note2 = nt.add("Second note", "Created second note!")
        val note3 = nt.add("Third note", "Created third note!")

        assertEquals(3, nt.getNumberOfNotes())
    }

    @Test
    fun createComment() {

        val nt = Notes()
        nt.clearData()

        val note1 = nt.add("First note", "Created first note!")
        val note2 = nt.add("Second note", "Created second note!")
        val note3 = nt.add("Third note", "Created third note!")

        val comment1 = nt.createComment(note2.toUInt(), 0U, "Great note $note2", "***")
        val comment2 = nt.createComment(note2.toUInt(), 0U, "Great note $note2", "***")
        val comment3 = nt.createComment(note3.toUInt(), 0U, "Great note $note3", "***")
        val comment4 = nt.createComment(note3.toUInt(), 0U, "Great note $note3", "***")

        assertEquals(4, nt.getNumberOfComments())
    }

    @Test
    fun delete() {

        val nt = Notes()
        nt.clearData()

        val note1 = nt.add("First note", "Created first note!")
        val note2 = nt.add("Second note", "Created second note!")
        val note3 = nt.add("Third note", "Created third note!")

        val comment1 = nt.createComment(note2.toUInt(), 0U, "Great note $note2", "***")
        val comment2 = nt.createComment(note2.toUInt(), 0U, "Great note $note2", "***")
        val comment3 = nt.createComment(note3.toUInt(), 0U, "Great note $note3", "***")
        val comment4 = nt.createComment(note3.toUInt(), 0U, "Great note $note3", "***")

        nt.delete(note1.toUInt())
        nt.delete(note3.toUInt())

        assertEquals(1, nt.getNumberOfNotes())
        assertEquals(2, nt.getNumberOfComments())
    }

    @Test
    fun edit() {

        val nt = Notes()
        nt.clearData()

        val note1 = nt.add("First note", "Created first note!")
        val note2 = nt.add("Second note", "Created second note!")
        val note3 = nt.add("Third note", "Created third note!")

        val res = nt.edit(note2.toUInt(), "Edit second note", "Edit second note")

        assertEquals(1, res)
    }

    @Test
    fun deleteComment() {

        val nt = Notes()
        nt.clearData()

        val note1 = nt.add("First note", "Created first note!")
        val note2 = nt.add("Second note", "Created second note!")
        val note3 = nt.add("Third note", "Created third note!")

        val comment1 = nt.createComment(note2.toUInt(), 0U, "Great note $note2", "***")
        val comment2 = nt.createComment(note2.toUInt(), 0U, "Great note $note2", "***")
        val comment3 = nt.createComment(note3.toUInt(), 0U, "Great note $note3", "***")
        val comment4 = nt.createComment(note3.toUInt(), 0U, "Great note $note3", "***")

        val res = nt.deleteComment(comment3.toUInt())

        assertEquals(1, res)
        assertEquals(3, nt.getNumberOfComments())
    }

    @Test
    fun editComment() {

        val nt = Notes()
        nt.clearData()

        val note1 = nt.add("First note", "Created first note!")
        val note2 = nt.add("Second note", "Created second note!")
        val note3 = nt.add("Third note", "Created third note!")

        val comment1 = nt.createComment(note2.toUInt(), 0U, "Great note $note2", "***")
        val comment2 = nt.createComment(note2.toUInt(), 0U, "Great note $note2", "***")
        val comment3 = nt.createComment(note3.toUInt(), 0U, "Great note $note3", "***")
        val comment4 = nt.createComment(note3.toUInt(), 0U, "Great note $note3", "***")

        val res = nt.editComment(comment3.toUInt(), "New message")

        assertEquals(1, res)
    }

    @Test
    fun get() {

        val nt = Notes()
        nt.clearData()

        val note1 = nt.add("First note", "Created first note!")
        val note2 = nt.add("Second note", "Created second note!")
        val note3 = nt.add("Third note", "Created third note!")
        val note4 = nt.add("Fourth note", "Created fourth note!")

        val nIds = "$note2,$note3"

        val notes = nt.get(nIds, 0U, 0U, 0U)

        assertEquals(2, notes?.size)
    }

    @Test
    fun getById() {

        val nt = Notes()
        nt.clearData()

        val note1 = nt.add("First note", "Created first note!")
        val note2 = nt.add("Second note", "Created second note!")
        val note3 = nt.add("Third note", "Created third note!")
        val note4 = nt.add("Fourth note", "Created fourth note!")

        val note = nt.getById(note4.toUInt())

        assertEquals(note4, note?.id)
    }

    @Test
    fun getComments() {

        val nt = Notes()
        nt.clearData()

        val note1 = nt.add("First note", "Created first note!")
        val note2 = nt.add("Second note", "Created second note!")
        val note3 = nt.add("Third note", "Created third note!")
        val note4 = nt.add("Fourth note", "Created fourth note!")

        val comment1 = nt.createComment(note2.toUInt(), 0U, "Great note $note2", "**1")
        val comment2 = nt.createComment(note2.toUInt(), 0U, "Great note $note2", "**2")
        val comment3 = nt.createComment(note3.toUInt(), 0U, "Great note $note3", "**3")
        val comment4 = nt.createComment(note3.toUInt(), 0U, "Great note $note3", "**4")
        val comment5 = nt.createComment(note3.toUInt(), 0U, "Great note $note3", "**5")

        val res1 = nt.getComments(noteId = note3.toUInt())
        val res2 = nt.getComments(noteId = note3.toUInt(), offset = 1U, count = 2U)

        assertEquals(3, res1.size)
        assertEquals(2, res2.size)
    }

    @Test
    fun restoreComment() {

        val nt = Notes()
        nt.clearData()

        val note1 = nt.add("First note", "Created first note!")
        val note2 = nt.add("Second note", "Created second note!")
        val note3 = nt.add("Third note", "Created third note!")


        val comment1 = nt.createComment(note2.toUInt(), 0U, "Great note $note2", "**1")
        val comment2 = nt.createComment(note2.toUInt(), 0U, "Great note $note2", "**2")
        val comment3 = nt.createComment(note3.toUInt(), 0U, "Great note $note3", "**3")
        val comment4 = nt.createComment(note3.toUInt(), 0U, "Great note $note3", "**4")
        val comment5 = nt.createComment(note3.toUInt(), 0U, "Great note $note3", "**5")

        val res1 = nt.deleteComment(comment3.toUInt())
        val res2 = nt.restoreComment(comment3.toUInt())

        assertEquals(1, res2)
        assertEquals(5, nt.getNumberOfComments())
    }
}