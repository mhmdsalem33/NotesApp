package mhmd.salem.notesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import mhmd.salem.notesapp.activity.MainActivity
import mhmd.salem.notesapp.data.Note
import mhmd.salem.notesapp.databinding.FragmentNoteBinding
import mhmd.salem.notesapp.viewModels.NoteViewModel


class NoteFragment : Fragment() {

    private lateinit var binding :FragmentNoteBinding
    val args by navArgs<NoteFragmentArgs>()
    private lateinit var noteMvvm :NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        noteMvvm = (activity as MainActivity).noteMvvm

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteBinding.inflate(inflater , container , false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        args.notes?.let {
            binding.apply {
                edtTitle.setText(it.noteTitle)
                edtNote.setText(it.noteText)
            }
            binding.imgDelete.visibility = View.VISIBLE

        }


        binding.apply {
            btnSave.setOnClickListener {
                val id        = args.notes?.noteId ?:0
                val noteTitle = edtTitle.text.toString()
                val noteText  = edtNote.text.toString()


                Note(id , noteTitle , noteText).also { note ->
                    if (noteTitle.isEmpty() && noteText.isEmpty())
                    {
                        Toast.makeText(context, "Title && Note is empty", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    noteMvvm.upsertNote(note)
                    findNavController().navigateUp()
                }



/*
                val finalData = Note( id, noteTitle , noteText)
                if (noteTitle.isEmpty() && noteText.isEmpty())
                {
                    Toast.makeText(context, "Title && Note is empty", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                noteMvvm.upsertNote(finalData)
                findNavController().navigateUp()
 */


            }




        }


        binding.apply {
            imgDelete.setOnClickListener {

                val noteId     =  args.notes!!.noteId
                val noteTitle  =  edtTitle.text.toString()
                val noteText   =  edtNote.text.toString()
                    Note(noteId , noteTitle, noteText).also { note ->
                        noteMvvm.deleteNote(note)
                    }
                findNavController().navigateUp()
            }
        }



    }




}