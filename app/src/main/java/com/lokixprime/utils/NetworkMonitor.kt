package com.lokixprime.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NetworkMonitor(private val context: Context) {
    val isConnected: Flow<Boolean> = callbackFlow {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val validNetworks = mutableSetOf<Network>()

        fun checkValidNetworks() {
            trySend(validNetworks.isNotEmpty())
        }

        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                val capabilities = connectivityManager.getNetworkCapabilities(network)
                if (capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true) {
                    validNetworks.add(network)
                    checkValidNetworks()
                }
            }

            override fun onLost(network: Network) {
                validNetworks.remove(network)
                checkValidNetworks()
            }

            override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
                if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    validNetworks.add(network)
                } else {
                    validNetworks.remove(network)
                }
                checkValidNetworks()
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(request, callback)

        // Initial state
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        if (capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true) {
            if (activeNetwork != null) {
                validNetworks.add(activeNetwork)
            }
        }
        checkValidNetworks()

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }
}
