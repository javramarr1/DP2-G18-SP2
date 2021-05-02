package acme.features.authenticated.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/task")
public class AuthenticatedTasksController extends AbstractController<Authenticated, Task>{

		@Autowired
		protected AuthenticatedTaskShowService	showService;
		
		@Autowired
		protected AuthenticatedTaskListService	listService;
		
		@PostConstruct
		protected void initialise() {
			super.addBasicCommand(BasicCommand.SHOW, this.showService);
			super.addBasicCommand(BasicCommand.LIST, this.listService);
		}
}
