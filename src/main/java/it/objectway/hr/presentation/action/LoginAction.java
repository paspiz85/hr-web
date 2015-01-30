package it.objectway.hr.presentation.action;

import java.util.ResourceBundle;

import it.objectway.hr.business.DBManager;
import it.objectway.hr.business.Manager;
import it.objectway.hr.presentation.form.LoginForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoginAction extends BaseAction {
	
	@Override
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginForm login = (LoginForm) form;		
		String username = login.getUsername();		
		String password = login.getPassword();		
		Manager manager = new DBManager();
		ActionForward actionForward = mapping.findForward("home");
		if ( ! manager.login(username, password) ){
			actionForward = mapping.findForward("ko");
			login.setPassword("");
			ResourceBundle res = ResourceBundle.getBundle("MessageResouces");
			login.setError(res.getString("msg.login.errato"));
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
		}
		return actionForward;		
	}
}
