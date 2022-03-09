fun main() {
    println("Сурков Д. А. 8В01 \n" + 
            "ЛР01-4.1 \n" + 
            "2.Дан список строк. Удалите из него все строки, которые содержат восклицательный или " +
            "вопросительный знаки. Выведите на экран новый список \n")
    
    val list_of_lines: MutableList<String> = mutableListOf()
    val list_of_index: MutableList<Int> = mutableListOf()
    
    list_of_lines.add("defeat")
    list_of_lines.add("play!")
    list_of_lines.add("!white!")
    list_of_lines.add("desk")
    list_of_lines.add("running horse!")
    
    println("\nИзначальный список:")
    
    var i: Int = 0;
    for (line in list_of_lines) { 
        print(line + ", ")
        
        if(line.contains('!')) {
            list_of_index.add(i)
        }
        
        i = i + 1
    }
    
    i = 0
    for (index in list_of_index) {
        list_of_lines.removeAt(index - i)
        i = i + 1
    }   
            
    println("\nИзмененный список:")
    for (line in list_of_lines)
        print(line + ", ")
    
}