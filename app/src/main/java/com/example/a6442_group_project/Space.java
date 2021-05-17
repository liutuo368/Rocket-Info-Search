package com.example.a6442_group_project;

public class Space {
    private String company;
    private String location;
    private String datum;
    private String detail;
    private String status_rocket;
    private String rocket;
    private String status_mission;

    public Space(String company, String location, String datum, String detail, String status_rocket, String rocket, String status_mission) {
        this.company = company;
        this.location = location;
        this.datum = datum;
        this.detail = detail;
        this.status_rocket = status_rocket;
        this.rocket = rocket;
        this.status_mission = status_mission;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getDatum() {
        return datum;
    }

    public String getDetail() {
        return detail;
    }

    public String getStatus_rocket() {
        return status_rocket;
    }

    public String getRocket() {
        return rocket;
    }

    public String getStatus_mission() {
        return status_mission;
    }

    @Override
    public String toString() {
        return "Space{" +
                "company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", datum='" + datum + '\'' +
                ", detail='" + detail + '\'' +
                ", status_rocket='" + status_rocket + '\'' +
                ", rocket='" + rocket + '\'' +
                ", status_mission='" + status_mission + '\'' +
                '}';
    }
}
