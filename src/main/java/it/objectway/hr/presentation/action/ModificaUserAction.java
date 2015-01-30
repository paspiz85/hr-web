package it.objectway.hr.presentation.action;

import it.objectway.hr.business.DBManager;
import it.objectway.hr.business.Manager;
import it.objectway.hr.dati.User;
import it.objectway.hr.presentation.form.ModificaForm;
import it.objectway.hr.presentation.form.NewUserForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ModificaUserAction extends BaseAction {

	@Override
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		Manager m = new DBManager();
		NewUserForm newUserForm = (NewUserForm) form;
		String action = newUserForm.getAzione();
		request.setAttribute("listaEmployee", request.getSession().getServletContext().getAttribute("listaEmployee"));
		User user = new User();
		user.setId(newUserForm.getId());
		user.setEnabled( Boolean.parseBoolean(newUserForm.getEnabled()) );
		user.setPassword(newUserForm.getPassword());		
		user.setEmployeeId(Integer.parseInt(newUserForm.getSelectedEmployee()));
		String msg = "azione.empty";		
		if( action.equals(ModificaForm.AZIONE_INSERT) ){
			request.setAttribute("title", "azione.insert");
			newUserForm.setAzione( "azione.insert");
			msg = m.insertUser(user) ? "azione.esito.ok" : "azione.esito.ko";
			log.info("Inserimento Utente : " + msg);	
		} else if( action.equals(ModificaForm.AZIONE_UPDATE) ){
			request.setAttribute("title", "azione.modify");
			newUserForm.setAzione("azione.modify");
			msg = m.updateUser(user) ? "azione.esito.ok" : "azione.esito.ko";
			log.info("Aggiornamento Utente : " + msg);			
		}
		request.setAttribute("msg", msg);
		return mapping.findForward("success");
		
	}
}
