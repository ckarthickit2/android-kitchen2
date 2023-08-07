package me.kartdroid.androidkitchen2.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.MutableCreationExtras
import me.kartdroid.androidkitchen2.drawover.FloatingWindowService


public inline fun <reified VM : ViewModel> FloatingWindowService.viewModels(
    noinline factoryProducer: () -> ViewModelProvider.Factory
): Lazy<VM> {
    return ViewModelLazy(
        VM::class,
        { viewModelStore },
        factoryProducer,
        { MutableCreationExtras() }
    )
}