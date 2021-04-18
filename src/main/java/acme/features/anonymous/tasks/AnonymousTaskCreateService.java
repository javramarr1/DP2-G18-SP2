package acme.features.anonymous.tasks;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousTaskCreateService implements AbstractCreateService<Anonymous, Task>{

	@Autowired
	protected AnonymousTaskRepository repository;
	
	
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
		result.setTitle("Pruebas unitarias");
		result.setDescription("Crear pruebas unitarias para el TokenService usando mockito");
		result.setEnd_date(LocalDateTime.parse("2021-06-13 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
		result.setEnd_date(LocalDateTime.parse("2021-06-13 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
		result.setIs_private(false);
		result.setWorkload("04 33/60");
		result.setOp_link("www.google.es");
		
		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}
	
}