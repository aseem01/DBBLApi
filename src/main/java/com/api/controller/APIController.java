/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.controller;

import com.api.dao.ApiDao;
import com.api.dao.HibernateUtil;
import com.api.entity.EmployeeRecord;
import com.api.entity.SalaryInfo;
import java.util.List;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ashim Chakraborty
 */
@RestController
public class APIController {

    @RequestMapping(value = "/")
    public String home() {
        System.out.println("API GOT CALL");
        Session s = HibernateUtil.getSessionFactory().openSession();
        return "API running";
    }

    @RequestMapping("/getSal")
    public SalaryInfo getSal() {
        System.out.println("HELLO>>>>>>>>>>>>");
        SalaryInfo sal = new SalaryInfo();
        sal.setBasicSalary(4500000);
        sal.setDesignation("PO");
        sal.setSalaryId(1500l);

        return sal;
    }

    @RequestMapping("/getToptensalary")
    public List<EmployeeRecord> getTopTenSalary() {
        System.out.println("yes.running");
        //EmployeeRecord employeeRecord = new EmployeeRecord();
        ApiDao ObjTop = new ApiDao();
        List<EmployeeRecord> list = ObjTop.ViewEmployee();
        System.out.println("yes.running ===========================================");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Name : " + list.get(i).getName() + " Designation : " + list.get(i).getDesignation() + " salary : " + list.get(i).getTotal());

        }
        return list;
    }

//    @RequestMapping(value = "addEmpListt")
//    public Boolean addEmp(@RequestBody List<EmployeeRecord> empList) {
//        for (EmployeeRecord emp : empList) {
//            System.out.println("API EMP " + emp.getName() + " " + emp.getAccNo() + " " + emp.getBasicSalary());
//        }
//
//        return true;
//    }

    @RequestMapping(value = "/employeeSalaryById")
    public EmployeeRecord getEmployeeSalaryList(@RequestParam(value = "id") String EmployeeId) {
        ApiDao objEmployee = new ApiDao();
        System.out.println("EMPID :" + EmployeeId);
        EmployeeRecord emp = objEmployee.EmployeeSalaryList(EmployeeId);
        return emp;
    }

    @RequestMapping(value = "/employeeSalaryByDesignation")
    public List<EmployeeRecord> getEmployeeSalaryListByDesignation(@RequestParam(value = "id") String designation) {
        ApiDao objEmployee = new ApiDao();
        System.out.println("EMPID :" + designation);
        List<EmployeeRecord> emp = objEmployee.EmployeeSalaryDesignationMethod(designation);
        System.out.println("*************************************************************");
        for (int i = 0; i < emp.size(); i++) {
            System.out.println("emp name : " + emp.get(1).getName() + " Designation : " + emp.get(i).getDesignation() + " Salary : " + emp.get(i).getTotal());
        }
        return emp;
    }

    //ENDUSER PAGE Task Start;
    @RequestMapping("/getSalaryInfo")
    public List<SalaryInfo> getAddEmployee() {
        System.out.println("yes.running");
        //EmployeeRecord employeeRecord = new EmployeeRecord();
        ApiDao ObjTop = new ApiDao();
        List<SalaryInfo> list = ObjTop.AddEmployeeMethod();
        System.out.println("yes.running ===========================================");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Designation : " + list.get(i).getDesignation() + " Salary Id : " + list.get(i).getSalaryId() + " Basic Salary : " + list.get(i).getBasicSalary());
        }
        return list;
    }

    @RequestMapping("/addEmpList")
    public boolean getAddEmployeeStatus(@RequestBody EmployeeRecord employee) {
        System.out.println("yes.running");
        ApiDao ObjTop = new ApiDao();
        boolean found = ObjTop.EmployeeInsert(employee);
        System.out.println("yes.running ===========================================");
        return found;
    }

    
    @RequestMapping("/viewAllEmpList")
    public List<EmployeeRecord> getAllEmployeeList() {
        System.out.println("yes.running");
        ApiDao ObjTop = new ApiDao();
        List<EmployeeRecord> list = ObjTop.getAllEmployee();
        System.out.println("yes.running ===========================================");
        return list;
    }
    
    
    @RequestMapping("/getEmployeeRecord")
    public EmployeeRecord getEmployeeRecord(@RequestParam(value = "empId") String empId) {
        
       ApiDao objDao = new ApiDao();
        return objDao.getUpdateEmployeeViewMethod(empId);

    }
    
    @RequestMapping(value = "/updateEmployee",method = RequestMethod.POST)
    public boolean updateEmployee(@RequestBody EmployeeRecord employeeRecord)
    {
        ApiDao objDao = new ApiDao();
        return objDao.updateEmp(employeeRecord);
    }

    
}
