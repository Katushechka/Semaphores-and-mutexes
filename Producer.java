package Modul3;

import java.util.concurrent.Semaphore;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;



public class Producer extends Thread{
	private Controller controller;	
	private FoodItem foodItem;
	private Buffer buffer;
	private Semaphore semaphore;
	private static FoodItem [] foodItems = new FoodItem [16];
	private boolean runProducer = true;
	
	
	public Producer (Buffer buffer) {
		this.buffer = buffer;		
		semaphore = new Semaphore(1);
	}
	
	public void run() {
		
			try {
				produce();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
	
	public void produce() throws InterruptedException {
		initFoodItems();
		while (runProducer) { 
			if (buffer.isRunning()) {
				
				//acquire lock to exclude all other consumer
				semaphore.acquire();				
				
				Random rand = new Random();
				int randomValue = rand.nextInt(14); // from 0 till 13
				foodItem = foodItems[randomValue];
		 
			
				//Put the value into the buffer
				buffer.WriteToBuffer(foodItem);
				
				
				//release lock
				semaphore.release();
			}
			Thread.sleep ( 300 );      //To be able to see the results on the screen 
			
			
		}
	}
	

	
	private static FoodItem[] initFoodItems() {

		foodItems[0] = new FoodItem(1.1, 0.5, "Milk");	//double weight, double volume, String name
		foodItems[1] = new FoodItem(0.6, 0.1, "Cream");
		foodItems[2] = new FoodItem(1.1, 0.5, "Youghurt");
		foodItems[3] = new FoodItem(2.34, 0.66, "Butter");	
		foodItems[4] = new FoodItem(3.4, 1.2, "Flower");
		foodItems[5] = new FoodItem(3.7, 1.8, "Sugar");
		foodItems[6] = new FoodItem(1.55, 0.27, "Salt");
		foodItems[7] = new FoodItem(0.6, 0.19, "Almonds");
		foodItems[8] = new FoodItem(1.98, 0.75, "Bread");
		foodItems[9] = new FoodItem(1.4, 0.5, "Donuts");
		foodItems[10] = new FoodItem(1.3, 1.5, "Jam");
		foodItems[11] = new FoodItem(1.3, 1.5, "Ham");
		foodItems[12] = new FoodItem(6.8, 3.6, "Chicken");
		foodItems[13] = new FoodItem(0.87, 0.55, "Salat");
		foodItems[14] = new FoodItem(2.46, 0.29, "Orange");
		foodItems[15] = new FoodItem(2.44, 0.4, "Apple");
		return foodItems;
	}	
}

