package de.kfzteile24.app.presentation.locations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import de.kfzteile24.app.R
import de.kfzteile24.app.domain.locations.LocationModel


class LocationsAdapter(
  private val data: List<LocationModel>
) : RecyclerView.Adapter<LocationsAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
    return ViewHolder(
      LayoutInflater.from(parent.context).inflate(R.layout.item_view_location, parent, false)
    )
  }

  override fun getItemCount() = data.size

  override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
    viewHolder.tvId.text = data[position].id
    viewHolder.tvName.text = data[position].name
  }

  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvName: TextView = view.findViewById(R.id.tvName)
    val tvId: TextView = view.findViewById(R.id.tvId)
  }
}

