package Modul3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

/**
 * The GUI for assignment 3
 */


public class GUISemaphore extends JPanel implements ActionListener {
	private Controller controller = new Controller(this);
	private int icaLimitItems = 15;
	private double icaLimitWeight = 27.00; //3.00;
	private double icaLimitVolume = 12.50;
	private int coopLimitItems = 12;
	private double coopLimitWeight = 31.50; 
	private double coopLimitVolume = 10.20;
	private int cityGrossLimitItems = 19;
	private double cityGrossLimitWeight = 22.30;
	private double cityGrossLimitVolume = 17.00;
	private int inPos;
	private int outPos;
	/**
	 * These are the components you need to handle.
	 * You have to add listeners and/or code
	 * Static controls are defined inline
	 */
	private JFrame frame;				// The Main window
	private JProgressBar bufferStatus;	// The progressbar, showing content in buffer
	
	// Data for Producer Scan
	private JButton btnStartS;			// Button start Scan
	private JButton btnStopS;			// Button stop Scan
	private JLabel lblStatusS;			// Status Scan
	// DAta for producer Arla
	private JButton btnStartA;			// Button start Arla
	private JButton btnStopA;			// Button stop Arla
	private JLabel lblStatusA;			// Status Arla
	//Data for producer AxFood
	private JButton btnStartX;			// Button start AxFood
	private JButton btnStopX;			// Button stop AxFood
	private JLabel lblStatusX;			// Status AxFood
	
	// Data for consumer ICA
	private JLabel lblIcaItems;			// Ica limits
	private JLabel lblIcaWeight;
	private JLabel lblIcaVolume;
	private JLabel lblIcaStatus;		// load status                           
	private JTextArea lstIca;			// The cargo list
	private JButton btnIcaStart;		// The buttons
	private JButton btnIcaStop;
	private JCheckBox chkIcaCont;		// Continue checkbox
	//Data for consumer COOP
	private JLabel lblCoopItems;
	private JLabel lblCoopWeight;
	private JLabel lblCoopVolume;
	private JLabel lblCoopStatus;		// load status
	private JTextArea lstCoop;			// The cargo list
	private JButton btnCoopStart;		// The buttons
	private JButton btnCoopStop;
	private JCheckBox chkCoopCont;		// Continue checkbox
	// Data for consumer CITY GROSS
	private JLabel lblCGItems;
	private JLabel lblCGWeight;
	private JLabel lblCGVolume;
	private JLabel lblCGStatus;			// load status
	private JTextArea lstCG;			// The cargo list
	private JButton btnCGStart;			// The buttons
	private JButton btnCGStop;
	private JCheckBox chkCGCont;		// Continue checkbox
	
	/**
	 * Constructor, creates the window
	 */
	public GUISemaphore()	{
	}
	
