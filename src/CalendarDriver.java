import java.util.Scanner;

public class CalendarDriver
{
    public static void main(String[] args)
    {
        int option = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the corresponding option number on your keyboard to use menu commands.");
        
        
        while (option != 4)
        {
            System.out.println("Calendar Event Scheduler main menu:");
            System.out.println("1) Go to create a Calendar event menu\n" +
                               "2) Go to create free time blocks in Calendar\n" +
                               "3) Go to create meeting times in Calendar\n" +
                               "4) Quit calendar program\n\n");
            
            System.out.print("Please enter the option number of the desired action: ");
            option = in.nextInt();
            
            if(option == 1)
                menuOption1();
            else if(option == 2)
                menuOption2();
            else if(option == 3)
                menuOption3();
            else if(option <= 1 || option > 4)
                System.out.print("Invalid option.\n\n");                            
        }
    }
    
    /**
     * Method to present the sub menu for the first option, which allows the user to create ics files.
     *
     */
    public static void menuOption1()
    {
        Scanner in = new Scanner(System.in);
        int option = 0;
        System.out.println("Enter the corresponding option number on your keyboard to use menu commands.");
        
        while (option != 2)
        {
            System.out.println("Create a Calendar event sub-menu:");
            System.out.println("1) Create a Calendar Event\n" + 
                               "2) Return to main menu\n");
                               
            System.out.print("Please enter the option number of the desired action: ");
            option = in.nextInt();
            
            if (option == 1)
            {
                icsEvent event = new icsEvent();
                event.setSummary();
                event.setLocation();
                event.setClassification();
                event.setPriority();
                event.setDtstart();
                event.setDtend();
                event.writeIcsFile(event, (event.getSummary() + ".ics"));
                System.out.println("Calendar event file created in local directory with name " + (event.getSummary().substring(7) + ".ics"));        
            }            
               
        }
    }
    
    /**
     * Method to present the sub menu for the second option, which allows a user to generate free time blocks in their calendar
     *
     */
    public static void menuOption2()
    {
        Scanner in = new Scanner(System.in);
        int option = 0;
        String filename;
        FreeTimeGenerator daysEvents = new FreeTimeGenerator();
        
        System.out.println("Enter the corresponding option number on your keyboard to use menu commands.");
        
        while (option != 4)
        {

            System.out.println("In order to create the free time files, add your day's events either by creating new ones, or reading them in from file.");
            System.out.println("Create free time blocks sub-menu:");
            System.out.println("1) Create a new event\n" + 
                               "2) Add an event from file\n" +
                               "3) Generate free time file\n" +
                               "4) Return to main menu\n");
                               
            System.out.print("Please enter the option number of the desired action: ");
            option = in.nextInt();
            
            if (option == 1)
            {
                icsEvent event = new icsEvent();
                event.setSummary();
                event.setLocation();
                event.setClassification();
                event.setPriority();
                event.setDtstart();
                event.setDtend();
                event.writeIcsFile(event, (event.getSummary() + ".ics"));
                System.out.println("Calendar event file created in local directory with name " + (event.getSummary().substring(7) + ".ics"));        
                
                if (daysEvents.addIcsEvent(event))
                {
                    System.out.println("Added newly created event to your day's events.");
                }
                else
                {
                    System.out.println("Failed to add the newly created event to your day's events.\n" +
                                       "In order to generate free time blocks, your events must all be scheduled on the same day, please try again.\n");
                }
            }
            
            else if (option == 2)
            {
                //Refresh System.in
                in.nextLine();
                
                System.out.println("Please enter the file name of your event file, if the file name is incorrect, please try again from the menu.");
                filename = in.nextLine();
                daysEvents.addIcsEvent(daysEvents.readIcs(filename));
            }
            
            else if (option == 3)
            {
                daysEvents.sortEvents(daysEvents.getEventList());
                daysEvents.writeFreeTime(daysEvents.getEventList());
            }            
               
        }
    }
    
     /**
     * Method to present the sub menu for the third option, which allows a user to generate a possible meeting time with
     another person
     *
     */
    public static void menuOption3()
    {
        Scanner in = new Scanner(System.in);
        int option = 0;
        int subOption = 0;
        String filename;
        FreeTimeGenerator person1 = new FreeTimeGenerator();
        FreeTimeGenerator person2 = new FreeTimeGenerator();
          
       
        while (option != 4)
        {
          
          System.out.println("In order to create possible meeting times, add events either by creating new ones, or reading them in from file.");
          System.out.println("Create free time blocks sub-menu:");
          System.out.println("1) Create a new event\n" + 
                             "2) Add an event from file\n" +
                             "3) Generate meeting time files\n" +
                             "4) Return to main menu\n");
                                 
          System.out.print("Please enter the option number of the desired action: ");
          option = in.nextInt();
          
            if (option == 1)
            {
                icsEvent event = new icsEvent();
                event.setSummary();
                event.setLocation();
                event.setClassification();
                event.setPriority();
                event.setDtstart();
                event.setDtend();
                event.writeIcsFile(event, (event.getSummary() + ".ics"));
                System.out.println("Calendar event file created in local directory with name " + (event.getSummary().substring(7) + ".ics"));        
                
                System.out.println("Is this an event for:\n" +
                                    "1) Person1\n"+
                                    "2) Person2\n");
                
                option = in.nextInt();
                                      
                if( option == 1){
                
                    if (person1.addIcsEvent(event))
                    {
                        System.out.println("Added newly created event to day's events of Person1.");
                    }
                }
                else if(option == 2){

                
                    if (person2.addIcsEvent(event))
                    {
                        System.out.println("Added newly created event to day's events of Person2.");
                    }
                }
                
                else
                {
                    System.out.println("Failed to add the newly created event to your day's events.\n" +
                                       "Please try again.\n");
                }
            }
            
            else if (option == 2)
            {
                //Refresh System.in
                in.nextLine();
                System.out.println("Is this an event for:\n" +
                                   "1) Person1\n"+
                                   "2) Person2\n");
                
                subOption = in.nextInt();               
                if(subOption == 1){
                        System.out.println("Please enter the file name of your event file, if the file name is incorrect, please try again from the menu.");
                        in.nextLine();
                        filename = in.nextLine();
                    
                        if (person1.addIcsEvent(person1.readIcs(filename)))
                        {
                            System.out.println("Added newly created event to day's events of Person1.");
                        }
                }
                else if(subOption == 2){
                
                        System.out.println("Please enter the file name of your event file, if the file name is incorrect, please try again from the menu.");
                        in.nextLine();
                        filename = in.nextLine();
                    
                        if (person2.addIcsEvent(person2.readIcs(filename)))
                        {
                            System.out.println("Added newly created event to day's events of Person2.");
                        }
                }

            }
            else if (option == 3)
            {
                System.out.println("Generating possible meeting times...");
                person1.sortEvents(person1.getEventList());
                person2.sortEvents(person2.getEventList());
                person1.writeMeetingTimes(person1.getEventList(), person2.getEventList());
                
                System.out.println("Created files for possible meeting times in local directory.");
            
            }
        }        
    }
}
 
 

 
   
