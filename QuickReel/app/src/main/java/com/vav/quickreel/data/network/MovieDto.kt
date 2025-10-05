package com.vav.quickreel.data.network

import com.squareup.moshi.Json

data class MovieListResponse(
    @Json(name = "results") val movies: List<MovieDto>
)

data class MovieDto(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "backdrop_path") val backdropPath: String,
    @Json(name = "vote_average") val rating: Float
)