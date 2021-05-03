package acme.features.manager.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.features.spam.SpamService;
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
		
		if (entity.getStart_date() != null && entity.getEnd_date() !=null) {
			errors.state(request, entity.okDates(), "start_date","manager.task.form.label.datesError", "");	
		}
		
		if (entity.getWorkload() != null && entity.getStart_date() != null && entity.getEnd_date() !=null) {
			errors.state(request, entity.itFits(), "workload","manager.task.form.label.workloadError", "");			
		}
		
		errors.state(request, this.spamService.validateNoSpam(entity.getTitle()), "title", "manager.task.form.label.spam", "spam");
		errors.state(request, this.spamService.validateNoSpam(entity.getDescription()), "description", "manager.task.form.label.spam", "spam");
		errors.state(request, this.spamService.validateNoSpam(entity.getOp_link()), "op_link", "manager.task.form.label.spam", "spam");
		
	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}
	
}