package de.kfzteile24.app.presentation.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.kfzteile24.app.databinding.ItemLocationBinding
import de.kfzteile24.app.domain.locations.entity.LocationEntity

class LocationAdapter(
    private val locations: MutableList<LocationEntity>,
    private val onTabEvent: (location: LocationEntity) -> Unit
) : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    fun updateList(mLocations: List<LocationEntity>) {
        locations.clear()
        locations.addAll(mLocations)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemBinding: ItemLocationBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(loc: LocationEntity) {
            itemBinding.tvName.text = loc.name
            itemBinding.tvId.text = loc.id.toString()
            itemBinding.root.setOnClickListener {
                onTabEvent.invoke(loc)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(locations[position])

    override fun getItemCount() = locations.size
}