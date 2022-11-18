package repository.implement;

import model.Dependente;
import model.Pessoa;
import repository.DependenteRepository;
import repository.PessoaRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PessoaImplementJDBC implements PessoaRepository {

    @Resource(lookup = "java:app/jdbc/pgadmin")
    private DataSource dataSource;

    private DependenteRepository DependenteRepository = new DependenteImplementJDBC();
    private Connection connection;

    public PessoaImplementJDBC() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://host-banco:5432/jsf-db",
                    "postgres","postgres"
            );
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DependenteImplementJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }

    }
    @Override
    public Pessoa salvar(Pessoa Pessoa) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO "+Pessoa.TABLE_NAME+" (titulo, dataDeLancamento, idDependente) VALUES ( ?, ?, ? ) RETURNING id;"
            );
            statement.setString(1, Pessoa.getTitulo());
            statement.setDate(2, new java.sql.Date (Pessoa.getDataDeLancamento().getTime()));
            statement.setInt(3, Pessoa.getDependente().getCodigo());

            statement.executeQuery();

            ResultSet result = statement.getGeneratedKeys();
            if(result.next()){
                int id = result.getInt(1);
                return buscarPessoa(id);
            }
            return new Pessoa();
        }catch (SQLException ex){
            Logger.getLogger(PessoaImplementJDBC.class.getName()).log(Level.SEVERE,null,ex);
            return new Pessoa();
        }
    }
    @Override
    public List<Pessoa> listarPessoa() {
        try {
            List<Pessoa> lista = new ArrayList<>();
            ResultSet result = connection.prepareStatement(
                    "SELECT * FROM " + Pessoa.TABLE_NAME
            ).executeQuery();
            while (result.next()) {
                Pessoa Pessoa = new Pessoa();
                Pessoa.setId(result.getInt("id"));
                Pessoa.setTitulo(result.getString("titulo"));
                Pessoa.setDataDeLancamento(result.getDate("dataDeLancamento"));
                Dependente Dependente = DependenteRepository.buscarDependente(result.getInt("idDependente"));
                Pessoa.setDependente(Dependente);
                lista.add(Pessoa);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PessoaImplementJDBC.class.getName()).log(Level.SEVERE,null,ex);
            return Collections.EMPTY_LIST;
        }

    }

    public Pessoa buscarPessoa(int id){
        try {
            Pessoa Pessoa = new Pessoa();
            PreparedStatement statement =connection.prepareStatement(
                    "SELECT * FROM " + Pessoa.TABLE_NAME + " where id = ?;"
            );
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Pessoa.setId(result.getInt("id"));
                Pessoa.setTitulo(result.getString("titulo"));
                Pessoa.setDataDeLancamento(result.getDate("dataDeLancamento"));
                Dependente Dependente = DependenteRepository.buscarDependente(Pessoa.getId());
                Pessoa.setDependente(Dependente);
            }
            return Pessoa;
        } catch (SQLException ex) {
            Logger.getLogger(PessoaImplementJDBC.class.getName()).log(Level.SEVERE,null,ex);
            return new Pessoa();
        }
    }

    @Override
    public Pessoa atualizar(Pessoa Pessoa) {
        try {
        PreparedStatement statement = connection.prepareStatement(
                "update "+Pessoa.TABLE_NAME+" set titulo = ?, datadelancamento = ?,idDependente = ? where id = ?;"
        );
        statement.setString(1,Pessoa.getTitulo());
        statement.setDate(2,new java.sql.Date (Pessoa.getDataDeLancamento().getTime()));
        statement.setInt(3,Pessoa.getDependente().getCodigo());
        statement.setInt(4,Pessoa.getId());
        statement.executeUpdate();

        return Pessoa;

        } catch (SQLException ex) {
            Logger.getLogger(PessoaImplementJDBC.class.getName()).log(Level.SEVERE,null,ex);
            return new Pessoa();
        }
    }

    @Override
    public void excluir(int id) {
        try {
            PreparedStatement statement =connection.prepareStatement(
                    "delete from " + Pessoa.TABLE_NAME + " where id = ?;"
            );
            statement.setInt(1, id);
            statement.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(PessoaImplementJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
