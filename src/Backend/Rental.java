package Backend;

public class Rental {
      private Customer customer;
    private Movie movie;
    private boolean isReturned;

    public Rental(Customer customer, Movie movie){
        this.customer = customer;
        this.movie = movie;
        this.isReturned = false;
    }

     public Customer getCustomer() {
        return customer;
    }

    public Movie getMovie() {
        return movie;
    }
    public boolean getIsReturned() {
        return isReturned;
    }

    public void returnRental(){
        isReturned = true;
    }
    
}
