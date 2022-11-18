package repository.implement;

import model.Dependente;
import repository.DependenteRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DependenteImplementJDBC implements DependenteRepository {

    @Resource(lookup = "java:app/jdbc/pgadmin")
    private DataSource dataSource;

    private Connection connection;

    public DependenteImplementJDBC() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://host-banco:5432/jsf-db",
                    "postgres", "postgres"
            );
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DependenteImplementJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Dependente salvar(Dependente Dependente) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO " + Dependente.TABLE_NAME + " (localDeOrigem, nomeFantasia) VALUES ( ?, ? ) RETURNING codigo;"
            );
            statement.setString(1, Dependente.getLocalDeOrigem());
            statement.setString(2, Dependente.getNomeFantasia());

            statement.executeQuery();

            ResultSet result = statement.getGeneratedKeys();
            if(result.next()){
                int codigo = result.getInt(1);
                return buscarDependente(codigo);
            }
            return new Dependente();

        } catch (SQLException ex) {
            Logger.getLogger(DependenteImplementJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Dependente();
    }

    @Override
    public List<Dependente> listarDependente() {
        try {
            List<Dependente> lista = new ArrayList<>();
            ResultSet result = connection.prepareStatement(
                    "SELECT * FROM " + Dependente.TABLE_NAME
            ).executeQuery();
            while (result.next()) {
                Dependente Dependente = new Dependente();
                Dependente.setCodigo(result.getInt("codigo"));
                Dependente.setLocalDeOrigem(result.getString("localDeOrigem"));
                Dependente.setNomeFantasia(result.getString("nomeFantasia"));
                lista.add(Dependente);
            }
            return lista;
        } catch (SQLException ex) {
            return Collections.EMPTY_LIST;
        }
    }

    public Dependente buscarDependente(int id) {
        try {
            Dependente Dependente = new Dependente();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM " + Dependente.TABLE_NAME + " WHERE codigo = ?");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Dependente.setCodigo(result.getInt("codigo"));
                Dependente.setLocalDeOrigem(result.getString("localDeOrigem"));
                Dependente.setNomeFantasia(result.getString("nomeFantasia"));
            }
            return Dependente;
        }
        catch(SQLException ex){
            return new Dependente();
        }
    }

    @Override
    public Dependente atualizarDependente(Dependente Dependente) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "update "+ Dependente.TABLE_NAME+" set localdeorigem = ?, nomefantasia = ? where codigo = ?;"
            );
            statement.setString(1,Dependente.getLocalDeOrigem());
            statement.setString(2,Dependente.getNomeFantasia());
            statement.setInt(3,Dependente.getCodigo());

            statement.executeUpdate();

            return Dependente;

        } catch (SQLException ex) {
            Logger.getLogger(DependenteImplementJDBC.class.getName()).log(Level.SEVERE,null,ex);
            return new Dependente();
        }
    }

    @Override
    public void excluirDependente(int codigo) {
        try {
            PreparedStatement statement =connection.prepareStatement(
                    "delete from " + Dependente.TABLE_NAME + " where codigo = ?;"
            );
            statement.setInt(1, codigo);
            statement.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(DependenteImplementJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public List<Dependente> porLocalDeOrigem(String localDeOrigem) {
        try {
            List<Dependente> lista = new ArrayList<>();
            PreparedStatement prepareStatement = this.dataSource.getConnection().prepareStatement(
                    "SELECT * FROM Dependente WHERE localDeOrigem like ?"
            );
            prepareStatement.setString(1, localDeOrigem);
            ResultSet result = prepareStatement.executeQuery();
            while (result.next()) {
               Dependente Dependente = new Dependente();
                    Dependente.setCodigo(result.getInt("codigo"));
                    Dependente.setLocalDeOrigem(result.getString("localDeOrigem"));
                    Dependente.setNomeFantasia(result.getString("nomeFantasia"));
                lista.add(Dependente);
            }
            return lista;
        } catch (SQLException ex) {
            return Collections.EMPTY_LIST;
        }
    }

}
