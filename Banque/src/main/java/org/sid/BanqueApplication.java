package org.sid;

import java.util.Date;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.CompteEpargne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BanqueApplication  implements CommandLineRunner {
	
	 @Autowired
		private ClientRepository clientRepository;
		
	    @Autowired
	   	private CompteRepository compteRepository;
	    

	    
	    
	public static void main(String[] args) {
		SpringApplication.run(BanqueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Client client1 = clientRepository.save(new Client("client1Courant","client1@gmail.com"));  
		Client client2 = clientRepository.save(new Client("client2Epargne","client2@gmail.com"));
		 
		//Compte compte1 = compteRepository.save(new CompteCourant(25L,new Date(),90000.0 ,client1,6000.0));
		//Compte compte2 = compteRepository.save(new CompteEpargne(50L,new Date(),6000.0, client2, 5.5));
		
	}

}
