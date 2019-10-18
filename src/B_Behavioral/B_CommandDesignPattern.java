package B_Behavioral;

import java.util.ArrayList;
import java.util.List;

interface Command{
	public void execute();
}

class TurnOffCommand implements Command{
	private Light light;
	public TurnOffCommand(Light light) {
		this.light = light;
	}
	@Override
	public void execute() {
		light.turnOffLight();
	}
}

class TurnOnCommand implements Command{
	private Light light;
	public TurnOnCommand(Light light) {
		this.light = light;
	}
	@Override
	public void execute() {
		light.turnOnLight();
	}
}

class Light{
	private boolean glow;
	public void turnOffLight(){
		glow=false;
		System.out.println("Light.turnOffLight() :"+ this);
	}
	public void turnOnLight(){
		glow=true;
		System.out.println("Light.turnOnLight()  :"+ this);
	}
	@Override
	public String toString() {
		return "{glow: "+this.glow+"}";
	}
}

class CommandRunner{
	public List<Command> commands;

	public CommandRunner() {
		this.commands = new ArrayList<Command>();
	}
	
	public void addCommand(Command c) {
		this.commands.add(c);
	}
	
	public void executeCommands() {
		commands.forEach(e->e.execute());
	}
	
}

public class B_CommandDesignPattern {
	public static void main(String[] args) {
		Light light = new Light();
		CommandRunner runner = new CommandRunner();
		Command c1 = new TurnOnCommand(light);
		Command c2 = new TurnOffCommand(light);
		runner.addCommand(c1);
		runner.addCommand(c2);
		
		runner.executeCommands();
		
	}
}
