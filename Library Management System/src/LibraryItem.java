public abstract class LibraryItem {
    private final int CatalogueID;
    private final String title;
    private ItemStatus currentStatus;
    private int borrowerMemberID;
    private int numberRenewalsUsed;
    static final String libraryName = "Bayt Al Hekma;";
    static final double adminCharge = 1.14;
    public static int totalNumberItem = 0;

    public LibraryItem(int catalogueID, String title) {
        CatalogueID = catalogueID;
        this.title = title;
        this.currentStatus = ItemStatus.AVAILABLE;
        this.borrowerMemberID = 0;
        totalNumberItem++;
    }

    public String display() {
        return "LibraryItem{" +
                "libraryName=" + libraryName +
                "CatalogueID=" + CatalogueID +
                "Catalogue" + nameOfCategory() +
                ", title='" + title + '\'' +
                ", currentStatus=" + currentStatus +
                ", borrowerMemberID=" + borrowerMemberID +
                ", Loan Period='" + days() + '\'' +
                ", adminCharge=" + adminCharge +
                '}';
    }


    public abstract int fine(int overdue);

    public abstract int days();

    public abstract String nameOfCategory();


    public void markLost() {
        if (currentStatus == ItemStatus.AVAILABLE) {
            currentStatus = ItemStatus.LOST;
        }

    }

    public void lendTo(int memberID) {
        if (currentStatus == ItemStatus.AVAILABLE) {
            this.borrowerMemberID = memberID;
            currentStatus = ItemStatus.ON_LOAN;
        }
    }

    public final void returning() {
        if (currentStatus == ItemStatus.ON_LOAN) {
            this.borrowerMemberID = 0;
            currentStatus = ItemStatus.AVAILABLE;
            this.numberRenewalsUsed = 0;
        }
    }

    public void recordRenewal() {
        this.numberRenewalsUsed++;
    }


    public int getCatalogueID() {
        return CatalogueID;
    }

    public ItemStatus getCurrentStatus() {
        return currentStatus;
    }

    public int getBorrowerMemberID() {
        return borrowerMemberID;
    }

    public int getNumberRenewalsUsed() {
        return numberRenewalsUsed;
    }

}


