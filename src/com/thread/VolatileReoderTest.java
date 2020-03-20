package com.thread;

public class VolatileReoderTest {
	private boolean v = false;
	private int x = 0;
	//volatile 
	
	public void writer() {
		this.x = 42;
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.v = true;
	}

	public void reader() {
		if ( this.v && this.x == 0 ) {
			System.out.println("Instruction Reoder found");
		}else if (!this.v && this.x == 42) {
			//System.out.println("In between state but no reoder found");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		for(int i = 5000; i > 0; i--) {
			VolatileReoderTest vt = new VolatileReoderTest();
			Thread t1 = new Thread(vt::writer);
			Thread t2 = new Thread(vt::reader);
			
			t1.start();
			t2.start();
			//t1.join();
		    //t2.join();
			
		}
	}
}
