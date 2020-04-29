package com.example.mynotes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.Model.Note
import com.example.mynotes.Utility.Utility
import com.example.mynotes.databinding.SingleNoteItemBinding


class NoteAdapter(val notes:List<Note>,val listener: OnItemClickListener,val context:Context) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(val itemview: SingleNoteItemBinding):RecyclerView.ViewHolder(itemview.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
         var layoutInflater:LayoutInflater= LayoutInflater.from(parent.context)
         var binding:SingleNoteItemBinding=DataBindingUtil.inflate(layoutInflater,R.layout.single_note_item,parent,false)
         var holder=NoteViewHolder(binding)
        binding.root.setOnClickListener {
            var adapterPosition=holder.adapterPosition
            if(adapterPosition != RecyclerView.NO_POSITION) {
                listener.OnItemClick(getNoteAt(adapterPosition))
            }
        }
        return holder

    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        var note:Note=notes[position]
        holder.itemview.titleTV.text=note.title
        holder.itemview.descriptionTV.text=note.description
        holder.itemview.timeTV.text=Utility.getCurrentDateAndTime(note.modificationTime)
        when(position%7){
            0->holder.itemview.view.setBackgroundColor(ContextCompat.getColor(context,R.color.color1))
            1->holder.itemview.view.setBackgroundColor(ContextCompat.getColor(context,R.color.color2))
            2->holder.itemview.view.setBackgroundColor(ContextCompat.getColor(context,R.color.color3))
            3->holder.itemview.view.setBackgroundColor(ContextCompat.getColor(context,R.color.color4))
            4->holder.itemview.view.setBackgroundColor(ContextCompat.getColor(context,R.color.color5))
            5->holder.itemview.view.setBackgroundColor(ContextCompat.getColor(context,R.color.color6))
            6->holder.itemview.view.setBackgroundColor(ContextCompat.getColor(context,R.color.color7))
        }


    }

    fun getNoteAt(position: Int): Note {
        return notes[position]
    }


}