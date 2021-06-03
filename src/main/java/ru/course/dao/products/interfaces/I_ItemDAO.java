package ru.course.dao.products.interfaces;

import ru.course.model.ItemList;

import java.sql.SQLException;
import java.util.List;

public interface I_ItemDAO {


    int insert(ItemList T) throws SQLException;
    int delete(int id) throws SQLException;
    int update(ItemList T, int id) throws SQLException;
    ItemList getByPK(int id) throws SQLException;
    List<ItemList> getAll() throws SQLException;
}