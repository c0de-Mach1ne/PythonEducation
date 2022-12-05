package com.example.simpleregistration.fragments.guid.guid_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.simpleregistration.databinding.GuidItemBinding
import com.example.simpleregistration.fragments.model.Guid
import com.example.simpleregistration.utils.ItemClickListener

class GuidListAdapter(private val callback: ItemClickListener<Guid>, /* private val flag: Boolean так мы можем прокинуть
флаг во вьюхолдер и отобразить нашу иконку для удаления */) :
    ListAdapter<Guid, GuidListViewHolder>(GuidListDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GuidListViewHolder(
        GuidItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), callback /* вот сюда мы его прокидываем
        и получаем так же, как и коллбек во вьюхолдере */
    )

    override fun onBindViewHolder(holder: GuidListViewHolder, position: Int) =
        holder.bind(currentList[position])
}