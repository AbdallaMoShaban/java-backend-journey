public class DVD extends LibraryItem{
    private int runTime;

    public DVD(int catalogueID, String title, int runTime) {
        super(catalogueID, title);
        this.runTime = runTime;
    }

    public  int fine(int overdue){
        return 15*overdue;
    }
    public  int days(){return 3;}
    public  String nameOfCategory(){return "DVD";}


    public int getRunTime() {
        return runTime;
    }
}
