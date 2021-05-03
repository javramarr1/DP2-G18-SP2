package acme.features.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.features.spam.SpamService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerTaskUpdateService implements AbstractUpdateService<Manager, Task> {

	// Internal state ---------------------------------------------------------

	protected ManagerTaskRepository repository;
	protected SpamService spamService;
	
	@Autowired
	protected ManagerTaskUpdateService(final ManagerTaskRepository repository, final SpamService spamService) {
		this.repository = repository;
		this.spamService = spamService;
	}
	

	// AbstractUpdateService<Manager, Task> interface -------------


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		
		boolean result;
		int taskId;
		Task task;
		Manager manager;
		Principal principal;
		
		taskId = request.getModel().getInteger("id");
		task = this.repository.findOneTaskById(taskId);
		manager = task.getManager();
		principal = request.getPrincipal();
		result = manager.getUserAccount().getId() == principal.getAccountId();
		
		return result;
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

		request.unbind(entity, model, "start_date", "end_date", "workload", "title", "is_private", "description", "op_link");
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;

		Task result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneTaskById(id);

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
		
		if (entity.getTitle() != null && entity.getDescription() != null && entity.getOp_link()!= null) {
			errors.state(request, this.spamService.validateNoSpam(entity.getTitle()), "title", "manager.task.form.label.spam", "spam");
			errors.state(request, this.spamService.validateNoSpam(entity.getDescription()), "description", "manager.task.form.label.spam", "spam");
			errors.state(request, this.spamService.validateNoSpam(entity.getOp_link()), "op_link", "manager.task.form.label.spam", "spam");
		}
	}

	@Override
	public void update(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		if (request.getModel().getString("is_private").equals("true")) {
			entity.setIs_private(true);
		} else if (request.getModel().getString("is_private").equals("false")) {
			entity.setIs_private(false);
		}

		this.repository.save(entity);
	}

}
