package com.example.testapi.di

import com.example.testapi.MainViewModel
import org.koin.dsl.module

val appModule = module {
    single { MainViewModel() }
}