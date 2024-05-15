package com.example.matuleme.objects

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest

object Client {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://rqotastukkhgkxberaht.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InJxb3Rhc3R1a2toZ2t4YmVyYWh0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTEyMDkyNzUsImV4cCI6MjAyNjc4NTI3NX0.0psgkOniteZrmybKUOC7xm2bvFTESLafhbZU2wpFvXI"
    ) {
        install(Auth) {
            scheme = "com.example.matuleme"
            host = "login-callback"
            alwaysAutoRefresh = true
        }
        install(Postgrest)
    }
}