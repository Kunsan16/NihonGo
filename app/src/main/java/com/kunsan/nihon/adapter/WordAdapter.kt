package com.kunsan.nihon.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kunsan.nihon.R
import com.kunsan.nihon.bean.WordBean
import kotlinx.android.synthetic.main.item_nihon_word.view.*

/**
 * Created by moge on 2018/10/24.
 */
class WordAdapter(private var mData: List<WordBean>,var itemClick:OnItemClickListener) : RecyclerView.Adapter<WordAdapter.VH>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

      return VH(View.inflate(parent.context, R.layout.item_nihon_word,null))
    }


    override fun onBindViewHolder(holder: VH, position: Int) {

        val entity:WordBean? = mData[position]
        if (entity != null){
            holder.bindView(position,entity)
        }
          }

    override fun getItemCount(): Int {
        return mData.size
    }

     interface OnItemClickListener {
        operator fun invoke(wordBean: WordBean)
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        @SuppressLint("SetTextI18n")
        fun bindView(position: Int, item:WordBean){

            with(item){
                kotlin.with(itemView) {
                    tv_word_japanese.text = japanese +"    "+chinese
                    checkbox.setOnClickListener {

                        item.checked = !item.checked
                        itemClick(item)
                    }
                }
            }
        }


    }

}