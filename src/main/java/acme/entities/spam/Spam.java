package acme.entities.spam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Spam extends DomainEntity{
	
	protected static final long	serialVersionUID	= 1L;
	
	@NotBlank
	@Column(unique=true)
	@Length(min = 1, max = 50)
	protected String word;
	
}
