package it.uniroma3.siw.catering.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Buffet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String nome;

	@Column(columnDefinition = "TEXT")
	@NotBlank
	private String descrizione;
	
	@OneToMany(mappedBy = "buffet", cascade = CascadeType.REMOVE)
    private List<Piatto> dishes = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "chef_id")
	private Chef chef;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public List<Piatto> getDishes() {
		return dishes;
	}

	public void setDishes(List<Piatto> dishes) {
		this.dishes = dishes;
	}

}
