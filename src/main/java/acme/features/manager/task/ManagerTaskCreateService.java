package acme.features.manager.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerTaskCreateService implements AbstractCreateService<Manager, Task>{

	@Autowired
	protected ManagerTaskRepository repository;
	
	
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

		result = new Task();
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
			if(Boolean.FALSE.equals(entity.okDates()) ) {
				errors.add("start_date", "The start of the date must go before the end.");
			}
		}
		
		if (entity.getWorkload() != null && entity.getStart_date() != null && entity.getEnd_date() !=null) {
			if(Boolean.FALSE.equals(entity.itFits())) {
				errors.add("workload", "Workload time must fit between both dates.");
			}			
		}
	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}
	
}