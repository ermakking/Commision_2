import java.util.Scanner

fun main() {

    var transferAmount = 0.0
    var amountMonth = 0.0


    println("Добро пожаловать в программу перевода денег!")
    val scan = Scanner(System.`in`)
    while (true) {
        println(
            "Введите порядковый номер вашей платёжной системы(тип карты/счёта) или нажмите \"Enter\" " +
                    "для выбора VK Pay(по умолчанию): " +
                    "\n1. MasterCard" +
                    "\n2. Maestro" +
                    "\n3. Visa" +
                    "\n4. Мир" +
                    "\n5. VK Pay"
        )

        var cardString = readLine()

        var card: Int

        when (cardString) {
            "1" -> card = 1
            "2" -> card = 2
            "3" -> card = 3
            "4" -> card = 4
            else -> card = 5
        }


        println("Введите сумму перевода:")
        val amount = (scan.nextInt() * 100).toDouble()

        if (card == 3 || card == 4) {
            if (amount <= 3500) {
                println("Минимальная комиссия за перевод составляет: 35 руб, введите сумму больше минимальной комиссии")
                continue
            }
        }
        var comission = calculation(card, amount, amountMonth)
        transferAmount = (amount - comission) / 100
        amountMonth = amountMonth + transferAmount
        println(
            "Комиссия составит: " + (comission / 100) +
                    "\nСумма перевода с учётом комиссии составит: " + transferAmount +
                    "\nСумма переводов в текущем периоде составила: " + amountMonth
        )

    }
}


fun calculation(card: Int, amount: Double, amountMonth: Double): Double {
    val minCommissionVisMir = 3500// в копейках

    return when (card) {
        1, 2 -> if (amountMonth > 75000 || amount + amountMonth * 100 > 75000 * 100) {
            amount * 0.006 + 2000
        } else 0.0

        3, 4 -> if (amount * 0.75 <= minCommissionVisMir) {
            35.0
        } else {
            amount * 0.0075
        }

        else -> 0.0

    }
}



