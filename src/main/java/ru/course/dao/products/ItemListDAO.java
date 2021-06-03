package ru.course.dao.products;


import ru.course.dao.products.interfaces.IBrandDAO;
import ru.course.dao.products.interfaces.IGroupDAO;
import ru.course.dao.products.interfaces.I_DetailedOrdersDAO;
import ru.course.dao.products.interfaces.I_ItemDAO;
import ru.course.model.DetailedOrders;
import ru.course.model.ItemList;

import java.sql.*;
import java.util.ArrayList;

public class ItemListDAO implements I_ItemDAO {

    private final IBrandDAO iBrandDAO;
    private final IGroupDAO iGroupDAO;

    public ItemListDAO() {

        iBrandDAO =
                DAO_Factory.getItemBrandDAO();


        iGroupDAO =
                DAO_Factory.getItemGroupDAO();

    }


    public int insert(ItemList itemList) throws SQLException {

        System.out.println(itemList.getBrandId().id()+", "+itemList.getGroupId().id()+", "+itemList.getModel());

        String query = "Insert into models(BrandId, GroupId, Model,Price) VALUES(?,?,?,?)";

        try(Connection conn= ConnectionPool.getConnection()) {


            try(PreparedStatement statement=conn.prepareStatement(query)){

                statement.setInt(1,itemList.getBrandId().id());
                statement.setInt(2,itemList.getGroupId().id());
                statement.setString(3,itemList.getModel());
                statement.setInt(4,itemList.Price());



                return statement.executeUpdate();
            }


        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();

            return 0;
        }

    }


    public int delete(int id) throws SQLException {

        String query = "DELETE from models where Id=?";

        try(Connection conn= ConnectionPool.getConnection()) {

            PreparedStatement statement=conn.prepareStatement(query);
            statement.setInt(1,id);

            return statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Connection failed");
            return 0;
        }
    }


    public int update(ItemList itemList, int id) throws SQLException {

        System.out.println(itemList.getBrandId().id()+", "+itemList.getGroupId().id()+", "+itemList.getModel());

        String query = "Update models set BrandId =?, GroupId=?, Model=?,Price=? where Id=?";


        try(Connection conn= ConnectionPool.getConnection()) {

            PreparedStatement statement=conn.prepareStatement(query);
            statement.setObject(1,itemList.getBrandId().id());
            statement.setObject(2,itemList.getGroupId().id());
            statement.setString(3,itemList.getModel());
            statement.setInt(4,itemList.Price());
            statement.setInt(5,id);



            return statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return 0;
        }
    }


    public ItemList getByPK(int id1) throws SQLException {

        String query = "SELECT * from models where  Id="+id1+"";

        int id=0;
        int brandId=0;
        int sectionId=0;
        int price=0;
        String Model="";




        try(Connection conn= ConnectionPool.getConnection()) {

            Statement statement=conn.createStatement();
            ResultSet res=statement.executeQuery(query);

            while(res.next()){

                id=res.getInt(1);
                brandId=res.getInt(2);
                sectionId=res.getInt(3);
                Model=res.getString(4);
                price=res.getInt(5);


            }

            return new ItemList(id,iBrandDAO.getByPK(brandId),
                    iGroupDAO.getByPK(sectionId),Model,price,brandId,sectionId);

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return null;
        }
    }



    public ArrayList<ItemList> getAll() throws SQLException {

        ArrayList<ItemList> listItems=new ArrayList<>();

        String query = "SELECT * from models";


        try(Connection conn= ConnectionPool.getConnection()) {

            Statement statement=conn.createStatement();
            ResultSet res=statement.executeQuery(query);
            ItemList item;
            while(res.next()){
                item= new ItemList(res.getInt("Id"),iBrandDAO.getByPK(res.getInt("BrandId")),
                        iGroupDAO.getByPK(res.getInt("GroupId")),
                        res.getString("Model"),
                        res.getInt("Price"),res.getInt("BrandId"),
                        res.getInt("GroupId"));
                item.setPicture(res.getString("Picture"));
                listItems.add(item);



            }

            return listItems;


        } catch (SQLException e) {
            System.out.println("Connection failed in getAll");
            e.printStackTrace();
            return null;
        }
    }
}