package test

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


fun main() {
    val host = "https://dapi.kakao.com/"
    val path = "v2/search/blog"
    val query = "?query=맛집"
    val apiUrl = "$host$path$query"

    val requestHeaders = HashMap<String, String>()
    requestHeaders["Authorization"] = "KakaoAK ed976d5c0eb8171b721bd41facdf51f4"
    val responseBody = get(apiUrl, requestHeaders)
    println(responseBody)

}


private fun get(apiUrl: String, requestHeaders: HashMap<String, String>): String{

    val url = URL(apiUrl)
    val connection = url.openConnection() as HttpURLConnection // 실제로 connection을 여기서 맺지 않는다.
    //connection.connect() // -> connect()함수 호출시 실제로 connection request 가 이뤄진다.

    return try {
        connection.requestMethod = "GET"

        requestHeaders.forEach { (key, value) ->
            connection.setRequestProperty(key, value)
        }
        connection.headerFields.entries.forEach {
            println("${it.key}: ${it.value}")
        }
        println()
        if (connection.responseCode == HttpURLConnection.HTTP_OK){
            readBody(connection.inputStream)
        }else{
            readBody(connection.errorStream)
        }
    } catch (e: IOException){
        throw RuntimeException("API request and response fail")
    } finally {
        connection.disconnect()
    }

}

private fun readBody(body: InputStream): String {
    val streamReader = InputStreamReader(body)
    try {
        BufferedReader(streamReader).use { lineReader ->
            val responseBody = StringBuilder()
            var line: String?
            while (lineReader.readLine().also { line = it } != null) {
                responseBody.append(line)
            }
            return responseBody.toString()
        }
    } catch (e: IOException) {
        throw RuntimeException("API 응답을 읽는데 실패했습니다.", e)
    }
}