package ItpecB;

public class Ticket {
	public static int ticketPrice(int age, boolean isMember) {
        int ret;

        if (age <= 10 || age >= 60 || isMember) {
            ret = 10; 
        } else {
            ret = 20; 
        }

        return ret;
    }
	

	public static void main(String[] args) {
        // Example test cases
        System.out.println("Age 8, not a member: $" + ticketPrice(8, false));      // $10
        System.out.println("Age 35, is a member: $" + ticketPrice(35, true));     // $10
        System.out.println("Age 30, not a member: $" + ticketPrice(30, false));   // $20
        System.out.println("Age 65, not a member: $" + ticketPrice(65, false));   // $10
    }

}
