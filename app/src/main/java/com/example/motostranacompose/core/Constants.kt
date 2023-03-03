package com.example.motostranacompose.core

object Constants {
    //App
    const val TAG = "MotoStrana"

    //Collection References
    const val FIRESTORE_NODE_USERS = "users"
    const val FIRESTORE_NODE_DASHBOARD = "dashboard"

    //User fields
    const val FIELD_USER_UID = "uid"
    const val FIELD_USER_DISPLAY_NAME = "displayName"
    const val FIELD_USER_PHOTO_URL = "photoUrl"
    const val FIELD_USER_CREATED_AT = "createdAt"
    const val FIELD_USER_LATLNG = "latlng"
    const val FIELD_USER_STATUS = "OnlineStatus"

    //Dashboard fields
    const val FIELD_DASHBOARD_KEY = "key"
    const val FIELD_DASHBOARD_TIMESTAMP = "timestamp"
    const val FIELD_DASHBOARD_UID = "uid"
    const val FIELD_DASHBOARD_TITLE = "title"
    const val FIELD_DASHBOARD_HEADER = "header"
    const val FIELD_DASHBOARD_BODY = "body"
    const val FIELD_DASHBOARD_IMAGE = "image"
    const val FIELD_DASHBOARD_LIKES = "likes"
    const val FIELD_DASHBOARD_TYPE = "type"

    //Names
    const val SIGN_IN_REQUEST = "signInRequest"
    const val SIGN_UP_REQUEST = "signUpRequest"
}