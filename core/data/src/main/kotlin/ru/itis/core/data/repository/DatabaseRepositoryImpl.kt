package ru.itis.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import ru.itis.core.database_constants.DatabaseConstants.NODE_USERS
import ru.itis.core.domain.models.User
import ru.itis.core.domain.repository.DatabaseRepository
import javax.inject.Inject

/**
 * Created by Iskandar on 10.04.2022.
 */

class DatabaseRepositoryImpl @Inject constructor(
    private val databaseReference: DatabaseReference,
    private val firebaseAuth: FirebaseAuth
) : DatabaseRepository {

    override suspend fun addUser(user: User) {
        databaseReference.child(NODE_USERS).child(user.id!!).updateChildren(user.toMap())
    }

    override suspend fun getUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUserId(): String? {
        return firebaseAuth.currentUser?.uid
    }
}
