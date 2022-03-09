fun main() {
   println("Сурков Д. А. 8В01 \n" + 
            "ЛР01-3 \n")
   
   open class Plane(Name: String, Color: String, Power: Double, Max_speed: Int) {
       var name: String;
       var color: String;
       var power: Double;
       var max_speed: Int;
       
       init {
           name = Name;
           color = Color;
           power = Power;
           max_speed = Max_speed;
       }
       
       open fun getPlaneClass(): String{
           return "Plane";
       } 
   }
   
   
   class Jet(Name: String, Color: String, Power: Double, Max_speed: Int) : Plane(Name, Color, Power, Max_speed) {
       var is_supersonic: Boolean = false;
       
       override fun getPlaneClass(): String{
           if (!is_supersonic) {return "Jet plane"}
           else { return "Supersonic jet plane"}
       }
   }
   
   
   class Transit(Total_seats: Int = 200, Nop: Int = 0, Name: String, Color: String, Power: Double, Max_speed: Int) : Plane(Name, Color, Power, Max_speed) {
       var total_seats: Int;
       var nop /* Number of passengers */: Int;
       
       init {
           total_seats = Total_seats;
           nop = Nop;
       }
       
       override fun getPlaneClass(): String{
           return "Transit plane";
       }
       
       fun getNumberOfFreeSeats(): Int{
           return total_seats - nop;
       }
   }
   
   // Реактивный сверхзвуковой самолет СУ-27 синего цвета, с мощностью двигателя 1.12 МВт и максимальной скоростью 1400 км/ч
   println("Flanker-B")
   var Flanker_B = Jet(Name = "SU-27", Color = "Blue", Power = 1.12, Max_speed = 1400);
   Flanker_B.is_supersonic = true;
   println(Flanker_B.name + " : " + Flanker_B.getPlaneClass())
   
   // Пассажирский самолет Boeing 727 светло-серого цвета, с мощностью двигателя 1.96 МВт и максимальной скоростью 917 км/ч
   // Вместительность: 131 место
   // На борту: 109 пассажиров
   println("\nPassenger plane")
   var Passenger_plane = Transit(131, 109, "Boeing 727", "Light gray", 1.96, 917)
   println(Passenger_plane.name + " : " + Passenger_plane.getPlaneClass())
   println("Color : " + Passenger_plane.color)
   println("Engine power : " + Passenger_plane.power + " MW")
   println("Max speed : " + Passenger_plane.max_speed + " km/h")
   println("Free seats : " + Passenger_plane.getNumberOfFreeSeats().toString())
}