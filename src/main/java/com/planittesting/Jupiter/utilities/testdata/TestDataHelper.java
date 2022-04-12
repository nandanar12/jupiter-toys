package com.planittesting.Jupiter.utilities.testdata;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestDataHelper {
    InputStream inputStream;

    public TestDataHelper(String inputStream){
        this.inputStream = new ByteArrayInputStream(inputStream.getBytes());
    }

    public List<String[]> getShoppingListItems() throws IOException{

        List<String[]> resultList = new ArrayList<String[]>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                resultList.add(row);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
        return resultList;
    }

}
