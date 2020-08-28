package junit.hamcrest;

public class Person {

    String name;
    String address;

    public Person(String personName, String personAddress) {
        name = personName;
        address = personAddress;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
