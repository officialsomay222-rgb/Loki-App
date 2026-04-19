package com.lokixprime.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

data class ChatRequest(
    val message: String,
    val history: List<MessageHistory>,
    val mode: String,
    val systemInstruction: String,
    val temperature: Float,
    val thinkingMode: Boolean = false,
    val searchGrounding: Boolean = false
)

data class MessageHistory(
    val role: String,
    val parts: List<MessagePart>
)

data class MessagePart(
    val text: String
)

data class ChatResponse(
    val text: String,
    val reasoning: String?,
    val error: String?
)

interface ApiService {
    @POST("/api/chat")
    suspend fun generateChatResponse(@Body request: ChatRequest): ChatResponse
}

object ApiClient {
    // Assuming backend runs on 10.0.2.2 (Android Emulator loopback for localhost)
    private const val BASE_URL = "http://10.0.2.2:3000"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
