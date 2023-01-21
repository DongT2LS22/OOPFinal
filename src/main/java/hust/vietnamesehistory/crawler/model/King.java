/**
 * This class was created at 18-Jan-23 14:08:36
 * This class is owned by FaceNet Company
 */
package hust.vietnamesehistory.crawler.model;

public class King extends Person{
    private String reignTime;
    private String predecessor;
    private String successor;
    private String aliases;
    private String realName;

    public King(String name, String href, String birth, String death, String reignTime, String predecessor, String successor, String aliases, String realName) {
        super(name, href, birth, death);
        this.reignTime = reignTime;
        this.predecessor = predecessor;
        this.successor = successor;
        this.aliases = aliases;
        this.realName = realName;
    }

    public String getReignTime() {
        return reignTime;
    }

    public void setReignTime(String reignTime) {
        this.reignTime = reignTime;
    }

    public String getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(String predecessor) {
        this.predecessor = predecessor;
    }

    public String getSuccessor() {
        return successor;
    }

    public void setSuccessor(String successor) {
        this.successor = successor;
    }

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
