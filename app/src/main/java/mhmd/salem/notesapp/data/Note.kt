package mhmd.salem.notesapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    var noteId    :Int = 0,
    var noteTitle :String ,
    var noteText  :String
):Parcelable
