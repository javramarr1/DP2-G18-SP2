package acme.features.anonymous.shout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousShoutListService implements AbstractListService<Anonymous, Shout> {

	// Internal state

	@Autowired
	protected AnonymousShoutRepository repository;


	// AbstractListService<Administrator, Shout> interface 

	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "moment");
	}

	@Override
	public Collection<Shout> findMany(final Request<Shout> request) {
		assert request != null;


		final List<Shout> list = new ArrayList<Shout>();
		

		Calendar  calendar;
		calendar=Calendar.getInstance();   //get the Calendar
		calendar.add(Calendar.MONTH, -1);  //subtract one month
		
		for(final Shout shout : this.repository.findMany()) {
			if(shout.getMoment().after(calendar.getTime())) {
				list.add(shout);
			}
					
			
		}
		
		return list;
	}

}
