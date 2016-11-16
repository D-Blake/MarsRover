import lejos.nxt.Button;
import lejos.nxt.Motor;

public class BotMovement {
	//Void ForwardMove()
	//{
	//	MotorA.Forward()
	//	MotorB.Forward()
	//}
	public void ForwardMove()
	{
		Motor.A.forward();
		Motor.C.forward();
	}
	
	//Void BackwardsMove()
	//{
	//	MotorA.Backward()
	//	MotorB.Backward()
	//}
	public void BackwardMove()
	{
		Motor.A.backward();
		Motor.C.backward();
	}
	
	//Void Spin()
	//{
	//	MotorA.Forward()
	//	MotorB.Stop()
	//}
	public void Spin()
	{
		Motor.A.forward();
		Motor.C.backward();
	}
	
	//Void StopMovement()
	//{
	//	MotorA.Stop()
	//	MotorB.Stop()
	//}
	public void StopMovement()
	{
		Motor.A.stop();
		Motor.C.stop();
	}
}
