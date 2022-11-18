package controller;

import model.Dependente;
import model.Pessoa;
import repository.EditoraRepository;
import repository.implement.EditoraImplementJDBC;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class EditoraController implements Serializable {
    private Dependente Dependente = new Dependente();
    private String busca = "";
    private List<Dependente> DependentesEncontrada;
    private DependenteRepository repository = new DependenteImplementJDBC();

    public List<Dependente> listar(){
        return repository.listarDependente();
    }

    public String filtrar(){
        if(null==busca || "".equals(busca.trim())){
            this.DependentesEncontrada = repository.listarDependente();
        }else{
            this.DependentesEncontrada = this.repository.porLocalDeOrigem(busca);
        }
        return null;
    }
    public String salvarDependente() {
        System.out.println(Dependente);
        if (Dependente.equals(new Dependente())){
            this.Dependente = repository.salvar(Dependente);
        }else {
            this.Dependente = repository.atualizarDependente(Dependente);
        }
        System.out.println(Dependente);
        return "";
    }
    public String editar(Dependente Dependente){
        System.out.println(Dependente);
        this.Dependente = Dependente;
        System.out.println(this.Dependente);
        return "";
    }
    public String excluir(Dependente Dependente){
        System.out.println(Dependente);
        repository.excluirDependente(Dependente.getCodigo());
        return "";
    }

    public void limpar() {
        this.Dependente = new Dependente();
    }

    @PostConstruct
    public void init(){
        this.DependentesEncontrada = repository.listarDependente();
    }
    public Dependente getDependente() {
        return Dependente;
    }

    public void setDependente(Dependente Dependente) {
        this.Dependente = Dependente;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }
}
