package org.sid.service;

import java.util.Date;

import org.sid.dao.CompteRepository;
import org.sid.dao.EmployeRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.Employe;
import org.sid.entities.Groupe;
import org.sid.entities.Operation;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BanqueServiceImpl implements IBanqueService {
	@Autowired
	private CompteRepository compteRepo;
	@Autowired
	private OperationRepository operationRepo;

	
	

	@Override
	public Compte consulterCompte(Long codeCpte) {
		Compte cp=compteRepo.findById(codeCpte).orElse(null);
		if(cp==null) throw new RuntimeException("Impossible de trouver le compte");
		return cp;
	}

	@Override
	public void verser(Long codeCpte, double montant) {
		System.out.println("aa");

		Compte cp=consulterCompte(codeCpte);
		System.out.println(cp.getSolde());
		System.out.println(montant);
		Employe emp = new Employe();
		Versement v = new Versement(new Date(), montant, cp,emp);
		System.out.println(v.getMontant());
		operationRepo.save(v);
		cp.setSolde(cp.getSolde()+montant);
		compteRepo.save(cp);
	}

	@Override
	public void retirer(Long codeCpte, double montant) {
		
		Compte cp=consulterCompte(codeCpte);
		double facilite=0;
		if(cp instanceof CompteCourant)
			facilite=((CompteCourant) cp).getDecouvert();
		if(cp.getSolde()+facilite<montant)
			throw new RuntimeException("Solde insuffisant");
		Employe emp = new Employe();
		Retrait r= new Retrait(new Date(),montant,cp,emp);
		operationRepo.save(r);
		cp.setSolde(cp.getSolde()-montant);
		compteRepo.save(cp);
		 
	}

	@Override
	public void virement(Long codeCpte1, Long codeCpte2, double montant) {
		retirer(codeCpte1, montant);
		verser(codeCpte2, montant);
	}

	@Override
	public Page<Operation> listOperation(Long codeCpte, int page, int size) {
		System.out.println("service av");

		Page<Operation> pagee =operationRepo.listOperation(codeCpte,PageRequest.of(page, size));
		System.out.println("service ap");
		return pagee ;
	}

}
