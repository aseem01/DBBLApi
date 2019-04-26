/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Md. Monirul Islam
 */

@Entity
public class SalaryInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long salaryId;
    private String designation;
    private int basicSalary;

    public Long getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Long salaryId) {
        this.salaryId = salaryId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(int basicSalary) {
        this.basicSalary = basicSalary;
    }
    
    
    
}
