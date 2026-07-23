package com.example.personaai.core.ai.routing
import javax.inject.Inject
/**
 * Represents the type of task requested by the user.
 *
 * PersonaRouter will use this to decide whether
 * the request should be handled by:
 *
 * - Local tools
 * - Local AI
 * - Cloud AI
 */
enum class TaskType {

    /** General conversation */
    CHAT,

    /** General AI reasoning */
    GENERAL,

    /** Programming related */
    CODING,

    /** Mathematical calculations */
    CALCULATOR,

    /** Weather information */
    WEATHER,

    /** Language translation */
    TRANSLATION,

    /** Summarization */
    SUMMARY,

    /** Store or retrieve memories */
    MEMORY,

    /** Internet search */
    SEARCH,

    /** Unknown request */
    UNKNOWN
}