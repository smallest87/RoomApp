package com.anushka.roomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var fungsiBookSiapPakai: BookDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val roomDatabaseBahan = Room.databaseBuilder(
                context = applicationContext,
                klass = BookDatabase::class.java,
                name = "book_database"
        )

        val roomDatabaseMatang = roomDatabaseBahan.build()

        fungsiBookSiapPakai = roomDatabaseMatang.beriFungsiBookDao()

        terapkanRoomDB()

    }

    private fun terapkanRoomDB(){

        lifecycleScope.launch(Dispatchers.IO) {

            Log.i("MyTAG","*****     Menghapus semua baris data     **********")
            fungsiBookSiapPakai.hapusSemuaBarisData()

            //Insert
            Log.i("MyTAG","*****     Inserting 3 Books     **********")
            fungsiBookSiapPakai.insertBook(
                Book(
                    id = 0,
                    name = "Java",
                    author = "Alex"
                )
            )
            fungsiBookSiapPakai.insertBook(
                Book(
                    id = 0,
                    name = "PHP",
                    author = "Mike"
                )
            )
            fungsiBookSiapPakai.insertBook(
                Book(
                    id = 0,
                    name = "Kotlin",
                    author = "Amelia"
                )
            )
            Log.i("MyTAG","*****     Inserted 3 Books       **********")

            //Queery
            val books = fungsiBookSiapPakai.getAllBooks()
            Log.i("MyTAG","*****   ${books.size} books there *****")
            for(book in books){
                Log.i("MyTAG","id: ${book.id} name: ${book.name} author: ${book.author}")
            }

            //Update
            Log.i("MyTAG","*****      Updating a book      **********")
            fungsiBookSiapPakai.updateBook(Book(1,"PHPUpdated","Mike"))
            //Queery
            val books2 = fungsiBookSiapPakai.getAllBooks()
            Log.i("MyTAG","*****   ${books2.size} books there *****")
            for(book in books2){
                Log.i("MyTAG","id: ${book.id} name: ${book.name} author: ${book.author}")
            }

            //delete
//            Log.i("MyTAG","*****       Deleting a book      **********")
//            fungsiRoomSiapPakai.deleteBook(
//                Book(
//                    id = 22,
//                    name = "Java",
//                    author = "Alex"
//                )
//            )

            //Query
            val books3 = fungsiBookSiapPakai.getAllBooks()
            Log.i("MyTAG","*****   ${books3.size} books there *****")
            for(book in books3){
                Log.i("MyTAG","id: ${book.id} name: ${book.name} author: ${book.author}")
            }

//            bookDao.hapusSemua()
        }

    }
}