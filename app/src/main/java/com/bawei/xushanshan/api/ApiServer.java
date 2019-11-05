package com.bawei.xushanshan.api;

import com.bawei.xushanshan.Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * FileName: ApiServer
 * Author: 徐姗姗
 * Date: 2019/11/5 10:18
 */
public interface ApiServer {
    @GET("commodity/v1/bannerShow")
    Observable<Bean> shou();
}
