package acme.features.manager.task;


import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.features.administrator.configuration.SpamService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerTaskCreateService implements AbstractCreateService<Manager, Task>{

	
	protected ManagerTaskRepository repository;
	protected SpamService spamService;
	
	@Autowired
	protected ManagerTaskCreateService(final ManagerTaskRepository repository, final SpamService spamService) {
		this.repository = repository;
		this.spamService = spamService;
	}
	
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "start_date", "end_date", "is_private", "op_link", "workload");
	}

	@Override
	public Task instantiate(final Request<Task> request) {
		assert request != null;
		
		Task result;
		Manager manager;

		manager = this.repository.findOneManagerById(request.getPrincipal().getActiveRoleId());
		result = new Task();
		result.setManager(manager);
		result.setTitle(null);
		result.setDescription(null);
		result.setIs_private(null);
		result.setWorkload(null);
		result.setOp_link(null);
		
		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("workload")){
	        final String doubleAsString = String.valueOf(entity.getWorkload()) + "0";
	        final int indexOfDecimal = doubleAsString.indexOf(".");
	        final Long minutes = Long.valueOf(doubleAsString.substring(indexOfDecimal+1,indexOfDecimal+3));
	        final boolean less60 = minutes <= 59;
	        errors.state(request, less60, "workload", "manager.task.form.label.less60", "");
	    }
		
		if (!errors.hasErrors("start_date") && !errors.hasErrors("end_date") ) {
			errors.state(request, entity.okDates(), "start_date","manager.task.form.label.datesError", "");
			errors.state(request, Calendar.getInstance().toInstant().isBefore(entity.getStart_date().toInstant()), "start_date", "manager.task.form.future", "");
			errors.state(request, Calendar.getInstance().toInstant().isBefore(entity.getEnd_date().toInstant()), "end_date", "manager.task.form.future", "");
		}
		
		if (!errors.hasErrors("workload") && !errors.hasErrors("start_date") && !errors.hasErrors("end_date")) {
			errors.state(request, entity.itFits(), "workload","manager.task.form.label.workloadError", "");
		}
		
		errors.state(request, this.spamService.validateNoSpam(entity.getTitle()), "title", "manager.task.form.label.spam", "spam");
		errors.state(request, this.spamService.validateNoSpam(entity.getDescription()), "description", "manager.task.form.label.spam", "spam");
			
		if(!errors.hasErrors("op_link")) {
			errors.state(request, this.spamService.validateNoSpam(entity.getOp_link()), "op_link", "manager.task.form.label.spam", "spam");
		}
			
		
		
	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}
	
}