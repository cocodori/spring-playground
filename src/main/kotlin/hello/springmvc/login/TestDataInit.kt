package hello.springmvc.login

import hello.springmvc.itemservice.domain.item.Item
import hello.springmvc.itemservice.domain.item.ItemRepository
import hello.springmvc.login.domain.member.Member
import hello.springmvc.login.domain.member.MemberRepository
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class TestDataInit(
    private val memberRepository: MemberRepository,
    private val itemRepository: ItemRepository,
) {

    @PostConstruct
    fun init() {
        itemRepository.save(Item("itemA", 10000,    10))
        itemRepository.save(Item("itemB", 20000, 20))

        val member = Member(
            loginId = "test",
            password = "test!",
            name = "tester"
        )

        memberRepository.save(member)
    }
}