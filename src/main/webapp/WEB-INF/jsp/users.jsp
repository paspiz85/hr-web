<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean"  prefix="bean"  %>
<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="html"  %>

<html>
	<head>
	<script type="text/javascript">
		function deleteUser(id){
			if ( confirm("<bean:message key='msg.conferma.elimina'/>") )
				window.location.href = "users.do?action=delete&id=" + id;
		}
	</script>
	</head>
	<body>
		<div style="width:100%;"> 
			 <html:link forward="home"> <bean:message key="msg.home"/> </html:link>
		</div>
		<logic:notEmpty name="userList" >
			<table border="1">
				<tr> 
					<th> <bean:message key="msg.id"/> </th>
					<th> <bean:message key="msg.nome"/> </th>
					<th> <bean:message key="msg.cognome"/> </th>
					<th> <bean:message key="msg.abilitato"/> </th>
					<th> <bean:message key="msg.modifica"/> </th>
					<th> <bean:message key="msg.elimina"/> </th>
				</tr>
				<logic:iterate name="userList" id="user">
					<tr> 
						<td> <bean:write name="user" property="employeeId"/> </td>
						<td> <bean:write name="user" property="nome"/> </td>
						<td> <bean:write name="user" property="cognome"/> </td>
						<td> <bean:write name="user" property="enabled"/> </td>
						<td> 
							<a href="<%= application.getContextPath() %>/initNewUser.do?id=<bean:write name="user" property="employeeId"/>">
								<bean:message key="msg.modifica"/>
							</a>
						</td>
						<td>
							<a href="javascript:deleteUser(<bean:write name="user" property="employeeId"/>)"> 
								<bean:message key="msg.elimina"/>
							</a>							
						</td>
					</tr>
				</logic:iterate>
			</table>
		</logic:notEmpty>
		<html:errors/>
	</body>
</html>