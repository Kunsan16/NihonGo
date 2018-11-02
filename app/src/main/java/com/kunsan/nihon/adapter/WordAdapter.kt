package com.kunsan.nihon.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.LogUtils
import com.kunsan.nihon.R
import com.kunsan.nihon.bean.WordBean
import com.kunsan.nihon.dao.WordList
import kotlinx.android.synthetic.main.item_nihon_word.view.*

/**
 * Created by moge on 2018/10/24.
 */
class WordAdapter(private var mData: List<WordList>,var itemClick:OnItemClickListener) : RecyclerView.Adapter<WordAdapter.VH>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

      return VH(View.inflate(parent.context, R.layout.item_nihon_word,null))
    }


    override fun onBindViewHolder(holder: VH, position: Int) {

        val entity:WordList? = mData[position]
        if (entity != null){
            holder.bindView(position,entity)
        }
          }

    override fun getItemCount(): Int {
        return mData.size
    }

     interface OnItemClickListener {
        operator fun invoke(wordBean: WordList)
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        @SuppressLint("SetTextI18n")
        fun bindView(position: Int, item:WordList){

            with(item){
                kotlin.with(itemView) {
                    LogUtils.i("更新数据库 adapter "+item.chinese+" "+item.collect)
                    tv_word_japanese.text = japanese +"    "+chinese
                    checkbox.isChecked = collect
                    checkbox.setOnClickListener {

                        item.collect = !item.collect
                        itemClick(item)
                    }
                }
            }
        }


    }

}