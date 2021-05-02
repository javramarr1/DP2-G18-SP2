package acme.features.spam;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spam.Spam;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SpamRepository extends AbstractRepository{

	@Query("SELECT spam.word from Spam spam")
	List<String> findAllSpamWords();
	
	@Query("SELECT threshold.threshold from Threshold threshold")
	Double findThreshold();
	
	@Query("SELECT spam from Spam spam")
	Collection<Spam> findAllSpam();

	@Query("SELECT spam from Spam spam WHERE spam.id = ?1")
	Spam findOneSpamById(int id);
}
