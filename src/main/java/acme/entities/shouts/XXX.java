package acme.entities.shouts;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class XXX extends DomainEntity{
	
	protected static final long	serialVersionUID	= 1L;
	
	@Column(unique=true)
	@Pattern(regexp = "^[a-zA-Z]{3}\\-\\d{4}/\\d{2}/\\d{2}$")//Patron con barras
	//@Pattern(regexp = "^[a-zA-Z]{3}\\-\\d{4}-\\d{2}-\\d{2}$")//este funciona con guiones (y-m-d)
	protected String creationDate;
	
	//@Past
	//Present y future habria que comprobarlos en el servicio
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date moment;
	
	@NotNull
	@Valid
	protected Money money;
	
	protected Boolean flag;
	
}
