package br.com.agenda.dao;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    public void save(Contato contato) {
        String sql = "INSERT INTO contatos (nome, idade, dataCadastro) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Criar uma conexao com o banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();
            //Criar uma PreparedStatment para executar uma query
            pstm = conn.prepareStatement(sql);
            //Adicionar os valores que sao esperados pela query
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

            //Executar a query
            pstm.execute();

            System.out.println("Contato salvo com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Fechar as conexoes
            try {
                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Contato contato) {
        String sql = "UPDATE contatos\n" +
                    "SET nome = ?, idade = ?, datacadastro = ? \n" +
                    "WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Criar conexao com o banco
            conn = ConnectionFactory.createConnectionToMySQL();

            //Criar a classe para executar a query
            pstm = conn.prepareStatement(sql);

            //Adicionar os valores para atualizar
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

            //Recuperado id do registro que vai ser atualizado
            pstm.setInt(4, contato.getId());

            //Executar a Querry
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstm != null) {
                    pstm.close();
                }

                if(conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteByID(int id) {
        String sql = "DELETE FROM contatos \n" +
                    "WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, id);

            pstm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if(conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Contato> getContatos() {

        {
            String sql = "SELECT * FROM contatos";

            List<Contato> contatos = new ArrayList<Contato>();

            Connection conn = null;
            PreparedStatement pstm = null;
            //Classe que vai recuperar os dados do banco. ***SELECT***
            ResultSet rset = null;

            try {
                conn = ConnectionFactory.createConnectionToMySQL();

                pstm = conn.prepareStatement(sql);

                rset = pstm.executeQuery();

                while (rset.next()) {
                    Contato contato = new Contato();

                    //Recuperar o id
                    contato.setId(rset.getInt("id"));
                    //Recuperar o nome
                    contato.setNome(rset.getString("nome"));
                    //Recuperar a idade
                    contato.setIdade(rset.getInt("idade"));
                    //Recuperar a data de cadastro
                    contato.setDataCadastro(rset.getDate("datacadastro"));

                    contatos.add(contato);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rset != null) {
                        rset.close();
                    }
                    if (pstm != null) {
                        pstm.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return contatos;
        }
    }
}