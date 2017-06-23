package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Print;
import classes.Result;
import javafx.collections.ObservableList;
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
	@SuppressWarnings("unchecked")
	private void makeSeparatedGrid() {
		ObservableList<Result> separated=WorkPanel.separatedTests;
		if(separated.size()>9){
			ArrayList<Result> results=new ArrayList<>();
			for (int i = 0; i < 9; i++) {
				results.add(separated.remove(i));
			}
			createGrid(results);
			WorkPanel.separatedTests=separated;
			new Print();
		}
		else{
			createGrid((ArrayList<Result>) separated);
		}
	}

	private void createGrid(ArrayList<Result> separated) {
		GridPane gridPane=new GridPane();
		int row=0;
		for (Result result :separated ) {
			Label l= new Label(result.getTestName());
			l.setWrapText(true);
			l.setMinHeight(40);
			l.setFont(Font.font(18));
			Label l2= new Label(result.getTestValue());
			l2.setWrapText(true);
			l2.setFont(Font.font(18));
			gridPane.add(l, 0, row);
			gridPane.add(l2, 1, row);
			gridPane.setHgap(150);
			gridPane.setPadding(new Insets(0,0,0,70));
			row++;
		}
		printedBox.getChildren().add(gridPane);
	}

}
