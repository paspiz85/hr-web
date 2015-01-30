package it.objectway.hr.presentation.action;

import java.text.SimpleDateFormat;

import it.objectway.hibernate.hr.model.Employees;
import it.objectway.hibernate.hr.services.EmployeesService;
import it.objectway.hr.presentation.form.ModificaForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InitModificaAction extends BaseAction {
	
	@Override
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward actionForward = mapping.findForward("success");
		
		EmployeesService employeesService = getBean(EmployeesService.class, request);
		
		ModificaForm initForm = (ModificaForm) form;
		initForm.setAction(ModificaForm.AZIONE_INSERT);
		String id = request.getParameter("id");
		if( id != null ){			
			Employees e = employeesService.getEmployee( Integer.parseInt(id) );
			initForm.setReadOnly(true);
			initForm.setId( ((Integer)e.getEmployeeId()).toString() );
			initForm.setFirstName(e.getFirstName());
			initForm.setLastName(e.getLastName());
			initForm.setPhoneNumber(e.getPhoneNumber());
			initForm.setEmail(e.getEmail());
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");			
			initForm.setHireDate( sdf.format(e.getHireDate()) );
			
			initForm.setSalary( e.getSalary().toString() );
			initForm.setCommissionPct( e.getCommissionPct().toString() );
			initForm.setSelectedDepartment( ((Short)e.getDepartments().getDepartmentId()).toString() );
			initForm.setSelectedJob(e.getJobs().getJobId() );
			initForm.setSelectedManager( ((Integer)e.getEmployees().getEmployeeId()).toString() );			
			initForm.setAction(ModificaForm.AZIONE_UPDATE);
		}		
		return actionForward;
	}
	
}