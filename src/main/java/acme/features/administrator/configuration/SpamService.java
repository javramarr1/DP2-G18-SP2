package acme.features.administrator.configuration;

import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpamService {
	
	@Autowired
	protected AdministratorConfigurationRepository spamRepository;

	public boolean validateNoSpam(final String text) {
		
		final List<String> spamWords = this.spamRepository.findAllSpam().stream().map(x->x.getWord()).collect(Collectors.toList());
		final String cutText = text.replace(" ", "").replace("\n", "").toLowerCase().trim();
		final int words = this.numberOfWords(text);
		Integer counter = 0; 
		for(int i = 0; i<spamWords.size();i++) {
			final String word = spamWords.get(i).replace(" ", "").toLowerCase().trim();
			boolean check = cutText.contains(word);
			while(check) {
				counter++;
				check = cutText.substring(cutText.indexOf(word)+word.length()).contains(word);
			}
		}
		
		final Double t = this.spamRepository.findAllConfigurations().get(0).getThreshold();
		if(words == 0) return true;
		return (counter*100.0/words)<=t;	
	}

	
	private int numberOfWords(final String text) {
		final StringTokenizer st = new StringTokenizer(text);
		return st.countTokens();
	}

}
