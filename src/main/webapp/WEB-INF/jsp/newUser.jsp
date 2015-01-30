<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html>
	<body>
		<div style="width:100%;"> 
			<html:link forward="home"> 
				<bean:message key="msg.home"/> 
			</html:link>
		</div>
		<html:form action="/modificaUser">
			<h2>
				<bean:message name="title" />
				<bean:message key="msg.dipendente"/> 
			</h2>
			<bean:message key="msg.dipendente"/>:
			<html:select property="selectedEmployee" name="ModificaUtente">
				<html:option value="0">
					<bean:message key="msg.seleziona.impiegato"/>
				</html:option>
				<html:optionsCollection name="employeeList" />
			</html:select>
			<br/>
			<bean:message key="msg.username"/>
			<html:text property="id"/>
			<br/>
			<bean:message key="msg.password"/>
			<html:password property="password"/>
			<br/>
			<bean:message key="msg.abilitato"/>:
			<html:radio property="enabled" value="true" /> 
			<bean:message key="msg.si"/>
			<html:radio property="enabled" value="false" /> 
			<bean:message key="msg.no"/>
			<br/>
			<html:submit property="azione">
				<bean:message property="azione" name="ModificaUtente" />
			</html:submit>
			<bean:message name="msg"/>
		</html:form>
		<html:errors/>
	</body>
</html>