package DAO;

import Requests.Request;
import Requests.RequestDay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RequestDAO {
    public ArrayList<Request> loadRequestsFromFile(File file) throws IOException {
        ArrayList<Request> requestList = new ArrayList<>();
        BufferedReader reader = null;

        //TODO: implement lol
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = "";

            //skip header line
            line = reader.readLine();

            while((line = reader.readLine()) != null) {
                Request r = new Request();
            }
        }catch(IOException e) {
            throw e;
        }

        return requestList;
    }
}
