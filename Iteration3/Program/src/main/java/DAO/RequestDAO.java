package DAO;

import Enums.Day;
import Enums.RequestStatus;
import Enums.RequestType;
import Enums.Shift;
import Models.Employee;
import Requests.Request;
import Requests.RequestDay;

import java.io.*;
import java.util.ArrayList;

public class RequestDAO {
    private ArrayList<Employee> employees = new ArrayList<>();

    public RequestDAO(ArrayList<Employee> employees){
        this.employees.addAll(employees);
    }
    public ArrayList<Request> loadRequestsFromFile(File file) throws IOException {
        ArrayList<Request> requestList = new ArrayList<>();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = "";

            //skip header line
            line = reader.readLine();

            while((line = reader.readLine()) != null) {
                Request r = new Request();
                String[] data = line.split(",");
                r.getEmp().setName(data[0]);
                r.setStatus(RequestStatus.valueOf(data[1]));
                r.setType(RequestType.valueOf(data[2]));
                RequestDay reqD = new RequestDay();
                reqD.setDay(Day.valueOf(data[3]));
                reqD.setShift(Shift.valueOf(data[4]));
                r.setDay(reqD);
                r.setReason(data[5]);
                r.setId(Integer.parseInt(data[6]));

                requestList.add(r);
            }
            reader.close();
        }catch(IOException e) {
            throw e;
        }

        return requestList;
    }

    public void saveRequestsToFile(File file, ArrayList<Request> reqs) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("id,status,type,day,shift,reason,request,request id\n");
            for(Request r : reqs) {
                writer.write(r.printRequest());
                writer.write('\n');
            }
            writer.close();
        } catch(IOException e) {
            throw e;
        }
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
