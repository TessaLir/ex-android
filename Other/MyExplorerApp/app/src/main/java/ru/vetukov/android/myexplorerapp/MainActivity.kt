package ru.vetukov.android.myexplorerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity(), ExplorerAdapter.OnItemClickListener {

    private var list: ArrayList<MyFile>? = null
    private var adapter: ExplorerAdapter? = null
    private var recycler: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val file = File(this.applicationInfo.dataDir)
        list = ArrayList()

        if (file.parent != null) list!!.add(MyFolder("...", file.parent))

        for (el in file.list()) {
            val f = File("${file.path}/${el}")
            if (f.isDirectory) list!!.add(MyFolder(el.toString(), file.path))
            else list!!.add(MyFile(el.toString(), file.path))
        }

        recycler = main_recycler
        adapter = ExplorerAdapter(list!!, this)

        adapter!!.setOnItemListener(this)
        recycler!!.layoutManager = LinearLayoutManager(this)
        recycler!!.adapter = adapter

        main_btn_add_folder.setOnClickListener{
            val folder = MyFolder("Папка № ${list!!.size + 1}", this.applicationInfo.dataDir)
            val file = File(folder.getFullPath())
            if (!file.exists()) {
                file.mkdir()
            } else {
                Toast.makeText(this, "Папка уже существует", Toast.LENGTH_SHORT).show()
            }
            list!!.add(folder)
            adapter!!.toggleSelection(list!!.size)
        }

        main_btn_drop_folder.setOnClickListener{
            val position = list!!.lastIndex
            val file = File((list!![position] as MyFolder).getFullPath())
            if (file.exists()) {
                file.delete()
            }
            list!!.remove(list!![position])
            adapter!!.toggleSelection(list!!.size)
        }

    }

    override fun onItemClick2(view: View, adapter: ExplorerAdapter, position: Int) {
        Toast.makeText(this, "Click $position position.", Toast.LENGTH_SHORT).show()

        val file = File(list!![position].getFullPath())

        if (position == 0) {
            val ff = File(file.parent)

            list!!.clear()

            if (ff.parent != null) list!!.add(MyFolder("...", ff.parent))

            for (el in ff.list()) {
                val f = File("${ff.path}/${el}")
                if (f.isDirectory) list!!.add(MyFolder(el.toString(), ff.path))
                else list!!.add(MyFile(el.toString(), ff.path))
            }

            recycler!!.adapter = adapter
        }

        if (file.isDirectory) {
            list!!.clear()

            if (file.parent != null) list!!.add(MyFolder("...", file.parent))

            for (el in file.list()) {
                val f = File("${file.path}/${el}")
                if (f.isDirectory) list!!.add(MyFolder(el.toString(), file.path))
                else list!!.add(MyFile(el.toString(), file.path))
            }

            recycler!!.adapter = adapter
        }

    }



}
