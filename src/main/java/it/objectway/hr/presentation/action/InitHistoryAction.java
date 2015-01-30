package it.objectway.hr.presentation.action;

import it.objectway.hr.business.DBManager;
import it.objectway.hr.business.Manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InitHistoryAction extends BaseAction {
	
	@Override
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String str = request.getParameter("id");
		if ( str != null && str.length() > 0 ) {
			int id = Integer.parseInt(str);
			Manager manager = new DBManager();
			request.setAttribute("jobHistoryList", manager.getJobHistory(id));
		}		
		return mapping.findForward("success");
	}
}
