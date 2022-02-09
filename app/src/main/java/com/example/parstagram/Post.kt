package com.example.parstagram

import com.parse.*

// Description : String
// Image : File
// User : User
@ParseClassName("Post")
class Post: ParseObject() {

    fun getDescription() :String? {
        return getString(KEY_DESCRIPTION)
    }

    fun getImage(): ParseFile? {
        return getParseFile(KEY_IMAGE)
    }

    fun getUser(): ParseUser? {
        return getParseUser(KEY_USER)
    }

    fun setDescription(description: String) {
        put(KEY_DESCRIPTION, description)
    }

    fun setImage(parsefile: ParseFile) {
        put(KEY_IMAGE, parsefile)
    }

    fun setUser(user: ParseUser) {
        put(KEY_USER, user)
    }



    companion object {
        const val KEY_DESCRIPTION = "description"
        const val KEY_IMAGE = "image"
        const val KEY_USER = "user"
    }
}