package com.bw.movie.model.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author hyy
 * @date 2018/11/2
 */
public class HttpUtils {


    public final Api api;

   /* public static HttpUtils getInstance() {
        Context context = null;

        if (context==null){

            App app = (App) App.getApp();
            context = app.getApplicationContext();
        }
        if (ourInstance == null) {
            synchronized (HttpUtils.class) {
                if (ourInstance == null) {
                    ourInstance = new HttpUtils(context);
                }
            }
        }
        return ourInstance;
    }*/

    private HttpUtils() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                /*.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        SharedPreferences user = context.getSharedPreferences("user", Context.MODE_PRIVATE);
                        int userId = user.getInt("userId", 0);
                        String userid = String.valueOf(userId);
                        String sessionId = user.getString("sessionId", "");

                        Log.i("userid", "intercept: userId==="+userid);
                        Log.i("sessionId", "intercept: sessionId==="+sessionId);

                        Request build = chain.request().newBuilder()
                                .addHeader("userId", userid)
                                .addHeader("sessionId", sessionId)
                                .build();

                        return chain.proceed(build);
                    }
                })*/
                .addNetworkInterceptor(new LoggingInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();

        api = retrofit.create(Api.class);
    }

    private static class GetHttpUtilsInstance {
        private static HttpUtils httpUtils = new HttpUtils();
    }

    /**
     * 拦截器
     */
    class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            //logger.info(String.format("Sending request %s on %s%n%s",
            //request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            //logger.info(String.format("Received response for %s in %.1fms%n%s",
            //response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }

    public static HttpUtils getInstance() {
        return GetHttpUtilsInstance.httpUtils;
    }
}
