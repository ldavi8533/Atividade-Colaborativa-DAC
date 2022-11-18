package repository.implement;

import model.Dependente;
import repository.DependenteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DependenteImplementEmMemoria implements DependenteRepository {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    private List<Dependente> DependenteSalva = new ArrayList<>() ;

    public Dependente salvar(Dependente Dependente){
        Dependente DependenteSalvar = new Dependente(Dependente.getLocalDeOrigem(),Dependente.getNomeFantasia());
        DependenteSalvar.setCodigo(ID_GENERATOR.getAndIncrement());
        DependenteSalva.add(DependenteSalvar);
        return DependenteSalvar;
    }
    public List<Dependente> listarDependente(){
        return DependenteSalva;
    }

    @Override
    public Dependente buscarDependente(int id) {
        return null;
    }

    @Override
    public Dependente atualizarDependente(Dependente Dependente) {
        return null;
    }

    @Override
    public void excluirDependente(int codigo) {
    }

    @Override
    public List<Dependente> porLocalDeOrigem(String localDeOrigem) {
        return null;
    }

}

