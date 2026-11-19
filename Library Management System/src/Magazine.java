public class Magazine extends LibraryItem implements Renewable{
   private int issueNumber;

    public Magazine(int catalogueID, String title, int issueNumber) {
        super(catalogueID, title);
        this.issueNumber = issueNumber;
    }
    public  int fine(int overdue){
        return  Math.min(overdue*3,30);

    }
    public  int days(){return 7;}
    public  String nameOfCategory(){return "Magazine";}
    public  boolean renewLoan(){
        if (getCurrentStatus()==ItemStatus.ON_LOAN&&getNumberRenewalsUsed()<getRenewLimit()){
            recordRenewal();
            return true;
        }else return false;
    }
    public  int getRenewLimit(){return 1 ;}

    public int getIssueNumber() {
        return issueNumber;
    }
}
