package de.kfzteile24.app.presentation.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import de.kfzteile24.app.R
import de.kfzteile24.app.databinding.ItemVehicleBinding
import de.kfzteile24.app.domain.vehicles.entity.VehicleEntity
import de.kfzteile24.app.presentation.common.extension.gone
import de.kfzteile24.app.presentation.common.extension.visible

class VehicleAdapter(
    private val vehicles: MutableList<VehicleEntity>
) : RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {

    fun updateList(mVehicles: List<VehicleEntity>) {
        vehicles.clear()
        vehicles.addAll(mVehicles)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemBinding: ItemVehicleBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(vehicle: VehicleEntity) {
            itemBinding.nameTextView.text  = "Plate : ${vehicle.numberPlate}"
            itemBinding.dataTextView1.text = "MODEL : ${vehicle.model}"
            itemBinding.dataTextView2.text = "FUEL : ${vehicle.fuel}"
            itemBinding.dataTextView3.text = "VIN : ${vehicle.vin}"
            itemBinding.dataTextView4.text = "Position Lat-Long : ${vehicle.position?.latitude} - ${vehicle.position?.longitude}"

            itemBinding.ivExpand.setOnClickListener {
                if (itemBinding.containerDetails.visibility == View.VISIBLE) {

                    TransitionManager.beginDelayedTransition(
                        itemBinding.containerDetails,
                        AutoTransition()
                    )
                    itemBinding.containerDetails.gone()
                    itemBinding.ivExpand.setImageResource(R.drawable.ic_baseline_expand_more_24)
                } else {
                    TransitionManager.beginDelayedTransition(
                        itemBinding.containerDetails,
                        AutoTransition()
                    )
                    itemBinding.containerDetails.visible()
                    itemBinding.ivExpand.setImageResource(R.drawable.ic_baseline_expand_less_24)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemVehicleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(vehicles[position])

    override fun getItemCount() = vehicles.size
}