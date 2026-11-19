public class Book extends LibraryItem implements Renewable {
    private final String author;
    private final int page;

    public Book(int catalogueID, String title, String author, int page) {
        super(catalogueID, title);
        this.author = author;
        this.page = page;
    }

    @Override

    public int fine(int overdue) {
        return 5 * overdue;
    }

    public int days() {
        return 14;
    }

    public String nameOfCategory() {
        return "BOOK";
    }

    public boolean renewLoan() {
        if (getCurrentStatus() == ItemStatus.ON_LOAN && getNumberRenewalsUsed() < getRenewLimit()) {
            recordRenewal();
            return true;
        } else return false;
    }

    public int getRenewLimit() {
        return 2;
    }
}

