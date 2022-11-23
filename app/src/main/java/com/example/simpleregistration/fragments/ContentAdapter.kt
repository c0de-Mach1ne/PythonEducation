package com.example.simpleregistration.fragments

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.simpleregistration.fragments.model.Guid

class ContentAdapter() : ListAdapter<Guid, ContentViewHolder>(ContentDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}