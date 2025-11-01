package com.advance.emotionscanapp.data.mapper

import com.advance.emotionscanapp.data.local.AboutEntity
import com.advance.emotionscanapp.data.remote.AboutDto
import com.advance.emotionscanapp.domain.model.AboutMe
import com.advance.emotionscanapp.domain.model.SocialLink
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class AboutMapper @Inject constructor(
    private val gson: Gson
){

    fun mapToDomain(dto: AboutDto): AboutMe {
        return AboutMe(
            personalInfo = AboutMe.PersonalInfo(
                name = dto.personalInfo.name,
                avatar = dto.personalInfo.avatar,
                bio = dto.personalInfo.bio
            ),
            professionalInfo = AboutMe.ProfessionalInfo(
                role = dto.professionalInfoDto.role,
                experience = dto.professionalInfoDto.experience,
                skills = dto.professionalInfoDto.skills.map { skillDto ->
                    AboutMe.Skill(
                        name = skillDto.name,
                        level = mapSkillLevel(skillDto.level),
                        category = mapSkillCategory(skillDto.category),
                    )
                }
            ),
            socialLinks = dto.socialLinks.map { socialLinkDto ->
                when (socialLinkDto.platform.uppercase()) {
                    "GITHUB" -> SocialLink.Github(socialLinkDto.url)
                    "LINKEDIN" -> SocialLink.LinkedIn(socialLinkDto.url)
                    else -> SocialLink.Other(socialLinkDto.url)
                }
            }
        )
    }

    fun mapToDto(domain: AboutMe): AboutDto {
        return AboutDto(
            personalInfo = AboutDto.PersonalInfoDto(
                name = domain.personalInfo.name,
                avatar = domain.personalInfo.avatar,
                bio = domain.personalInfo.bio,
            ),
            professionalInfoDto = AboutDto.ProfessionalInfoDto(
                role = domain.professionalInfo.role,
                experience = domain.professionalInfo.experience,
                skills = domain.professionalInfo.skills.map { it ->
                    AboutDto.SkillDto(
                        name = it.name,
                        level = it.level.name,
                        category = it.category.name
                    )
                }
            ),
            socialLinks = domain.socialLinks.map { it ->
                AboutDto.SocialLinkDto(
                    platform = it.platformName,
                    url = it.url
                )
            }
        )
    }

    fun mapToEntity(dto: AboutDto): AboutEntity {
        return AboutEntity(
            name = dto.personalInfo.name,
            avatar = dto.personalInfo.avatar,
            bio = dto.personalInfo.bio,
            location = dto.personalInfo.location,
            phone = dto.personalInfo.phone,
            role = dto.professionalInfoDto.role,
            experience = dto.professionalInfoDto.experience,
            company = dto.professionalInfoDto.company,
            skillsJson = gson.toJson(dto.professionalInfoDto.skills),
            socialLinksJson = gson.toJson(dto.socialLinks)
        )
    }

    fun mapToDomain(entity: AboutEntity): AboutMe {

        val skillDtos: List<AboutDto.SkillDto> = gson.fromJson(
            entity.skillsJson,
            object : TypeToken<List<AboutDto.SkillDto>>() {}.type
        )

        val socialLinkDtos: List<AboutDto.SocialLinkDto> = gson.fromJson(
            entity.socialLinksJson,
            object : TypeToken<List<AboutDto.SocialLinkDto>>() {}.type
        )

        return AboutMe(
            personalInfo = AboutMe.PersonalInfo(
                name = entity.name,
                avatar = entity.avatar,
                bio = entity.bio
            ),
            professionalInfo = AboutMe.ProfessionalInfo(
                role = entity.role,
                experience = entity.experience,
                skills = skillDtos.map { it ->
                    AboutMe.Skill(
                        name = it.name,
                        level = mapSkillLevel(it.level),
                        category = mapSkillCategory(it.category)
                    )
                }
            ),
            socialLinks = socialLinkDtos.map { it ->
                when(it.platform.uppercase()) {
                    "GITHUB" -> SocialLink.Github(it.url)
                    "LINKEDIN" -> SocialLink.LinkedIn(it.url)
                    else -> SocialLink.Other(it.url)
                }
            }
        )
    }

    private fun mapSkillLevel(level: String): AboutMe.SkillLevel {
        return when(level.uppercase()) {
            "BEGINNER" -> AboutMe.SkillLevel.BEGINNER
            "INTERMEDIATE" -> AboutMe.SkillLevel.INTERMEDIATE
            "ADVANCED" -> AboutMe.SkillLevel.ADVANCE
            "EXPERT" -> AboutMe.SkillLevel.EXPERT
            else -> AboutMe.SkillLevel.INTERMEDIATE
        }
    }

    private fun mapSkillCategory(category: String): AboutMe.SkillCategory {
        return when(category.uppercase()) {
            "TECHNICAL" -> AboutMe.SkillCategory.TECHNICAL
            "SOFT" -> AboutMe.SkillCategory.SOFT
            "LANGUAGE" -> AboutMe.SkillCategory.LANGUAGE
            else -> AboutMe.SkillCategory.TECHNICAL
        }
    }

    private fun extractUsername(url: String): String? {
        return url.substringAfterLast('/').takeIf { it.isNotBlank() }
    }

}