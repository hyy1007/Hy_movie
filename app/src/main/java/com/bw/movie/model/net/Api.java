package com.bw.movie.model.net;

import com.bw.movie.model.bean.AllCinemaBean;
import com.bw.movie.model.bean.CinemaDetailsBean;
import com.bw.movie.model.bean.CinemaDetailsListBean;
import com.bw.movie.model.bean.CinemaReviewBean;
import com.bw.movie.model.bean.DetailsBean;
import com.bw.movie.model.bean.FindMovieByCinemaIdBean;
import com.bw.movie.model.bean.FollowMovieBean;
import com.bw.movie.model.bean.FollowMovieListBean;
import com.bw.movie.model.bean.HotMovieBean;
import com.bw.movie.model.bean.LoginBean;
import com.bw.movie.model.bean.NearbyCinemaBean;
import com.bw.movie.model.bean.NowMovieBean;
import com.bw.movie.model.bean.RegisterBean;
import com.bw.movie.model.bean.ReviewListBean;
import com.bw.movie.model.bean.UpLoadHeadPicBean;
import com.bw.movie.model.bean.WXBean;
import com.bw.movie.model.bean.WillMovieBean;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author hyy
 * @date 2018/11/2
 */
public interface Api {

    /**
     * 查询热门电影列表
     */
    @GET("movie/v1/findHotMovieList?page=1&count=7")
    Observable<HotMovieBean> hotMovie();

    /**
     * 正在热映
     */
    @GET("movie/v1/findReleaseMovieList?page=1&count=10")
    Observable<NowMovieBean> nowMovie();

    /**
     * 即将上映
     */
    @GET("movie/v1/findComingSoonMovieList?page=1&count=10")
    Observable<WillMovieBean> willMovie();

    /**
     * 电影详情
     *
     * @param movieId
     */
    @GET("movie/v1/findMoviesDetail")
    Observable<DetailsBean> details(@Query("movieId") String movieId);

    /**
     * 附近影院
     */
    @GET("cinema/v1/findRecommendCinemas?page=1&count=10")
    Observable<NearbyCinemaBean> nearby();

    /**
     * 全部影院
     */
    @GET("cinema/v1/findAllCinemas?page=1&count=10")
    Observable<AllCinemaBean> allCinema();

    /**
     * 影院详情
     */
    @GET("cinema/v1/findCinemaInfo")
    Observable<CinemaDetailsBean> cinemaDetails(@Query("cinemaId") String cinemaId);

    /**
     * 根据电影ID和影院ID查询电影排期列表
     */
    @GET("movie/v1/findMovieScheduleList")
    Observable<CinemaDetailsListBean> cinemaDetailsList(@Query("cinemasId") String cinemasId, @Query("movieId") String movieId);

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("user/v1/registerUser")
    Observable<RegisterBean> register(@FieldMap Map<String, String> map);

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/v1/login")
    Observable<LoginBean> login(@FieldMap Map<String, String> map);

    /**
     * 查询影片评论
     */
    @GET("movie/v1/findAllMovieComment?page=1&count=5")
    Observable<ReviewListBean> reviewList(@Query("movieId") String movieId);

    /**
     * 影院评论列表
     */
    @GET("cinema/v1/findAllCinemaComment?page=2&count=4")
    Observable<CinemaReviewBean> cinemaReview(@Query("cinemaId") String cinemaId);

    /**
     * 根据影院ID查询该影院当前排期的电影列表
     */
    @GET("movie/v1/findMovieListByCinemaId")
    Observable<FindMovieByCinemaIdBean> findMovieByCinemaId(@Query("cinemaId") String cinemaId);

    /**
     * 关注电影
     */
    @GET("movie/v1/verify/followmovie")
    Observable<FollowMovieBean> followMovie(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("movieId") String movieId);

    /**
     * 查询用户关注的影片列表
     */
    @GET("movie/v1/verify/findMoviePageList?page=1&count=10")
    Observable<FollowMovieListBean> followMovieList(@Header("userId") String userId, @Header("sessionId") String sessionId);

    /**
     * 微信登录
     */
    @FormUrlEncoded
    @POST("user/v1/weChatBindingLogin")
    Observable<WXBean> wxLogin(@Field("code")String code);

    /**
     * 头像上传
     */
    @FormUrlEncoded
    @POST("user/v1/verify/uploadHeadPic")
    Observable<UpLoadHeadPicBean> upLoadHeadPic(@Header("userId")String userId,@Header("sessionId")String sessionId,@Field("image")File image);

}
