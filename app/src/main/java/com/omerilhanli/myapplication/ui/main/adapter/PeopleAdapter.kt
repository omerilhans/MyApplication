package com.omerilhanli.myapplication.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.omerilhanli.myapplication.R
import com.omerilhanli.myapplication.data.datasource.Person
import com.omerilhanli.myapplication.databinding.ItemPersonBinding

class PeopleAdapter(
    var people: MutableList<Person> = mutableListOf(),
    val swipeEndListener: RecyclerSwipeEndListener? = null
) : RecyclerView.Adapter<PeopleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        DataBindingUtil
            .inflate<ItemPersonBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_person,
                parent,
                false
            ).also {
                return PeopleViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        people
            .let {
                holder.bind(it[position])
            }

        if (position == itemCount - 1) { // for visible last item
            swipeEndListener?.onSwipeEnd()
        }
    }

    override fun getItemCount() = people.size

    fun update(personList: List<Person>? = null, isRefresh: Boolean = false) {
        personList
            ?.apply {
                if (isRefresh) {
                    people.clear()
                }
            }?.also {
                people.addAll(it)
                people = people.toSet().toList() as MutableList // remove duplicate items
                notifyDataSetChanged()
            }
    }
}