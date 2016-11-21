package Sensors;

import Interfaces.IEventListener;
import Interfaces.ILightInterface;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class LightSensing implements Runnable, IEventListener<ILightInterface> {
	private LightSensor lightSense = new LightSensor(SensorPort.S3);
	private ILightInterface listener;
	private int currentLight = 100;
	private boolean sensingLight = true;

	public void StopLightSensing()
	{
		sensingLight = false;
	}
	
	public void isOutside() {
		currentLight = lightSense.readValue();
		if(currentLight > 100 || currentLight < 0)
		{
			currentLight = lightSense.readValue();
		}
		System.out.println(currentLight);
		if (currentLight <= 20 && (currentLight < 100 || currentLight > 0)) {
			this.notifyListeners(listener);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (sensingLight) {
			this.isOutside();
		}
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
	
	@Override
	public void subscribe(ILightInterface listener) {
		// TODO Auto-generated method stub
		if (this.listener == null) {
			this.listener = listener;
		}
	}

	@Override
	public void unsubscribe(ILightInterface listener) {
		// TODO Auto-generated method stub
		if (this.listener == listener && this.listener != null) {
			this.listener = null;
		}
	}

	@Override
	public void notifyListeners(ILightInterface listener) {
		// TODO Auto-generated method stub
		listener.OnDarkFound();
	}

}
