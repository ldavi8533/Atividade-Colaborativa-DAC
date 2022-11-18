package model;

import java.util.List;
import java.util.Objects;

public class Dependente {
    public static final String TABLE_NAME = "Dependente";
    private int codigo;
    private String localDeOrigem;
    private String nomeFantasia;

    public Dependente() {
    }

    public Dependente(String localDeOrigem, String nomeFantasia) {
        this.localDeOrigem = localDeOrigem;
        this.nomeFantasia = nomeFantasia;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLocalDeOrigem() {
        return localDeOrigem;
    }

    public void setLocalDeOrigem(String localDeOrigem) {
        this.localDeOrigem = localDeOrigem;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dependente Dependente = (Dependente) o;
        return codigo == Dependente.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Dependente{" +
                "codigo=" + codigo +
                ", localDeOrigem='" + localDeOrigem + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                '}';
    }
}
