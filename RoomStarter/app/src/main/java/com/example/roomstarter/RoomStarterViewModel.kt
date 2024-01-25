package com.example.roomstarter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomstarter.room.User
import com.example.roomstarter.room.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomStarterViewModel @Inject constructor(
    private val userDao: UserDao
): ViewModel() {
    private val logTag = "RoomStarterViewModel"
    private var curUserIndex = DEFAULT_INDEX

    private val _testDistinctData = MutableSharedFlow<Int>()
    private val testDistinctData: Flow<Int>
        get() = _testDistinctData.distinctUntilChanged()

    val userData = userDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = listOf()
        )

    val selectedData = userDao.loadAllById(3)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val selectedMultiData = userDao.loadAllByIdsFlow(2, 3)
        .distinctUntilChanged(areEquivalent = {
            old, new ->
            if (old.size != new.size) {
                return@distinctUntilChanged false
            }

            for (i in old.indices) {
                if (old[i] != new[i]) {
                    return@distinctUntilChanged false
                }
            }

            return@distinctUntilChanged true
        })

    init {
        viewModelScope.launch {
            testDistinctData.collect { value ->
                Log.d(logTag, "collectDistinctData: $value")
            }
        }
    }

    fun selectedMultiDataDistinct() = userDao.loadAllByIdsFlow(2, 3)
        .distinctUntilChanged(areEquivalent = { old, new ->
//            if (old.size != new.size) {
//                return@distinctUntilChanged false
//            }
//
//            for (i in old.indices) {
//                if (old[i] != new[i]) {
//                    return@distinctUntilChanged false
//                }
//            }

            return@distinctUntilChanged true
        })

    fun insertData() {
        viewModelScope.launch(Dispatchers.IO) {
            curUserIndex++
            userDao.insert(User(curUserIndex, curUserIndex.toString(), curUserIndex.toString()))
        }
    }

    fun updateUser0() {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.update(User(0, "$curUserIndex", "$curUserIndex"))
        }
    }

    fun updateUser3() {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.update(User(3, "$curUserIndex", "$curUserIndex"))
        }
    }

    fun deleteLastData() {
        viewModelScope.launch(Dispatchers.IO) {
            if (curUserIndex != DEFAULT_INDEX) {
                userDao.deleteByUid(curUserIndex)
                curUserIndex--
            }
        }
    }

    fun startTestDistinctData() {
        viewModelScope.launch(Dispatchers.IO) {
            val random = (0..2).random()
            Log.d(logTag, "emitDistinctData: $random")
            _testDistinctData.emit(random)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.deleteAll()
            curUserIndex = DEFAULT_INDEX
        }
    }

    companion object {
        private const val DEFAULT_INDEX = -1
    }
}