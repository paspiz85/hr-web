package it.objectway.hr.presentation.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import it.objectway.hibernate.hr.model.Departments;
import it.objectway.hibernate.hr.model.Employees;
import it.objectway.hibernate.hr.model.Jobs;
import it.objectway.hibernate.hr.services.EmployeesService;
import it.objectway.hr.presentation.form.ModificaForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ModificaAction extends BaseAction {
	
	@Override
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EmployeesService employeesService = getBean(EmployeesService.class, request);
		ModificaForm initForm = (ModificaForm) form;		
		Employees newEmployee = new Employees();
		newEmployee.setEmployeeId(Integer.parseInt(initForm.getId()));
		newEmployee.setFirstName(initForm.getFirstName());
		newEmployee.setLastName(initForm.getLastName());
		newEmployee.setEmail(initForm.getEmail());
		newEmployee.setPhoneNumber(initForm.getPhoneNumber());
		newEmployee.setHireDate( new SimpleDateFormat("dd/MM/yyyy").parse(initForm.getHireDate()) );
		newEmployee.setSalary( new BigDecimal(initForm.getSalary()));
		newEmployee.setCommissionPct( new BigDecimal(initForm.getCommissionPct()) );
		Jobs job = new Jobs();
		job.setJobId(initForm.getSelectedJob());
		newEmployee.setJobs(job);
		Employees manager = new Employees();
		manager.setEmployeeId(Integer.parseInt(initForm.getSelectedManager()) );
		newEmployee.setEmployees(manager);
		Departments dep = new Departments();
		dep.setDepartmentId( Short.parseShort(initForm.getSelectedDepartment()) );
		newEmployee.setDepartments(dep);
		String action = initForm.getAction();
		String msg = null;
		if ( action.equals(ModificaForm.AZIONE_INSERT) ){
			employeesService.insert(newEmployee);
			msg = ModificaForm.ESITO_OK;
			log.info("Insert : " + msg);
		} else if ( action.equals(ModificaForm.AZIONE_UPDATE) ){
			employeesService.update(newEmployee);
			msg = ModificaForm.ESITO_OK ;
			log.info("Update : " + msg);
		}
		initForm.setMessage(msg);
		Employees e = employeesService.getEmployee( newEmployee.getEmployeeId() );
		initForm.setReadOnly(true);
		initForm.setId( ((Integer)newEmployee.getEmployeeId()).toString() );
		initForm.setFirstName(e.getFirstName());
		initForm.setLastName(e.getLastName());
		initForm.setPhoneNumber(e.getPhoneNumber());
		initForm.setEmail(e.getEmail());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");			
		initForm.setHireDate( sdf.format(e.getHireDate()) );

		initForm.setSalary( e.getSalary().toString() );
		initForm.setCommissionPct( e.getCommissionPct().toString() );		
		initForm.setSelectedDepartment( ((Short)e.getDepartments().getDepartmentId()).toString() );
		initForm.setSelectedJob( e.getJobs().getJobId() );
		initForm.setSelectedManager( ((Integer)e.getEmployees().getEmployeeId() ).toString() );		

		return mapping.findForward("success");
	}

}
