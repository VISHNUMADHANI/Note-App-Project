package com.example.noteapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class databasedelet(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "delete.db"
        private const val DATABASE_VERSION = 2
        private const val TABLE_NAME = "notedb_table"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENT = "content"
        private const val COLUMN_DATE = "date"
        private const val COLUMN_COLOUR = "colour"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableSQL= "CREATE TABLE ${databasedelet.TABLE_NAME}(${databasedelet.COLUMN_ID} INTEGER PRIMARY KEY ,${databasedelet.COLUMN_TITLE} STRING , ${databasedelet.COLUMN_CONTENT} STRING,${databasedelet.COLUMN_DATE},$COLUMN_COLOUR INTEGER)"
        db?.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val droptablequary = "DROP TABLE IF EXISTS ${databasedelet.TABLE_NAME}"
        db?.execSQL(droptablequary)
        onCreate(db)

    }






    fun insertnote(note : addNote){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE,note.TITLE)
            put(COLUMN_CONTENT,note.CONTENT)
            put(COLUMN_DATE,note.DATE)
            put(COLUMN_COLOUR,note.COLOUR)
        }
        db.insert(TABLE_NAME,null,values)
        db.close()
    }




    fun getAllNotes():List<addNote>{
        val noteslist = mutableListOf<addNote>()
        val db = readableDatabase
        val quary = "SELECT * FROM ${databasedelet.TABLE_NAME}"
        val cursor = db.rawQuery(quary,null)
        while (cursor.moveToNext()){
            val id2 = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title2 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val content2 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))
            val date2 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))
            val color2 = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_COLOUR))
            val note2 = addNote(id2 , title2,content2,date2,color2)
            noteslist.add(note2)
        }
        cursor.close()
        db.close()
        return noteslist
    }

    fun deletenote(noteId : Int){
        val db = writableDatabase
        val whereClase  =  "${databasedelet.COLUMN_ID} = ?"
        val whereArgs = arrayOf(noteId.toString())
        db.delete(databasedelet.TABLE_NAME,whereClase,whereArgs)
        db.close()
    }


    fun getnote(noteID : Int):addNote{
        val db = readableDatabase
        val quary = "SELECT * FROM ${databasedelet.TABLE_NAME} WHERE ${COLUMN_ID} = $noteID"
        val cursor = db.rawQuery(quary,null)
        cursor.moveToFirst()
        val id= cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val title= cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
        val content= cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))
        val date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))
        val color = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_COLOUR))
        cursor.close()
        db.close()
        return  addNote(id,title,content,date,color)


    }


}