package com.kunsan.nihon.adapter

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
class WordAdapter(mData:List<WordBean>) : RecyclerView.Adapter<WordAdapter.VH>() {

    private var itemClick: (View, WordBean) -> Unit = { _, _ -> }
    var mData:List<WordBean> =mData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

      return VH(View.inflate(parent.context, R.layout.item_nihon_word,null))
    }


    override fun onBindViewHolder(holder: VH, position: Int) {

        val entity:WordBean? = mData[position]
        if (entity != null){
            holder.bindView(position,entity,itemClick)
        }
          }

    override fun getItemCount(): Int {
        return mData.size
    }



    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView(position: Int,item:WordBean,itemClick: (View, WordBean) -> Unit){

            with(item){
                kotlin.with(itemView) {
                    tv_word_japanese.text = japanese
                    tv_word_chinese.text = chinese
                }
            }
        }


    }

}