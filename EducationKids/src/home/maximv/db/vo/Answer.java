package home.maximv.db.vo;

public class Answer {

    public Answer() {
        super();
    }

    private int pid;

    private String answer;

    private int pidMen;

    private int rating;
 
    private int questionPid;


    public int getPid() {
        return this.pid;
    }

    public String getAnswer() {
        return this.answer;
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
    
    public int getQuestionId() {
        return this.questionPid;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setPidMen(int pidMen) {
        this.pidMen = pidMen;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public void setQuestionId(int questionPid) {
        this.questionPid = questionPid;

    }

}
