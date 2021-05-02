<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.spam.form.label.word" path="word"/>
	

	<acme:form-submit test="${command == 'show'}" code="administrator.spam.form.button.update" action="/administrator/spam/update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.spam.form.button.delete" action="/administrator/spam/delete"/>
	<acme:form-submit test="${command == 'create'}" code="administrator.spam.form.button.create" action="/administrator/spam/create"/>
  	<acme:form-return code="administrator.spam.form.button.return"/>
</acme:form>
