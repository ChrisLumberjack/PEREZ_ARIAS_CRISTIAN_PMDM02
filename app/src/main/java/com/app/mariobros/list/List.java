package com.app.mariobros.list;

public class List {
    private int titleImage;
    private String heading;
    private String skills;

    public List(int titleImage, String heading, String skills) {
        this.titleImage = titleImage;
        this.heading = heading;
        this.skills = skills;
    }

    public int getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(int titleImage) {
        this.titleImage = titleImage;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
