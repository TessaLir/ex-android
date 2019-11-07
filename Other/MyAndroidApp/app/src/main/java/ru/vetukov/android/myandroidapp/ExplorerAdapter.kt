package ru.vetukov.android.myandroidapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_file.view.*
import java.util.zip.Inflater

class ExplorerAdapter<T>(val list: ArrayList<T>):
    RecyclerView.Adapter<ExplorerAdapter.ViewHolder>() {

    interface OnClickListener {
        fun onItemClick(position: Int)
    }

    private var onClickListener: AdapterView.OnItemClickListener? = null

    fun setOnItemListener (listener: AdapterView.OnItemClickListener) { this.onClickListener = listener }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                                 .inflate(R.layout.item_file, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val node = list[position]
        if (node is MyFolder) holder.mTitle?.text = node.name
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val mImage: ImageView? = itemView.main_recycler_image
        val mTitle: TextView? = itemView.main_recycler_title
        val mView: ConstraintLayout = itemView.main_recycler_view

        init {

        }

    }
}