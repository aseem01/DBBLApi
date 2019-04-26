/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.dao;

import com.api.entity.EmployeeRecord;
import com.api.entity.SalaryInfo;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Ashim Chakraborty
 */
public class ApiDao {

    SessionFactory factory = HibernateUtil.getSessionFactory();

    public List<EmployeeRecord> ViewEmployee() {
        List<EmployeeRecord> empList = new ArrayList();
        Session session = factory.openSession();
        try {

//            String hql = "select distinct total from EmployeeRecord order by total desc";
//            Query query = session.createQuery(hql);
//            query.setFirstResult(0);
//            query.setMaxResults(10);
//             list = query.list();
//             System.out.println("list : "+list.size());
//            Criteria c = session.createCriteria(EmployeeRecord.class);
//            session.getTransaction();
//            list = c.list();
            Criteria crt = session.createCriteria(EmployeeRecord.class);
            crt.addOrder(Order.desc("total"));
            crt.setMaxResults(10);
            empList = crt.list();
            session.close();

        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        //session.close();
        return empList;
    }

    public EmployeeRecord EmployeeSalaryList(String Search) {
        EmployeeRecord emp = new EmployeeRecord();
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            emp = (EmployeeRecord) session.get(EmployeeRecord.class, Long.parseLong(Search));
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return emp;
    }

    public List<EmployeeRecord> EmployeeSalaryDesignationMethod(String Search) {
        //EmployeeRecord emp = new EmployeeRecord();
        List<EmployeeRecord> empList = new ArrayList();
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Criteria crit = session.createCriteria(EmployeeRecord.class);
            crit.add(Restrictions.eq("designation", Search));
            empList = crit.list();
            session.close();
//              //Criteria c = session.createCriteria(EmployeeRecord.class);
//              empList=(List<EmployeeRecord>)(EmployeeRecord)session.get(EmployeeRecord.class,Search);
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

        System.out.println("emplist : " + empList.size());
        System.out.println("----------------------------------------------");
        for (int i = 0; i < empList.size(); i++) {
            System.out.println("emp name : " + empList.get(1).getName() + " Designation : " + empList.get(i).getDesignation() + " Salary : " + empList.get(i).getTotal());
        }
        return empList;
    }

    public List<SalaryInfo> AddEmployeeMethod() {
        List<SalaryInfo> empList = new ArrayList();
        Session session = factory.openSession();
        try {
            String hql = "FROM SalaryInfo";
            Query query = session.createQuery(hql);
            empList = query.list();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return empList;
    }

    public boolean EmployeeInsert(EmployeeRecord employeeRecord) {
        Session session = factory.openSession();
        boolean found = true;
        try {
            SalaryInfo salary=getSalById(employeeRecord.getSalary().getSalaryId());
            employeeRecord.setDesignation(salary.getDesignation());
            employeeRecord.setBasicSalary(salary.getBasicSalary());
            
            int houseResnt=(int) (salary.getBasicSalary()*0.60);
            int healthCare=(int) (salary.getBasicSalary()*0.20);
            int others=(int) (salary.getBasicSalary()*0.30);
            
            employeeRecord.setHealthCare(healthCare);
            employeeRecord.setHouseRent(houseResnt);
            employeeRecord.setOthers(others);
            employeeRecord.setTotal(others+healthCare+houseResnt+salary.getBasicSalary());
            
            session.beginTransaction();
            session.save(employeeRecord);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            found = false;
        }
        return found;
    }

    public List<EmployeeRecord> getAllEmployee() {
        List<EmployeeRecord> empList = new ArrayList();
        Session session = factory.openSession();
        try {
            String hql = "FROM EmployeeRecord";
            Query query = session.createQuery(hql);
            empList = query.list();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return empList;
    }
    
     public EmployeeRecord getUpdateEmployeeViewMethod(String empId) {
        EmployeeRecord emp = new EmployeeRecord();
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            emp = (EmployeeRecord) session.get(EmployeeRecord.class, Long.parseLong(empId));
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return emp;
    }
    
    public SalaryInfo getSalById(Long id)
    {
        Session session=factory.openSession();
        SalaryInfo salaryInfo=new SalaryInfo();
        try{session.beginTransaction();
            salaryInfo=(SalaryInfo)session.get(SalaryInfo.class,id);
            session.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return salaryInfo;
        
    }
    
    public boolean updateEmp(EmployeeRecord employeeRecord)
    {
        Session session = factory.openSession();
        boolean found = true;
        try {
            SalaryInfo salary=getSalById(employeeRecord.getSalary().getSalaryId());
            employeeRecord.setDesignation(salary.getDesignation());
            employeeRecord.setBasicSalary(salary.getBasicSalary());
            
            int houseResnt=(int) (salary.getBasicSalary()*0.60);
            int healthCare=(int) (salary.getBasicSalary()*0.20);
            int others=(int) (salary.getBasicSalary()*0.30);
            
            employeeRecord.setHealthCare(healthCare);
            employeeRecord.setHouseRent(houseResnt);
            employeeRecord.setOthers(others);
            employeeRecord.setTotal(others+healthCare+houseResnt+salary.getBasicSalary());
            
            session.beginTransaction();
            session.update(employeeRecord);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            found = false;
        }
        return found;
    }
}
