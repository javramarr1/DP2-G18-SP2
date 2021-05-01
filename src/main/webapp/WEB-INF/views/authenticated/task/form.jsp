<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
	<acme:form-textbox code="authenticated.task.form.label.title" path="title"/>
	<acme:form-textarea code="authenticated.task.form.label.description" path="description"/>
	<acme:form-moment code="authenticated.task.form.label.start_date" path="start_date"/>
	<acme:form-moment code="authenticated.task.form.label.end_date" path="end_date"/>
	<acme:form-textbox code="authenticated.task.form.label.workload" path="workload"/>
	<acme:form-textbox code="authenticated.task.form.label.op_url" path="op_url"/>
	<acme:form-select code="authenticated.task.form.label.is_private" path="is_private">
		<acme:form-option code="YES" value="true" selected="${is_private == true}"/>
		<acme:form-option code="NO" value="false" selected="${is_private == false}"/>
	</acme:form-select>
	

	<acme:form-submit test="${command == 'create'}" code="authenticated.task.form.button.create" action="/authenticated/task/create"/>
  	<acme:form-return code="authenticated.task.form.button.return"/>

</acme:form>