
public class BotEngine {
	public BotEngine()
	{
		BotLocation location = new BotLocation();
		BotAudio audio = new BotAudio();
		BotMovement movement = new BotMovement();
		BotTimer timer = new BotTimer();
		
		timer.StartTimer();
		
		while(!location.CansCleared())
		{
			movement.Spin();
			location.FindCan();
			while(!location.IsOutside())
			{
				audio.PlayBeep();
				movement.ForwardMove();
				if(location.IsPushing())
				{
					audio.ChangeBeep();
				}
			}
			while(location.IsOutside())
			{
				audio.ChangeBeep();
				audio.PlayBeep();
				movement.BackwardMove();
			}
		}
		movement.ForwardMove();
		if(location.IsOutside())
		{
			timer.StopTimer();
			movement.StopMovement();
			timer.DisplayTime();
		}
	
	}
}
