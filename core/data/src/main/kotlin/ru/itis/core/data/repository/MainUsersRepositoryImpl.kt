package ru.itis.core.data.repository

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import ru.itis.core.database_constants.DatabaseConstants.CHILD_INTERESTS
import ru.itis.core.database_constants.DatabaseConstants.NODE_USERS
import ru.itis.core.domain.models.User
import ru.itis.core.domain.repository.MainUsersRepository
import javax.inject.Inject

/**
 * Created by Iskandar on 02.06.2022.
 */

internal class MainUsersRepositoryImpl @Inject constructor(
    private val databaseReference: DatabaseReference
) : MainUsersRepository {

    override suspend fun getAllUsersByInterests(): List<User> {
        val listOfUsers = mutableListOf<User>()
        val query = databaseReference.child(NODE_USERS).orderByChild(CHILD_INTERESTS).equalTo("")

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    snapshot.children.forEach {
                        Log.e("DEBUG", "users: ${it.children}")
                        listOfUsers.add(it.value as User)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return listOfUsers
    }
}
