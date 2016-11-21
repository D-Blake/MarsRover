package Sensors;

import Interfaces.IEventListener;
import enums.State;
import Interfaces.IButtonInterface;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;

public class TouchSensing implements Runnable, IEventListener<IButtonInterface> {
	private TouchSensor sensor1 = new TouchSensor(SensorPort.S1);
	private TouchSensor sensor2 = new TouchSensor(SensorPort.S4);
	private IButtonInterface listener;
	private boolean runningTouch = true;
	private boolean lowVol = true;

	public boolean isLowVol(){
		return lowVol;
	}
	
	public void Sleep()
	{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void StopRunningTouch()
	{
		runningTouch = false;
	}
	
	public void botTouchSense() {
		if (sensor1.isPressed() || sensor2.isPressed()) {
			lowVol = false;
			this.notifyListeners(listener);
		} else {
			lowVol = true;
			this.notifyListeners(listener);
		}
		Sound.beep();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (runningTouch) {
			this.botTouchSense();
		}
	}

	@Override
	public void subscribe(IButtonInterface listener) {
		// TODO Auto-generated method stub
		if (this.listener == null) {
			this.listener = listener;
		}
	}

	@Override
	public void unsubscribe(IButtonInterface listener) {
		// TODO Auto-generated method stub
		if (this.listener == listener && this.listener != null) {
			this.listener = null;
		}
	}

	@Override
	public void notifyListeners(IButtonInterface listener) {
		// TODO Auto-generated method stub
		listener.OnButtonPress();
	}

}
