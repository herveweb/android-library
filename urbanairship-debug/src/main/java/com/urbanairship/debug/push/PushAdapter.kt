/* Copyright Urban Airship and Contributors */

package com.urbanairship.debug.push

import android.arch.paging.PagedListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.urbanairship.debug.BR
import com.urbanairship.debug.databinding.UaItemPushBinding
import com.urbanairship.debug.push.persistence.PushEntity
import com.urbanairship.debug.utils.SimpleDiffItemCallback

/**
 * RecyclerView adapter for events.
 */
internal class PushAdapter(private val callback: ((push: PushEntity) -> Unit)) : PagedListAdapter<PushEntity, PushAdapter.ViewHolder>(SimpleDiffItemCallback<PushEntity>()) {

    class ViewHolder(val binding: UaItemPushBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.apply {
            with(holder.binding) {
                setVariable(BR.push, PushItem(this@apply))
                root.setOnClickListener {
                    this@PushAdapter.callback(this@apply)
                }
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = UaItemPushBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
}