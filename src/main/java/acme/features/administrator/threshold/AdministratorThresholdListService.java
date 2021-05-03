package acme.features.administrator.threshold;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Threshold;
import acme.features.spam.SpamRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorThresholdListService implements AbstractListService<Administrator, Threshold>{
	
	// Internal state

		@Autowired
		protected SpamRepository repository;


		// AbstractListService<Administrator, Threshold> interface 

		@Override
		public boolean authorise(final Request<Threshold> request) {
			assert request != null;

			return true;
		}

		@Override
		public void unbind(final Request<Threshold> request, final Threshold entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "threshold");
		}

		@Override
		public Collection<Threshold> findMany(final Request<Threshold> request) {
			assert request != null;

			final Collection<Threshold> result;

			result = this.repository.findAllThreshold();
			
			return result;
		}

}
