package com.example.noteapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class adepter2(private var notede: List<addNote>, context: Context):
    RecyclerView.Adapter<adepter2.VIEwholder>() {

    lateinit var delet : addNote
    private val dbdelet  : databasedelet= databasedelet(context)
    private val db : databasehhelper = databasehhelper(context)
    inner class VIEwholder(itemView: View):RecyclerView.ViewHolder(itemView){
        val currentdate = itemView.findViewById<TextView>(R.id.date)
        val titletext: TextView = itemView.findViewById(R.id.v_title)
        val contenttext: TextView = itemView.findViewById(R.id.v_content)
        val deletebuttone: ImageView = itemView.findViewById(R.id.v_delete)
        val restorbutton : ImageView = itemView.findViewById(R.id.restor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VIEwholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trashview,parent,false)
        return VIEwholder(view)
    }

    override fun getItemCount(): Int {
        return notede.size
    }

    override fun onBindViewHolder(holder: VIEwholder, position: Int) {
        val item = notede[position]
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
            val intent = Intent(holder.itemView.context,delet_undelet::class.java).apply {
                putExtra("item_id",item.ID)
            }
            holder.itemView.context.startActivity(intent)

        }

        holder.deletebuttone.setOnClickListener{
            delet = dbdelet.getnote(item.ID)
            dbdelet.deletenote(item.ID)
            refreshdata(dbdelet.getAllNotes())
            Toast.makeText(holder.itemView.context,"Note Permanent deleted", Toast.LENGTH_LONG).show()


        }
        holder.restorbutton.setOnClickListener{
            val title = item.TITLE
            val context = item.CONTENT
            val date = item.DATE
            val id = item.ID
            val color  = item.COLOUR
            val notee: addNote = addNote(id,title,context,date,color)
            db.insertnote(notee)
            dbdelet.deletenote(item.ID)
            refreshdata(dbdelet.getAllNotes())
            Toast.makeText(holder.itemView.context,"Note Restored",Toast.LENGTH_SHORT).show()
        }
    }

    fun refreshdata(newNotes : List<addNote>){
         notede = newNotes
        notifyDataSetChanged()
    }


}

