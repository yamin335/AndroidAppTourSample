package mollah.yamin.apptoursample.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import mollah.yamin.apptoursample.R
import mollah.yamin.apptoursample.databinding.AppTourSliderIndicatorListItemBinding

class AppTourSliderIndicatorAdapter internal constructor(
    private var noOfSlides: Int
) : RecyclerView.Adapter<AppTourSliderIndicatorAdapter.AppTourSliderIndicatorViewHolder>() {

    private var checkedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppTourSliderIndicatorViewHolder {
        val binding: AppTourSliderIndicatorListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item_app_tour_indicator, parent, false)
        return AppTourSliderIndicatorViewHolder(binding)
    }

    inner class AppTourSliderIndicatorViewHolder (private val binding: AppTourSliderIndicatorListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            if (checkedPosition == adapterPosition) {
                binding.indicator.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.theme_red))
            } else {
                binding.indicator.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.theme_light))
            }
        }
    }

    override fun onBindViewHolder(holder: AppTourSliderIndicatorViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return noOfSlides
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setIndicatorAt(position: Int) {
        if (position in 0 until noOfSlides) {
            checkedPosition = position
            notifyDataSetChanged()
        }
    }
}
