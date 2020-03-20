package com.thread;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantScenario {
    static int counter = 1; // a global counter
    

    static ReentrantLock counterLock = new ReentrantLock(true);

    public ReentrantScenario() {
    }

    static synchronized void incrementCounter() {
         System.out.println(Thread.currentThread().getName() + ": " + counter);
         counter++;
    }
    
    static void incrementCounterRe(){
        counterLock.lock();

        // Always good practice to enclose locks in a try-finally block
        try{
            System.out.println(Thread.currentThread().getName() + ": " + counter);
            counter++;
        }finally{
             counterLock.unlock();
        }
     }


    public static void main(String[] args) {
         Thread thread1 = new Thread(() -> {
        	   while(counter<1000){
        		   ReentrantScenario.incrementCounterRe();
        	   } 
         });
         Thread thread2 = new Thread(() -> {
      	   while(counter<1000){
      		   ReentrantScenario.incrementCounterRe();
      	   } 
       });

         thread1.start();
         thread2.start();          
    }
}
	
	


