package com.example.appscriptapi.nav

sealed class Routes(val route: String) {
    object Login : Routes("homeview")
    object Register : Routes("mainview")
    object AnimeList: Routes("favoritos")
    object PostForm : Routes("postForm")
    object ReviewFormView : Routes("reviewFormView")
}