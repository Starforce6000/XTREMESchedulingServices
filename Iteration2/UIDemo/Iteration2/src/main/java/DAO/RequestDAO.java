package DAO;

import Enums.Day;
import Enums.RequestStatus;
import Enums.RequestType;
import Enums.Shift;
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
                String[] data = line.split(",");

                r.setStatus(RequestStatus.valueOf(data[0]));
                r.setType(RequestType.valueOf(data[1]));
                RequestDay reqD = new RequestDay();
                reqD.setDay(Day.valueOf(data[2]));
                reqD.setShift(Shift.valueOf(data[3]));
                r.setDay(reqD);
                r.setReason(data[4]);

                requestList.add(r);
            }
        }catch(IOException e) {
            throw e;
        }

        return requestList;
    }

    //testing DAO
    /*
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world");
        RequestDAO dao = new RequestDAO();
        ArrayList<Request> temp = dao.loadRequestsFromFile(new File ("requests.csv"));

        for(Request r : temp) {
            System.out.println(r.printRequest());
        }
    }
     */
}
