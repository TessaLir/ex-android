package ru.vetukov.android.myexplorerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_file.view.*

class ExplorerAdapter(val list: ArrayList<MyFile>, val mainContext: MainActivity):
    RecyclerView.Adapter<ExplorerAdapter.ViewHolder>() {


    interface OnItemClickListener {
        fun onItemClick2(view: View, adapter: ExplorerAdapter, position: Int)
    }

    private var onClickListener: OnItemClickListener? = null

    fun setOnItemListener (listener: MainActivity) { this.onClickListener = listener }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                                 .inflate(R.layout.item_file, parent, false)
        return ViewHolder(view, this, mainContext)
    }

    override fun getItemCount() = list.size

    fun toggleSelection(position: Int) {
        notifyItemChanged(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val node = list[position]
        if (node is MyFolder) holder.mTitle?.text = node.name
        else if (node is MyFile) {
            holder.mTitle?.text = node.name
            holder.mImage?.setImageResource(R.drawable.ic_file_grey_32dp)
        }
    }

    class ViewHolder(view: View
                    ,val adapter: ExplorerAdapter
                    ,val listener: OnItemClickListener): RecyclerView.ViewHolder(view) {
        val mImage: ImageView? = itemView.main_recycler_image
        val mTitle: TextView? = itemView.main_recycler_title
        val mView: ConstraintLayout = itemView.main_recycler_view

        init {
            mView.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick2(view,adapter, position)
                }
            }
        }

    }
}