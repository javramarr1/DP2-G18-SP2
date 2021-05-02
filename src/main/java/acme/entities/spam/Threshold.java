package acme.entities.spam;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Threshold extends DomainEntity{

	protected static final long	serialVersionUID	= 1L;
	
	@NotNull
	@Range(min = 0, max = 100)
	@Digits(integer = 3, fraction = 2)
	protected Double threshold;
}
