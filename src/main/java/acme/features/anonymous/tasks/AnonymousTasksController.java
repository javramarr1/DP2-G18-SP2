package acme.features.anonymous.tasks;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/task")
public class AnonymousTasksController extends AbstractController<Anonymous, Task>{
	
		@Autowired
		protected AnonymousTaskCreateService createService;
		
		@PostConstruct
		protected void initialise() {
			super.addBasicCommand(BasicCommand.CREATE, this.createService);
		}
}
