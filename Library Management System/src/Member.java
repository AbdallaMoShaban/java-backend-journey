public class Member {
    private String name;
    private final int memberId;
    private final MembershipType membershipType;
    private int numberItemsHeld;
    private double balanceOwed = 0;

    public Member(int memberId, String name, MembershipType membershipType) {
        this.membershipType = membershipType;
        this.name = name;
        this.memberId = memberId;

    }

    public Member(int memberId, String name, MembershipType membershipType, double balanceOwed) {
        this(memberId, name, membershipType);
        this.balanceOwed = Math.max(0.0, balanceOwed);
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", memberId=" + memberId +
                ", membershipType=" + membershipType +
                ", numberItemsHeld=" + numberItemsHeld +
                ", balanceOwed=" + balanceOwed +
                '}';
    }

    public int getMemberId() {
        return memberId;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberItemsHeld() {
        return numberItemsHeld;
    }

    public double getBalanceOwed() {
        return balanceOwed;
    }

    public void receiveFine(double fine) {
        if (fine > 0) {
            balanceOwed += fine;

        }
    }

    public boolean makePayment(double amount) {
        if (amount > 0 && amount <= balanceOwed) {
            balanceOwed -= amount;
            return true;
        } else return false;
    }

    public boolean allowedToBorrow() {
        return numberItemsHeld < 3 && balanceOwed <= 100;
    }

    public void recordBorrowing() {
        if (allowedToBorrow()) {
            numberItemsHeld++;
        }
    }

    public void recordReturn() {
        if (numberItemsHeld > 0) {
            numberItemsHeld--;
        }
    }

}
