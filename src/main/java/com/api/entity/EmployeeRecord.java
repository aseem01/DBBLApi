/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 *
 * @author Ashim Chakraborty
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class EmployeeRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
     private Long empId;
    private String name;
    private String email;
    private String contact;
    private String address;
    private String designation;
    private String accNo;
    private int basicSalary;
    private int houseRent;
    private int healthCare;
    private int others;
    private int total;
    private String fathername;
    private String mothername;
    private String education;
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "salary_id")
    private SalaryInfo salary;

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public int getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(int basicSalary) {
        this.basicSalary = basicSalary;
    }

    public int getHouseRent() {
        return houseRent;
    }

    public void setHouseRent(int houseRent) {
        this.houseRent = houseRent;
    }

    public int getHealthCare() {
        return healthCare;
    }

    public void setHealthCare(int healthCare) {
        this.healthCare = healthCare;
    }

    public int getOthers() {
        return others;
    }

    public void setOthers(int others) {
        this.others = others;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public SalaryInfo getSalary() {
        return salary;
    }

    public void setSalary(SalaryInfo salary) {
        this.salary = salary;
    }

    
    
}
