package ru.course.dao.products;



import ru.course.dao.products.interfaces.I_ItemDAO;
import ru.course.dao.products.interfaces.I_OrderDAO;
import ru.course.model.DeliveryType;
import ru.course.model.Orders;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO implements I_OrderDAO {


    @Override
    public int insert(Orders order) throws SQLException {

        String query = "Insert into orders(UserId,OrderCode,Status,address,deliveryId) VALUES(?,?,?,?,?)";

        try(Connection conn= ConnectionPool.getConnection()) {

            try(PreparedStatement statement=conn.prepareStatement(query)){


                statement.setInt(1,order.getUserId());
                statement.setInt(2,order.getOrderCode());
                statement.setString(3,order.getStatus());
                statement.setString(4,order.getAddress());
                statement.setInt(5,order.getDeliveryTypeID());
                return statement.executeUpdate();
            }


        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();

            return 0;
        }


    }

    @Override
    public int delete(int id) throws SQLException {

        String query = "DELETE from orders where Id=?";

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

    @Override
    public int update(Orders order, int id) throws SQLException {

        String query = "Update orders set Status =? where Id=?";

        try(Connection conn= ConnectionPool.getConnection()) {

            PreparedStatement statement=conn.prepareStatement(query);
            statement.setString(1,order.getStatus());
            statement.setInt(2,id);
            return statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return 0;

        }

    }

    @Override
    public Orders getByPK(int id1) throws SQLException {

        String query = "SELECT * from orders where  Id="+id1+"";

        int id=0;
        int UserId=0;
        int OrderCode=0;
        String status="in progress";
        int deliveryTypeId=1;
        String address=" ";


        try(Connection conn= ConnectionPool.getConnection()) {

            Statement statement=conn.createStatement();
            ResultSet res=statement.executeQuery(query);
            Orders oreder = new Orders();
            while(res.next()){

                oreder.setId(res.getInt(1));
                oreder.setUserId(res.getInt(2));
                oreder.setOrderCode(res.getInt(3));
                oreder.setStatus(res.getString(4));
                oreder.setDeliveryTypeID(res.getInt("deliveryId"));
                oreder.setAddress(res.getString("address"));

            }

            return oreder;

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return null;
        }

    }




    @Override
    public List<Orders> getAll() throws SQLException {

        ArrayList<Orders> listOrders=new ArrayList<>();

        String query = "SELECT * from orders";

        try(Connection conn= ConnectionPool.getConnection()) {

            Statement statement=conn.createStatement();
            ResultSet res=statement.executeQuery(query);
            while(res.next()){
                Orders oreder;
                oreder = new Orders();
                oreder.setId(res.getInt(1));
                oreder.setUserId(res.getInt(2));
                oreder.setOrderCode(res.getInt(3));
                oreder.setStatus(res.getString(4));
                oreder.setDeliveryTypeID(res.getInt("deliveryId"));
                oreder.setAddress(res.getString("address"));

                listOrders.add(oreder);

            }

            return listOrders;

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return null;
        }


    }
    @Override
    public List<DeliveryType> getAllDeliveryTypes()  {

        ArrayList<DeliveryType> listDeliveryType= new ArrayList<>();

        String query = "SELECT * from delivery_type";

        try(Connection conn= ConnectionPool.getConnection()) {

            Statement statement=conn.createStatement();
            ResultSet res=statement.executeQuery(query);
            while(res.next()){
                DeliveryType deliveryType;
                deliveryType = new DeliveryType();
                deliveryType.setId(res.getInt("id"));
                deliveryType.setName(res.getString("name"));

                listDeliveryType.add(deliveryType);

            }

             return listDeliveryType;


        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public DeliveryType getDeliveryType(Orders orders) {
        int deliveryTypeId = orders.getDeliveryTypeID();
        DeliveryType deliveryType = new DeliveryType();
        String query = "SELECT * from delivery_type where id="+orders.getDeliveryTypeID()+"";
        try(Connection conn= ConnectionPool.getConnection()) {
            Statement statement=conn.createStatement();
            ResultSet res=statement.executeQuery(query);
            while(res.next()){
               deliveryType.setName(res.getString("name"));
                deliveryType.setId(res.getInt("id"));
            }


        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return null;
        }
        return deliveryType;
    }

    @Override
    public List<Orders> getByUserId(int UserId1) throws SQLException {

        ArrayList<Orders> listOrders=new ArrayList<>();

        String query = "SELECT * from orders where UserId="+UserId1+"";

        try(Connection conn= ConnectionPool.getConnection()) {

            Statement statement=conn.createStatement();
            ResultSet res=statement.executeQuery(query);
            while(res.next()){
                Orders oreder;
                oreder = new Orders();
                oreder.setId(res.getInt(1));
                oreder.setUserId(res.getInt(2));
                oreder.setOrderCode(res.getInt(3));
                oreder.setStatus(res.getString(4));
                oreder.setDeliveryTypeID(res.getInt("deliveryId"));
                oreder.setAddress(res.getString("address"));
                listOrders.add(oreder);

            }

            return listOrders;

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return null;
        }

    }
}