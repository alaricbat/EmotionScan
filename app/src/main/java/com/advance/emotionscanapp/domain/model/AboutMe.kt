package com.advance.emotionscanapp.domain.model

data class AboutMe(
    val name: String,
    val role: String,
    val description: String,
    val skills: List<String>
) {

    data class PersonalInfo(
        val name: String,
        val avatar: String,
        val bio: String
    )

    data class ProfessionalInfo(
        val role: String,
        val experience: String,
        val skills: List<Skill>
    )

    data class Skill(
        val name: String,
        val level: SkillLevel,
        val category: SkillCategory
    )

    enum class SkillLevel { BEGINNER, INTERMEDIATE, ADVANCE, EXPERT }
    enum class SkillCategory { TECHNICAL, SOFT, LANGUAGE }

}
