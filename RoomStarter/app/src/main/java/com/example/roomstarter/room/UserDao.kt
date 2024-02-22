package com.example.roomstarter.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<User>>

    @Query("SELECT * FROM user")
    fun queryAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE uid in (:userIds)")
    fun loadAllByIdsFlow(userIds: List<Int>): Flow<List<User>>

    @Query("SELECT * FROM user WHERE uid = :userId")
    fun queryByUserId(userId: Int): Flow<User?>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: User)

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("DELETE FROM user WHERE uid = :uid")
    fun deleteByUid(uid: Int)

    @Query("DELETE FROM user")
    fun deleteAll()
}