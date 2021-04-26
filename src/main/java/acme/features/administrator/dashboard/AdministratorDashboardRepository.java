/*
 * AdministratorDashboardRepository.java
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

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select COUNT(t) from Task t where t.is_private=TRUE")
	Double numPrivateTask();
	
	@Query("select COUNT(t) from Task t where t.is_private=FALSE")
	Double numPublicTask();
	
	@Query("select COUNT(t) from Task t where t.end_date>CURDATE()")
	Double numFinishedTask();
	
	@Query("select COUNT(t) from Task t where t.end_date<CURDATE()")
	Double numCurrentTask();
	
//	@Query("select AVG((datediff(dd, t.end_date, t.start_date))), stddev((datediff(dd, t.end_date, t.start_date))), min((datediff(dd, t.end_date, t.start_date))), max((datediff(dd, t.end_date, t.start_date))) from Task t")
//	String numExecutions();
	
	@Query("select avg(datediff(t.end_date, t.start_date)), stddev(datediff(t.end_date, t.start_date)), min(datediff(t.end_date, t.start_date)), max(datediff(t.end_date, t.start_date)) from Task t")
	String numExecutions();
	
	
	@Query("select avg(t.workload), stddev(t.workload), min(t.workload), max(t.workload) from Task t")
	String numWorkloads();

}
