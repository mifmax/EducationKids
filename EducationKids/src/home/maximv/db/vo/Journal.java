package home.maximv.db.vo;

public class Journal {

    public Journal() {
        super();
    }

    public Journal(int pid, int learnerId, String date, int course1, int course2, int course3, int course4,
            int course5, int course6, int course7, int course8, int course9, int course10, int course11, int course12,
            int course13, int course14) {
        super();
        this.pid = pid;
        this.lernerPid = learnerId;
        this.date = date;
        this.course1 = course1;
        this.course2 = course2;
        this.course3 = course3;
        this.course4 = course4;
        this.course5 = course5;
        this.course6 = course6;
        this.course7 = course7;
        this.course8 = course8;
        this.course9 = course9;
        this.course10 = course10;
        this.course11 = course11;
        this.course12 = course12;
        this.course13 = course13;
        this.course14 = course14;
    }

    private int pid;

    private Learner learner;

    private int lernerPid;

    private String date;

    private int course1;

    private int course2;

    private int course3;

    private int course4;

    private int course5;

    private int course6;

    private int course7;

    private int course8;

    private int course9;

    private int course10;

    private int course11;

    private int course12;

    private int course13;

    private int course14;

    public Learner getLearner() {
        return this.learner;
    }

    public int getLearnerId() {
        return this.lernerPid;
    }

    public int getPid() {
        return this.pid;
    }

    public String getDate() {
        return this.date;
    }

    public int getCourse1() {
        return this.course1;
    }

    public int getCourse2() {
        return this.course2;
    }

    public int getCourse3() {
        return this.course3;
    }

    public int getCourse4() {
        return this.course4;
    }

    public int getCourse5() {
        return this.course5;
    }

    public int getCourse6() {
        return this.course6;
    }

    public int getCourse7() {
        return this.course7;
    }

    public int getCourse8() {
        return this.course8;
    }

    public int getCourse9() {
        return this.course9;
    }

    public int getCourse10() {
        return this.course10;
    }

    public int getCourse11() {
        return this.course11;
    }

    public int getCourse12() {
        return this.course12;
    }

    public int getCourse13() {
        return this.course13;
    }

    public int getCourse14() {
        return this.course14;
    }

    public void setPid(int pid) {
        this.pid = pid;

    }

    public void setLearner(Learner lerner) {
        this.learner = lerner;

    }

    public void setLearnerId(int lernerPid) {
        this.lernerPid = lernerPid;

    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCourse1(int course1) {
        this.course1 = course1;
    }

    public void setCourse2(int course2) {
        this.course2 = course2;
    }

    public void setCourse3(int course3) {
        this.course3 = course3;
    }

    public void setCourse4(int course4) {
        this.course4 = course4;
    }

    public void setCourse5(int course5) {
        this.course5 = course5;
    }

    public void setCourse6(int course6) {
        this.course6 = course6;
    }

    public void setCourse7(int course7) {
        this.course7 = course7;
    }

    public void setCourse8(int course8) {
        this.course8 = course8;
    }

    public void setCourse9(int course9) {
        this.course9 = course9;
    }

    public void setCourse10(int course10) {
        this.course10 = course10;
    }

    public void setCourse11(int course11) {
        this.course11 = course11;
    }

    public void setCourse12(int course12) {
        this.course12 = course12;
    }

    public void setCourse13(int course13) {
        this.course13 = course13;
    }

    public void setCourse14(int course14) {
        this.course14 = course14;
    }

}
