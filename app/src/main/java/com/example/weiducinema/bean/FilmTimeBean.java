package com.example.weiducinema.bean;

/**
 * Created by 邵新轩(轩少)
 * function:
 * on 2019/1/26
 */
public class FilmTimeBean {

    private String beginTime;
    private String duration;
    private String endTime;
    private int id;
    private String screeningHall;
    private int seatsTotal;
    private int seatsUseCount;
    private int status;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScreeningHall() {
        return screeningHall;
    }

    public void setScreeningHall(String screeningHall) {
        this.screeningHall = screeningHall;
    }

    public int getSeatsTotal() {
        return seatsTotal;
    }

    public void setSeatsTotal(int seatsTotal) {
        this.seatsTotal = seatsTotal;
    }

    public int getSeatsUseCount() {
        return seatsUseCount;
    }

    public void setSeatsUseCount(int seatsUseCount) {
        this.seatsUseCount = seatsUseCount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
