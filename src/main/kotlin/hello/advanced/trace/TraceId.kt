package hello.advanced.trace

import java.util.UUID

class TraceId(
    id: String? = null,
    level: Int? = null,
) {

    val id: String = id ?: createId()
    val level: Int = level ?: 0

    private fun createId(): String = UUID.randomUUID().toString().substring(0,8)

    fun createNextId() = TraceId(id, level + 1)

    fun createPreviousId() = TraceId(id, level - 1)

    fun isFirstLevel() = level == 0
}

