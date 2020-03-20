package com.thread;

final class SetCheck {
	  private int  a = 0;
	  private long b = 0;

	  void set() {
	    a =  1;
	    b = -1;
	  }

	  boolean check() {
	    return ((b ==  0) ||
	            (b == -1 && a == 1)); 
	  }
	  
	  
	  public static void main(String[] args) throws InterruptedException {
		  SetCheck sc = new SetCheck();

			Thread writer = new Thread(sc::set);
			Thread reader = new Thread(() -> {
				System.out.println(sc.check());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});


			writer.start();
			reader.start();
			Thread.sleep(100);

			System.out.println(reader.getState());
			
			writer.join();
			reader.join();

		}
	  
	}