import lejos.nxt.Sound;

public class BotAudio {
	boolean lowBeep;
	final int maxVolume = 100;
	final int halfVolume = 50;
	//Void ChangeBeep()
	//{
	//	if(LowBeep())
	//	{
	// 		SetVolume(HighVolume)
	//	}
	//	else
	//	{
	//		SetVolume(LowVolume)
	//	}
	//}
	public void ChangeBeep()
	{
		if(lowBeep)
		{
			Sound.setVolume(maxVolume);
		}
		else
		{
			Sound.setVolume(halfVolume);
		}
	}
	
	//Void PlayBeep()
	//{
	//	Beep()
	//}
	public void PlayBeep()
	{
		Sound.beep();
	}
	
	
}
