package com.apodaca.clean_architecture.domain.usecase

import android.util.Log
import com.apodaca.clean_architecture.data.database.entities.UserEntity
import com.apodaca.clean_architecture.data.repository.LocalUserRepository
import timber.log.Timber
import javax.inject.Inject

class AddUserToDatabaseUseCase @Inject constructor(
    private val localUserRepository: LocalUserRepository
) {
    suspend operator fun invoke(userEntity: UserEntity) {
        Timber.d("invoke: $userEntity")
        localUserRepository.upsert(userEntity)
    }
}