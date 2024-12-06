package com.example.firebasetestapplication.core.presentation

sealed class Route(val route: String) {
    data object Registration : Route("Registration")
    data object Login : Route("Login")
}