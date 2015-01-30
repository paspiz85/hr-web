package it.objectway.hr.business;

import java.util.ArrayList;
import java.util.List;

import it.objectway.hr.dati.Department;
import it.objectway.hr.dati.Employee;
import it.objectway.hr.dati.Job;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.struts.util.LabelValueBean;

public class MyContextListener implements ServletContextListener{
 
	public static final Logger log = Logger.getLogger(MyContextListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");
	}
 
        //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		log.info("ServletContextListener started");
		Manager m = new DBManager();
		List<LabelValueBean> departmentsList = new ArrayList<>();
		List<LabelValueBean> jobsList = new ArrayList<>();
		List<LabelValueBean> employeeList = new ArrayList<>();
		List<LabelValueBean> managerList = new ArrayList<>();
		try {
			for( Department dep: m.getDipartimenti() ){
				String id = ((Integer)dep.getId()).toString(); 
				departmentsList.add(new LabelValueBean(dep.getName(), id));
			}
			for( Job job: m.getJobs() ){
				jobsList.add(new LabelValueBean(job.getTitle(), job.getId()));
			}
			for( Employee employee: m.getEmployee() ){
				String id = ((Integer)employee.getId()).toString(); 
				employeeList.add(new LabelValueBean(employee.getLastName(), id));
			}
			for( Employee employee: m.getManager() ){
				String id = ((Integer)employee.getId()).toString(); 
				managerList.add(new LabelValueBean(employee.getLastName(), id));
			}
		} catch (Exception e) { 
			log.error("Errore caricamento dati", e);
		} finally {
			ServletContext servletContext = arg0.getServletContext();
			servletContext.setAttribute("employeeList", employeeList);
			servletContext.setAttribute("departmentsList", departmentsList);
			servletContext.setAttribute("jobsList", jobsList);
			servletContext.setAttribute("managerList", managerList);
		}		
	}
}