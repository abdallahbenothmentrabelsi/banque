package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Employe implements Serializable{
	@Id
	@GeneratedValue
	private Long codeEmploye;
	private String nomEmploye;
	@OneToMany(mappedBy="employe",fetch=FetchType.LAZY)
	private Collection<Compte> comptes;
	@ManyToOne
	@JoinColumn(name="Code_GRP")
	private Groupe groupe;
	public Employe() {
		super();
	}
	public Employe(Long codeEmploye, String nomEmploye, Collection<Compte> comptes, Groupe groupe) {
		super();
		this.codeEmploye = codeEmploye;
		this.nomEmploye = nomEmploye;
		this.comptes = comptes;
		this.groupe = groupe;
	}
	
	
}
