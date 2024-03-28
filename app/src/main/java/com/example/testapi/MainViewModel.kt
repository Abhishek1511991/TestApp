package com.example.testapi

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapi.api.RetroObj
import com.example.testapi.model.Hero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel: ViewModel() {

    //this is the data that we will fetch asynchronously
    val heroList  =  MutableStateFlow(mutableStateListOf<Hero>())

    fun getHerosList()
    {
        viewModelScope.launch(Dispatchers.IO) {

            try {
               // withContext(Dispatchers.IO) {
                    val api = RetroObj.getInterface()
                   val response = api.getHeroes()
                if(response.isSuccessful){
                    response.body()?.let {
                        heroList.value = it.toMutableStateList()
                    }

                }

               // }
            } catch (e: Exception) {
                //...
            }



        }
    }
}