package com.bawei.xushanshan.utils;

import android.content.Context;
import android.net.Network;

import com.bawei.xushanshan.api.ApiServer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * FileName: RetrofitUtil
 * Author: 徐姗姗
 * Date: 2019/11/5 10:18
 */
public class RetrofitUtil {
    public static ApiServer apiServer;
    private OkHttpClient interceptor;
    private HttpLoggingInterceptor httpLoggingInterceptor;
    private Context context;

    public RetrofitUtil() {
    }

    public static ApiServer getApiServer() {
        if (apiServer == null) {
            synchronized (RetrofitUtil.class) {
                if (apiServer == null) {
                    apiServer = new RetrofitUtil().getRetrofit();
                }
            }
        }
        return apiServer;
    }


    private ApiServer getRetrofit() {
        ApiServer apiServer = initRetrofit(initOk()).create(ApiServer.class);
        return apiServer;
    }

    private Retrofit initRetrofit(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.17.8.100/small/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }


    private OkHttpClient initOk() {
        //拦截器
        httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        interceptor = new OkHttpClient.Builder()
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .callTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Response response = chain.proceed(request);
                        return response;
                    }
                }).build();
        return interceptor;
    }
}
