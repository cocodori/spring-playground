package hello.springmvc.login.domain.member

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class MemberRepository {
    private val log = LoggerFactory.getLogger(javaClass)
    companion object {
        private val store = mutableMapOf<Long, Member>()
        private var sequence = 0L
    }

    fun save(member: Member): Member {
        member.id = ++sequence
        log.info("save: member={}", member)
        store[member.id!!] = member
        return member
    }

    fun findById(id: Long): Member? {
        return store[id]
    }

    fun findByLoginId(loginId: String): Member? {
        return findAll().firstOrNull { it.loginId == loginId }
    }

    fun findAll(): List<Member> {
        return store.values.toList()
    }

    fun clearStore() {
        return store.clear()
    }

}