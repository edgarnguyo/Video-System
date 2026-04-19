package Backend;

public class Customer {
    private String name;
    private int phone;
    private String email;

    public Customer(String name, int phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    @Override
    public String toString() {
        return name;
    }
}
