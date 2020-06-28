package com.justin.ferron.bijenkorf.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.justin.ferron.bijenkorf.internal.NoConnectivityExceptionHandler
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptorImplementation(
    context: Context
) : ConnectivityInterceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isUserOnline())
            throw NoConnectivityExceptionHandler()
        return chain.proceed(chain.request())
    }

    //This function is added to see if the user has an internet connection
    private fun isUserOnline(): Boolean {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}