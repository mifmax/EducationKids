package home.maximv.db.vo;

public class Question {

    public Question() {
        super();
    }

    private int pid;

    private String question;

    private int pidMen;

    private int rating;



    public int getPid() {
        return this.pid;
    }

    public String getQuestion() {
        return this.question;
    }

    public int getPidMen() {
        return this.pidMen;
    }
    public int getRating() {
        return this.rating;
    }

    public void setPid(int pid) {
        this.pid = pid;

    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setPidMen(int pidMen) {
        this.pidMen = pidMen;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
}
