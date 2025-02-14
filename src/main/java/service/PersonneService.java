package service;

import model.Personne;
import utils.MyDatabse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonneService implements IService<Personne>{

    Connection con ;

    public PersonneService(){
        con = MyDatabse.getInstance().getCon();

    }
    @Override
    public void add(Personne personne) {
        String sql = "INSERT INTO `personne`( `prenom`, `nom`, `age`) VALUES ('"+personne.getPrenom()+"','"+personne.getNom()+"',"+personne.getAge()+")";

        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void update(Personne personne) {
        String sql = "UPDATE personne SET prenom = ? ,nom= ? ,age= ?  WHERE id  = ?" ;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,personne.getPrenom());
            preparedStatement.setString(2,personne.getNom());
            preparedStatement.setInt(3,personne.getAge());
            preparedStatement.setInt(4,personne.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Personne> display() {
        String query = "SELECT * FROM `personne`";

        List<Personne> personnes = new ArrayList<>();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Personne p = new Personne();

                p.setId(rs.getInt("id"));
                p.setPrenom(rs.getString("prenom"));
                p.setNom(rs.getString("nom"));
                p.setAge(rs.getInt("age"));

                personnes.add(p);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return personnes;
    }
}
