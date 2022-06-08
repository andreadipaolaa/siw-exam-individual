package it.uniroma3.siw.siwexamindividual.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "chef")
public class Chef {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @NotNull
    private String nome;

    @NotNull
    @NotBlank
    private String cognome;

    @NotNull
    @NotBlank
    private String nazionalita;

    @OneToMany(mappedBy = "chef")
    private List<Buffet> buffetOfferti;

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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public List<Buffet> getBuffetOfferti() {
        return buffetOfferti;
    }

    public void setBuffetOfferti(List<Buffet> buffetOfferti) {
        this.buffetOfferti = buffetOfferti;
    }
}