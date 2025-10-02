import java.io.*;
import java.util.*;

public class inheritance
{
	public static void main(String[] args) {
		System.out.println("Hello World");
		Car c = new Car();
		
		i20 i = new i20("Asta");
		println(i.getBrand());
		i.startCar();
		println(i.getIgnition());

		HondaCity h = new HondaCity("ZX");
		println(h.getVariant());
		println(h.getIgnition());
	}
	
	private static void print(Object o){
        System.out.print(String.valueOf(o));
    }
    
    private static void println(Object o){
        System.out.println(String.valueOf(o));
    }
}

class Car{
    protected String brand,segment,name;
    protected float engCap,price;
    boolean ig;
    
    public String getBrand(){
        return this.brand;
    }
    
    public String getSegment(){
        return this.segment;
    }
    
    public float getEngCap(){
        return this.engCap;
    }
    
    public float getPrice(){
        return this.price;
    }
    
    @Override
    public String toString(){
        String ret = "[" + this.brand + ", " + this.segment + ", " + this.engCap + " L, " + this.price + " lakhs]";
        return ret;
    }

    public String getIgnition(){
    	String s = "Ignition ";
    	s += ig ? "on" : "off";
    	return s; 
    }

    public void startCar(){
    	println("Starting " + name);
    	this.ig = true;
    }

    public void stopCar(){
    	println("Stopping " + name);
    	this.ig = false;
    }
    
    private void print(Object o){
        System.out.print(String.valueOf(o));
    }
    
    private void println(Object o){
        System.out.println(String.valueOf(o));
    }
}

class i20 extends Car{
	String variant;
    i20(String variant){
        name = "i20";
        brand = "Hyundai";
        segment = "Premium Hatchback";
        price = (float)11.5;
        engCap = (float)1.2;
        ig = false;
        this.variant = variant;
    }

    public String getVariant(){
		return this.variant;
	}
}

class HondaCity extends Car{
	String variant;
	HondaCity(String variant){
		name = "Honda City";
        brand = "Honda";
        segment = "Sedan";
        price = (float)18;
        engCap = (float)1.5;
        ig = false;
        this.variant = variant;
	}

	public String getVariant(){
		return this.variant;
	}
}