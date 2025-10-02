import java.util.*;

class polymorphism{
	public static void main(String[] args) {
		Apple iphone = new Apple("iPhone 13");
		iphone.swicthOn();

		Samsung s = new Samsung("S23 Ultra");
		s.swicthOn();
	}
}

class Phone{
	float screenSize;
	int price;
	String name;
	boolean power;

	public void swicthOn(){
		String s = "Phone Switched On";
		power = true;
		println(s);
	}

	public void swicthOff(){
		String s = "Phone Switched Off";
		power = false;
		println(s);
	}

	public static void println()
    {
        System.out.println();
    }
	public static void print(Object o)
    {
        System.out.print(String.valueOf(o));
    }
    public static void println(Object o)
    {
        System.out.println(String.valueOf(o));
    }
}

class Apple extends Phone{
	String model;
	Apple(String model){
		this.model = model;
	}

	//Runtime Polymorphism or Method Overriding
	public void swicthOn(){
		println("Welcome to Apple Ecosystem!");
		power = true;
	}
}

class Samsung extends Phone{
	String model;
	Samsung(String model){
		this.model = model;
	}
}