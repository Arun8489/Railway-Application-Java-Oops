import java.util.Scanner;

public class main 
{
	  public static void main(String[] args)
	    {
	        Scanner s = new Scanner(System.in);
	        boolean loop = true;
	        //loop to get choices from user until he stops
	        while(loop)
	        {
	            System.out.println(" 1. Book Ticket \n 2. Cancel Ticket \n 3. Available Tickets \n 4. Booked Tickets \n 5. Exit");
	            int choice = s.nextInt();
	            switch(choice)
	            {
	                // book ticket
	                case 1:
	                {
	                    //get details from Passenger
	                	System.out.println("------Booking A Ticket------");
	                    System.out.println("Enter Passenger name : ");
	                    String name = s.next();
	                    System.out.println("Enter Passenger age : ");
	                    int age = s.nextInt();
	                    System.out.println("Enter Berth preference [L or M or U]"); //get berth preference (L,U,M)
	                    String berthPreference = s.next();
	                    //create a passenger object
	                    Passenger p = new Passenger(name,age,berthPreference);
	                    //booking
	                    bookTicket(p);
	                }
	                break;
	                //cancel ticket
	                case 2:
	                {
	                    //get passenger id to cancel
	                    System.out.println("Enter passenger Id to cancel");
	                    int id = s.nextInt();
	                    cancelTicket(id);
	                }
	                break;
	                //available tickets print
	                case 3:
	                {
	                    Ticket_Booker booker = new Ticket_Booker();
	                    booker.printAvailable();
	                }
	                break;
	                //occupied tickets print
	                case 4:
	                {
	                    Ticket_Booker booker = new Ticket_Booker();
	                    booker.printPassengers();
	                }
	                break;
	                //exit
	                case 5:
	                {
	                    loop = false;
	                }
	                break;
	                default:
	                break;
	            }
	        }
	        
	    }
	  public static void bookTicket(Passenger p)
      {
      	Ticket_Booker booker = new Ticket_Booker();
      	
        //if no WL is available , means all tickets are filled.. so no tickets available
      	if(Ticket_Booker.availableWaitingList == 0)
      	{
      		System.out.println("Oops!! Sorry :) No Tickets Available");
      		return;
      	}
      	// Checking if the preferred berth is available
          	if( (p.berthPreference.equals("L") && Ticket_Booker.availableLowerBerths > 0) ||
      			(p.berthPreference.equals("M") && Ticket_Booker.availableMiddleBerths > 0) || 
                (p.berthPreference.equals("U") && Ticket_Booker.availableUpperBerths > 0))
      			{
      		       System.out.println(" -----Prefered Berth Available-----");
      		       
      		       if(p.berthPreference.equals("L"))
      		       {
      		    	   System.out.println("Lower Berth Given");
      		    	   // call booking function in the ticket booker class
      		    	   booker.bookTicket(p,   (Ticket_Booker.lowerBerthsPositions.get(0)), "L");
      		    	   Ticket_Booker.lowerBerthsPositions.remove(0);
      		    	   Ticket_Booker.availableLowerBerths--;
      		       }
      		       
      		       else if(p.berthPreference.equals("M"))
    		       {
    		    	   System.out.println("Middle Berth Given");
    		    	   // call booking function in the ticket booker class
    		    	   booker.bookTicket(p,(Ticket_Booker.middleBerthsPositions.get(0)),"M");
    		    	   Ticket_Booker.middleBerthsPositions.remove(0);
    		    	   Ticket_Booker.availableMiddleBerths--;
    		       }

      		       else if(p.berthPreference.equals("U"))
    		       {
    		    	   System.out.println("Upper Berth Given");
    		    	   // call booking function in the ticket booker class
    		    	   booker.bookTicket(p,(Ticket_Booker.upperBerthsPositions.get(0)),"U");
    		    	   Ticket_Booker.upperBerthsPositions.remove(0);
    		    	   Ticket_Booker.availableUpperBerths--;
    		       }
      			}
            else if(Ticket_Booker.availableLowerBerths > 0)
            {
                System.out.println("Lower Berth Given");
                //call booking function in the TicketBooker class
                booker.bookTicket(p,(Ticket_Booker.lowerBerthsPositions.get(0)),"L");
                //remove the booked position from available positions and also decrease available seats of that
                // particular type
                Ticket_Booker.lowerBerthsPositions.remove(0);
                Ticket_Booker.availableLowerBerths--;
                

            }
            else if(Ticket_Booker.availableMiddleBerths > 0)
            {
                System.out.println("Middle Berth Given");
                //call booking function in the TicketBooker class
                booker.bookTicket(p,(Ticket_Booker.middleBerthsPositions.get(0)),"M");
                //remove the booked position from available positions and also decrease available seats of that
                // particular type
                Ticket_Booker.middleBerthsPositions.remove(0);
                Ticket_Booker.availableMiddleBerths--;

            }
            else if(Ticket_Booker.availableUpperBerths > 0)
            {
                System.out.println("Upper Berth Given");
                //call booking function in the TicketBooker class
                booker.bookTicket(p,(Ticket_Booker.upperBerthsPositions.get(0)),"U");
                //remove the booked position from available positions and also decrease available seats of that
                // particular type
                Ticket_Booker.upperBerthsPositions.remove(0);
                Ticket_Booker.availableUpperBerths--;
                
            }
            else if(Ticket_Booker.availableRacTickets > 0)
            {
                System.out.println("RAC available");
                booker.addToRAC(p,(Ticket_Booker.racPositions.get(0)),"RAC" );
            }
            // if no RAC available go to WL
            else if(Ticket_Booker.availableWaitingList > 0)
            {
                System.out.println("Added to Waiting List");
                booker.addToWaitingList(p,(Ticket_Booker.waitingListPositions.get(0)),"WL");
                
            }
      	
      }
	  public static void cancelTicket(int id)
	    {
	        Ticket_Booker booker = new Ticket_Booker();
	        //check if passenger id is valid
	        if(!booker.passengers.containsKey(id))
	        {
	            System.out.println("Passenger detail Unknown");
	        }
	        else
	            booker.cancelTicket(id);
	    }
}







