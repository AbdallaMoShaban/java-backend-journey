public static void displayMenu() {
    System.out.println("\n=== Bayt Al Hekma Library Menu ===");
    System.out.println("1. View catalogue");
    System.out.println("2. Register member");
    System.out.println("3. Borrow item");
    System.out.println("4. Return item");
    System.out.println("5. Renew loan");
    System.out.println("6. Search item by ID");
    System.out.println("7. View items by status");
    System.out.println("8. Pay outstanding fines");
    System.out.println("9. View all members");
    System.out.println("10. Mark item as lost");
    System.out.println("0. Exit");
    System.out.print("Enter your choice: ");
}

public static void seedSampleData(Library library) {
    // 1. الكتب (Books)
    library.registeringItem(new Book(101, "Clean Code", "Robert C. Martin", 464));
    library.registeringItem(new Book(102, "Effective Java", "Joshua Bloch", 416));
    library.registeringItem(new Book(103, "Head First Design Patterns", "Eric Freeman", 694));
    library.registeringItem(new Book(104, "Introduction to Algorithms", "Thomas H. Cormen", 1312));

    // 2. المجلات (Magazines)
    library.registeringItem(new Magazine(201, "National Geographic", 145));
    library.registeringItem(new Magazine(202, "MIT Technology Review", 92));
    library.registeringItem(new Magazine(203, "Scientific American", 310));

    // 3. الـ DVDs
    library.registeringItem(new DVD(301, "Inception", 148));
    library.registeringItem(new DVD(302, "Interstellar", 169));
    library.registeringItem(new DVD(303, "The Social Network", 120));

    // 4. الأعضاء (Members) - بأنواع عضوية وأرصدة مختلفة لاختبار كل القواعد
    library.registeringMember(new Member(1, "Abdullah Mohammed", MembershipType.Student));
    library.registeringMember(new Member(2, "Dr. Mona Hassan", MembershipType.Staff));
    library.registeringMember(new Member(3, "Omar Khaled", MembershipType.Public, 40.0));
    library.registeringMember(new Member(4, "Sara Mahmoud", MembershipType.Student, 130.0)); // ممنوعة من الاستعارة (> 100)[cite: 2, 4]
    library.registeringMember(new Member(5, "Youssef Tarek", MembershipType.Public));
}

void main() {

    Scanner in = new Scanner(System.in);
    Library library = new Library();
    seedSampleData(library);
    int choice;
    do {
        displayMenu();
        choice = Integer.parseInt(in.nextLine());
        switch (choice) {
            case 1: {
                library.viewCatalogue();
                break;
            }

            case 2: {
                boolean success = false;
                System.out.println("Enter Member Id");
                int memberId = Integer.parseInt(in.nextLine());
                if (library.findMemberId(memberId) == null) {
                    System.out.println("Enter Member name ");
                    String name = in.nextLine();
                    System.out.println("Which your Category STUDENT | STAFF | PUBLIC");
                    String category = in.nextLine().toUpperCase().trim();
                    MembershipType membershipType = switch (category) {
                        case "STUDENT" -> MembershipType.Student;
                        case "STAFF" -> MembershipType.Staff;
                        case "PUBLIC" -> MembershipType.Public;
                        default -> null;
                    };
                    success = library.registeringMember(new Member(memberId, name, membershipType));
                } else System.out.println("The id is not Unique");
                if (success) System.out.println("Registering is Successful");
                break;
            }
            case 3: {
                System.out.println("Enter Member ID");
                int memberId = Integer.parseInt(in.nextLine());
                System.out.println("Enter item ID");
                int itemId = Integer.parseInt(in.nextLine());
                library.borrowItem(memberId, itemId);
                break;
            }
            case 4: {
                boolean success = false;
                System.out.println("Enter item id");
                int itemId = Integer.parseInt(in.nextLine());
                System.out.println("Enter the overDue ");
                int overDue = Integer.parseInt(in.nextLine());
                if (overDue >= 0) {
                    success = library.returnItem(itemId, overDue);
                } else System.out.println("overDue must be greater than 0");
                if (success) {
                    System.out.println("Returning process successful");

                } else System.out.println("some error");

                break;
            }
            case 5: {
                System.out.println("Enter Item ID");
                int itemId = Integer.parseInt(in.nextLine());
                library.renewLoan(itemId);


                break;
            }
            case 6: {
                System.out.println("Enter item id ");
                int itemId = Integer.parseInt(in.nextLine());
                if (library.findCatalogue(itemId) != null) {
                    System.out.println(library.findCatalogue(itemId).display());
                } else System.out.println("Item not found ");
                break;
            }
            case 7: {
                System.out.println("Which The Status Available | ON_LOAN | LOST");
                String status = in.nextLine().toUpperCase().trim();
                ItemStatus status1 = switch (status) {
                    case "AVAILABLE" -> ItemStatus.AVAILABLE;
                    case "ON_LOAN" -> ItemStatus.ON_LOAN;
                    case "LOST" -> ItemStatus.LOST;
                    default -> null;
                };
                library.itemStatus(status1);
                break;
            }
            case 8: {
                System.out.println("Enter Member Id");
                int memberId = Integer.parseInt(in.nextLine());
                System.out.println("Enter the amount to pay");
                double amount = Double.parseDouble(in.nextLine());
                library.payOutstandingFines(memberId, amount);

                break;
            }
            case 9: {
                library.viewAllMember();
                break;
            }
            case 10: {
                System.out.println("Enter item id");
                int itemId = Integer.parseInt(in.nextLine());
                library.markLost(itemId);
                break;
            }
            case 0: {
                System.out.println("Thank you");
                break;
            }

        }
    } while (choice != 0);


}
