package acme.features.spam;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface SpamRepository extends AbstractRepository{

	@Query("SELECT spam.word from Spam spam")
	List<String> findAllSpamWords();
	
	@Query("SELECT threshold.threshold from Threshold threshold")
	Double findThreshold();
}
