/*
 * AdministratorUserAccountUpdateService.java
 *
 * Copyright (c) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerTaskUpdateService implements AbstractUpdateService<Manager, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerTaskRepository repository;

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
