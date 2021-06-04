package ru.course.dao.products;


import ru.course.dao.products.interfaces.IGroupDAO;
import ru.course.model.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemGroupDAO implements IGroupDAO {



    public int insert(Group group) throws SQLException {

        String query = "Insert into groupse(Group1) VALUES(?)";

        try(Connection conn= ConnectionPool.getConnection()) {

            try(PreparedStatement statement=conn.prepareStatement(query)){

                statement.setString(1,group.SectionName());

                return statement.executeUpdate();
            }


        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return 0;
        }

    }


    public int delete(int id) throws SQLException {

        String query = "DELETE from groupse where Id=?";



        try(Connection conn= ConnectionPool.getConnection()) {

            PreparedStatement statement=conn.prepareStatement(query);
            statement.setInt(1,id);

            return statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return 0;
        }

    }


    public int update(Group group, int id) throws SQLException {

        String query = "Update groupse set Group1 =? where Id=?";

        try(Connection conn= ConnectionPool.getConnection()) {

            PreparedStatement statement=conn.prepareStatement(query);
            statement.setString(1,group.SectionName());
            statement.setInt(2,id);

            return statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return 0;

        }
    }


    public Group getByPK(int id1) throws SQLException {

        String query = "SELECT * from groups where  Id="+id1+"";
        int id = 0;
        String SectionName ="";


        try(Connection conn= ConnectionPool.getConnection()) {

            Statement statement=conn.createStatement();
            ResultSet res=statement.executeQuery(query);

            while(res.next()){

                id=res.getInt(1);
                SectionName=res.getString(2);


            }

            return new Group(id,SectionName);

        } catch (SQLException e) {
            System.out.println("Connection failed in Group.getByPK");
            return null;

        }
    }


    public List<Group> getAll() throws SQLException {

        List<Group> listGroup=new ArrayList<>();

        String query = "SELECT * from groupse";



        try(Connection conn= ConnectionPool.getConnection()) {

            Statement statement=conn.createStatement();
            System.out.println("Connection good");
            ResultSet res=statement.executeQuery(query);
            while(res.next()){
                listGroup.add(
                        new Group(res.getInt("Id"),res.getString("Group1"))
                );

            }

            return listGroup;
        } catch (SQLException e) {
            System.out.println("Connection failed");
            return null;
        }
    }



}