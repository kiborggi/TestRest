package ru.course.dao.products;


import ru.course.dao.products.interfaces.IBrandDAO;
import ru.course.model.*;

import java.sql.*;
import java.util.ArrayList;


public class ItemBrandDAO implements IBrandDAO {


    public int insert(Brands brand) throws SQLException {


        String query = "Insert into brands(Brand) VALUES(?)";


        try(Connection conn= ConnectionPool.getConnection()) {

            try(PreparedStatement statement=conn.prepareStatement(query)){

                statement.setString(1,brand.BrandName());
                return statement.executeUpdate();
            }


        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();

            return 0;
        }

    }


    public int delete(int id) throws SQLException {

        String query = "DELETE from brands where Id=?";

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


    public int update(Brands brand, int id) throws SQLException {

        String query = "Update brands set Brand =? where Id=?";

        try(Connection conn= ConnectionPool.getConnection()) {

            PreparedStatement statement=conn.prepareStatement(query);
            statement.setString(1,brand.BrandName());
            statement.setInt(2,id);
            return statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return 0;

        }

    }


    public Brands getByPK(int id1) throws SQLException {

        String query = "SELECT * from brands where  Id="+id1+"";
        int id = 0;
        String brand ="";

        try(Connection conn= ConnectionPool.getConnection()) {

            Statement statement=conn.createStatement();
            ResultSet res=statement.executeQuery(query);

            while(res.next()){

                id=res.getInt(1);
                brand=res.getString(2);


            }

            return new Brands(id,brand);

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return null;
        }

    }


    public ArrayList<Brands> getAll() throws SQLException {

        ArrayList<Brands> listBrands=new ArrayList<>();

        String query = "SELECT * from brands";

        try(Connection conn= ConnectionPool.getConnection()) {

            Statement statement=conn.createStatement();
            ResultSet res=statement.executeQuery(query);
            while(res.next()){
                listBrands.add(
                        new Brands(res.getInt("Id"),res.getString("Brand"))
                );

            }

            return listBrands;

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return null;
        }

    }
}