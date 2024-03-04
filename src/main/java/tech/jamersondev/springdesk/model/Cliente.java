package tech.jamersondev.springdesk.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Pessoa {
    @OneToMany
    private List<Chamado> chamados = new ArrayList<>();

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }


}
