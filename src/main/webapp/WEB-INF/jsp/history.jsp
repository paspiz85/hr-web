<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean"  prefix="bean"  %>
<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="html"  %>
<html>
	<body>
		<logic:notEmpty name="jobHistoryList">
			<table border="1">
				<tr> 
					<th> <bean:message key="msg.id"/> </th> 
					<th> <bean:message key="history.date.start"/> </th>
					<th> <bean:message key="history.date.end"/> </th> 
					<th> <bean:message key="msg.titolo"/> </th> 
					<th> <bean:message key="msg.dipartimento"/> </th> 
				</tr>
				<logic:iterate name="jobHistoryList" id="jobHistory">
					<tr> 
						<td> 
							<bean:write name="jobHistory" property="employee.firstName"/>
							<bean:write name="jobHistory" property="employee.lastName"/>  
						</td>
						<td> <bean:write property="startDate" name="jobHistory" formatKey="formato.data"/> </td>
						<td> <bean:write property="endDate" name="jobHistory" formatKey="formato.data" /> </td>
						<td> <bean:write name="jobHistory" property="job.title"/> </td>
						<td> <bean:write name="jobHistory" property="department.name"/> </td>
					</tr>
				</logic:iterate>
			</table>			
		</logic:notEmpty>
		<logic:empty name="jobHistoryList">
			<bean:message key="msg.history.vuota"/>
		</logic:empty>
		<html:errors/>
	</body>	
</html>