package acme.features.administrator.threshold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Threshold;
import acme.features.spam.SpamRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorThresholdUpdateService implements AbstractUpdateService<Administrator, Threshold> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected SpamRepository repository;

	// AbstractUpdateService<Administrator, Threshold> interface -------------


	@Override
	public boolean authorise(final Request<Threshold> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Threshold> request, final Threshold entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Threshold> request, final Threshold entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "threshold");
	}

	@Override
	public Threshold findOne(final Request<Threshold> request) {
		assert request != null;

		Threshold result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneThresholdById(id);

		return result;
	}

	@Override
	public void validate(final Request<Threshold> request, final Threshold entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Threshold> request, final Threshold entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
