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

    public Conta(String numeroConta){
        this.numeroConta = numeroConta;
    }

    public Conta(String usuario, String senha, String numeroConta){
        this.usuario = usuario;
        this.senha = senha;
        this.numeroConta = numeroConta;
    }

}
