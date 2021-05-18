package app.test.uzGardens.utils

import androidx.navigation.NavDirections

sealed class NavCommand {
    object BACK : NavCommand()
    object HOME : NavCommand()
    data class BackTo(val destinationId: Int, val inclusive: Boolean) : NavCommand()
    data class To(val direction: NavDirections) : NavCommand()
}