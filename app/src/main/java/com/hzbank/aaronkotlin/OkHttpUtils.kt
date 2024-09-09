package com.hzbank.aaronkotlin

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

class OkHttpUtils private constructor(){

    suspend inline fun <reified T> requestPost(apiName: String,
                    params: JsonObject): T?{

        try {

            val request = Request.Builder()
                .url(rootUrl + apiName)
                .post(params.toString().toRequestBody("application/json".toMediaType()))
                .build()

            val response = getOkClient().newCall(request)!!.execute();

            response?.let {
                it.body?.let {

                    it.string()?.let {


                        //解析
                        val isJsonObject = JsonParser().parse(it).isJsonObject();
                        val isJsonArray = JsonParser().parse(it).isJsonArray();

                        val gson = Gson()


                        if(isJsonObject){

                            return gson.fromJson(it, T::class.java)

                        } else if(isJsonArray){

                            return gson.fromJson<T>(it, object :TypeToken<T>(){}.type)

                        }

                        return null


                    }

                }


            }

        }catch (e: Exception){
            (e?.message?:"请求异常").showLog()
            e.printStackTrace()
        }

        return null
    }


    companion object{

        private var okInstance: OkHttpUtils? = null

        private var okClient: OkHttpClient? = null

        fun getInstance(): OkHttpUtils{
            return okInstance?: synchronized(this){
                okInstance?:OkHttpUtils().also { okInstance = it }
            }

        }

        const val rootUrl = "https://uapis.cn/"

        fun getOkClient(): OkHttpClient{

            return okClient?: synchronized(this){
                okClient?: OkHttpClient.Builder()
                    .cache(Cache( File(BaseApplication.app.cacheDir.absolutePath+ File.separator + "cache" + File.separator), 10*1024*1024))
                    .callTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .connectionPool(ConnectionPool(32, 5, TimeUnit.MINUTES))
                    .retryOnConnectionFailure(true)
                    .addNetworkInterceptor(HttpLoggingInterceptor( HttpLoggingInterceptor.Logger() {

                        ("接口请求=="+it).showLog()

                    }).setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build().also { okClient = it }
            }

        }

    }

}