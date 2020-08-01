import java.util.concurrent.atomic.AtomicInteger;

public class OddThread extends Thread{
	

	private AtomicInteger num;
	private Object lock;

	public OddThread(AtomicInteger num,Object lock, String name) {
		super(name);
		this.num = num;
		this.lock = lock;
	}
	
	@Override
	public void run() {
		synchronized (lock) {
			while(true) {
				if(num.get() %2 == 1) {
					System.out.print(Thread.currentThread().getName() + " : ");
					System.out.println(num);
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					num.getAndIncrement();
					lock.notify();
					
				}else {
					
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
