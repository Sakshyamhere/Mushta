package com.example.mushta.data

    open class DestinationScreen(var route:String) {
        object Home : DestinationScreen("home")
        object Recipe : DestinationScreen("recipe/{id}")
        object Search : DestinationScreen("search/{query}")
    }
