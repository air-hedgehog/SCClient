package com.akimchenko.antony.scclient.fragments

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import blurUtils.ImageUtils
import blurUtils.ScrollableImageView
import com.akimchenko.antony.scclient.MainActivity
import com.akimchenko.antony.scclient.R
import dialogs.TransparentDialog
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.item_random.view.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by antony on 10/8/17.
 */
class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater!!.inflate(R.layout.fragment_main, null)
        setRecycler(view.fragment_main_recycler)
        val blurredToolbar: ScrollableImageView = view.blurred_toolbar
        val screenWidth: Int = ImageUtils.getScreenWidth(activity)
        blurredToolbar.setScreenWidth(screenWidth)
        return view
    }

    private fun get100Items(): ArrayList<ListItem> {
        val random = Random()
        val list: ArrayList<ListItem> = ArrayList<ListItem>()
        for (x in 1..50) {
            if (x == 5) list.add(ListItem(random.nextInt().toString(), ContextCompat.getColor(activity, R.color.colorAccent)))
            list.add(ListItem(random.nextInt().toString(), ContextCompat.getColor(activity, if (x % 2 == 0) R.color.colorPrimaryDark else R.color.colorPrimary)))
        }
        return list
    }

    private fun setRecycler(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = MainListAdapter(activity, get100Items())
    }

    private class MainListAdapter(val context: Context, val items: ArrayList<ListItem>) : RecyclerView.Adapter<MainListAdapter.ViewHolder>() {

        private val ITEM_TYPE_RANDOM: Int = 10

        abstract class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
            abstract fun updateUI(position: Int)
        }

        fun inflate(resourceID: Int, viewGroup: ViewGroup): View =
                LayoutInflater.from(context).inflate(resourceID, viewGroup, false)

        class RandomViewHolder(context: Context, itemView: View?, val items: ArrayList<ListItem>) : ViewHolder(itemView) {

            init {
                itemView?.setOnClickListener({
                    val transparentDialog = TransparentDialog()
                    //transparentDialog.dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    transparentDialog.show((context as MainActivity).fragmentManager, transparentDialog.tag)
                })
            }

            override fun updateUI(position: Int) {
                itemView.item_text_view.text = items[position].getTitle()
                itemView.setBackgroundColor(items[position].getBackgroundColor()!!)

            }
        }

        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            holder?.updateUI(position)
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            when (viewType) {
                ITEM_TYPE_RANDOM -> return RandomViewHolder(context, inflate(R.layout.item_random, parent!!), items)
                else -> return RandomViewHolder(context, inflate(R.layout.item_random, parent!!), items)
            }
        }

        override fun getItemViewType(position: Int): Int = ITEM_TYPE_RANDOM

        override fun getItemCount(): Int = items.size
    }

    private class ListItem(title: String, backgroundColor: Int) {
        //encaplsulated A.F.
        private var title: String? = title
        private var backgroundColor: Int? = backgroundColor

        fun getTitle(): String? = title
        fun getBackgroundColor(): Int? = backgroundColor
    }
}