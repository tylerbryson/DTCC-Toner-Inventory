package application;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	static ArrayList<Printer> printerlist = new ArrayList<Printer>();
	static ArrayList<Toner> tonerlist = new ArrayList<Toner>();
	static ArrayList<String> convP = new ArrayList<String>();
	static ArrayList<String> convT = new ArrayList<String>();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("StartupGUI.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	static public String fileBrowser() {
		JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "CSV Files", "csv");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
        }
        if(chooser.getSelectedFile() == null) {
        	//System.out.println("Default CSV chosen");
        	return "";
        }
        else
        	return chooser.getSelectedFile().getPath();
	}
	
	public static void fileReader(File f, String fileType) {
    	//Filereader function
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(f));
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine == false) {
                    String[] info = line.split(csvSplitBy);
                    if(fileType.equals("printer"))
                    	printerlist.add(new Printer(Integer.valueOf(info[0]), info[1], info[2], info[3], info[4], info[5], info[6], info[7], info[8], info[9]));
                    else if(fileType.equals("toner"))
                    	tonerlist.add(new Toner(info[0], info[1], info[2], Integer.valueOf(info[3]), Integer.valueOf(info[4]), Integer.valueOf(info[5]), info[6], Integer.valueOf(info[7])));
                    else if(fileType.equals("cross")) {
                    	convP.add(info[0]);
                    	String tempstr0 = "";
                    	for(int i = 1; i < info.length; i++)
                    		tempstr0 += info[i];
                    	convT.add(tempstr0);
                    }
                } else {
                    firstLine = false;
                }
            }

        } catch (FileNotFoundException e) {
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
	static public boolean csvVerify(File f, String fileType) {
		//Verifies that a file meets either printer or toner constructor requirements per each line
		BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        int exceptionTest;
		try {
			br = new BufferedReader(new FileReader(f));
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine == false) {
                    String[] info = line.split(csvSplitBy);
                    if(fileType.equals("printer")) {
                    	if(info.length == 10) {
                    		try{
                    			exceptionTest = Integer.valueOf(info[0]);
                    			return true;
            				}
            				catch(InputMismatchException ex) {
            					return false;
            				}
                    	}else
                    		return false;
                    }else if(fileType.equals("toner")) {
                    	tonerlist.add(new Toner(info[0], info[1], info[2], Integer.valueOf(info[3]), Integer.valueOf(info[4]), Integer.valueOf(info[5]), info[6], Integer.valueOf(info[7])));
                    	if(info.length == 8) {
                    		try{
	                			for(int i = 3; i <= 7; i++)
	                				if(i != 6)
	                					exceptionTest = Integer.valueOf(info[i]);
	                			return true;
	        				}
	        				catch(InputMismatchException ex) {
	        					return false;
	        				}
                    	}else
                    		return false;
                    }
                } else {
                    firstLine = false;
                }
            }
		}catch (FileNotFoundException e) {
			System.out.println("CSV Verification Error: File not found");
			return false;
		}catch (IOException e) {
			System.out.println("CSV Verification Error: IOException");
			return false;
        }finally {
        	if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                	System.out.println("CSV Verification Error: IOException");
                	return false;
                }
            }
        }
		return false;
		
	}
}
