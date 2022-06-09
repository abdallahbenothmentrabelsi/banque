package org.sid.web;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.service.IBanqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BanqueController {
	@Autowired
	private IBanqueService banqueService;
	
	
	@RequestMapping("/operations")
	public String index(){
		return "comptes";
	}
	
	@RequestMapping("/consultercompte")
	public String consulter(Model model, Long codeCompte,@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="5")int size){ 
		model.addAttribute("codeCompte", codeCompte);
		try {
			Compte cp=banqueService.consulterCompte(codeCompte);
			System.out.println(cp);
			Page<Operation> pageOperations=banqueService.listOperation(codeCompte,page,4);
			System.out.println(cp);
			int[] pages= new int[pageOperations.getTotalPages()];
			model.addAttribute("pages",pages);
			model.addAttribute("listOperations", pageOperations.getContent());
		model.addAttribute("compte",cp);
		} catch (Exception e) {
			model.addAttribute("exception",e);
			
		}
		
		return "comptes";
	}
	
	@RequestMapping(value="/saveoperation",method=RequestMethod.POST)
	public String saveOperation(Model model, String typeOperation,Long codeCompte,double montant,Long codecompte2){
		try {
			
		
		if(typeOperation.equals("VERS")){
			banqueService.verser(codeCompte, montant);
		}
		else if(typeOperation.equals("RET")){
			banqueService.retirer(codeCompte, montant);
		}else if(typeOperation.equals("VIR")){
			banqueService.virement(codeCompte, codecompte2, montant);
		}
		} catch (Exception e) {
			model.addAttribute("error",e);
			return "redirect:/consultercompte?codeCompte="+codeCompte+"&error="+e.getMessage();

		}
		return "redirect:/consultercompte?codeCompte="+codeCompte;
	}
}
