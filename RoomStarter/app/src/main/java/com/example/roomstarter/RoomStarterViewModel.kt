package com.example.roomstarter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomstarter.room.User
import com.example.roomstarter.room.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomStarterViewModel @Inject constructor(
    private val userDao: UserDao
): ViewModel() {
    private val logTag = "RoomStarterViewModel"
    private var curUserIndex = DEFAULT_INDEX

    private val _selectedUserData = MutableStateFlow<List<User>>(listOf())
    val selectedUserData: Flow<List<User>>
        get() = _selectedUserData

    val userData = userDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = listOf()
        )

    val selectedData = userDao.queryByUserId(3)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    fun initSelectedUserData(userId: Int) {
        Log.d(logTag, "initSelectedUserData: $userId")
        viewModelScope.launch {
            userDao.queryByUserId(userId)
                .map { listOf(it) }
                .flowOn(Dispatchers.IO)
                .collect { _selectedUserData.emit(it) }
        }
    }

    fun initMultiSelectedUserData(vararg userIds: Int) {
        Log.d(logTag, "initMultiSelectedUserData: $userIds")
        viewModelScope.launch {
            userDao.loadAllByIdsFlow(userIds.toList())
                .distinctUntilChangedUserData()
                .flowOn(Dispatchers.IO)
                .collect {
                    _selectedUserData.emit(it)
                }
        }
    }

    fun insertData() {
        viewModelScope.launch(Dispatchers.IO) {
            curUserIndex++
            userDao.insert(User(curUserIndex, curUserIndex.toString(), curUserIndex.toString()))
        }
    }

    fun updateUser(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.update(User(userId, "$curUserIndex", "$curUserIndex"))
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

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.deleteAll()
            curUserIndex = DEFAULT_INDEX
        }
    }

    companion object {
        private const val DEFAULT_INDEX = -1
        private fun Flow<List<User>>.distinctUntilChangedUserData() =
            distinctUntilChanged(areEquivalent = {
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
    }
}