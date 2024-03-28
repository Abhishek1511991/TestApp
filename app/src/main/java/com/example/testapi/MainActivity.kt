package com.example.testapi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.testapi.model.Hero
import com.example.testapi.ui.theme.TestApiTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val mainViewModel by inject<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    HerosListingScreen(viewModel = mainViewModel)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getHerosList()
    }
}



@OptIn(ExperimentalGlideComposeApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HerosListingScreen(viewModel: MainViewModel) {
    val list = viewModel.heroList.collectAsState().value
    if(list.isNotEmpty()){
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(items = list, key = { index: Int, item: Hero -> item.publisher + index }){ index, item ->

                Column(modifier = Modifier.padding(20.dp)) {
                    Text(text = item.name, modifier = Modifier
                        .fillMaxWidth().wrapContentHeight())
                    GlideImage(
                        model = item.imageurl,
                        contentDescription = item.realname,
                        modifier = Modifier.fillMaxWidth().height(200.dp),
                    )


                }

            }
        }
    }
    else {
        ProgressBar()
    }


}

@Composable
fun ProgressBar() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}
