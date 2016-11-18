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
	private boolean sensingCans = true;
	public State state = State.FIND;
	private final int halfVolume = 50;
	CanSensing canSense = new CanSensing();
	TouchSensing touchSense = new TouchSensing();
	LightSensing lightSense = new LightSensing();
	int canCount = 3;

	public State getState() {
		return state;
	}

	public void StartBotEngine() {
		BotTimer timer = new BotTimer();
		// IButtonInterface buttonListener = null;
		// ILightInterface lightListener = null;
		// IUltrasonicInterface ultrasonicListener = null;

		canSense.subscribe(this);
		touchSense.subscribe(this);
		lightSense.subscribe(this);
		timer.StartTimer();
		Motor.A.setSpeed(250);
		Motor.C.setSpeed(250);
		movement.Spin();
		new Thread(touchSense).start();
		new Thread(canSense).start();
		new Thread(lightSense).start();
		while (canCount > 0) {
			/*switch (state) {
			case FIND:
				break;
			case FOUND:
				break;
			case PUSH:
				break;
			case RESET:
				break;
			case EVAC:
				break;
			}*/
		}
		canSense.StopCanSense();
		lightSense.StopLightSensing();
		touchSense.StopRunningTouch();
		movement.ForwardMove();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		movement.StopMovement();
		timer.StopTimer();
		timer.DisplayTime();
		Button.waitForAnyPress();
	}

	@Override
	public void OnCanFound() {
		// TODO Auto-generated method stub
		synchronized (this) {
			if (sensingCans) {
				movement.ForwardMove();
				SwapSensing();
			}
		}

	}

	@Override
	public void OnDarkFound() {
		// TODO Auto-generated method stub
		synchronized (this) {
			if (!sensingCans) {
				touchSense.Sleep();
				movement.BackwardMove();
				lightSense.Sleep();
				canCount--;
				SwapSensing();
				movement.Spin();
			}
		}
	}

	@Override
	public void OnButtonPress() {
		// TODO Auto-generated method stub
		synchronized (this) {
			if (touchSense.isLowVol()) {
				Sound.setVolume(halfVolume);
			} else {
				Sound.setVolume(maxVolume);
			}
		}
	}

	public void SwapSensing() {
		sensingCans = !sensingCans;
	}

}
