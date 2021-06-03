package acme.features.administrator.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Spam;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorConfigurationSpamCreateService implements AbstractCreateService<Administrator, Spam>{

	@Autowired
	protected AdministratorConfigurationRepository repository;
	
	protected SpamService spamService;
	
	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "word");
	}

	@Override
	public Spam instantiate(final Request<Spam> request) {
		assert request != null;
		
		Spam result;

		result = new Spam();
		result.setWord(null);
		
		return result;
	}

	@Override
	public void validate(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("spam")) {
            Spam existing;
            existing = this.repository.findOneSpamByName(entity.getWord());
            errors.state(request, existing == null, "word", "administrator.spam.form.label.duplicate", "word");
        }
		
	}

	@Override
	public void create(final Request<Spam> request, final Spam entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}
	
}