package de.shanox.android.lifecalendar.utils

import androidx.compose.runtime.mutableStateOf

class ConfigDialogManager {

    companion object {
        public var __instance: ConfigDialogManager = ConfigDialogManager()

        fun getInstance(): ConfigDialogManager {
            if(ConfigDialogManager.__instance == null) {
                ConfigDialogManager.__instance = ConfigDialogManager()
            }

            return __instance
        }
    }

    public var isVisible = mutableStateOf(false)

}
