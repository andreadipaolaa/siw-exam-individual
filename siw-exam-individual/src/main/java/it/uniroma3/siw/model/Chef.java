package it.uniroma3.siw.model;

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

    public String getNazionalita() {
        return nazionalita;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}