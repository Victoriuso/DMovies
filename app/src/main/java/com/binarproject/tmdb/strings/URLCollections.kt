package com.binarproject.tmdb.strings

class URLCollections {

    companion object {
        const val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0ZWNkYTcwZTE2MjVmZmZhNDEzODBkNzFlZGI4NzJhZCIsInN1YiI6IjVlZjY0NTBiZTkzZTk1MDAzNWI0MTkwYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.48mlighmuU4aSvOc-VpnmF-fBGbkVbw7pO1OSuKN3ao"

        const val SERVER = "https://api.themoviedb.org/3/"
        const val IMAGE_SERVER = "https://image.tmdb.org/t/p/"

        const val GET_MOVIE = "movie";
        const val GET_TRAILERS = "videos"
        const val GET_REVIEWS = "reviews"
        const val GET_POPULAR_MOVIE = "popular"
        const val GET_TOP_RATED_MOVIE = "top_rated"
        const val GET_GENRES = "genre/movie/list"
        const val GET_DISCOVERED_MOVIE = "discover/movie"
    }
}