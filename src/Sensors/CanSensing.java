package Sensors;

import Interfaces.IEventListener;
import Interfaces.ILightInterface;
import Interfaces.IUltrasonicInterface;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class CanSensing implements Runnable, IEventListener<IUltrasonicInterface> {
	private UltrasonicSensor sonicSensor = new UltrasonicSensor(SensorPort.S2);
	private boolean sensingCans = true;
	private IUltrasonicInterface listener;
	private int maxDistance = 50;
	private int currentDistance = 0;

	public void StopCanSense() {
		sensingCans = false;
	}

	public void Sleep() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void FindCan() {
		boolean canFound = false;
		currentDistance = sonicSensor.getDistance();
		if (currentDistance < maxDistance) {
			canFound = !canFound;
			this.notifyListeners(listener);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (sensingCans) {
			FindCan();
		}
	}

	@Override
	public void subscribe(IUltrasonicInterface listener) {
		// TODO Auto-generated method stub
		if (this.listener == null) {
			this.listener = listener;
		}
	}

	@Override
	public void unsubscribe(IUltrasonicInterface listener) {
		// TODO Auto-generated method stub
		if (this.listener == listener && this.listener != null) {
			this.listener = null;
		}
	}

	@Override
	public void notifyListeners(IUltrasonicInterface listener) {
		// TODO Auto-generated method stub
		listener.OnCanFound();
	}

}
