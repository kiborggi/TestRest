package ru.course.dao.products;



import ru.course.dao.products.interfaces.I_ItemDAO;
import ru.course.dao.products.interfaces.I_OrderDAO;
import ru.course.model.Orders;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO implements I_OrderDAO {


    @Override
    public int insert(Orders order) throws SQLException {

        String query = "Insert into orders(UserId,OrderCode,Status) VALUES(?,?,?)";

        try(Connection conn= ConnectionPool.getConnection()) {

            try(PreparedStatement statement=conn.prepareStatement(query)){

//                statement.setInt(1,order.getUserId());
                statement.setInt(1,order.getUserId());
                statement.setInt(2,order.getOrderCode());
                statement.setString(3,order.getStatus());

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


        try(Connection conn= ConnectionPool.getConnection()) {

            Statement statement=conn.createStatement();
            ResultSet res=statement.executeQuery(query);

            while(res.next()){

                id=res.getInt(1);
                UserId=res.getInt(2);
                OrderCode=res.getInt(3);
                status=res.getString(4);

            }

            return new Orders(id,OrderCode,UserId,status);

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
                Orders order;
                order = new Orders();
                order.setId(res.getInt("Id"));
                order.setUserId(res.getInt("UserId"));
                order.setStatus(res.getString("Status"));
                order.setOrderCode(res.getInt("OrderCode"));
                listOrders.add(order);

            }

            return listOrders;

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public List<Orders> getByUserId(int UserId1) throws SQLException {

        ArrayList<Orders> listOrders=new ArrayList<>();

        String query = "SELECT * from orders where UserId="+UserId1+"";

        try(Connection conn= ConnectionPool.getConnection()) {

            Statement statement=conn.createStatement();
            ResultSet res=statement.executeQuery(query);
            while(res.next()){
                Orders order;
                order = new Orders();
                order.setId(res.getInt("Id"));
                order.setUserId(res.getInt("UserId"));
                order.setStatus(res.getString("Status"));
                order.setOrderCode(res.getInt("OrderCode"));
                listOrders.add(order);

            }

            return listOrders;

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return null;
        }

    }
}