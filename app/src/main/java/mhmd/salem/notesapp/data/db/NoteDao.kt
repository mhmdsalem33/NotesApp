package mhmd.salem.notesapp.data.db

import androidx.core.view.WindowInsetsCompat
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import mhmd.salem.notesapp.data.Note

@Dao
interface NoteDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNote(note :Note)

    @Delete()
    suspend fun deleteNote(note: Note)

    @Query("DELETE  FROM Note")
    suspend fun deleteAllNote()




    @Query("SELECT * FROM Note")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM Note ORDER BY noteId DESC")
    fun selectNote() :Flow<List<Note>>

    @Query("SELECT * FROM Note WHERE noteTitle LIKE '%'||:searchQuery||'%'")
    fun searchInNoteTitle(searchQuery:String) : Flow<List<Note>>




}