package Model;

import Controller.DBController;
import Controller.Controller;
import View.GUIFacilitator;


import javafx.stage.FileChooser;
import java.math.BigDecimal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVImport {
    private DBController dbController;

    public CSVImport(DBController dbController) {
        this.dbController = dbController;

    }

    public void readProductFromCSVFile(String filename) {

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line = br.readLine();

            while (line != null) {

                String[] data = line.split(";"); //Separatorn för datan som införs i kolumner
                createProductCSV(data);

                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createProductCSV(String[] CSVData){

        String name = CSVData[0];
        int stock = Integer.parseInt(CSVData[1]);
        BigDecimal price = new BigDecimal(CSVData[2]);
        String category = (CSVData[3]);
        String shelfPosition = CSVData[4];
        String supplier = (CSVData[5]);
        BigDecimal cost = new BigDecimal(CSVData[6]);
        dbController.addProduct(name, stock, price ,Integer.parseInt(category),shelfPosition,Integer.parseInt(supplier),cost);
    }
}