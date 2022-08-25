package hello.springmvc.exception.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiExceptionController {

    @GetMapping("/api/members/{id}")
    fun getMember(
        @PathVariable id: String
    ): MemberDto {
        if (id == "ex") {
            throw RuntimeException("잘못된 사용자")
        }

        return MemberDto(id, "hello $id")
    }
}

data class MemberDto(
    val memberId: String,
    val name: String,
)
