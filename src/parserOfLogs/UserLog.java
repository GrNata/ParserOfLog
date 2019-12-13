package parserOfLogs;

import java.util.Date;

public class UserLog {
    private  String ip;
    private String user;
    private Date date;
    private Event event;
    private int task;
    private Status status;

    public UserLog(String ip, String user, Date date, Event event, int task, Status status){
        this.ip = ip;
        this.user = user;
        this.date = date;
        this.event = event;
        this.task = task;
        this.status = status;
    }

    public UserLog(String ip, String user, Date date, Event event, Status status){
        this.ip = ip;
        this.user = user;
        this.date = date;
        this.event = event;
//        this.task = ;
        this.status = status;
    }

    public void setIp(String ip){}
    public void setUser(String user){}
    public void setDate(Date date){}
    public void setEvent(Event event){}
    public void setTask(int task){}
    public void setStatus(Status status){}

    public String getIp(){ return ip;}
    public String getUser(){ return user;}
    public Date getDate() { return date;}
    public Event getEvent(){ return event;}
    public int getTask() { return task;}
    public Status getStatus() { return status;}

    @Override
    public String toString() {
        return "UserLog: " +
                " ip= "+ip+
                " user= "+user+
                " date="+date+
                " event="+event+
                " task"+task+
                " status="+status;
    }
}
