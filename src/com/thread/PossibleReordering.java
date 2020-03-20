package com.thread;

public class PossibleReordering {
	static volatile  int  x;

	public static void main(String[] args) throws InterruptedException {

		Thread writer = new Thread(PossibleReordering::setS);
		Thread writer2 = new Thread(PossibleReordering::setS);

		Thread reader = new Thread(PossibleReordering::getS);
		Thread reader2 = new Thread(PossibleReordering::getS);

		writer.start();
		reader.start();
		writer.sleep(10000);
		writer.join();
		
		reader.join();

		writer2.start();
		reader2.start();

		writer2.join();
		reader2.join();
	}

	static  void setS() {
		System.out.println("Thread writer started ");
		someHeavyWork();
		if(x == 0)
			x = 1;
		else
			x = 2;


	}

	static  void getS(){
		System.out.println("Thread reader :  " + x );
	}

	private static void someHeavyWork() {
		int i =1 ; while(i != 0) { i++; i++; i--; i--; i+=2; i--;}
	}

}