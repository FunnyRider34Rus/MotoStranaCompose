package com.example.motostranacompose.ui

import androidx.lifecycle.ViewModel
import com.example.motostranacompose.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: AuthRepository) :
    ViewModel() {
    val isUserAuthenticated get() = repository.isUserAuthenticatedInFirebase
}