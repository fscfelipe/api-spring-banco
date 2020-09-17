package br.com.felipesobreira.banco.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="conta")
@Getter @Setter
public class Conta {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "numero_conta", nullable = false)
    private String numeroConta;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "senha")
    private String senha;

    @Column(name = "saldo")
    private String saldo;

    public Conta(){}

    public Conta(String numeroConta){
        this.numeroConta = numeroConta;
    }

    public Conta(String usuario, String senha, String numeroConta, String saldo){
        this.usuario = usuario;
        this.senha = senha;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    /*
     * Mesmo utilizando o lombok, foi adicionado os getters e setters para evitar erros
     * ao importar o projeto.
     */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
}
