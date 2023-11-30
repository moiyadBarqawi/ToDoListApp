package edu.birzeit.myapplication;

public class In_List {
    private String task;
    private String actor;
    private String time;

    public In_List() {
    }

    public In_List(String task, String actor, String time) {
        this.task = task;
        this.actor = actor;
        this.time = time;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return " " + task +
                " ,      " + actor + '\n' +
                "  " + time
               ;
    }
}
