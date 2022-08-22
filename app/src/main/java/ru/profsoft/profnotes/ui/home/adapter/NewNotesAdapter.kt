package ru.profsoft.profnotes.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.profsoft.profnotes.R
import ru.profsoft.profnotes.data.model.MyTasks
import ru.profsoft.profnotes.databinding.ItemYouNewBinding

class NewNotesAdapter : RecyclerView.Adapter<NewNotesAdapter.ViewHolder>() {

    private var items = emptyList<MyTasks>()

    inner class ViewHolder(private val binding: ItemYouNewBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(item: MyTasks) {
                with(binding) {
                    tvNoteTitle.text = item.title
                    tvNoteDescription.text = item.description
                    tvNoteStatus.text = item.status
                    tvChangeTime.text = item.date

                    when (item.status) {
                        "Новое" -> {
                            tvNoteStatus.setTextColor(ContextCompat.getColor(tvNoteStatus.context, R.color.blue_FF))
                            lineStart.setBackgroundColor(ContextCompat.getColor(lineStart.context, R.color.blue_FF))
                        }
                        "В работе" -> {
                            tvNoteStatus.setTextColor(ContextCompat.getColor(tvNoteStatus.context, R.color.green_85))
                            lineStart.setBackgroundColor(ContextCompat.getColor(lineStart.context, R.color.green_85))
                        }
                        "Выполнено" -> {
                            tvNoteStatus.setTextColor(ContextCompat.getColor(tvNoteStatus.context, R.color.yellow_16))
                            lineStart.setBackgroundColor(ContextCompat.getColor(lineStart.context, R.color.yellow_16))
                        }
                        else -> {
                            tvNoteStatus.setTextColor(ContextCompat.getColor(tvNoteStatus.context, R.color.red_55))
                            lineStart.setBackgroundColor(ContextCompat.getColor(lineStart.context, R.color.red_55))
                        }
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            ItemYouNewBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newItems: List<MyTasks>) {
        items = newItems
        notifyDataSetChanged()
    }
}