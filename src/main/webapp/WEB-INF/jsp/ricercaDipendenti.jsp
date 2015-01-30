<%@ page language="java" contentType="text/html; charset=utf-8" %><%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean"  prefix="bean"  %>
<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="html"  %>

<html>
<head>
	<script type="text/javascript">
		function history(id){
			window.open ('/ProgettoHR/history.do?id=' + id,
					'History', 'status=yes,resizable=yes,scrollbars=yes,width=700,height=700');
		}
	</script>
</head>
	<body>
		<div style="float:right;width:100%;"> 
			<html:link forward="home"> <bean:message key="msg.home"/> </html:link>
		</div>
		<h2> <bean:message key="ricerca.up.title"/> </h2>
		<h4> <bean:message key="ricerca.cercaper"/> </h4>
		<html:form action="/ricercaDipendenti" >	
			<bean:message key="ricerca.cognome"/> 
	    	<html:text property="lastName" />
			<br/>
			<bean:message key="ricerca.dipartimento"/> 
			<html:select property="selectedDepartment" >
				<html:option value="0"> <bean:message key="ricerca.seleziona.dipartimento"/> </html:option>
				<html:optionsCollection name="departmentsList" />
			</html:select>
			<br/>
			<bean:message key="msg.titolo"/>
			<html:select property="selectedJob" >
				<html:option value="0"><bean:message key="ricerca.seleziona.jobs"/></html:option>
				<html:optionsCollection name="jobsList" />
			</html:select>
			<br/>
			<html:submit > <bean:message key="msg.cerca"/> </html:submit><br/>				
			<logic:notEmpty name="initRicerca" property="employeesList">
				<table border="1">
					<tr> 
						<th> <bean:message key="msg.id"/> </th> 
						<th> <bean:message key="msg.nome"/> </th>
						<th> <bean:message key="msg.cognome"/> </th> 
						<th> <bean:message key="msg.email"/> </th> 
						<th> <bean:message key="msg.recapito"/> </th> 
						<th> <bean:message key="msg.lavoro"/> </th>
						<th> <bean:message key="msg.dipartimento"/> </th>  
					</tr>
					<logic:iterate name="initRicerca" property="employeesList" id="impiegati">
						<tr> 
							<td> 
								<a href="javascript:history(<bean:write name="impiegati" property="id"/>)"> 
									<bean:write name="impiegati" property="id"/>
								</a>								 
							</td>
							<td> <bean:write name="impiegati" property="firstName"/> </td>
							<td> <bean:write name="impiegati" property="lastName"/> </td> 
							<td> <bean:write name="impiegati" property="email"/> </td> 
							<td> <bean:write name="impiegati" property="phoneNumber"/> </td>
							<td> <bean:write name="impiegati" property="job.title"/> </td>
							<td> <bean:write name="impiegati" property="department.name"/> </td>
							<td> 
								<a href="/ProgettoHR/InitModifica.do?id=<bean:write name="impiegati" property="id"/>">
									<bean:message key="msg.modifica"/>
								</a>
							</td>  
						</tr>
					</logic:iterate>
				</table>			
			</logic:notEmpty>
		<html:errors/>			
		</html:form>
	</body>
</html>