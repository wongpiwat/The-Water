package models;

public class Log {
    private String no;
    private String date;
    private String type;
    private String accountType;
    private String name;
    private String event;

    public Log(String no, String date, String type, String accountType, String name, String event) {
        this.no = no;
        this.date = date;
        this.type = type;
        this.accountType = accountType;
        this.name = name;
        this.event = event;
    }

    public String getNo() {
        return no;
    }

    public String getDate() {
        return date;
    }

    public String getLogType() {
        return type;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getName() {
        return name;
    }

    public String getEvent() {
        return event;
    }
}
