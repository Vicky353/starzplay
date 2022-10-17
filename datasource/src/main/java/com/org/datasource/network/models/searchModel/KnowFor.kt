package com.org.datasource.network.models.searchModel

import com.google.gson.annotations.SerializedName

data class KnowFor (


    @SerializedName("backdrop_path"     ) var backdropPath     : String?        = null,
    @SerializedName("original_title"    ) var originalTitle    : String?        = null,
    @SerializedName("overview"          ) var overview         : String?        = null,
    @SerializedName("poster_path"       ) var posterPath       : String?        = null,
    @SerializedName("title"             ) var title            : String?        = null,
    @SerializedName("profile_path"      ) var profilePath       : String?        = null,

)