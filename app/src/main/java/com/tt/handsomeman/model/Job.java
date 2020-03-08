package com.tt.handsomeman.model;

import com.tt.handsomeman.util.DayCount;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Job implements Serializable {

    private Integer id;
    private Integer categoryId;
    private String title;
    private Date createTime;
    private String detail;
    private Integer budgetMin;
    private Integer budgetMax;
    private Date deadline;
    private String location;
    private Double lat;
    private Double lng;
    private Integer bidMin;
    private Integer bidMax;
    private Integer interviewing;
    private Integer customerId;
    private String status;

    public Job(Date createTime, String title, Integer budgetMin, Integer budgetMax, Date deadline, String location) {
        this.createTime = createTime;
        this.title = title;
        this.budgetMin = budgetMin;
        this.budgetMax = budgetMax;
        this.deadline = deadline;
        this.location = location;
    }

    public Integer setCreateTimeBinding(Date createTime) {
        String myFormat = "h"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
        return Integer.valueOf(sdf.format(createTime));
    }

    public String setBudgetRange(Integer budgetMin, Integer budgetMax) {
        return ("$" + budgetMin + " - " + "$" + budgetMax);
    }

    public Integer setDeadlineBinding(Date deadline) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Calendar now = Calendar.getInstance();
        Date today = formatter.parse(formatter.format(now.getTime()));
        deadline = formatter.parse(formatter.format(deadline));

        return DayCount.getCountOfDays(today, deadline);
    }

    public String setBidRange(Integer bidMin, Integer bidMax) {
        return (bidMin + " to " + bidMax);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getBudgetMin() {
        return budgetMin;
    }

    public void setBudgetMin(Integer budgetMin) {
        this.budgetMin = budgetMin;
    }

    public Integer getBudgetMax() {
        return budgetMax;
    }

    public void setBudgetMax(Integer budgetMax) {
        this.budgetMax = budgetMax;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getBidMin() {
        return bidMin;
    }

    public void setBidMin(Integer bidMin) {
        this.bidMin = bidMin;
    }

    public Integer getBidMax() {
        return bidMax;
    }

    public void setBidMax(Integer bidMax) {
        this.bidMax = bidMax;
    }

    public Integer getInterviewing() {
        return interviewing;
    }

    public void setInterviewing(Integer interviewing) {
        this.interviewing = interviewing;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
