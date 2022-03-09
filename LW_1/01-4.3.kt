fun main() {
    println("Сурков Д. А. 8В01 \n" + 
            "ЛР01-4.3 \n" + 
            "Сформируйте и выведите на экран два множества, каждое из 10 случайных чисел в\n" +
            "интервале [0..20]. Сформируйте и выведите на экран третье множество, которое содержит\n" +
            "только те числа, которые есть в первых двух множествах.\n")
    
    var set_1: MutableSet<Int> = mutableSetOf()
    var set_2: MutableSet<Int> = mutableSetOf()
    var result_set: MutableSet<Int> = mutableSetOf()
    
    for (i in 0 until 10) {
        set_1.add((0..20).random())
        set_2.add((0..20).random())
    }
    
    for (num in set_1) {
        if (set_2.contains(num)) {
            result_set.add(num)
        }
    }
    
    print("\nset_1: ")
    for (i in set_1) { print(i.toString() + " ") }
    
    print("\nset_2: ")
    for (i in set_2) { print(i.toString() + " ") }
    
    print("\nResult: ")
    for (i in result_set) { print(i.toString() + " ") }
}