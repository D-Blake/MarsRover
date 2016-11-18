
public class BotTimer {
	long startTime;
	long endTime;

	// Void DisplayTime()
	// {
	// Print endTime-currentTime
	// }
	public void DisplayTime() {
		System.out.println("Time Spent: " + ((endTime - startTime)/1000));
	}

	// Void StopTimer()
	// {
	// endTime = currentTimeMillis()
	// }
	public void StopTimer() {
		endTime = System.currentTimeMillis();
	}

	// Void StartTimer()
	// {
	// currentTime = currentTimeMillis()
	// }
	public void StartTimer() {
		startTime = System.currentTimeMillis();
	}
}
