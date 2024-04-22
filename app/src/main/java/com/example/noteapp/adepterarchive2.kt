package com.example.noteapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class adepterarchive2(private var ad2Note: List<addNote>,context: Context):
    RecyclerView.Adapter<adepterarchive2.VIEWHOLDER>() {


    private val db : databasehhelper = databasehhelper(context)
    private val dbarchive : datasearchive = datasearchive(context)
    private val dbdelet : databasedelet = databasedelet(context)
    inner class VIEWHOLDER(itemView : View):RecyclerView.ViewHolder(itemView){
        val currentdate = itemView.findViewById<TextView>(R.id.date)
        val titletext: TextView = itemView.findViewById(R.id.v_title)
        val contenttext: TextView = itemView.findViewById(R.id.v_content)
        val deletbutton : ImageView = itemView.findViewById(R.id.v_delete)
        val unarchivbutton : ImageView = itemView.findViewById(R.id.unarchive)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VIEWHOLDER {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.unarcivview,parent,false)
        return VIEWHOLDER(view)
    }

    override fun getItemCount(): Int {
       return ad2Note.size
    }

    override fun onBindViewHolder(holder: VIEWHOLDER, position: Int) {
        val item = ad2Note[position]
        holder.currentdate.text = item.DATE
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
            val intent = Intent(holder.itemView.context,archive_unarchive::class.java).apply {
                putExtra("item_id",item.ID)
            }
            holder.itemView.context.startActivity(intent)

        }
        holder.deletbutton.setOnClickListener{
            val title = item.TITLE
            val context = item.CONTENT
            val date = item.DATE
            val id = item.ID
            val color = item.COLOUR
            val notee: addNote = addNote(id,title,context,date,color)

            dbdelet.insertnote(notee)
            dbarchive.deletenote(item.ID)
            refreshdata(dbarchive.getAllNotes())
        }

        holder.unarchivbutton.setOnClickListener{
            val title = item.TITLE
            val context = item.CONTENT
            val date = item.DATE
            val id = item.ID
            val color = item.COLOUR
            val notee: addNote = addNote(id,title,context,date,color)

            db.insertnote(notee)
            dbarchive.deletenote(item.ID)
            refreshdata(dbarchive.getAllNotes())
        }
    }


    fun refreshdata(newNotes : List<addNote>){
        ad2Note = newNotes
        notifyDataSetChanged()
    }

}