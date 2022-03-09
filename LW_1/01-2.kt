fun main() {
    println("Сурков Д. А. 8В01 \n" + 
            "ЛР01-2 \n" + 
            "1. Написать метод, который в переданной строке заменяет все точки на многоточие. С его" +
            "помощью обработать пять разных строк и отобразить их на экране. \n")
   
    fun three_dots(string: String): String{
        // Заменить "." -> "…"
        println(string);
        
        val string_: String = "└>" + string.replace(".", "…") + "\n";
        return string_;
   }
   
   val s1: String = "Лямбда-функции можно использовать в качестве параметров других функций.";
    println(three_dots(s1));
    
   val s2: String = "Определение функции начинается с ключевого слова fun (от англ. function – функция).";
    println(three_dots(s2));
    
   val s3: String = ".код, имеющий минимум связей с другими частями кода."
    println(three_dots(s3));
    
   val s4: String = "Цветы в вазе."
    println(three_dots(s4));
    
   val s5: String = "1. 2. 3. 4. 5. 6. 7. 8. 9. 10."
    println(three_dots(s5));
}