<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<bean:define id="read" name="Modifica" property="readOnly"/>

<html>
	<body>
		<div style="float:right;width:100%;"> 
			<html:link forward="home"> <bean:message key="msg.home"/> </html:link>
		</div>
		<h2> <bean:write property="title" name="Modifica"/> <bean:message key="msg.dipendente"/> </h2>
		<html:form action="/ModificaEmployee" >
			<html:hidden property="action"/>
			<table>
				<tr>
					<td> <bean:message key="msg.id"/> </td>
					<td> 
						<html:text property="id" readonly="<%=(Boolean)read%>" /> 
					</td>
				</tr>
				<tr> 
					<td> <bean:message key="msg.nome"/> </td>
					<td> <html:text property="firstName"/> </td>
				<tr> 
					<td> <bean:message key="msg.cognome"/> </td>
					<td> <html:text property="lastName"/> </td>
				</tr>
				<tr>	
					<td> <bean:message key="msg.email"/> </td>
					<td> <html:text property="email"/> </td>
				</tr>	
				<tr>
					<td> <bean:message key="msg.recapito"/> </td>
					<td> <html:text property="phoneNumber"/> </td>
				</tr>
				<tr>
					<td> <bean:message key="msg.data.assunzione"/> </td>
					<td> 
						 <html:text property="hireDate" >
						 	<bean:write property="hireDate" name="Modifica" formatKey="formato.data" /> 
						 </html:text>
					</td>
				</tr>
				<tr>	
					<td> <bean:message key="msg.stipendio"/> </td>
					<td> <html:text property="salary"/> </td>
				</tr>
				<tr>
					<td> <bean:message key="msg.lavoro"/> </td>
					<td>
						<html:select property="selectedJob" >
							<html:optionsCollection name="jobsList" />
						</html:select>
					</td>
				</tr>	
				<tr> 
					<td> <bean:message key="msg.commissione.pct"/> </td>
					<td> <html:text property="commissionPct"/> </td>
				</tr>
				<tr> 
					<td> <bean:message key="msg.manager"/> </td>
					<td> 
						<html:select property="selectedManager" >
							<html:optionsCollection name="managerList" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td> <bean:message key="msg.dipartimento"/> </td>
					<td>
						<html:select property="selectedDepartment" >
							<html:optionsCollection name="departmentsList" />
						</html:select>
					</td>	
				</tr>
				<tr> 
					<td> <html:submit> <bean:write property="button" name="Modifica"/> </html:submit> </td>
				</tr>
				<tr> 
					<td colspan="2"> <bean:write property="message" name="Modifica"/>  </td>
				</tr>
			</table>
		</html:form>
		<html:errors/>
	</body>
</html>