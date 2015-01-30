package it.objectway.hr.presentation.action;

import it.objectway.hr.business.DBManager;
import it.objectway.hr.business.Manager;
import it.objectway.hr.dati.User;
import it.objectway.hr.presentation.form.NewUserForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class InitNewUserAction extends BaseAction {
	
	@Override
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewUserForm userForm = (NewUserForm) form;
		Manager m = new DBManager();
		String id = request.getParameter("id");
		if ( id != null ){
			User u = new User();
			u.setEmployeeId(Integer.parseInt(id));
			u = m.getUser(u);
			userForm.setId(u.getId());
			userForm.setPassword(u.getPassword());
			userForm.setSelectedEmployee(id);
			userForm.setEnabled( u.getEnabled() ? "true" : "false" );
			request.setAttribute("title", "azione.modify");
			userForm.setAzione("azione.modify");
		} else {
			request.setAttribute("title", "azione.insert");
			userForm.setAzione("azione.insert");
			userForm.setEnabled("true");
		}
		request.setAttribute("msg", "azione.empty");
		return mapping.findForward("success");
	}

}
