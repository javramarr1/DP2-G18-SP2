<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
	<acme:form-textbox code="anonymous.task.form.label.title" path="title"/>
	<acme:form-textarea code="anonymous.task.form.label.description" path="description"/>
	<acme:form-moment code="anonymous.task.form.label.start_date" path="start_date"/>
	<acme:form-moment code="anonymous.task.form.label.end_date" path="end_date"/>
	<acme:form-double code="anonymous.task.form.label.workload" path="workload"/>
	<acme:form-textbox code="anonymous.task.form.label.op_url" path="op_url"/>
	<acme:form-checkbox code="manager.task.form.label.is_private" path="is_private"/>
	
  	<acme:form-return code="anonymous.task.form.button.return"/>

</acme:form>