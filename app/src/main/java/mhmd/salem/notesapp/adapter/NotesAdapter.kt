package mhmd.salem.notesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mhmd.salem.notesapp.data.Note
import mhmd.salem.notesapp.databinding.NotesItemBinding

class NotesAdapter():RecyclerView.Adapter<NotesAdapter.ViewHolder>() {


    var onCLick : ((Note) -> Unit) ? = null

    private val diffCallBack = object :DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.noteId == newItem.noteId
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return  oldItem == newItem
        }


    }
    val differ = AsyncListDiffer(this , diffCallBack)



    inner class ViewHolder( val binding :NotesItemBinding):RecyclerView.ViewHolder(binding.root)
    {

        fun bind(note: Note)
        {
            binding.noteTile.text = note.noteTitle
        }





    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(NotesItemBinding.inflate(LayoutInflater.from(parent.context),parent , false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val note = differ.currentList[position]
      //  holder.bind(note)

        holder.binding.noteTile.text = note.noteTitle

        holder.itemView.setOnClickListener{
            onCLick?.invoke(note)
        }

    }

    override fun getItemCount() = differ.currentList.size


}