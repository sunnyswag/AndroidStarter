package com.example.kotlinstarter.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

class CoroutineViewModel: ViewModel() {

    private val test = "sfd"

    suspend fun getBanner() {
        coroutineScope {
            launch {

            }
        }

        runBlocking {

        }

        GlobalScope.launch {

        }
    }
}