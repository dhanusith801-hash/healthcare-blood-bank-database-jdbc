import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BloodBankDAO dao = new BloodBankDAO();

        while (true) {
            System.out.println("\n===== Healthcare Blood Bank Database =====");
            System.out.println("1. Add Donor");
            System.out.println("2. View Donors");
            System.out.println("3. Add Recipient");
            System.out.println("4. View Recipients");
            System.out.println("5. Add/Update Blood Stock");
            System.out.println("6. View Blood Stock");
            System.out.println("7. Issue Blood");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter donor name: ");
                    String dName = sc.nextLine();
                    System.out.print("Enter age: ");
                    int dAge = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter gender: ");
                    String dGender = sc.nextLine();
                    System.out.print("Enter blood group: ");
                    String dBloodGroup = sc.nextLine();
                    System.out.print("Enter phone: ");
                    String dPhone = sc.nextLine();
                    System.out.print("Enter address: ");
                    String dAddress = sc.nextLine();

                    Donor donor = new Donor(dName, dAge, dGender, dBloodGroup, dPhone, dAddress);
                    dao.addDonor(donor);
                    break;

                case 2:
                    dao.viewDonors();
                    break;

                case 3:
                    System.out.print("Enter recipient name: ");
                    String rName = sc.nextLine();
                    System.out.print("Enter age: ");
                    int rAge = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter gender: ");
                    String rGender = sc.nextLine();
                    System.out.print("Enter blood group: ");
                    String rBloodGroup = sc.nextLine();
                    System.out.print("Enter phone: ");
                    String rPhone = sc.nextLine();
                    System.out.print("Enter disease: ");
                    String rDisease = sc.nextLine();

                    Recipient recipient = new Recipient(rName, rAge, rGender, rBloodGroup, rPhone, rDisease);
                    dao.addRecipient(recipient);
                    break;

                case 4:
                    dao.viewRecipients();
                    break;

                case 5:
                    System.out.print("Enter blood group: ");
                    String bGroup = sc.nextLine();
                    System.out.print("Enter units to add: ");
                    int units = sc.nextInt();
                    sc.nextLine();

                    BloodStock stock = new BloodStock(bGroup, units);
                    dao.addOrUpdateBloodStock(stock);
                    break;

                case 6:
                    dao.viewBloodStock();
                    break;

                case 7:
                    System.out.print("Enter recipient ID: ");
                    int recipientId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter blood group: ");
                    String issueBloodGroup = sc.nextLine();
                    System.out.print("Enter units to issue: ");
                    int issueUnits = sc.nextInt();
                    sc.nextLine();

                    dao.issueBlood(recipientId, issueBloodGroup, issueUnits);
                    break;

                case 8:
                    System.out.println("Exiting program...");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}