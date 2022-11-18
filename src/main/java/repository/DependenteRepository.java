package repository;

import model.Dependente;

import java.util.List;

public interface DependenteRepository {

    Dependente salvar(Dependente Dependente);
    List<Dependente> listarDependente();
    Dependente buscarDependente(int id);
    Dependente atualizarDependente(Dependente Dependente);
    void excluirDependente(int codigo);

    List<Dependente> porLocalDeOrigem(String localDeOrigem);

}
