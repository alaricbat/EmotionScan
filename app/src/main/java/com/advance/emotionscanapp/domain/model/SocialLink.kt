package com.advance.emotionscanapp.domain.model

sealed class SocialLink {
    abstract val url: String
    abstract val platformName: String

    data class Github(
        override val url: String
    ): SocialLink() {
        override val platformName: String = "GitHub"
    }

    data class LinkedIn(
        override val url: String
    ): SocialLink() {
        override val platformName: String = "LinkedIn"
    }

}