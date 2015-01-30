<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean"  prefix="bean"  %>
<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="html"  %>

<html>
	<body>
		<div style="width:100%;">
			<html:link forward="home"> <bean:message key="msg.home"/> </html:link>
		</div>
		<logic:notEmpty name="reportDepartmentsList">
			<table border="1">
				<tr> 
					<th> <bean:message key="msg.id"/> </th>
					<th> <bean:message key="msg.dipartimento"/> </th>
					<th> <bean:message key="msg.nazione"/> </th>
					<th> <bean:message key="msg.numero.impiegati"/> </th>
					<th> <bean:message key="msg.stipendio.medio"/> </th>
					<th> <bean:message key="msg.cognome"/> </th>
				</tr>
				<logic:iterate name="reportDepartmentsList" id="dep">
					<tr> 
						<td> <bean:write name="dep" property="id"/> </td>
						<td> <bean:write name="dep" property="name"/> </td>
						<td> <bean:write name="dep" property="countryName"/> </td>
						<td> <bean:write name="dep" property="employeeNumber"/> </td>
						<td> <bean:write name="dep" property="stipendioMedio"/> </td>
						<td> <bean:write name="dep" property="managerLastName"/> </td>
					</tr>
				</logic:iterate>
			</table>
		</logic:notEmpty>	
		<html:errors/>
	</body>
</html>