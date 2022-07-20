package mhmd.salem.notesapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import mhmd.salem.notesapp.R
import mhmd.salem.notesapp.Repositories.NotesRepo
import mhmd.salem.notesapp.data.db.NotesDatabase
import mhmd.salem.notesapp.viewModels.NoteViewModel
import mhmd.salem.notesapp.viewModels.NotesViewModelProviderFactory.NotesViewModelProviderFactory

class MainActivity : AppCompatActivity() {


    val noteMvvm : NoteViewModel by lazy {
        val noteDatabase = NotesDatabase.getDatabaseInstance(this)
        val notesRepo    = NotesRepo(noteDatabase)
        val noteProviderFactory = NotesViewModelProviderFactory(notesRepo)
        ViewModelProvider(this , noteProviderFactory)[NoteViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }
}