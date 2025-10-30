package com.advance.emotionscanapp.data.remote

import com.google.gson.annotations.SerializedName

data class AboutDto(

    @SerializedName("personal_info")
    val personalInfo: PersonalInfoDto,

    @SerializedName("professional_info")
    val professionalInfoDto: ProfessionalInfoDto,

    @SerializedName("social_links")
    val socialLinks: List<SocialLinkDto>

) {

    data class PersonalInfoDto(
        @SerializedName("name")
        val name: String,

        @SerializedName("avatar")
        val avatar: String,

        @SerializedName("bio")
        val bio: String,

        @SerializedName("location")
        val location: String? = null,

        @SerializedName("phone")
        val phone: String? = null
    )

    data class ProfessionalInfoDto(

        @SerializedName("role")
        val role: String,

        @SerializedName("experience")
        val experience: String,

        @SerializedName("company")
        val company: String? = null,

        @SerializedName("skills")
        val skills: List<SkillDto>

    )

    data class SkillDto(

        @SerializedName("name")
        val name: String,

        @SerializedName("level")
        val level: String,

        @SerializedName("category")
        val category: String,

        @SerializedName("year_of_experience")
        val yearsOfExperience: Int? = 0

    )

    data class SocialLinkDto(

        @SerializedName("platform")
        val platform: String,

        @SerializedName("url")
        val url: String,

        @SerializedName("username")
        val username: String? = null

    )

}