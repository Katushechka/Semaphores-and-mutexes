package Modul3;

public class View  {
	private Consumer newConsumer;
	private GUISemaphore ui;
	
	public View (GUISemaphore ui, Consumer newConsumer ) {
		this.ui = ui;
		this.newConsumer=newConsumer;
		newConsumer.addItemListener(new ItemPrinter());
	}
	

	
	/**
	 * The class Observes Consumer, when the method "printItem" is called it
	 * updates the UIet with the incoming text
	 * 
	 * @author Ekaterina Korotetskaya
	 */
	private class ItemPrinter implements ItemListener {
		public void printItem(String item, String threadName) {
			if (threadName=="ica") {
				ui.icaConsumer(item);
			}
			if (threadName=="coop") {
				ui.coopConsumer(item);
			}
			if (threadName=="cityGross") {
				ui.citygrossConsumer(item);
			}
		}
	}

}

