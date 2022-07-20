package mhmd.salem.notesapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mhmd.salem.notesapp.data.Note

@Database(entities = [Note::class] , version = 1)
abstract class NotesDatabase :RoomDatabase() {
            abstract val noteDao :NoteDao

            companion object{
                @Volatile
                var INSTANCE : NotesDatabase ? = null
                @Synchronized
                fun getDatabaseInstance(context: Context):NotesDatabase{
                    if (INSTANCE == null)
                        INSTANCE = Room.databaseBuilder(
                            context,
                            NotesDatabase::class.java,
                            "noteDb"
                        ).fallbackToDestructiveMigration().build()
                    return INSTANCE!!
                }


            }




}