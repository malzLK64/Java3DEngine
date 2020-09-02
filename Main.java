import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
 
public class Main extends JFrame implements ActionListener {
    JButton start;
    JButton stop;
    JButton finish;
    JLabel timeClockText;
    JLabel saveSettings;
    JTextArea pathInput;
    
    public static String startingTime;
    public static String endingTime;
    
    public String csvFile = "";
    public BufferedReader br = null;
    public String line = "";
    String csvSplitBy = ",";
    
    public Main(){
    	
    	//Create window
        this.setTitle("Stechuhr");
        this.setSize(600, 200);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLayout(null);
        
        //Create panels
        JPanel timeClock = new JPanel();
        JPanel overview = new JPanel();
        JPanel settings = new JPanel();
        JLabel timeClockText = new JLabel();
        
        //Create objects for timeClock
        JButton start = new JButton("Start");
        JButton stop = new JButton ("Stop");
        JButton finish = new JButton ("Finish");
        JLabel pathLable = new JLabel("Pfad");
        JTextField pathInput = new JTextField("", 15);
        JButton saveSettings = new JButton("Speichern");
        
        //Create tabbedPane
        JTabbedPane tabbedPane = new JTabbedPane
                (JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT );
        
        //Add objects to timeClock
        timeClock.add(start);
        timeClock.add(stop);
        timeClock.add(finish);
        timeClock.add(timeClockText);

        
        //Add objects to settings
        settings.add(pathLable);
        settings.add(pathInput);
        
        //Add action listener to the objects
        start.addActionListener(this);
        stop.addActionListener(this);
        finish.addActionListener(this);
        saveSettings.addActionListener(this);
        
        //Add panels to tabbedPane
        tabbedPane.addTab("Stechuhr", timeClock);
        tabbedPane.addTab("Übersicht", overview);
        tabbedPane.addTab("Einstellungen", settings);
        
        //Change size of window dependent on active panel
        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(tabbedPane.getSelectedIndex() == 0) {
                	Main.this.setSize(600, 200);
                }else if(tabbedPane.getSelectedIndex() == 1 || tabbedPane.getSelectedIndex() == 2) {
                	Main.this.setSize(600,700);
                }
            }
        });
        
        //Add tabbedPane to window
        this.add(tabbedPane);
    }
    
    public void actionPerformed (ActionEvent ae){
    	//If button start was pressed set startingTime to current time
        if(ae.getSource() == this.start){
        	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");  
        	LocalDateTime now = LocalDateTime.now();  
        	startingTime = dtf.format(now);
        	timeClockText.setText("Starting time: " + startingTime);
        	endingTime = null;
        	start.setText("Reset");
        	
        }
      //If button stop was pressed set endingTime to current time
        else if(ae.getSource() == this.stop){
	        if(startingTime != null) {	
        		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");  
	        	LocalDateTime now = LocalDateTime.now();  
	        	endingTime = dtf.format(now);
	        	timeClockText.setText("Starting Time: " + startingTime + "\n Ending time: " + endingTime);
	        }else {
	        	timeClockText.setText("Bitte zuerst Starten.");
	        }
        }
        //If button finish was pressed calculate time difference and the amount of money made in time
        else if (ae.getSource() == this.finish){
        	if(startingTime != null && endingTime != null) {
        		
        		String dateStart = startingTime;
        		String dateStop = endingTime;

        		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        		Date d1 = null;
        		Date d2 = null;

        		try {
        			d1 = format.parse(dateStart);
        			d2 = format.parse(dateStop);

        			long diff = d2.getTime() - d1.getTime();

        			double diffMinutes = diff / (60 * 1000) % 60;
        			double diffHours = diff / (60 * 60 * 1000) % 24;
        			
        			double diffHM = diffHours + diffMinutes/10;
        			
        			double moneyMade = diffHours * 7.5 + diffMinutes * 0.125;
        			
        			timeClockText.setText("Gearbeitete Zeit: " + diffHM + "    Verdientes Geld: " + moneyMade);
        			
        			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
    	        	LocalDateTime now = LocalDateTime.now();  
    	        	endingTime = dtf.format(now);

        			String fileString = new String(now + " - " + diffHM + " - " + moneyMade + "\n");
        			
        			int remuneration = 0;
        			int workedTime = 0;
        			
        			String str = "Verdientes Geld: " +  String.valueOf(remuneration) + " Gearbeitete Stunden: " + String.valueOf(workedTime) + "\n";
        			
        			
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
        	}
        //If button saveSetting was pressed get Text in pathInput and save it in csvFile
        }else if(ae.getSource() == this.saveSettings) {
        	csvFile = pathInput.getText();
        }
    }
    
    //Read CSV file
    public void csvReader() {
    	try {
    		br = new BufferedReader (new FileReader(csvFile));
    		while((line = br.readLine()) !=null) {
    			String[] country = line.split(csvSplitBy);
    			
    			System.out.println("Country [code=" + country[4] + ", name=" + country[5] + "]");
    			
    		}
    		
    		}catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }	
    	}

	public static void main(String[] args) {
		Main main = new Main();
		//main.csvReader();
        main.setVisible(true);
    }
}