<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html>
  <body>
    <h2> <bean:message key="msg.home"/> </h2>
    <div style="text-align:right;width:100%;" > 
    	<html:link page="/logout.do"> 
    		<bean:message key="msg.logout"/>
    	</html:link>
    </div>
    
    <html:link page="/ricerca.do"> 
    	<bean:message key="msg.ricerca"/> 
    </html:link> <br/>
    
    <html:link page="/InitModifica.do"> 
    	<bean:message key="msg.inserimento"/> 
    </html:link> <br/>
    <html:link page="/reportDepartments.do"> 
    	<bean:message key="msg.report.dipartimenti"/> 
    </html:link> <br/>
    <html:link page="/users.do"> 
    	<bean:message key="msg.elenco.utenti"/> 
    </html:link> <br/>
    <html:link page="/initNewUser.do"> 
    	<bean:message key="msg.nuovo.utente"/> 
    </html:link> <br/>
    <html:errors/>
  </body>
</html>