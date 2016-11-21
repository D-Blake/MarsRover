package TestPackage;
import static org.junit.Assert.*;

import org.junit.Test;

public class MarsRoverTests {

	@Test
	public void HighTest() {
		TestLightSense testLight = new TestLightSense();
		testLight.SetLightValue(500);
		int currentLight = testLight.GetLightValue();
		if(currentLight > 100 || currentLight < 0)
		{
			currentLight = testLight.GetLightValue();
		}
		System.out.println(currentLight);
		assertTrue(currentLight <= 20 && (currentLight < 100 || currentLight > 0));
	}
	@Test
	public void CorrectTest() {
		TestLightSense testLight = new TestLightSense();
		testLight.SetLightValue(10);
		int currentLight = testLight.GetLightValue();
		if(currentLight > 100 || currentLight < 0)
		{
			currentLight = testLight.GetLightValue();
		}
		System.out.println(currentLight);
		assertTrue(currentLight <= 20 && (currentLight < 100 || currentLight > 0));
	}
	@Test
	public void IncorrectTest() {
		TestLightSense testLight = new TestLightSense();
		testLight.SetLightValue(50);
		int currentLight = testLight.GetLightValue();
		if(currentLight > 100 || currentLight < 0)
		{
			currentLight = testLight.GetLightValue();
		}
		System.out.println(currentLight);
		assertTrue(currentLight <= 20 && (currentLight < 100 || currentLight > 0));
	}
}

//distance for ultrasonic test
//light number for when it will detect outer circle
//light sensor - moving forward if light, moving back if dark
