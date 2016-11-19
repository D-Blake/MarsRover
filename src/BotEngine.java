import Interfaces.IButtonInterface;
import Interfaces.ILightInterface;
import Interfaces.IUltrasonicInterface;
import Sensors.CanSensing;
import Sensors.LightSensing;
import Sensors.TouchSensing;
import enums.State;
import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.Sound;

public class BotEngine implements IButtonInterface, ILightInterface, IUltrasonicInterface {
	BotMovement movement = new BotMovement();
	private final int maxVolume = 100;
	public State currentState = State.INIT;
	private final int halfVolume = 50;
	CanSensing canSense = new CanSensing();
	TouchSensing touchSense = new TouchSensing();
	LightSensing lightSense = new LightSensing();
	int canCount = 3;

	public State getState() {
		return currentState;
	}

	public void StartBotEngine() {
		BotTimer timer = new BotTimer();
		canSense.subscribe(this);
		touchSense.subscribe(this);
		lightSense.subscribe(this);
		timer.StartTimer();
		Motor.A.setSpeed(250);
		Motor.C.setSpeed(250);
		movement.Spin();
		currentState = State.FORWARD;
		new Thread(touchSense).start();
		new Thread(canSense).start();
		new Thread(lightSense).start();
		while (canCount >= 0) {
			if (canCount == 0) {
				currentState = State.EVAC;
			}
		}
		canSense.StopCanSense();
		lightSense.StopLightSensing();
		touchSense.StopRunningTouch();
		movement.StopMovement();
		timer.StopTimer();
		timer.DisplayTime();
		Button.waitForAnyPress();
	}

	@Override
	public void OnCanFound() {
		synchronized (this) {
			switch (currentState) {
			case INIT:
				break;
			case FORWARD:
				movement.ForwardMove();
				currentState = State.BACK;
				break;
			case BACK:
				break;
			case EVAC:
				movement.ForwardMove();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				currentState = State.INIT;
				canCount--;
				break;
			}
		}
	}

	@Override
	public void OnDarkFound() {
		synchronized (this) {
			switch (currentState) {
			case INIT:
				break;
			case FORWARD:
				break;
			case BACK:
				movement.BackwardMove();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				movement.Spin();
				try {
					Thread.sleep(650);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				currentState = State.FORWARD;
				canCount--;
				break;
			case EVAC:
				movement.ForwardMove();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				currentState = State.INIT;
				canCount--;
				break;
			}
		}
	}

	@Override
	public void OnButtonPress() {
		synchronized (this) {
			if (touchSense.isLowVol()) {
				Sound.setVolume(halfVolume);
			} else {
				Sound.setVolume(maxVolume);
			}
		}
	}

}
