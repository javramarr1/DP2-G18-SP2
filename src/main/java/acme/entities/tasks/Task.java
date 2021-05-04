package acme.entities.tasks;

import java.time.Duration;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Manager;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity{
	
	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
		@NotNull
		@Valid
		@ManyToOne(optional = false)
		protected Manager manager;
		
		@NotBlank
		@Size(min= 1, max = 80)
		protected String title;
		
		@NotBlank
		@Size(min = 1, max = 500)
		protected String description;
		
		@Temporal(TemporalType.TIMESTAMP)
		@NotNull
		protected Date start_date;
		
		@Temporal(TemporalType.TIMESTAMP)
		@NotNull
		protected Date end_date;
		
		@NotNull
		protected Boolean is_private;
		
		@URL
		protected String op_link;
		
		@NotNull
		@Min(0)
		@Max(1000)
		@Digits(integer = 3, fraction = 2)
		protected Double workload;
		
		public Boolean okDates() {
			final Duration duration = Duration.between(this.start_date.toInstant(), this.end_date.toInstant());
			
			if(duration.isNegative()) {
				return false;
			}else {
				return true;
			}
		}
		
		
		public Boolean itFits() {
			
			final Duration duration = Duration.between(this.start_date.toInstant(), this.end_date.toInstant());
			
			final long diff = Math.abs(duration.toMinutes());
			
			final String doubleAsString = String.valueOf(this.workload);
			final int indexOfDecimal = doubleAsString.indexOf(".");
						
			final long wld = 60 * Long.valueOf(doubleAsString.substring(0, indexOfDecimal)) + Long.valueOf(doubleAsString.substring(indexOfDecimal+1));
			
			
			if(wld <= diff) { return true;} else { return false;}
		}
}
