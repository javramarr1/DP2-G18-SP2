<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
	<acme:form-textbox code="manager.task.form.label.title" path="title"/>
	<acme:form-textarea code="manager.task.form.label.description" path="description"/>
	<acme:form-moment code="manager.task.form.label.start_date" path="start_date"/>
	<acme:form-moment code="manager.task.form.label.end_date" path="end_date"/>
	<acme:form-double code="manager.task.form.label.workload" path="workload"/>
	<acme:form-textbox code="manager.task.form.label.op_url" path="op_link"/>
	
	<jstl:if test="${!readonly}">
		<acme:form-checkbox code="manager.task.form.label.is_private" path="is_private"/>
	</jstl:if>

	<acme:form-submit test="${command == 'show'}" code="manager.task.form.button.update" action="/manager/task/update"/>
	<acme:form-submit test="${command == 'show'}" code="manager.task.form.button.delete" action="/manager/task/delete"/>
	<acme:form-submit test="${command == 'create'}" code="manager.task.form.button.create" action="/manager/task/create"/>
  	<acme:form-return code="manager.task.form.button.return"/>

</acme:form>