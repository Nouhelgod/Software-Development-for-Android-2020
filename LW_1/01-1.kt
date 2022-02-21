fun main() {
    println("Сурков Д. А. 8В01 \n" + 
            "ЛР01-1 \n" + 
            "3. Найти наибольший элемент и поменять его местами с первым элементом. \n")
    
    var arr = IntArray(10) { it };
    
    // Наибольший элемент массива
    var tmp: Int = arr[0];
    var tmp_pos: Int = 0;
    
    println("Исходный массив:")
    for (num in 0..arr.size-1){
        print(arr[num].toString() + " ")
        
        if (arr[num] > tmp) {
            tmp = arr[num];
            tmp_pos = num;
        }
    }
    
    println("\nМодифицированный массив:")
    arr[tmp_pos] = arr[0]
    arr[0] = tmp;
    
    for (num in 0..arr.size-1) {
        print(arr[num].toString() + " ")
    }
    
}