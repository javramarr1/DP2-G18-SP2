package acme.features.authenticated.task;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedTaskShowService implements AbstractShowService<Authenticated, Task>{
	
	@Autowired
	protected AuthenticatedTaskRepository repository;

	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		Task task;
		int taskId;
		taskId = request.getModel().getInteger("id");
		task = this.repository.findOneTaskById(taskId);
		
		if(task.getIs_private().equals(true) || task.getEnd_date().after(Calendar.getInstance().getTime())) return false;
		return true;
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
		
		final Date current = Calendar.getInstance().getTime();
		assert result.getEnd_date().before(current);

		return result;
	}

}
