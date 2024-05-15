package com.example.matuleme.objects

import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.OTP
import io.github.jan.supabase.postgrest.from

object Requests {

    suspend fun signIn(emailUser: String, passUser: String) {
        Client.supabase.auth.signInWith(Email) {
            email = emailUser
            password = passUser
        }
    }

    suspend fun signOut(emailUser: String, passUser: String) {
        Client.supabase.auth.signUpWith(Email) {
            email = emailUser
            password = passUser
        }
    }

    suspend fun sendOtp(emailUser: String) {
        Client.supabase.auth.signInWith(OTP) {
            email = emailUser
            createUser = false
        }
    }

    suspend fun checkToken(emailUser: String, tokenUser: String) {
        Client.supabase.auth.verifyEmailOtp(
            type = OtpType.Email.MAGIC_LINK,
            email = emailUser,
            token = tokenUser)
    }

    suspend fun updatePass(newPass: String) {
        Client.supabase.auth.modifyUser(true) {
            password = newPass
        }
    }


}