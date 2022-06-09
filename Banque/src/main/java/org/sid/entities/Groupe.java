package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Groupe implements Serializable{
	
	@Id
	@GeneratedValue
	private Long numGroupe;
	private String nomGroupe;
	@OneToMany(mappedBy="groupe",fetch=FetchType.LAZY)
	private Collection<Employe> employes;
	public Groupe() {
		super();
	}
	public Groupe(Long numGroupe, String nomGroupe, Collection<Employe> employes) {
		super();
		this.numGroupe = numGroupe;
		this.nomGroupe = nomGroupe;
		this.employes = employes;
	}
	
	
	
}
