package com.kunsan.nihon.app.ui

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.kunsan.nihon.R
import com.kunsan.nihon.bean.WordBean
import com.kunsan.nihon.dao.Word
import com.kunsan.nihon.dao.WordList
import kotlinx.android.synthetic.main.item_nihon_word.view.*

/**
 * Created by moge on 2018/10/24.
 */
class CollectAdapter(var itemClick:OnItemClickListener) : RecyclerView.Adapter<CollectAdapter.VH>() {

   private var mewData : ArrayList<WordList> = ArrayList()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

      return VH(View.inflate(parent.context, R.layout.item_nihon_word, null))
    }


    fun setData(mList:List<WordList>){
        mewData.addAll(mList)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: VH, position: Int) {

        val entity: WordList? = mewData[position]
        if (entity != null){
            holder.bindView(position,entity)
        }
          }

    override fun getItemCount(): Int {
        return mewData.size
    }

     interface OnItemClickListener {
        operator fun invoke(wordBean: WordList)
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        @SuppressLint("SetTextI18n")
        fun bindView(position: Int, item: WordList){

            with(item){
                kotlin.with(itemView) {
                    tv_word_japanese.text = japanese +"    "+chinese

                }
            }
        }


    }

}