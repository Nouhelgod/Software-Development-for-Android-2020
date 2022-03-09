fun main() {
    println("Сурков Д. А. 8В01 \n" + 
            "ЛР01-4.2 \n" + 
            "Подсчитайте и выведите на экран сколько раз используется каждый\n" +
            "символ в строке, используя словарь для хранения этой информации. Заглавные и строчные\n" +
            "буквы считать одинаковыми.\n")
    
    val phrase: String = "без труда не вытащишь и рыбку из пруда"
    val map_of_char: MutableMap<Char, Int> = mutableMapOf()
    
    for(char in phrase) {
        if map_of_char[char] == Null {
            map_of_char.put(char, 0)
            
        } else {
             map_of_char.set(char, map_of_char.getValue(char) + 1)
        }
    }


    // for(char in phrase) {
    //     map_of_char.put(char, 0)
    // }
    
    // for (char in phrase) {
    //     map_of_char.set(char, map_of_char.getValue(char) + 1)
    // }
    
    println(phrase)
    for (char in map_of_char) {
        println(char.key + ":" + char.value)
    }
}