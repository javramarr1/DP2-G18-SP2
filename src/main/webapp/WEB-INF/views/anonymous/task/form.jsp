<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script type="text/javascript">
			$(function(){
				$("#start_date").datepicker({"yyyy-MM-dd HH:mm"});
				$("#end_date").datepicker({"yyyy-MM-dd HH:mm"});
			});
</script>


<acme:form>
	<acme:form-textbox code="anonymous.task.form.label.title" path="title"/>
	<acme:form-textarea code="anonymous.task.form.label.description" path="description"/>
	<acme:form-textbox code="anonymous.task.form.label.start_date" path="start_date"/>
	<acme:form-textbox code="anonymous.task.form.label.end_date" path="end_date"/>
	<acme:form-textbox code="anonymous.task.form.label.workload" path="workload"/>
	<acme:form-textbox code="anonymous.task.form.label.op_url" path="op_url"/>
	<acme:form-select code="anonymous.task.form.label.is_private" path="is_private">
		<acme:form-option code="YES" value="true" selected="${is_private == true}"/>
		<acme:form-option code="NO" value="false" selected="${is_private == false}"/>
	</acme:form-select>
	
	<acme:form-submit code="anonymous.task.form.button.create" action="/anonymous/task/create"/>
  	<acme:form-return code="anonymous.task.form.button.return"/>

</acme:form>