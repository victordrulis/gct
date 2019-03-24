package br.com.drulis.gct.dao;

import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.dominio.Usuario;

public class Teste {

    public static void main(String[] args) {
        Contato contato = new Contato();
        Usuario usuario = new Usuario();
        ContatoDao dao = new ContatoDao();
        
        // Dados de teste
        String nome = "Teste DAO";
        String cpfCnpj = "385.620.053-39";
        String telefone = "11 4674-0953";
        String email = "email@email.com";
        int ativo = 1;
        usuario.setId(1);
        contato.setNome(nome);
        contato.setCpfCnpj(cpfCnpj);
        contato.setTel(telefone);
        contato.setEmail(email);
        contato.setAtivo(ativo);
        contato.setUsuarioInclusao(usuario);
        
        System.out.println("Id do Contato inserido: " + dao.inserir(contato).getId());
        
        System.out.println("Contato consultado. Resultados = " + dao.consultar(contato).size());
    }

}
