package acme.features.spam;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpamService {
	
	@Autowired
	protected SpamRepository spamRepository;

	public boolean validateNoSpam(final String text) {
		
		final List<String> spamWords = this.spamRepository.findAllSpamWords();
		final String cutText = text.replace(" ", "").replace("\n", "n").toLowerCase().trim();
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
		
		final Double t = this.spamRepository.findThreshold();
		return (counter*100.0/words)<=t;	
	}

	
	private int numberOfWords(final String text) {
		final StringTokenizer st = new StringTokenizer(text);
		return st.countTokens();
	}

}
