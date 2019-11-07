package ru.vetukov.android.myandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity(), ExplorerAdapter.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val file = File(this.applicationInfo.dataDir)
        val list = ArrayList<MyFile>()

        for (el in file.list()) {
            list.add(MyFolder(el.toString()))
        }

        val recycler = main_recycler
        val adapter = ExplorerAdapter(list)

        adapter.setOnItemListener(this)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
    }


    override fun onItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
