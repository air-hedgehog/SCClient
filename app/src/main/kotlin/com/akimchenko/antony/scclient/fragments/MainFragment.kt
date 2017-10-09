package com.akimchenko.antony.scclient.fragments

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akimchenko.antony.scclient.R
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_random.view.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by antony on 10/8/17.
 */
class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater!!.inflate(R.layout.fragment_main, container)
        setRecycler(this.fragment_main_recycler)
        return view
    }

    private fun get100Items(): ArrayList<String> {
        val random = Random()
        val list: ArrayList<String> = ArrayList<String>()
        for (x in 1..50) {
            list.add(random.nextInt().toString())
        }
        return list
    }

    private fun setRecycler(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = MainListAdapter(activity, get100Items())
    }

    class MainListAdapter(val context: Context, val items: ArrayList<String>) : RecyclerView.Adapter<MainListAdapter.ViewHolder>() {

        private val ITEM_TYPE_RANDOM: Int = 10

        abstract class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
            abstract fun updateUI(position: Int)
        }

        fun inflate(resourceID: Int, viewGroup: ViewGroup): View =
                LayoutInflater.from(context).inflate(resourceID, viewGroup, false)

        class RandomViewHolder(itemView: View?, val items: ArrayList<String>) : ViewHolder(itemView) {
            override fun updateUI(position: Int) {
                this.itemView.item_text_view.text = items[position]
            }
        }

        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            holder?.updateUI(position)
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            when (viewType) {
                ITEM_TYPE_RANDOM -> return RandomViewHolder(inflate(R.layout.item_random, parent!!), items)
                else -> return RandomViewHolder(inflate(R.layout.item_random, parent!!), items)
            }
        }

        override fun getItemViewType(position: Int): Int = ITEM_TYPE_RANDOM

        override fun getItemCount(): Int = items.size


    }
}