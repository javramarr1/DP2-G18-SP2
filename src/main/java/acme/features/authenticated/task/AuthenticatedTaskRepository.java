package acme.features.authenticated.task;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedTaskRepository extends AbstractRepository{


	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);

	@Query("select t from Task t where t.is_private = 0 and t.end_date < ?1 order by t.workload ")
	Collection<Task> findFinishedAndPublicTask(Date time);

}
