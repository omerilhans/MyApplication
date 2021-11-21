package com.omerilhanli.myapplication.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.omerilhanli.myapplication.BR
import com.omerilhanli.myapplication.common.applyAnimation
import com.omerilhanli.myapplication.data.datasource.Person
import com.omerilhanli.myapplication.databinding.ItemPersonBinding

class PeopleViewHolder(private val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.applyAnimation()
    }

    fun bind(person: Person){
        binding.setVariable(BR.person, person)
        binding.executePendingBindings()
    }
}