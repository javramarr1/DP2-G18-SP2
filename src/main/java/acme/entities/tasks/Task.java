package acme.entities.tasks;

import java.time.Duration;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@ScriptAssert(lang = "javascript", script = "_this.start_date <= _this.end_date;", message = "The end of the task must be later than the start")
//@ScriptAssert(lang = "javascript", script = "_this.itFits() == true;", message = "The workload time must fit between the period of the task")
public class Task extends DomainEntity{
	
	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

		@NotBlank
		@Size(min= 1, max = 80)
		protected String title;
		
		@NotBlank
		@Size(min = 1, max = 500)
		protected String description;
		
		@Temporal(TemporalType.TIMESTAMP)
		@Future
		@NotNull
		protected Date start_date;
		
		@Temporal(TemporalType.TIMESTAMP)
		@Future
		@NotNull
		protected Date end_date;
		
		@NotNull
		protected Boolean is_private;
		
		@URL
		protected String op_link;
		
		@NotBlank
		@Pattern(regexp = "^[0-9]{2}[\\s][0-9]{2}[/][60]*$")
		protected String workload;
		
		public Boolean itFits() {
			
			final Duration duration = Duration.between(this.start_date.toInstant(), this.end_date.toInstant());
			final long diff = Math.abs(duration.toMinutes());
			
			final long wld = 60 * Long.valueOf(this.workload.substring(0,2)) + Long.valueOf(this.workload.substring(3, 5));
			
			if(wld <= diff) { return true;} else { return false;}
		}
}
