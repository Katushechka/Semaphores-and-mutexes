package Modul3;

import java.util.concurrent.Semaphore;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Controller extends Thread {
	private Producer producer;
	private Buffer buffer;
	private final int numOfProducer = 3;
	private final int numOfCunsumer = 3;
	private JLabel lblStatusScan;
	private JTextArea lstIca;
	private GUISemaphore ui;
	private Thread newThreadIca;
	private Thread newThreadCoop;
	private Thread newThreadCityGross;
	private Thread newThreadScan;
	private Thread newThreadArla;
	private Thread newThreadAxFood;
	private volatile boolean running = true;
	private Consumer consumer;
	private View view;
		
	public Controller (GUISemaphore ui) {
		buffer = new Buffer(ui);	
		this.ui = ui;
	}
	
	
	public void startThreadScanProducer(boolean start) {

			newThreadScan = new Producer (buffer); 
			if (start == true) {
				newThreadScan.start();
			} 
	}
	
	public void stopScanProducer() {
		newThreadScan.suspend();
	}
	
	
	public void startThreadArlaProducer(boolean start) {		
		newThreadArla = new Producer (buffer); 		
		if (start == true) {
			newThreadArla.start();	
		}
	}
	
	public void stopArlaProducer() {
		newThreadArla.suspend();

	}
	
	
	public void starThreadAxFoodProducer(boolean start) {		
		newThreadAxFood = new Producer (buffer); 
		if (start == true) {
			newThreadAxFood.start();	
		}
	}
	
	public void stopAxFoodProducer() {
		newThreadAxFood.suspend();
	}

	
	
	
	public void startIcaConsumer(boolean continueLoad, int limitAntalItems, double limitWeight, double limitVolume) {
		newThreadIca = new Consumer(continueLoad, buffer, ui, limitAntalItems, limitWeight, limitVolume, "ica");
		newThreadIca.start();	
		view = new View(ui, (Consumer) newThreadIca);
	}
	
	public void stopIcaConsumer() {
		((Consumer) newThreadIca).shutdown();
	}
	
	public void startCoopConsumer(boolean continueLoad, int limitAntalItems, double limitWeight, double limitVolume) {
		newThreadCoop = new Consumer(continueLoad, buffer, ui, limitAntalItems, limitWeight, limitVolume, "coop");
		newThreadCoop.start();	
		view = new View(ui, (Consumer) newThreadCoop);
	}
	
	public void stopCoopConsumer() {
		((Consumer) newThreadCoop).shutdown();
	}
	
	public void startCityGrossConsumer(boolean continueLoad, int limitAntalItems, double limitWeight, double limitVolume) {
		newThreadCityGross = new Consumer(continueLoad, buffer, ui, limitAntalItems, limitWeight, limitVolume, "cityGross");
		newThreadCityGross.start();	
		view = new View(ui, (Consumer) newThreadCityGross);
	}
	
	public void stopCityGrossConsumer() {
		((Consumer) newThreadCityGross).shutdown();
	}
	
}

