package multiThreadedTimer;

public class Timer implements Runnable{
	private int time = 0;
	private boolean run = true;
	
	public int getTime() {
		return time;
	}

	public Timer() {
		super();
	}

	public Timer(int time) {
		super();
		this.time = time;
	}

	@Override
	public void run() {	
		run = true;
		while(run){
			try {				
				Thread.sleep(1000);
				time += 1;
			} catch (InterruptedException e) {
				run = false;
			}
		}
		
	}

}