	/**
	 * Starts the application
	 */
	public void start()
	{
		frame = new JFrame();
		frame.setBounds(0, 0, 730, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Food Supply System");
		InitializeGUI();					// Fill in components
		frame.setVisible(true);
		frame.setResizable(false);			// Prevent user from change size
		frame.setLocationRelativeTo(null);	// Start middle screen
	}
	
	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI()
	{
		// First create the three main panels
		JPanel pnlBuffer = new JPanel();
		pnlBuffer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Storage"));
		pnlBuffer.setBounds(13, 403, 693, 82);
		pnlBuffer.setLayout(null);
		// Then create the progressbar, only component in buffer panel
		bufferStatus = new JProgressBar();
		bufferStatus.setMaximum(49);
		bufferStatus.setMinimum(0);
		bufferStatus.setValue(0);
		bufferStatus.setStringPainted(true);
		bufferStatus.setBounds(220, 37, 300, 23);   //155, 37, 500, 23         (x,y,bredd,h??jd)
		bufferStatus.setBorder(BorderFactory.createLineBorder(Color.black));
		bufferStatus.setForeground(Color.GREEN);
		pnlBuffer.add(bufferStatus);
		JLabel lblmax = new JLabel("Max capacity (items): 50");
		lblmax.setBounds(10, 42, 180,13); //10, 42, 126,13   (x,y,bredd,h??jd)
		pnlBuffer.add(lblmax);
		frame.add(pnlBuffer);
		
		JPanel pnlProd = new JPanel();
		pnlProd.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Producers"));
		pnlProd.setBounds(13, 13, 229, 379);
		pnlProd.setLayout(null);
		
		JPanel pnlCons = new JPanel();
		pnlCons.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Consumers"));
		pnlCons.setBounds(266, 13, 440, 379);
		pnlCons.setLayout(null);
		
		// Now add the three panels to producer panel
		JPanel pnlScan = new JPanel();
		pnlScan.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Producer: Scan"));
		pnlScan.setBounds(6, 19, 217, 100);
		pnlScan.setLayout(null);
		
		// Content Scan panel
		btnStartS = new JButton("Start Producing");
		btnStartS.setBounds(10, 59, 125, 23);
		pnlScan.add(btnStartS);
		btnStopS = new JButton("Stop");
		btnStopS.setBounds(140, 59, 65, 23);
		pnlScan.add(btnStopS);
		lblStatusS = new JLabel("Staus Idle/Stop/Producing");
		lblStatusS.setBounds(10, 31, 200, 13);
		pnlScan.add(lblStatusS);
		// Add Scan panel to producers		
		pnlProd.add(pnlScan);
		
		// The Arla panel
		JPanel pnlArla = new JPanel();
		pnlArla.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Producer: Arla"));
		pnlArla.setBounds(6, 139, 217, 100);
		pnlArla.setLayout(null);
		
		// Content Arla panel
		btnStartA = new JButton("Start Producing");
		btnStartA.setBounds(10, 59, 125, 23);
		pnlArla.add(btnStartA);
		btnStopA = new JButton("Stop");
		btnStopA.setBounds(140, 59, 65, 23);
		pnlArla.add(btnStopA);
		lblStatusA = new JLabel("Staus Idle/Stop/Producing");
		lblStatusA.setBounds(10, 31, 200, 13);
		pnlArla.add(lblStatusA);
		// Add Arla panel to producers		
		pnlProd.add(pnlArla);
		
		// The AxFood Panel
		JPanel pnlAxfood = new JPanel();
		pnlAxfood.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Producer: AxFood"));
		pnlAxfood.setBounds(6, 262, 217, 100);
		pnlAxfood.setLayout(null);
		
		// Content AxFood Panel
		btnStartX = new JButton("Start Producing");
		btnStartX.setBounds(10, 59, 125, 23);
		pnlAxfood.add(btnStartX);
		btnStopX = new JButton("Stop");
		btnStopX.setBounds(140, 59, 65, 23);
		pnlAxfood.add(btnStopX);
		lblStatusX = new JLabel("Staus Idle/Stop/Producing");
		lblStatusX.setBounds(10, 31, 200, 13);
		pnlAxfood.add(lblStatusX);
		// Add Axfood panel to producers		
		pnlProd.add(pnlAxfood);
		// Producer panel done, add to frame		
		frame.add(pnlProd);
		
		// Next, add the three panels to Consumer panel
		JPanel pnlICA = new JPanel();
		pnlICA.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Consumer: ICA"));
		pnlICA.setBounds(19, 19,415, 100);
		pnlICA.setLayout(null);
		
		// Content ICA panel
		// First the limits panel
		JPanel pnlLim = new JPanel();
		pnlLim.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Package Limits"));
		pnlLim.setBounds(6, 19, 107, 75);
		pnlLim.setLayout(null);
		JLabel lblItems = new JLabel("Items:");
		lblItems.setBounds(7, 20, 50, 13);
		pnlLim.add(lblItems);
		JLabel lblWeight = new JLabel("Weight:");
		lblWeight.setBounds(7, 35, 50, 13);
		pnlLim.add(lblWeight);
		JLabel lblVolume = new JLabel("Volume:");
		lblVolume.setBounds(7, 50, 50, 13);
		pnlLim.add(lblVolume);
		lblIcaItems = new JLabel("15");
		lblIcaItems.setBounds(60, 20, 47, 13);
		pnlLim.add(lblIcaItems);
		lblIcaWeight = new JLabel("27.00");
		lblIcaWeight.setBounds(60, 35, 47, 13);
		pnlLim.add(lblIcaWeight);
		lblIcaVolume = new JLabel("12.50");
		lblIcaVolume.setBounds(60, 50, 47, 13);
		pnlLim.add(lblIcaVolume);
		pnlICA.add(pnlLim);
		// Then rest of controls
		lstIca = new JTextArea();
		lstIca.setEditable(false);
		JScrollPane spane = new JScrollPane(lstIca);		
		spane.setBounds(307, 16, 102, 69);
		spane.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlICA.add(spane);
		btnIcaStart = new JButton("Start Loading");
		btnIcaStart.setBounds(118, 64, 120, 23);
		pnlICA.add(btnIcaStart);
		btnIcaStop = new JButton("Stop");
		btnIcaStop.setBounds(240, 64, 60, 23);
		pnlICA.add(btnIcaStop);
		lblIcaStatus = new JLabel("Ica stop status here");
		lblIcaStatus.setBounds(118, 16, 150, 23);
		pnlICA.add(lblIcaStatus);
		chkIcaCont = new JCheckBox("Continue load");
		chkIcaCont.setBounds(118, 39, 130, 17);
		pnlICA.add(chkIcaCont);
		// All done, add to consumers panel
		pnlCons.add(pnlICA);
		
		JPanel pnlCOOP = new JPanel();
		pnlCOOP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Consumer: COOP"));
		pnlCOOP.setBounds(19, 139, 415, 100);
		pnlCOOP.setLayout(null);
		pnlCons.add(pnlCOOP);
		
		// Content COOP panel
		// First the limits panel
		JPanel pnlLimC = new JPanel();
		pnlLimC.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Package Limits"));
		pnlLimC.setBounds(6, 19, 107, 75);
		pnlLimC.setLayout(null);
		JLabel lblItemsC = new JLabel("Items:");
		lblItemsC.setBounds(7, 20, 50, 13);
		pnlLimC.add(lblItemsC);
		JLabel lblWeightC = new JLabel("Weight:");
		lblWeightC.setBounds(7, 35, 50, 13);
		pnlLimC.add(lblWeightC);
		JLabel lblVolumeC = new JLabel("Volume:");
		lblVolumeC.setBounds(7, 50, 50, 13);
		pnlLimC.add(lblVolumeC);
		lblCoopItems = new JLabel("12");
		lblCoopItems.setBounds(60, 20, 47, 13);
		pnlLimC.add(lblCoopItems);
		lblCoopWeight = new JLabel("31.50");
		lblCoopWeight.setBounds(60, 35, 47, 13);
		pnlLimC.add(lblCoopWeight);
		lblCoopVolume = new JLabel("10.20");
		lblCoopVolume.setBounds(60, 50, 47, 13);
		pnlLimC.add(lblCoopVolume);
		pnlCOOP.add(pnlLimC);
		// Then rest of controls
		lstCoop = new JTextArea();
		lstCoop.setEditable(false);
		JScrollPane spaneC = new JScrollPane(lstCoop);		
		spaneC.setBounds(307, 16, 102, 69);
		spaneC.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlCOOP.add(spaneC);
		btnCoopStart = new JButton("Start Loading");
		btnCoopStart.setBounds(118, 64, 120, 23);
		pnlCOOP.add(btnCoopStart);
		btnCoopStop = new JButton("Stop");
		btnCoopStop.setBounds(240, 64, 60, 23);
		pnlCOOP.add(btnCoopStop);
		lblCoopStatus = new JLabel("Coop stop status here");
		lblCoopStatus.setBounds(118, 16, 150, 23);
		pnlCOOP.add(lblCoopStatus);
		chkCoopCont = new JCheckBox("Continue load");
		chkCoopCont.setBounds(118, 39, 130, 17);
		pnlCOOP.add(chkCoopCont);
		// All done, add to consumers panel
		pnlCons.add(pnlCOOP);
		
		JPanel pnlCG = new JPanel();
		pnlCG.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Consumer: CITY GROSS"));
		pnlCG.setBounds(19, 262, 415, 100);
		pnlCG.setLayout(null);
		pnlCons.add(pnlCG);
		
		// Content CITY GROSS panel
		// First the limits panel
		JPanel pnlLimG = new JPanel();
		pnlLimG.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Package Limits"));
		pnlLimG.setBounds(6, 19, 107, 75);
		pnlLimG.setLayout(null);
		JLabel lblItemsG = new JLabel("Items:");
		lblItemsG.setBounds(7, 20, 50, 13);
		pnlLimG.add(lblItemsG);
		JLabel lblWeightG = new JLabel("Weight:");
		lblWeightG.setBounds(7, 35, 50, 13);
		pnlLimG.add(lblWeightG);
		JLabel lblVolumeG = new JLabel("Volume:");
		lblVolumeG.setBounds(7, 50, 50, 13);
		pnlLimG.add(lblVolumeG);
		lblCGItems = new JLabel("19");
		lblCGItems.setBounds(60, 20, 47, 13);
		pnlLimG.add(lblCGItems);
		lblCGWeight = new JLabel("22.30");
		lblCGWeight.setBounds(60, 35, 47, 13);
		pnlLimG.add(lblCGWeight);
		lblCGVolume = new JLabel("17.00");
		lblCGVolume.setBounds(60, 50, 47, 13);
		pnlLimG.add(lblCGVolume);
		pnlCG.add(pnlLimG);
		// Then rest of controls
		lstCG = new JTextArea();
		lstCG.setEditable(false);
		JScrollPane spaneG = new JScrollPane(lstCG);		
		spaneG.setBounds(307, 16, 102, 69);
		spaneG.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlCG.add(spaneG);
		btnCGStart = new JButton("Start Loading");
		btnCGStart.setBounds(118, 64, 120, 23);
		pnlCG.add(btnCGStart);
		btnCGStop = new JButton("Stop");
		btnCGStop.setBounds(240, 64, 60, 23);
		pnlCG.add(btnCGStop);
		lblCGStatus = new JLabel("CITY GROSS stop status here");
		lblCGStatus.setBounds(118, 16, 150, 23);
		pnlCG.add(lblCGStatus);
		chkCGCont = new JCheckBox("Continue load");
		chkCGCont.setBounds(118, 39, 130, 17);
		pnlCG.add(chkCGCont);
		// All done, add to consumers panel
		pnlCons.add(pnlCOOP);
		
		// Add consumer panel to frame
		frame.add(pnlCons);
		
		btnStartS.addActionListener(this);
		btnStartA.addActionListener(this);
		btnStartX.addActionListener(this);
		btnStopS.addActionListener(this);
		btnStopA.addActionListener(this);
		btnStopX.addActionListener(this);
							
		btnIcaStart.addActionListener(this);
		chkIcaCont.addActionListener(this);
		btnIcaStop.addActionListener(this);
		
		btnCoopStart.addActionListener(this);
		chkCoopCont.addActionListener(this);
		btnCoopStop.addActionListener(this);
		
		btnCGStart.addActionListener(this);
		chkCGCont.addActionListener(this);
		btnCGStop.addActionListener(this);
	}
	

	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnStartS) {                     // Button start Scan
			controller.startThreadScanProducer(true);
			lblStatusS.setText("Producing");
		}
		if(e.getSource() == btnStartA) { 					// Button start Arla
			controller.startThreadArlaProducer(true);
			lblStatusA.setText("Producing");
		}
		if(e.getSource() == btnStartX) {					// Button start AxFood
			controller.starThreadAxFoodProducer(true);
			lblStatusX.setText("Producing");
		}
		if(e.getSource() == btnStopS) {
			controller.stopScanProducer();
			lblStatusS.setText("Stopped");
		}
		if(e.getSource() == btnStopA) {
			controller.stopArlaProducer();
			lblStatusA.setText("Stopped");
		}
		if(e.getSource() == btnStopX) {
			controller.stopAxFoodProducer();
			lblStatusX.setText("Stopped");
		}
//------------------------------------------------------------------------------------------
//ICA
//------------------------------------------------------------------------------------------
		
