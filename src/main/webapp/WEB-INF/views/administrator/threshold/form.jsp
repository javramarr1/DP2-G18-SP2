<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.threshold.form.label.threshold" path="threshold"/>
	
	<acme:form-submit test="${command == 'show'}" code="administrator.threshold.form.button.update" action="/administrator/threshold/update"/>
  	<acme:form-return code="administrator.threshold.form.button.return"/>
</acme:form>
