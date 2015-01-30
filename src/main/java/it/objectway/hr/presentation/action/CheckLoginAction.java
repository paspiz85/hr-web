package it.objectway.hr.presentation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CheckLoginAction extends BaseAction {
	
	@Override
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward actionForward;
		HttpSession session = request.getSession();
		String user = (String)session.getAttribute("username");
		actionForward = (user != null && user.length() > 0) ? mapping.findForward("ok") : mapping.findForward("ko");
		return actionForward;
	}

}
