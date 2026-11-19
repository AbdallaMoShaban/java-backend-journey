
public class Library {
    LibraryItem[] catalogue = new LibraryItem[20];
    Member[] member = new Member[20];
    int catalogueCount = 0;
    int memberCount = 0;

    public Member findMemberId(int memberId) {
        for (Member member1 : member) {
            if (member1 != null && member1.getMemberId() == memberId) {
                return member1;
            }
        }
        return null;
    }

    public LibraryItem findCatalogue(int itemId) {
        for (LibraryItem catalogue1 : catalogue) {
            if (catalogue1 != null && catalogue1.getCatalogueID() == itemId) {
                return catalogue1;//كده هو موجود ابعتهولي
            }
        }
        return null;
    }

    public boolean registeringMember(Member person) {
        Member p = findMemberId(person.getMemberId()); //لو الشخص دهه موجود الداله هاتبعت غلط
        if (p == null) {
            member[memberCount] = person;
            memberCount++;
            return true;
        }
        return false;
    }

    public void registeringItem(LibraryItem item) {
        if (item == null || catalogueCount >= catalogue.length) {
            return;
        }

        LibraryItem i = findCatalogue(item.getCatalogueID());

        if (i == null) {
            catalogue[catalogueCount] = item;
            catalogueCount++;
        }
    }

    public void viewCatalogue() {
        for (LibraryItem catalogue1 : catalogue) {
            if (catalogue1 != null) {
                System.out.println(catalogue1.display());
            }

        }
    }

    public void borrowItem(int memberId, int itemID) {
        Member p = findMemberId(memberId);
        LibraryItem i = findCatalogue(itemID);
        if (p != null) {
            if (i != null) {
                if (p.allowedToBorrow()) {
                    p.recordBorrowing();
                    i.lendTo(memberId);
                } else System.out.println("Not allowed to borrow");
            } else System.out.println("Item not found");
        } else System.out.println("Member not found");
    }

    public boolean returnItem(int itemId, int overDue) {
        LibraryItem i = findCatalogue(itemId);
        if (i != null) {
            Member p = findMemberId(i.getBorrowerMemberID());
            p.receiveFine(i.fine(overDue));
            p.recordReturn();
            i.returning();
            return true;
        }
        return false;
    }

    public void renewLoan(int itemId) {
        LibraryItem item = findCatalogue(itemId);
        if (item != null) {
            if (item instanceof Renewable r) {
                if (r.renewLoan()) {
                    System.out.println("Renew successful");
                } else System.out.println("Reached the maximum limit of renew or you must loan the item");
            } else System.out.println("item not support renew");
        } else System.out.println("Item not found");
    }

    public void itemStatus(ItemStatus status) {
        boolean found = false;
        for (LibraryItem i : catalogue) {
            if (i != null && i.getCurrentStatus() == status) {
                System.out.println(i.display());
                found = true;
            }
        }
        if (!found) System.out.println("no item found with this status {" + status + "}");
    }

    public void payOutstandingFines(int memberId, double amount) {
        Member p = findMemberId(memberId);
        if (p != null) {
            if (p.makePayment(amount)) {
                System.out.println("Payment process successful");
            } else System.out.println("payment rejected check your Amount");
        } else System.out.println("Member not found");
    }

    public void viewAllMember() {
        boolean found = false;
        for (Member person : member) {
            if (person != null) {
                System.out.println(person);
                found = true;
            }
        }
        if (!found) System.out.println("Member Not Found");
    }

    public void markLost(int itemId) {
        LibraryItem i = findCatalogue(itemId);
        i.markLost();
        System.out.println("Marked as lost ");
    }


}


