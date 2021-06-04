package ru.course.dao.products.interfaces;

import ru.course.model.Item;

import java.sql.SQLException;
import java.util.List;

public interface I_ItemDAO {


    int insert(Item T) throws SQLException;
    int delete(int id) throws SQLException;
    int update(Item T, int id) throws SQLException;
    Item getByPK(int id) throws SQLException;
    List<Item> getAll() throws SQLException;


}