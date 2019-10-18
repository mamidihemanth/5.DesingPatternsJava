package B_Behavioral;

import java.util.ArrayList;
import java.util.List;

interface MyObserver {
	public void update(int presure, int temperature, int humidity);
}

class WeatherObserver implements MyObserver{

	private int pressure;
	private int temeperature;
	private int humidity;
	private MySubject subject;

	//Constructor
	public WeatherObserver(MySubject subject) {
		this.subject = subject;
		this.subject.addObserver(this);
	}
	
	//LifeCycle method
	@Override
	public void update(int pressure, int temperature, int humidity) {
		 this.pressure =  pressure;
		 this.temeperature =  temperature;
		 this.humidity = humidity;
		 shodata();
	}
	
	private void shodata() {
		System.out.println("[Pressure: "+pressure+" Temperature:"+temeperature+" Humidity:"+humidity+"]");
	}
}


interface MySubject {
	public void addObserver(MyObserver o);
	public void removeObserver(MyObserver o);
	public void notifyAllObserver();
}

class WeatherStation implements MySubject{

	private int pressure;
	private int temperature;
	private int humidity;
	private List<MyObserver> observerList;
	
	//Getter
	public int getPressure() { return pressure; }
	public int getTemperature() { return temperature; }
	public int getHumidity() { return humidity; }
	//Setter
	public void setPressure(int pressure) { 
		this.pressure = pressure;
		notifyAllObserver();
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
		notifyAllObserver();
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
		notifyAllObserver();
	}
	
	//Constructor
	public WeatherStation() {
		this.observerList = new ArrayList<MyObserver>();
	}
	
	//LifeCycle methods
	@Override
	public void addObserver(MyObserver o) {
		this.observerList.add(o);
	}

	@Override
	public void removeObserver(MyObserver o) {
		this.observerList.remove(o);
	}

	@Override
	public void notifyAllObserver() {
		for(MyObserver o: this.observerList)
			o.update(pressure, temperature, humidity);
	}
	
}


public class G_Observer {
	public static void main(String[] args) {
		WeatherStation subject = new WeatherStation();
		WeatherObserver observer1 = new WeatherObserver(subject);
		WeatherObserver observer2 = new WeatherObserver(subject);
		
		subject.setHumidity(100);
		subject.setTemperature(300);
		subject.setPressure(200);
	}
}
