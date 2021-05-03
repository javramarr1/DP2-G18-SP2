package acme.features.spam;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.spam.Spam;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/spam")
public class AdministratorSpamController extends AbstractController<Administrator, Spam>{
		@Autowired
		protected AdministratorSpamCreateService createService;
	
		@Autowired
		protected AdministratorSpamListService	listService;
		
		@Autowired
		protected AdministratorSpamShowService	showService;
		
		@Autowired
		protected AdministratorSpamUpdateService	updateService;
		
		@Autowired
		protected AdministratorSpamDeleteService	deleteService;
	
		@PostConstruct
		protected void initialise() {
			super.addBasicCommand(BasicCommand.LIST, this.listService);
			super.addBasicCommand(BasicCommand.CREATE, this.createService);
			super.addBasicCommand(BasicCommand.SHOW, this.showService);
			super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
			super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		}
}
