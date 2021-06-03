package ru.course.dao.products.interfaces;

import ru.course.model.Group;

import java.sql.SQLException;
import java.util.List;

public interface IGroupDAO {

    int insert(Group group) throws SQLException;
    int  delete(int id) throws SQLException;
    int update(Group group, int id) throws SQLException;
    Group getByPK(int id) throws SQLException;
    List<Group> getAll() throws SQLException;

}