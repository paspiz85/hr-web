package it.objectway.hr.presentation.action;

import java.text.SimpleDateFormat;

import it.objectway.hr.business.DBManager;
import it.objectway.hr.business.Manager;
import it.objectway.hr.dati.Employee;
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
		Manager manager = new DBManager();
		ModificaForm initForm = (ModificaForm) form;
//		List<LabelValueBean> listaDipartimenti = Utils.getListaDepartment();
//		List<LabelValueBean> listaJobs = Utils.getListaJobs();
//		List<LabelValueBean> listaManager = Utils.getListaManager();
//		initForm.setAction(ModificaForm.AZIONE_INSERT);
//		initForm.setDepartmentsList(listaDipartimenti);
//		initForm.setJobsList(listaJobs);
//		initForm.setManagerList(listaManager);
		String id = request.getParameter("id");
		if( id != null ){			
			Employee e = new Employee();
			e.setId(Integer.parseInt(id));
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
			initForm.setAction(ModificaForm.AZIONE_UPDATE);
		}		
		return actionForward;
	}
	
}