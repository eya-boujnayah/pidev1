package tn.esprit.Interfaces;
import tn.esprit.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface Iservices <T> {
    default void add(T t) {}

    void update (T t) ;

    void delete (int id );

    List<T> display ();
    List<T> getAll();
    T get (int id);
}

