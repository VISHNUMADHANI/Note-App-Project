package com.example.noteapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class adepter(private var notes: List<addNote>, context: Context):
    RecyclerView.Adapter<adepter.viewholder>() {
    lateinit var  delet : addNote
    private val db : databasehhelper = databasehhelper(context)
    private val dbdelet : databasedelet = databasedelet(context)
    private val dbarchive : datasearchive = datasearchive(context)

    inner class viewholder(itemView: View):RecyclerView.ViewHolder(itemView){
        val currentdate = itemView.findViewById<TextView>(R.id.date)
        val titletext:TextView = itemView.findViewById(R.id.v_title)
        val contenttext:TextView = itemView.findViewById(R.id.v_content)
        val deletebuttone:ImageView = itemView.findViewById(R.id.v_delete)
        val archivebuttone : ImageView = itemView.findViewById(R.id.v_archive)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.view,parent,false)
        return viewholder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val item = notes[position]
        holder.currentdate.text=item.DATE
        holder.titletext.text = item.TITLE
        holder.contenttext.text = item.CONTENT


            when(item.COLOUR){
                1->holder.itemView.setBackgroundResource(R.drawable.shape1)
                2->holder.itemView.setBackgroundResource(R.drawable.shap2)
                3->holder.itemView.setBackgroundResource(R.drawable.shappink)
                4->holder.itemView.setBackgroundResource(R.drawable.shapbrouwn)
                5->holder.itemView.setBackgroundResource(R.drawable.shaplime)
                6->holder.itemView.setBackgroundResource(R.drawable.shappurple)
            }

        holder.itemView.setOnClickListener{

            val intent = Intent(holder.itemView.context , update::class.java).apply {
                putExtra("item_id",item.ID)
            }
            holder.itemView.context.startActivity(intent)
        }
        holder.deletebuttone.setOnClickListener{


            val title2 = item.TITLE
            val conten2 = item.CONTENT
            val date2 = db.getcurrentdate()
            val color2 = item.COLOUR
            val Note4:addNote = addNote(0, title2,conten2,date2,color2)
            dbdelet.insertnote(Note4)
            delet = db.getnote(item.ID)
            db.deletenote(item.ID)
            refreshdata(db.getAllNotes())
        }


        holder.archivebuttone.setOnClickListener{
            val title2 = item.TITLE
            val conten2 = item.CONTENT
            val date2 = item.DATE
            val color2 = item.COLOUR
            val Note4:addNote = addNote(0, title2,conten2,date2,color2)
            dbarchive.insertnote(Note4)
            delet = db.getnote(item.ID)
            db.deletenote(item.ID)
            refreshdata(db.getAllNotes())
        }
    }
    fun refreshdata(newNotes : List<addNote>){
        notes = newNotes
        notifyDataSetChanged()
    }

}
