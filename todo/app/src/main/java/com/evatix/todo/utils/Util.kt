package com.evatix.todo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Patterns
import java.security.MessageDigest
import java.util.regex.Pattern

class Util {


    companion object {

        fun isValidEmail(email: String): Boolean {

            val pattern: Pattern = Patterns.EMAIL_ADDRESS
            return pattern.matcher(email).matches()
        }

        fun sha256(base: String): String {
            return try {
                val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
                val hash: ByteArray = digest.digest(base.toByteArray(charset("UTF-8")))
                val hexString = StringBuffer()
                for (i in hash.indices) {
                    val hex = Integer.toHexString(0xff and hash[i].toInt())
                    if (hex.length == 1) hexString.append('0')
                    hexString.append(hex)
                }
                hexString.toString()
            } catch (ex: Exception) {
                throw RuntimeException(ex)
            }
        }

        fun isOnline(context: Context?): Boolean {
            val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val n = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                cm.activeNetwork
            } else {
                TODO("VERSION.SDK_INT < M")
            }
            if (n != null) {
                val nc = cm.getNetworkCapabilities(n)
                //It will check for both wifi and cellular network
                return nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                )
            }
            return false
        }

    }



}