package ru.course.dao.products.interfaces;

import ru.course.model.DetailedOrders;

import java.sql.SQLException;
import java.util.List;

public interface I_DetailedOrdersDAO {
    int insert(DetailedOrders T) throws SQLException;
    int delete(int id) throws SQLException;
    int update(DetailedOrders T, int id) throws SQLException;
    DetailedOrders getByPK(int id) throws SQLException;
    List<DetailedOrders> getAll() throws SQLException;
    List<DetailedOrders> getByOrderId(int orderId) throws SQLException;
}
