/*
 * AdministratorDashboardShowService.java
 *
 * Copyright (c) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	// AbstractShowService<Administrator, Dashboard> interface ----------------


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"numPrivateTask", "numPublicTask", // 
			"numFinishedTask", "numCurrentTask", //
			"numExecutions", "numWorkloads");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result;
		Double numPrivateTask;
		Double numPublicTask;
		Double numFinishedTask;
		Double numCurrentTask;
		String numExecutions;
		String numWorkloads;
		

		numPrivateTask = this.repository.numPrivateTask();
		numPublicTask = this.repository.numPublicTask();
		numFinishedTask = this.repository.numFinishedTask();
		numCurrentTask = this.repository.numCurrentTask();
		numExecutions = this.repository.numExecutions();
		numWorkloads = this.repository.numWorkloads();
		

		result = new Dashboard();
		result.setNumPrivateTask(numPrivateTask);
		result.setNumPublicTask(numPublicTask);
		result.setNumFinishedTask(numFinishedTask);
		result.setNumCurrentTask(numCurrentTask);
		result.setNumExecutions(numExecutions);
		result.setNumWorkloads(numWorkloads);
		

		return result;
	}

}
