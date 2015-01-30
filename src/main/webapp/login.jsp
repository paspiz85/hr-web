<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html>
  <body>
    <html:form action="/loginActionTest">
    	<div style='float:left;width:40%;height:100%;'></div>
    	<div style='float:left;width:20%;margin-top:20%;'>
		    	<bean:message key="msg.username"/>
		    	<html:text property="username" value="admin"/>       		    	 
		        <bean:message key="msg.password"/>
		        <html:password property="password" value="admin"/><br/>        		        
		        <html:submit > <bean:message key="msg.login"/> </html:submit><br/>          		        
		        <html:errors/> 
	    </div>
    </html:form>    
  </body>
</html>