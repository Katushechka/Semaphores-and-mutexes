package Modul3;

import java.util.concurrent.Semaphore;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Buffer {
	private int bufferSize = 51; //size of queue 
	private Boolean done = false;  //to stop the program
	private Semaphore semProducer;  //counting empty slots (to write)
    private Semaphore semConsumer;  //counting full slots  (to read and remove)
    private Semaphore wholeBuffMutex;  //locking the whole buffer (for ME)
    private FoodItem [] foodBuffer;
    private int inPos = 0;  //position of next writing
	private int outPos = 0; //position of next reading
	private int numberOfItemsBuffer = 0;
    private FoodItem foodItem;
    private GUISemaphore ui;
	
	public Buffer(GUISemaphore ui) {
		this.ui = ui;
		foodBuffer = new FoodItem[bufferSize];   //skapar en ny array
        semProducer = new Semaphore(bufferSize);
        semConsumer = new Semaphore(0);
        wholeBuffMutex = new Semaphore(1); //binary semaphore
		
	}
	public Boolean isRunning() {
		return !done; 
	}

	public void WriteToBuffer(FoodItem foodItem) throws InterruptedException {  
		//acquire a permit to an empty slot 
		semProducer.acquire();
		
		
		//acquire ME (mutual exclusion) lock
		 wholeBuffMutex.acquire();
		 Thread currentThread = Thread.currentThread();
		 
		//Critical section	
		 
		 if (numberOfItemsBuffer != 50) {
			 numberOfItemsBuffer +=1;	
			 foodBuffer[numberOfItemsBuffer] = foodItem;
			 
			 if(numberOfItemsBuffer == 50) {
				 done = true;
			 }
		 }	 
				
		 ui.storageShowKapacity(numberOfItemsBuffer% bufferSize);	 
		 
		 wholeBuffMutex.release();
	 
		 semConsumer.release();  //notify Consumer  to read
		
	}
	
	public FoodItem readFromBuffer () throws InterruptedException {  
		semConsumer.acquire();
		
		//ME lock
        wholeBuffMutex.acquire();
                
		
//      Critical section       	
        if (numberOfItemsBuffer >=1) {
        	foodItem = foodBuffer[numberOfItemsBuffer]; //Läser första Item-object
            numberOfItemsBuffer -= 1;
            done = false;
            ui.storageShowKapacity(numberOfItemsBuffer % bufferSize);
            try { 
            	Thread.sleep(500);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }      

		wholeBuffMutex.release();  //exit Critical section
		
        semProducer.release();  //notify producer to write 

		return foodItem;        
	}
}





