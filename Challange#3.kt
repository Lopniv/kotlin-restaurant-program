val menu: ArrayList<Menu>? = arrayListOf()
var order: ArrayList<String>? = arrayListOf()
var totalOrder: ArrayList<Int>? = arrayListOf()
val code = mutableListOf<String>()
var position = 0
var doneTakeOrder = false
var menuOrder: String? = null

private fun main(){
    runRestaurantMy()
}

data class Menu (
    val codeOrder: String,
    val order: String,
    val price: Int
)

private fun runRestaurantMy(){
    println("""
            Welcome to MY Restaurant
        ===============================
            Please take order here..
    """.trimIndent())
    addItemMenu()
    addCodeMenu()
    menu()
}

private fun addItemMenu(){
    val menu1 = Menu("1","Nasi Goreng  ", 10000)
    val menu2 = Menu("2","Mie Goreng   ", 10000)
    val menu3 = Menu("3","Kwetiau      ", 10000)
    val menu4 = Menu("4","Ayam Goreng  ", 15000)
    val menu5 = Menu("5","Ayam Bakar   ", 18000)
    val menu6 = Menu("6","Jus Mangga   ", 8000)
    val menu7 = Menu("7","Jus Jeruk    ", 7000)
    val menu8 = Menu("8","Teh Manis    ", 5000)

    menu?.add(0, menu1)
    menu?.add(1, menu2)
    menu?.add(2, menu3)
    menu?.add(3, menu4)
    menu?.add(4, menu5)
    menu?.add(5, menu6)
    menu?.add(6, menu7)
    menu?.add(7, menu8)
}

private fun addCodeMenu(){
    menu?.forEachIndexed{ _, menu ->
        code.add(menu.codeOrder)
    }
}

private fun menu(){
    do {
        println("""
                     MENU
        ===============================
    """.trimIndent())
        var number = 1
        menu?.forEachIndexed { _, element ->
            println("""
                    $number. ${element.order} | Rp${element.price}
                """.trimIndent())
            number++
        }
        println("Pilih menu pesanan(1-8):")
        menuOrder = readLine()
        when(menuOrder){
            in code -> {
                order?.add(position, menuOrder.toString())
                position++
            }
            else -> println("Error... Pilih satu menu terlebih dahulu!")
        }
        anotherOrder()
    } while ((!doneTakeOrder))
    showOrders()
}

private fun anotherOrder(){
    var order: Boolean
    do{
        println("Apakah ingin memesan menu lain?(Y/T)")
        when(readLine()){
            "Y" -> {
                doneTakeOrder = false
                order = true
            }
            "T" -> {
                doneTakeOrder = true
                order = true
            }
            else -> {
                println("Mohon pilih Y/T!")
                order = false
            }
        }
    } while (!order)
}

private fun showOrders() {
    var number = 1
    println("Pesanan anda: ")

    order?.forEach { s ->
        menu?.forEach { m ->
            if (s == m.codeOrder){
                println("""
                    $number. ${m.order}     | Rp${m.price}
                """.trimIndent())
                totalOrder?.add(m.price)
                number++
            }
        }
    }
    println("""
        ======================================
        Total pesanan:         Rp${totalOrder?.sum()}
    """.trimIndent())
}