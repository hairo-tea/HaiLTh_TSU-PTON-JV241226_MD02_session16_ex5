import java.util.Scanner;

public class InputDataHelper {
    public static String inputTitle(Scanner sc) {
        String title;
        do{
            System.out.println("Enter title: ");
            title = sc.nextLine().trim();
            if (!title.isEmpty()){
                return title;
            }
            System.err.println("Please enter a valid title.");
        }while(true);
    }
    public static String inputDescription(Scanner sc) {
        String description;
        do{
            System.out.println("Enter description: ");
            description = sc.nextLine().trim();
            if (!description.isEmpty()){
                return description;
            }
            System.err.println("Please enter a valid description.");
        }while(true);
    }
    public static int inputYear(Scanner sc) {
        int year;
        do {
            System.out.println("Enter year: ");
            try {
                year = Integer.parseInt(sc.nextLine().trim());
                return year;
            }catch (NumberFormatException e){
                System.err.println("Please enter a valid year.");
            }
        }while(true);
    }

}
