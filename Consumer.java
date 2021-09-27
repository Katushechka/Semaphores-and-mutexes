package Modul3;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;



public class Consumer extends Thread{
	private GUISemaphore ui;
	private FoodItem foodItem;
	private Buffer buffer;
	private Semaphore readMutex;
	private FoodItem [] foodItems;
	private double weight = 0;
	private double volume = 0;
	private int limitAntalItems;
	private double limitWeight;
	private double limitVolume;
	private boolean continueLoad;
	private volatile boolean running = true;
	private String threadName;
	private LinkedList<ItemListener> list = new LinkedList<ItemListener>();

	
	
	
	
	public Consumer (boolean continueLoad, Buffer buffer, GUISemaphore ui, int limitAntalItems, double limitWeight, double limitVolume, String threadName) {
		this.buffer = buffer;
		this.ui = ui;
		this.limitAntalItems = limitAntalItems;
		this.limitWeight = limitWeight;
		this.limitVolume = limitVolume;
		this.continueLoad = continueLoad;
		this.threadName=threadName;
		readMutex = new Semaphore (1);
	}
	
	public void run() {
		try {
			consume();
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}

	

	
	public void consume() throws InterruptedException {
		try {
			//acquire lock to exclude all other consumer
			readMutex.acquire ( );
			
		}catch (InterruptedException exc) { 
 
        } 
		
		Thread currentThread = Thread.currentThread();
		
		//Perform reading
		double totalWeight = 0;
		double totalVolume = 0;
		int totalAntal = 0;
		foodItems = new FoodItem [limitAntalItems];
		
	
		while (continueLoad == true) {


			
			while (totalWeight <= limitWeight && totalVolume <= limitVolume && totalAntal <=limitAntalItems) {
				if (running) {
					int i = 0;
					foodItems[i] = buffer.readFromBuffer(); 

					foodItem = foodItems[i];
					
					for (ItemListener listener : list) {
						listener.printItem(foodItem.getName(), threadName);
					}
					weight = foodItem.getWeight();
					volume = foodItem.getVolume();

					totalWeight +=weight;
					totalVolume +=volume;
					i +=1;
					totalAntal +=i;		
				}else {
					shutdown();
				}	
			}
			Thread.sleep(1500);
			totalWeight =0;
			totalVolume = 0;
			totalAntal = 0;
		} 
	
		if (continueLoad == false) {
			
			while (totalWeight <= limitWeight && totalVolume <= limitVolume && totalAntal <=limitAntalItems) {
				if (running) {	
					int i = 0;
					foodItems[i] = buffer.readFromBuffer();  
					foodItem = foodItems[i];
					for (ItemListener listener : list) {
						listener.printItem(foodItem.getName(), threadName);
					}
					weight = foodItem.getWeight();
					volume = foodItem.getVolume();

					totalWeight +=weight;
					totalVolume +=volume;
					i +=1;
					totalAntal +=i;	
				} else {
					shutdown();
				}	
			}
		}

	
	
		readMutex.release ( );
		
		
		
		try {
			Thread.sleep ( 200 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  	
	}
	
	public void shutdown() {
		running = false;
	}
	
	/**
	 * Adding listeners to the list.
	 * @param listener Instance of classes implementing the interface ItemListener
	 *  @author Ekaterina Korotetskaya
	 */
	public void addItemListener(ItemListener listener) {
		if (listener != null) {
			list.add(listener);
		}
	}
}

