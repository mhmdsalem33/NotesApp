package mhmd.salem.notesapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mhmd.salem.notesapp.Repositories.NotesRepo
import mhmd.salem.notesapp.data.Note

class NoteViewModel(
    private val notesRepo: NotesRepo
):ViewModel() {

    val notes       = notesRepo.getNotes()
    val getAllNotes = notesRepo.getAllNotes()

    private val _searchNotes = MutableStateFlow<List<Note>>(emptyList())
    val searchNotes :StateFlow<List<Note>> = _searchNotes


    fun upsertNote(note :Note) = viewModelScope.launch {
        notesRepo.upsetNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        notesRepo.deleteNote(note)
    }

    fun  deleteAllNotes() = viewModelScope.launch {
        notesRepo.deleteAllNotes()
    }


    fun searchNotes(searchQuery:String) = viewModelScope.launch {
        notesRepo.searchNotes(searchQuery).collect{ noteList ->
               _searchNotes.emit(noteList)
        }
    }


}