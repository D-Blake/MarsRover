import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;

public class BotLocation {
	boolean canFound;
	int canCount;
	int maxDistance;
	UltrasonicSensor sonicSensor = new UltrasonicSensor(SensorPort.S1);
	//Void FindCan()
	//{
	//	while(!canFound)
	//	{
	//		if(Distance < groundLevel)
	//		{
	//			canFound = true
	//		}
	//	}
	//}
	public void FindCan()
	{
		int currentDistance;
		while(!canFound)
		{
			currentDistance = sonicSensor.getDistance();
			if(currentDistance < maxDistance)
			{
				canFound = true;
			}
		}
	}
	
	//Bool IsPushing()
	//{
	//	if(touchSensorA is touched or touchSensorB is touched)
	//	{
	//		isPushing = true
	//	}
	//	else
	//	{
	//		isPushing = false
	//	}
	//	return isPushing
	//}
	public boolean IsPushing()
	{
		TouchSensor sensor1 = new TouchSensor(SensorPort.S2);
		TouchSensor sensor2 = new TouchSensor(SensorPort.S3);
		boolean isPushing;
		if(sensor1.isPressed() || sensor2.isPressed())
		{
			isPushing = true;
		}
		else
		{
			isPushing = false;
		}
		return isPushing;
	}
	
	//Bool CansCleared()
	//{
	//	if(canCount <= 0)	
	//	{
	//		zoneCleared = true
	//	}
	//	else
	//	{
	//		zoneCleared = false
	//	}
	//	return zoneCleared
	//}
	public boolean CansCleared()
	{
		boolean zoneCleared;
		if(canCount <= 0)	
		{
			zoneCleared = true;
		}
		else
		{
			zoneCleared = false;
		}
		return zoneCleared;
	}
	
	//Bool IsOutside()
	//{
	//	if(currentLight <= dark)
	//	{
	//		outsideCircle = !outsideCircle
	//	}
	//	return outsideCircle
	//}
	public boolean IsOutside()
	{
		boolean outsideCircle = false;
		int currentLight = 100;
		if(currentLight <= 50)
		{
			outsideCircle = !outsideCircle;
		}
		return outsideCircle;
	}
}
