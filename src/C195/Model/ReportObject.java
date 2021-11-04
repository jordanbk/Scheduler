package C195.Model;

/** * This class handles the Report model * @author Jordan Burke */

public class ReportObject {
    int count;
    String month;
    String type;

    public ReportObject(int count, String month, String type) {
        this.count = count;
        this.month = month;
        this.type = type;
    }
    public ReportObject() {
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
