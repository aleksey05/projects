package parser;

public class Tracker {
	
	private static int callsAmount;
	private long startTime;
	private long endTime;

	public Tracker() {
		callsAmount = 0;
	}

	public int getCallsAmount() {
		return callsAmount;
	}

	public void setStartTime() {
		startTime = System.currentTimeMillis();
	}

	public void setEndTime() {
		endTime = System.currentTimeMillis();
	}

	public void countCalls() {
		callsAmount++;
	}

	public void printInfo(int productAmount) {
		long totalTime = ((endTime - startTime) / (1000));
		System.out.println("Total time required: " + totalTime + " seconds");
		System.out.println("Products selected: " + productAmount);
		System.out.println("Amount of calls performed: " + callsAmount);

	}

}
