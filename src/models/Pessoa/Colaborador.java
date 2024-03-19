package models.Pessoa;

import java.util.Date;

public class Colaborador extends Pessoa {

    private int codigoColaborador;

    public Colaborador(int idPessoa,
                   String nome,
                   String cpf,
                   String email,
                   String telefone,
                   String logradouro,
                   String cidade,
                   String estado,
                   String cep,
                   Date dataCadastro,
                   Date dataNascimento,
                   String uf,
                   String senha,
                   int codigoColaborador) {
        super(idPessoa, nome, cpf, email, telefone, logradouro, cidade, estado, cep, dataCadastro, dataNascimento, uf, senha);
        this.codigoColaborador = codigoColaborador;
    }


}
