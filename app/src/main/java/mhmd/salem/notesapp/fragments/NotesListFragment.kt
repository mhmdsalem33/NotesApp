package mhmd.salem.notesapp.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import mhmd.salem.notesapp.R
import mhmd.salem.notesapp.activity.MainActivity
import mhmd.salem.notesapp.adapter.NotesAdapter
import mhmd.salem.notesapp.databinding.FragmentNotesListBinding
import mhmd.salem.notesapp.viewModels.NoteViewModel


class NotesListFragment : Fragment() {

    private lateinit var binding :FragmentNotesListBinding
    private lateinit var noteMvvm : NoteViewModel
    private lateinit var noteAdapter :NotesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteMvvm    = (activity as MainActivity).noteMvvm
        noteAdapter = NotesAdapter()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotesListBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupRecyclerView()



        lifecycleScope.launchWhenStarted {  // Submit All Saved Notes  To Adapter
            noteMvvm.getAllNotes.collect { noteList ->
                noteAdapter.differ.submitList(noteList)
            }
        }
/*
        lifecycleScope.launchWhenStarted {  // Submit Saved Notes by id To Adapter
            noteMvvm.notes.collect { noteList ->
                noteAdapter.differ.submitList(noteList)
            }
        }


 */
        lifecycleScope.launchWhenStarted {
            noteMvvm.searchNotes.collect{searchNotes ->
                noteAdapter.differ.submitList(searchNotes)
            }
        }

        binding.edtSearch.addTextChangedListener {
            noteMvvm.searchNotes(it.toString().trim()) // Trim removed Spaces
        }



        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment)
        }


        noteAdapter.onCLick = { note ->
            val bundle = Bundle().apply {
                putParcelable("notes" , note )
            }

            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment, bundle)

        }

        lifecycleScope.launchWhenStarted {
            binding.btnDeleteAll.setOnClickListener {

                            val builder = AlertDialog.Builder(context)
                                builder.setTitle("Attention")
                                builder.setCancelable(false)
                                builder.setMessage("Are you to delete all note ${noteAdapter.differ.currentList.size}  ?")
                                builder.setNegativeButton("Cancel"){dialog , _->dialog.dismiss()}
                                builder.setPositiveButton("Delete"){ dialog , _->
                                noteMvvm.deleteAllNotes()
                                dialog.dismiss()
                            }

                val dialog = builder.create()
                    dialog.setOnShowListener {
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                            .setTextColor(Color.rgb(1, 135 , 134))
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                            .setTextColor(Color.rgb(1, 135 , 134))

                    }
                    dialog.show()



            }
        }





            if (noteAdapter.differ.currentList.size >= 1)
            {
                binding.btnDeleteAll.visibility = View.VISIBLE
            }


    }

    private fun setupRecyclerView() {
            binding.recViewNotes.apply {
                layoutManager = LinearLayoutManager(context)
                adapter       = noteAdapter
            }
    }
}