package acme.entities.configuration;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Configuration extends DomainEntity{
	
	protected static final long	serialVersionUID	= 1L;
	
	@Valid
	@OneToMany
	@Size(min = 1, max = 50)
	protected Collection<Spam> spamList;
	
	@NotNull
	@Range(min = 0, max = 100)
	@Digits(integer = 3, fraction = 2)
	protected Double threshold;

}
