package repository.implement;

import model.Dependente;
import model.Pessoa;
import repository.PessoaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PessoaImplementEmMemoria implements PessoaRepository {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    private List<Pessoa> PessoaSalvo = new ArrayList<>() ;

    public Pessoa salvar(Pessoa Pessoa){
        Pessoa salvarPessoa = new Pessoa(Pessoa.getTitulo(), Pessoa.getDataDeLancamento(), Pessoa.getDependente());
        salvarPessoa.setId(ID_GENERATOR.getAndIncrement());
        PessoaSalvo.add(salvarPessoa);
        return salvarPessoa;
    }
    public List<Pessoa> listarPessoa(){
        return PessoaSalvo;
    }

    @Override
    public Pessoa buscarPessoa(int id) {
        return null;
    }

    @Override
    public Pessoa atualizar(Pessoa Pessoa) {
        return null;
    }

    @Override
    public void excluir(int id) {

    }
}

