package com.qhy.ppmsadmin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double money;
    @Column(name = "user_name")
    private String userName;
    private String recorder;
    private String source; // 来源
    @Column(name = "describle")
    private String describle;
    private String title;

    @Column(name = "created_time")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the money
     */
    public Double getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(Double money) {
        this.money = money;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the recorder
     */
    public String getRecorder() {
        return recorder;
    }

    /**
     * @param recorder the recorder to set
     */
    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the createdTime
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime the createdTime to set
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return the describle
     */
    public String getDescrible() {
        return describle;
    }

    /**
     * @param describle the describle to set
     */
    public void setDescrible(String describle) {
        this.describle = describle;
    }

}
