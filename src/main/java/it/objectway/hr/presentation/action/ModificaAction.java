package it.objectway.hr.presentation.action;

import java.text.SimpleDateFormat;

import it.objectway.hr.business.DBManager;
import it.objectway.hr.business.Manager;
import it.objectway.hr.dati.Department;
import it.objectway.hr.dati.Employee;
import it.objectway.hr.dati.Job;
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
		Manager manager = new DBManager();
		ModificaForm initForm = (ModificaForm) form;		
		Employee newEmployee = new Employee();
		newEmployee.setId(Integer.parseInt(initForm.getId()));
		newEmployee.setFirstName(initForm.getFirstName());
		newEmployee.setLastName(initForm.getLastName());
		newEmployee.setEmail(initForm.getEmail());
		newEmployee.setPhoneNumber(initForm.getPhoneNumber());
		newEmployee.setHireDate( new SimpleDateFormat("dd/MM/yyyy").parse(initForm.getHireDate()) );
		newEmployee.setSalary(Double.parseDouble(initForm.getSalary()));
		newEmployee.setCommissionPct(Float.parseFloat(initForm.getCommissionPct()));
		Job job = new Job();
		job.setId(initForm.getSelectedJob());
		newEmployee.setJob(job);
		Employee m = new Employee();
		m.setId(Integer.parseInt(initForm.getSelectedManager()));
		newEmployee.setManager(m);
		Department dep = new Department();
		dep.setId(Integer.parseInt(initForm.getSelectedDepartment()));
		newEmployee.setDepartment(dep);
		String action = initForm.getAction();
		String msg = null;
		if ( action.equals(ModificaForm.AZIONE_INSERT) ){
			msg = manager.insert(newEmployee) ? ModificaForm.ESITO_OK : ModificaForm.ESITO_KO;
			log.info("Insert : " + msg);
		} else if ( action.equals(ModificaForm.AZIONE_UPDATE) ){
			msg = manager.update(newEmployee) ? ModificaForm.ESITO_OK : ModificaForm.ESITO_KO;
			log.info("Update : " + msg);
		}
		initForm.setMessage(msg);
		Employee e = new Employee();
		e.setId(newEmployee.getId());
		e = manager.getEmployeeById(e);
		initForm.setReadOnly(true);
		initForm.setId(((Integer)e.getId()).toString());
		initForm.setFirstName(e.getFirstName());
		initForm.setLastName(e.getLastName());
		initForm.setPhoneNumber(e.getPhoneNumber());
		initForm.setEmail(e.getEmail());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");			
		initForm.setHireDate( sdf.format(e.getHireDate()) );

		initForm.setSalary(((Double)e.getSalary()).toString());
		initForm.setCommissionPct(((Float)e.getCommissionPct()).toString());		
		initForm.setSelectedDepartment(((Integer)e.getDepartment().getId()).toString());
		initForm.setSelectedJob(e.getJob().getId());
		initForm.setSelectedManager(((Integer)e.getManager().getId()).toString());		

		return mapping.findForward("success");
	}

}
