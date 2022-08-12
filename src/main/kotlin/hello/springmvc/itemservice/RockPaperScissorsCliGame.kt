package hello.springmvc.itemservice

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    println("가위바위보 게임입니다.")
    println("가위 바위 보 중 하나를 입력하세요")

    val list = listOf("가위", "바위", "보")
    val userRecord = mutableMapOf("win" to  0, "fail" to 0, "draw" to 0)

    while (scanner.hasNext()) {
        val user = scanner.next().trim()
        val computer = list.random()

        if (!list.contains(user)) {
            println("잘못 입력하셨습니다. 가위, 바위, 보 중 하나를 입력하세요.")
            continue
        }


        println("computer: $computer")

        val result = when {
            user == computer -> null
            user == "가위" && computer == "보" -> true
            user == "가위" && computer == "보" -> true
            user == "바위" && computer == "가위" -> true
            user == "가위" && computer == "바위" -> false
            user == "가위" && computer == "바위" -> false
            user == "바위" && computer == "보" -> false
            else -> println("가위, 바위, 보 중 하나를 입력하세요.")
        }

        when (result) {
            null ->  {
                println("아쉽네요! 무승부입니다.")
                userRecord["draw"]?.let { it + 1 } ?: 1
            }
            true -> {
                println("이겼습니다!")
                userRecord["win"] = userRecord["win"]?.let { it + 1 } ?: 1
            }
            else -> {
                println("졌습니다...")
                userRecord["fail"] = userRecord["fail"]?.let { it + 1 } ?: 1
            }
        }

        println("현재까지 ${userRecord["win"]}승 ${userRecord["fail"]}패 ${userRecord["draw"]} 무승부!")

        continue
    }
}