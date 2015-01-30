package it.objectway.hr.presentation.action;

import it.objectway.hr.business.DBManager;
import it.objectway.hr.business.Manager;
import it.objectway.hr.dati.Department;
import it.objectway.hr.dati.Employee;
import it.objectway.hr.dati.Job;
import it.objectway.hr.presentation.form.InitRicercaForm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RicercaAction extends BaseAction {
	
	@Override
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Manager manager = new DBManager();		
		InitRicercaForm initForm = (InitRicercaForm) form;		
		Employee e = new Employee();
		
		String lastName = initForm.getLastName();
		String jobId = initForm.getSelectedJob();
		String departmentId = initForm.getSelectedDepartment();
		
		if( lastName != null && lastName.length() > 0 ){
			e.setLastName(lastName);
		}
		if( jobId != null && jobId.length() > 0 ){
			if( ! jobId.equals("0") ){	
				Job j = new Job();
				j.setId(jobId);
				e.setJob(j);
			}
		}		
		if( departmentId != null && departmentId.length() > 0 ){
			int deptId = Integer.parseInt(departmentId);
			if( deptId > 0){
				Department d = new Department();
				d.setId(deptId);
				e.setDepartment(d);
			}
		}
		
		List<Employee> listaEmployee = manager.getEmployee(e);
		initForm.setEmployeesList(listaEmployee);
		ActionForward actionForward = mapping.findForward("success");
		
		return actionForward;
		
	}
	
}
