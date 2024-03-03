package com.chopecard.ui.utils

class CurrentActivityTracker {
    companion object {
        private var currentActivity: ActivityName = ActivityName.LoginRegisterActivity

        fun setCurrentActivity(activity: ActivityName) {
            currentActivity = activity
        }

        fun getCurrentActivity(): ActivityName {
            return currentActivity
        }
    }
}