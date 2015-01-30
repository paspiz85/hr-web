package it.objectway.hr.presentation.action;

import it.objectway.hr.business.DBManager;
import it.objectway.hr.business.Manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InitUsersAction extends BaseAction {

	@Override
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Manager manager = new DBManager();
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		if ( action != null && id != null ){
			if(action.equals("delete")){
				String msg = manager.deleteUser(Integer.parseInt(id)) ? "Eliminato" : "Errore";
				log.info("Eliminazione : " + msg);
			}
		}
		request.setAttribute("userList", manager.getUsers());
		return mapping.findForward("success");
	}
}