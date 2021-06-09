package acme.features.anonymous.shout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.features.administrator.configuration.SpamService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout> {

	// Internal state 
	
	protected AnonymousShoutRepository repository;
	protected SpamService spamService;
	
	@Autowired
	protected AnonymousShoutCreateService(final AnonymousShoutRepository repository, final SpamService spamService) {
		this.repository = repository;
		this.spamService = spamService;
	}

	// AbstractCreateService<Administrator, Shout> interface 

	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "info","xxx");
	}

	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;

		Shout result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new Shout();
		result.setMoment(moment);		

		return result;
	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(entity.getAuthor() != null && entity.getText() != null) {
			errors.state(request, this.spamService.validateNoSpam(entity.getAuthor()), "author", "anonymous.shout.form.label.spam", "spam");
			errors.state(request, this.spamService.validateNoSpam(entity.getText()), "text", "anonymous.shout.form.label.spam", "spam");			
		}
		
		if(entity.getInfo() != null)	errors.state(request, this.spamService.validateNoSpam(entity.getInfo()), "info", "anonymous.shout.form.label.spam", "spam");
		
		if(!errors.hasErrors("xxx.creationDate") && entity.getXxx().getCreationDate() != null) {
			final String s = entity.getXxx().getCreationDate();
			final String[] splitter = s.split("-");
			final String stringDate = splitter[1].trim();
			final String[] splitter2 = stringDate.split("/");
			
			final boolean correctDate = Integer.parseInt(splitter2[1])<=12&&Integer.parseInt(splitter2[2])<=31;
			errors.state(request, correctDate, "xxx.creationDate", "anonymous.shout.form.dateFormat", "");
			if(correctDate) {
				final LocalDate date = LocalDate.parse(stringDate,DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			
				errors.state(request, date.isEqual(LocalDate.now()), "xxx.creationDate", "anonymous.shout.form.label.today", " ");
			
				errors.state(request, this.repository.numberOfShoutsByCreationDate(s).equals(0), "xxx.creationDate", "anonymous.shout.form.unique", " ");
			}
		}
		
		
		if(!errors.hasErrors("xxx.money") && entity.getXxx().getMoney() != null) 
			errors.state(request,
				entity.getXxx().getMoney().getCurrency().equals("EUR")||entity.getXxx().getMoney().getCurrency().equals("USD"),
				"xxx.money", "anonymous.shout.form.label.currency", "");
	}

	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity.getXxx());
		this.repository.save(entity);
	}

}

