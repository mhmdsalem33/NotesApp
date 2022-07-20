package mhmd.salem.notesapp.viewModels.NotesViewModelProviderFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mhmd.salem.notesapp.Repositories.NotesRepo
import mhmd.salem.notesapp.viewModels.NoteViewModel


class NotesViewModelProviderFactory(
    private val notesRepo: NotesRepo
):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(notesRepo) as T
    }
}
