package it.objectway.hr.presentation.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public abstract class BaseAction extends Action {
	
	protected final <T> T getBean(Class<T> c, ServletContext servletContext) {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		return applicationContext.getBean(c);
	}
	
	protected final <T> T getBean(Class<T> c, HttpServletRequest request) {
		return getBean(c, request.getSession().getServletContext());
	}
	
	public final Logger log = Logger.getLogger(getClass());
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		ActionForward doIt = null;
		log.info(getClass() + " --> Execute");
		try {
			doIt = doIt(mapping, form, request, response);
		} catch (Exception e){
			log.error(e.getMessage(), e);
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.imprevisto", e.getMessage()));
			//request.setAttribute("errors", errors);
			saveErrors(request, errors);
		}
		if ( doIt == null )
			doIt = mapping.findForward("ko");
		return doIt;
	}
	
	public abstract ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	
}