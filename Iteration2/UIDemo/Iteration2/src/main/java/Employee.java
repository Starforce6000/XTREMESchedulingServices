import java.util.LinkedList;
import java.util.List;

public class Employee {
    String name;
    Department department;
    List<Availability> availabilityList = new LinkedList<Availability>();

    public List<Availability> getAvailability() {
        return availabilityList;
    }

    public String getName() {
        return name;
    }
}