package B_Behavioral;

interface MobileAlertState {
	public void alert(AlertStateContext ctx);
}

class Silent implements MobileAlertState {
	@Override
	public void alert(AlertStateContext ctx) {
		System.out.println("silent...");
	}
}

class Vibration implements MobileAlertState {
	@Override
	public void alert(AlertStateContext ctx) {
		System.out.println("vibration...");
	}
}

class AlertStateContext {
	private MobileAlertState currentState;

	public AlertStateContext() {
		currentState = new Vibration();
	}

	public void setState(MobileAlertState state) {
		currentState = state;
	}

	public void alert() {
		currentState.alert(this);
	}
}


public class H_State {
	public static void main(String[] args) {
		AlertStateContext stateContext = new AlertStateContext();
		stateContext.setState(new Vibration());
		stateContext.alert();
		stateContext.alert();
		stateContext.setState(new Silent());
		stateContext.alert();
		stateContext.alert();
		stateContext.alert();
	}
}
