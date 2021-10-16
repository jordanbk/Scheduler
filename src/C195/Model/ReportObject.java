package C195.Model;

public class ReportObject {
    String type;
    String month;
    int count;

    public ReportObject(String type, String month, int count) {
        this.type = type;
        this.month = month;
        this.count = count;
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
