package mollah.yamin.apptoursample.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import mollah.yamin.apptoursample.R
import mollah.yamin.apptoursample.databinding.AppTourListItemBinding
import mollah.yamin.apptoursample.models.AppTourModel

class AppTourSliderAdapter: RecyclerView.Adapter<AppTourSliderAdapter.ViewHolder>() {

    private var slides: ArrayList<AppTourModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: AppTourListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item_app_tour, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(slides[position])
    }

    override fun getItemCount(): Int {
        return slides.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(slides: List<AppTourModel>) {
        this.slides = slides as ArrayList<AppTourModel>
        notifyDataSetChanged()
    }

    inner class ViewHolder (private val binding: AppTourListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ClickableViewAccessibility")
        fun bind(item: AppTourModel) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}