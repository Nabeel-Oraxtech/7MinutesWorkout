package com.example.a7minutesworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkout.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(val items:ArrayList<ExerciseModel>):
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder> (){
        class ViewHolder(binding: ItemExerciseStatusBinding):RecyclerView.ViewHolder(binding.root){

            val tvIem=binding.tvItem
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder( ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model:ExerciseModel=items[position]
        holder.tvIem.text=model.getId().toString()

        when{
            model.getIsSelected()->{
                holder.tvIem.background=ContextCompat.
                getDrawable(holder.itemView.context,R.drawable.item_circular_color_thin_accent_border)
                holder.tvIem.setTextColor(Color.parseColor("#212121"))
            }
            model.getIsCompleted()->{
                holder.tvIem.background=ContextCompat.
                getDrawable(holder.itemView.context,R.drawable.item_circular_color_accent_background)
                holder.tvIem.setTextColor(Color.parseColor("#ffffff"))

            }
            else->{
                holder.tvIem.background=ContextCompat.
                getDrawable(holder.itemView.context,R.drawable.item_circular_color_gray_background)
                holder.tvIem.setTextColor(Color.parseColor("#212121"))

            }

        }
    }
}