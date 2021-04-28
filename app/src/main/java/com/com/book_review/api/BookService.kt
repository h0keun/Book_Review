package com.com.book_review.api

import com.com.book_review.model.BestSellerDto
import com.com.book_review.model.SearchBookDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    // get : 데이터를 요청했을 때 서버에서 반환해주는 http형식
    // post : 보통은 요청을할 때, 새로운 데이터를 만들때(create 할때)
    // get 형식은 url에 다 넣어서 요청을 하는방식이고,
    // post는 데이터가 좀더 크기 때문에 http body에 넣어서 전달하는 방식으로 사용
    // 본 프로젝트에서는 데이터를 가져오는것만 필요하기 때문에 get 사용

    @GET("/api/search.api?output=json")
    fun getBooksByName(
        @Query("key") apiKey: String,
        @Query("query") keyword: String
    ): Call<SearchBookDto>

    @GET("/api/bestSeller.api?output=json&categoryId=100")
    fun getBestSellerBooks(
        @Query("key") apiKey: String
    ): Call<BestSellerDto>
}