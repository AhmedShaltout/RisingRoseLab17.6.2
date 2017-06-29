package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Print;
import classes.Result;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;

public class PrintControl implements Initializable {
	
	@FXML
	VBox printedBox;
	private String name="";
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		printedBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY,Insets.EMPTY)));
		makeSeparatedGrid();
		
	}
	
	public void print(Event event) {
		Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.getDefaultPageLayout();
        double pWidth = pageLayout.getPrintableWidth();
        double pHeight = pageLayout.getPrintableHeight();
        double nWidth = printedBox.getBoundsInParent().getWidth();
        double nHeight = printedBox.getBoundsInParent().getHeight();
        double widthLeft = pWidth - nWidth;
        double heightLeft = pHeight - nHeight;
        double scale = 0;
        if (widthLeft < heightLeft) {
            scale = pWidth / nWidth;
        } else {
            scale = pHeight / nHeight;
        }
        printedBox.getTransforms().add(new Scale(scale, scale));
		PrinterJob job = PrinterJob.createPrinterJob();
		if (job != null) {
			boolean printed = job.printPage(printedBox);
			if (printed) {
				job.endJob();
				Print.win.close();
			}
		}
	}
	
	public void cancelPrinting(Event event){
		Print.win.close();
	}

	private void makeSeparatedGrid() {
		ArrayList<Result> separated=WorkPanel.separatedTests;
		ArrayList<ArrayList<Result>> grouped=WorkPanel.groupedTests;
		ArrayList<String> groupedNames=WorkPanel.namesGroup;
		if(!separated.isEmpty()){
			int s=separated.size()/9;
			ArrayList<Result>print =new ArrayList<>();
			if(s>=9){
				for(int z=0;z<9;z++){
					print.add(separated.remove(z));
				}
				createGrid(print,false);
				if(!separated.isEmpty())
					new Print();
			}else{
				for (Result result : separated) {
					print.add(result);
				}
				separated.removeAll(print);
				createGrid(print,false);
				new Print();
			}
		}
		if(!grouped.isEmpty()){
			if(!grouped.get(0).isEmpty()){
				name=groupedNames.remove(0);
				createGrid(grouped.remove(0),true);
				if(!grouped.isEmpty())
					new Print();
			}
		}
	}

	private void createGrid(ArrayList<Result> arrayList,boolean isGroup) {
		GridPane gridPane=new GridPane();
		int row=0;
		if(isGroup){
			Label l= new Label(name);
			l.setWrapText(true);
			l.setMinHeight(40);
			l.setFont(Font.font(18));
			gridPane.add(l, 0, row);
			row++;
		}
		for (Result result :arrayList ) {
			Label l= new Label(result.getTestName());
			l.setWrapText(true);
			l.setMinHeight(40);
			l.setFont(Font.font(18));
			Label l2= new Label(result.getTestValue());
			l2.setWrapText(true);
			l2.setFont(Font.font(18));
			gridPane.add(l, 0, row);
			gridPane.add(l2, 1, row);
			row++;
		}
		gridPane.setHgap(150);
		gridPane.setPadding(new Insets(0,0,0,70));
		printedBox.getChildren().add(gridPane);
	}

}
