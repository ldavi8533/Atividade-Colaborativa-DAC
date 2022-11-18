package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Livro {

    public static final  String TABLE_NAME = "livro";
    private int id;
    private String titulo;
    private Date dataDeLancamento;

    private Editora editora;

    public Livro() {
        this.editora = new Editora();
    }

    public Livro(String titulo, Date dataDeLancamento, Editora editora) {
        this.titulo = titulo;
        this.dataDeLancamento = dataDeLancamento;
        this.editora = editora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataDeLancamento() {
        return dataDeLancamento;
    }

    public void setDataDeLancamento(String dataDeLancamento){
        try {
            this.dataDeLancamento = new SimpleDateFormat("dd/MM/yyyy").parse(dataDeLancamento);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public void setDataDeLancamento(Date dataDeLancamento) {
        this.dataDeLancamento = dataDeLancamento;
    }

    public Editora getEditora() {return editora; }

    public void setEditora(Editora editora) {this.editora = editora;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return id == livro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", dataDeLancamento=" + dataDeLancamento +
                ", editora=" + editora +
                '}';
    }
}
