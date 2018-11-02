package com.kunsan.nihon

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.kunsan.nihon.adapter.WordAdapter
import com.kunsan.nihon.app.base.BaseActivity
import com.kunsan.nihon.app.base.ViewModelFactory
import com.kunsan.nihon.app.ui.CollectActivity
import com.kunsan.nihon.bean.LocalWordBean
import com.kunsan.nihon.bean.WordBean
import com.kunsan.nihon.dao.Word
import com.kunsan.nihon.dao.WordList
import com.kunsan.nihon.utils.JsonConvertUtils
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by moge on 2018/10/24.
 */
class MainActivity : BaseActivity<WordViewModel>() {



    override val layoutId: Int
        get() = R.layout.activity_main


    override val createViewModel: WordViewModel
        get() = getViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        saveAllWord()
        fab.setOnClickListener { CollectActivity.launch(this) }
    }

    private fun saveAllWord() {


        if(mViewModel.getAllWords().isNotEmpty()){
            initAdapter(mViewModel.getAllWords())
        }else{
            val bean = JsonConvertUtils.JsonToObject(this, "nihon_language.json", LocalWordBean::class.java)
            val list = bean.data
            mViewModel.saveAllWords(list)
            initAdapter(list)
        }




    }

    private fun initView() {


    }

    private fun initAdapter(mList:List<WordList>) {


        val adapter = WordAdapter(mList, object : WordAdapter.OnItemClickListener {
            override fun invoke(wordBean: WordList) {

                Observable.just<WordList>(wordBean)
                        .subscribeOn(Schedulers.io())
                        .subscribe { s ->
                            if (wordBean.collect) {
                                mViewModel.unCollectWord(s)
                                mViewModel.updateWords(s)
                            } else {
                                mViewModel.collectWord(s)
                                mViewModel.updateWords(s)
                            }
                        }
            }
        })
        rv_main.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_main.adapter = adapter
    }


}