package com.kunsan.nihon.app.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kunsan.nihon.WordViewModel
import me.bakumon.moneykeeper.Injection

/**
 * Created by moge on 2018/11/2.
 */
abstract class BaseActivity<VM : ViewModel> : AppCompatActivity(){

    lateinit var mViewModel: VM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        mViewModel = createViewModel
    }

    inline fun <reified T : BaseViewModel> getViewModel(): T {
        val viewModelFactory = Injection.provideViewModelFactory()
        return ViewModelProviders.of(this, viewModelFactory).get(T::class.java)
    }

    protected abstract val layoutId: Int

    protected abstract val createViewModel:VM
}