package br.com.agenda.aplicacao;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Contato contato = new Contato();
        ContatoDAO contatoDAO = new ContatoDAO();
        Scanner scanner = new Scanner(System.in);

        System.out.println(
                "Opções de entrada: \n" +
                        "1 - Adicionar contato \n" +
                        "2 - Listar contatos \n" +
                        "3 - Atualizar contato \n" +
                        "4 - Remover contato \n" +
                        "5 - Finalizar programa"
        );
        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1: //Adicionar contato.
                System.out.println("Nome do contato: ");
                scanner.nextLine();
                String nome = scanner.nextLine();

                System.out.println("Idade do contato: ");
                int idade = scanner.nextInt();

                scanner.nextLine();

                contato.setNome(nome);
                contato.setIdade(idade);
                contato.setDataCadastro(new Date());
                contatoDAO.save(contato);
                break;
            case 2: //Listar contatos.
                for (Contato c : contatoDAO.getContatos()) {
                    System.out.println(c.exibir());
                }
                break;
            case 3: //Atualizar contato pelo ID.
                Contato c1 = new Contato();

                System.out.println("Qual o id do contato que voce deseja alterar?");
                int id = scanner.nextInt();

                System.out.println("Novo nome do contato: ");
                scanner.nextLine();
                String novoNome = scanner.nextLine();

                System.out.println("Nova idade do contato: ");
                int novaIdade = scanner.nextInt();

                scanner.nextLine();

                c1.setNome(novoNome);
                c1.setIdade(novaIdade);
                c1.setDataCadastro(new Date());
                c1.setId(id);

                contatoDAO.update(c1);
                break;
            case 4: //Remover contato pelo ID.
                System.out.println("Qual o ID do contato que deseja remover?");
                int removerId = scanner.nextInt();

                contatoDAO.deleteByID(removerId);
                break;
            case 5: //Finalizar
                break;
        }
    }
}