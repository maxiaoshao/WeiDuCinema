package com.example.weiducinema.bean;

/**
 * Created by 邵新轩(轩少)
 * function:
 * on 2019/1/26
 */
public class EventMessage {
    private String cinemaId;
    private String img_pic;
    private String ciname_name;
    private String address;

    public EventMessage(String cinemaId, String img_pic, String ciname_name, String address) {
        this.cinemaId = cinemaId;
        this.img_pic = img_pic;
        this.ciname_name = ciname_name;
        this.address = address;
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getImg_pic() {
        return img_pic;
    }

    public void setImg_pic(String img_pic) {
        this.img_pic = img_pic;
    }

    public String getCiname_name() {
        return ciname_name;
    }

    public void setCiname_name(String ciname_name) {
        this.ciname_name = ciname_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
