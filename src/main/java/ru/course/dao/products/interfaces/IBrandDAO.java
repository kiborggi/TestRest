package ru.course.dao.products.interfaces;




import ru.course.model.Brands;

import java.sql.SQLException;
import java.util.List;

public interface IBrandDAO {

    int insert(Brands brand) throws SQLException;
    int delete(int id) throws SQLException;
    int update(Brands T, int id) throws SQLException;
    Brands getByPK(int id) throws SQLException;
    List<Brands> getAll() throws SQLException;
}
