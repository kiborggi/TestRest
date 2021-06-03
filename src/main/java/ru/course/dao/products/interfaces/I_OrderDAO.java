package ru.course.dao.products.interfaces;



import ru.course.model.Orders;

import java.sql.SQLException;
import java.util.List;

public interface I_OrderDAO {

    int insert(Orders T) throws SQLException;
    int delete(int id) throws SQLException;
    int update(Orders T, int id) throws SQLException;
    Orders getByPK(int id) throws SQLException;
    List<Orders> getAll() throws SQLException;
    List<Orders> getByUserId(int UserId) throws SQLException;

}