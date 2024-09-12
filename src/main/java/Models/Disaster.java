/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author PJ
 */
public class Disaster {
    private String name;
    private String description;
    private String location;
    private String damage;
    private String category;
    private String risk;
    private String date;
    private String reportedBy;
    private String status;

    public Disaster(String name, String description, String location, String damage, String category, String risk, String date, String reportedBy, String status) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.damage = damage;
        this.category = category;
        this.risk = risk;
        this.date = date;
        this.reportedBy = reportedBy;
        this.status = status;
    }

        
    public Disaster(String name, String description, String location, String damage, String category, String risk, String date, String status) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.damage = damage;
        this.category = category;
        this.risk = risk;
        this.date = date;
        this.status = status;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
