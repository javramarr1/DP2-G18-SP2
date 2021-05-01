<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list >
	<acme:list-column code="manager.task.list.label.title" path="title" width="20%"/>
	<acme:list-column code="manager.task.list.label.start_date" path="start_date" width="20%"/>
	<acme:list-column code="manager.task.list.label.end_date" path="end_date" width="20%"/>
	<acme:list-column code="manager.task.list.label.workload" path="workload" width="40%"/>	
</acme:list>