		if(e.getSource() == btnIcaStart) {
			if(chkIcaCont.isSelected()) {
				controller.startIcaConsumer(true, icaLimitItems, icaLimitWeight, icaLimitVolume);
			} else {
				controller.startIcaConsumer(false, icaLimitItems, icaLimitWeight, icaLimitVolume);
			}
		}

		
		if(e.getSource() == btnIcaStop) {		
			controller.stopIcaConsumer();			
		}
		
//-----------------------------------------------------------------------------------------
//		COOP
//-----------------------------------------------------------------------------------------
		if(e.getSource() == btnCoopStart) {
			if(chkCoopCont.isSelected()) {
				controller.startCoopConsumer(true, coopLimitItems, coopLimitWeight, coopLimitVolume);
			} else {
				controller.startCoopConsumer(false, coopLimitItems, coopLimitWeight, coopLimitVolume);
			}
		}

		
		if(e.getSource() == btnCoopStop) {		
			controller.stopCoopConsumer();
		}
		
		
//-----------------------------------------------------------------------------------------
//CityGross
//-----------------------------------------------------------------------------------------
		if(e.getSource() == btnCGStart) {
			if(chkCGCont.isSelected()) {
				controller.startCityGrossConsumer(true, cityGrossLimitItems, cityGrossLimitWeight, cityGrossLimitVolume);
			} else {
				controller.startCityGrossConsumer(false, cityGrossLimitItems, cityGrossLimitWeight, cityGrossLimitVolume);
			}
		}

		
		if(e.getSource() == btnCGStop) {		
			controller.stopCityGrossConsumer();
		}
	}

	
//---------------------------------------------------------------------------------------	
	public void icaConsumer (String setText) {
		lstIca.append(setText + "\n");
	}
	public void coopConsumer (String setText) {
		lstCoop.append(setText + "\n");
	}
	public void citygrossConsumer (String setText) {
		lstCG.append(setText + "\n");
	}
	
	public void icaContinueLoad() {
		lstIca.setText(null);	
		lstCoop.setText(null);
		lstCG.setText(null);
	}
	public int intPos (int inPos) {
		this.inPos=inPos;
		return inPos;
	}
	
	public int outPos (int outPos) {
		this.outPos=outPos;
		return outPos;
	}
	
	public void storageShowKapacity (int res) {
		bufferStatus.setValue(res);
	}	
}


