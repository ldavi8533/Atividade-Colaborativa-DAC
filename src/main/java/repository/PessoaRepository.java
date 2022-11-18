package repository;

import model.Pessoa;

import java.util.List;

public interface PessoaRepository {

    Pessoa salvar(Pessoa Pessoa);
    List<Pessoa> listarPessoa();

    Pessoa buscarPessoa(int id);

    Pessoa atualizar(Pessoa Pessoa);

    void excluir(int id);
}
