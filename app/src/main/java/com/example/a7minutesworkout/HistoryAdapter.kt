package com.example.a7minutesworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkout.databinding.ItemHistoryRowBinding

class HistoryAdapter(val items:ArrayList<String>):RecyclerView.Adapter<HistoryAdapter.ViewHolder>(){

    class ViewHolder(binding: ItemHistoryRowBinding):RecyclerView.ViewHolder(binding.root){
        val llHistoryRow=binding.llHistoryRow
        val tvItem=binding.tvItem
        val tvPosition=binding.tvPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date:String= items.get(position)
        holder.tvPosition.text=(position+1).toString()
        holder.tvItem.text=date

        if(position % 2 == 0){
            holder.llHistoryRow.setBackgroundColor(Color.parseColor("#EBEBEB"))

        }
        else{
            holder.llHistoryRow.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }
}