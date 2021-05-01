package acme.features.manager.task;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository{
	
	@Query("select t from Task t where t.end_date >= ?1 order by t.workload ")
	Collection<Task> findNonFinishedTask(Date end);
	
	@Query("select t from Task t where t.end_date >= ?1 and t.manager.id = ?1 order by t.workload ")
	Collection<Task> findNonFinishedTaskByManagerId(Date end, int id);
	
	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);
	
	@Query("select m from Manager m where m.id = ?1")
	Manager findOneManagerById(int id);
	
}
