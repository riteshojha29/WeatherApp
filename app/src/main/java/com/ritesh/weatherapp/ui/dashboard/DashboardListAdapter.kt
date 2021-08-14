package com.ritesh.weatherapp.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ritesh.weatherapp.R
import com.ritesh.weatherapp.model.LocationModel


class DashboardListAdapter(
    private var items: List<LocationModel>,
    private val context: Context)
    : RecyclerView.Adapter<DashboardListAdapter.ViewHolder>() {

    var onItemClicked: ((LocationModel) -> Unit)? = null
    var onItemDeleted: ((LocationModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_location, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.let {
            it.itemCity.text = item.locality

            it.itemView.setOnClickListener {
                onItemClicked?.invoke(item)
            }

            it.itemDelete.setOnClickListener {
                onItemDeleted?.invoke(item)
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemCity: TextView = view.findViewById(R.id.itemCity)
        val itemDelete: ImageView = view.findViewById(R.id.itemDelete)
    }
}

