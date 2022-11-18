package controller;

import model.Pessoa;
import repository.PessoaRepository;
import repository.implement.PessoaImplementJDBC;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ViewScoped
@Named
public class PessoaController implements Serializable {

    private Pessoa Pessoa =  new Pessoa();
    private Pessoa PessoaSelecionado;
    private String busca = "";
    private List<Pessoa> PessoaEncontrado;
    private PessoaRepository repository = new PessoaImplementJDBC();

    public String salvar() {
        System.out.println(Pessoa);
        if (Pessoa.equals(new Pessoa())){
            this.Pessoa = repository.salvar(Pessoa);
        }else {
            this.Pessoa = repository.atualizar(Pessoa);
        }
        System.out.println(Pessoa);
        return "";
    }
    public List<Pessoa> listar(){
        return repository.listarPessoa();
    }

    public String editar(Pessoa Pessoa){
        System.out.println(Pessoa);
        this.Pessoa = Pessoa;
        return "";
    }

    public String excluir(Pessoa Pessoa){
        Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE,null,PessoaSelecionado);
        repository.excluir(Pessoa.getId());
        return "";
    }

    public void limpar() {
        this.Pessoa = new Pessoa();
    }
    @PostConstruct
    public void init(){
        this.PessoaEncontrado = repository.listarPessoa();
    }

    public Pessoa getPessoa() {
        return Pessoa;
    }

    public void setPessoa(Pessoa Pessoa) {
        this.Pessoa = Pessoa;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

    public List<Pessoa> getPessoaEncontrado() {
        return PessoaEncontrado;
    }

    public void setPessoaEncontrado(List<Pessoa> PessoaEncontrado) {
        this.PessoaEncontrado = PessoaEncontrado;
    }

    public Pessoa getPessoaSelecionado() {
        return PessoaSelecionado;
    }

    public void setPessoaSelecionado(Pessoa PessoaSelecionado) {
        this.PessoaSelecionado = PessoaSelecionado;
    }
}
