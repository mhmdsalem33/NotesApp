package mhmd.salem.notesapp.Repositories

import mhmd.salem.notesapp.data.Note
import mhmd.salem.notesapp.data.db.NotesDatabase

class NotesRepo(
    notesDatabase :NotesDatabase
) {
    val noteDao = notesDatabase.noteDao

    suspend fun upsetNote(note: Note) = noteDao.upsertNote(note)

    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

    suspend fun deleteAllNotes() = noteDao.deleteAllNote()

    fun getNotes()      = noteDao.selectNote()

    fun getAllNotes()   = noteDao.getAllNotes()

    fun searchNotes(searchQuery :String) = noteDao.searchInNoteTitle(searchQuery)






}