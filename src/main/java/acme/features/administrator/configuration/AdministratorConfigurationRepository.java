package acme.features.administrator.configuration;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.Configuration;
import acme.entities.configuration.Spam;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorConfigurationRepository extends AbstractRepository {
	
	@Query("SELECT c FROM Configuration c")
	List<Configuration> findAllConfigurations();
		
	@Query("SELECT s FROM Spam s")
	Collection<Spam> findAllSpam();
	
	@Query("SELECT spam from Spam spam WHERE spam.id = ?1")
	Spam findOneSpamById(int id);
	
	@Query("select s from Spam s where s.word = ?1")
    Spam findOneSpamByName(String name);
}
