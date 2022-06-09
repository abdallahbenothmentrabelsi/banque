package org.sid.service;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.springframework.data.domain.Page;

public interface IBanqueService {
	public Compte consulterCompte(Long codeCpte);
	public void verser(Long codeCpte,double montant);
	public void retirer(Long codeCpte,double montant);
	public void virement(Long codeCpte1,Long codeCpte2,double montant);
	public Page<Operation> listOperation(Long codeCpte,int page , int size);

}
