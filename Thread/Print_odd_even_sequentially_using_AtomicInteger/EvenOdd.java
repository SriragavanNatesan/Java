public class EvenOdd {

	public static void main(String[] args) {
		
		AtomicInteger atomNum = new AtomicInteger(1);
		Object lock = new Object();
		
		Thread evenThread = new EvenThread(atomNum, lock, "Even");
		Thread oddThread = new OddThread(atomNum, lock,  "Odd");
		oddThread.start();
		evenThread.start();
	}
}

