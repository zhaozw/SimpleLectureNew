package com.simplelecture.main.model.viewmodel;

import java.io.Serializable;

/**
 * Created by Raos on 2/16/2016.
 */
public class MyCourses {

    private String cId;
    private String cName;
    private String cIcon;

    public MyCourses() {
    }

    public MyCourses(String cId, String cName, String cIcon) {
        this.cId = cId;
        this.cName = cName;
        this.cIcon = cIcon;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcIcon() {
        return cIcon;
    }

    public void setcIcon(String cIcon) {
        this.cIcon = cIcon;
    }

    @Override
    public String toString() {
        return "MyCourses{" +
                "cId='" + cId + '\'' +
                ", cName='" + cName + '\'' +
                ", cIcon='" + cIcon + '\'' +
                '}';
    }
}
