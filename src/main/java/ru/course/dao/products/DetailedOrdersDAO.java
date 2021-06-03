package ru.course.dao.products;

import ru.course.dao.products.ConnectionPool;
import ru.course.dao.products.DAO_Factory;
import ru.course.dao.products.interfaces.I_DetailedOrdersDAO;
import ru.course.dao.products.interfaces.I_ItemDAO;
import ru.course.model.DetailedOrders;
import ru.course.model.ItemList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;

public class DetailedOrdersDAO implements I_DetailedOrdersDAO {


    private final I_ItemDAO i_ItemDAO;

    public DetailedOrdersDAO() {

        i_ItemDAO =
                DAO_Factory.getItemListDAO();


    }


    public int insert(DetailedOrders order) throws SQLException {

        String query = "Insert into detailedorders(Count1, ModelId, Summ,OrderId) VALUES(?,?,?,?)";

        try(Connection conn= ConnectionPool.getConnection()) {

            try(PreparedStatement statement=conn.prepareStatement(query)){

                statement.setInt(1,order.getCounts());
                statement.setInt(2,order.Model().id());
                statement.setInt(3,order.Price());
                statement.setInt(4,order.getOrderId());

                return statement.executeUpdate();
            }


        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();

            return 0;
        }

    }


    public int delete(int id) throws SQLException {

        String query = "DELETE from detailedorders where Id=?";

        try(Connection conn= ConnectionPool.getConnection()) {

            PreparedStatement statement=conn.prepareStatement(query);
            statement.setInt(1,id);

            return statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Connection failed");
            return 0;
        }
    }


    public int update(DetailedOrders order, int id) throws SQLException {

        String query = "Update detailedorders set Count1=?, ModelId=?,Summ=? where Id=?";


        try(Connection conn= ConnectionPool.getConnection()) {

            PreparedStatement statement=conn.prepareStatement(query);
            statement.setInt(1,order.getCounts());
            statement.setInt(2,order.Model().id());
            statement.setInt(3,order.Price());
            statement.setInt(4,id);

            return statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList<DetailedOrders> getByOrderId(int orderId ) throws SQLException {
        ArrayList<DetailedOrders> detailedOrdersArrayList = new ArrayList<DetailedOrders>();
        I_DetailedOrdersDAO detailedOrdersDAO = DAO_Factory.getDetailedOrdersDAO();
        String query = "SELECT * from detailedorders where OrderId = " + orderId;
        try(Connection conn= ConnectionPool.getConnection()) {

            Statement statement=conn.createStatement();
            ResultSet res=statement.executeQuery(query);
            ItemList item;
            while(res.next()){

                detailedOrdersArrayList.add(detailedOrdersDAO.getByPK(res.getInt("id")));

            }

        }
        return detailedOrdersArrayList;
    }


    public DetailedOrders getByPK(int id1) throws SQLException {

        String query = "SELECT * from detailedorders where  Id="+id1+"";

        int id=0;
        int price=0;
        int orderNumber=0;
        int orderCount=0;
        int modelId=0;



        try(Connection conn= ConnectionPool.getConnection()) {

            Statement statement=conn.createStatement();
            ResultSet res=statement.executeQuery(query);

            while(res.next()){

                id=res.getInt(1);
                orderCount=res.getInt(2);
                modelId=res.getInt(3);
                price=res.getInt(4);
                orderNumber=res.getInt(5);


            }
            return new DetailedOrders(id,i_ItemDAO.getByPK(modelId),orderCount,price,orderNumber);

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return null;
        }
    }



    public ArrayList<DetailedOrders> getAll() throws SQLException {

        ArrayList<DetailedOrders> listOrders=new ArrayList<>();

        String query = "SELECT * from detailedorders";


        try(Connection conn= ConnectionPool.getConnection()) {

            Statement statement=conn.createStatement();
            ResultSet res=statement.executeQuery(query);
            while(res.next()){
                listOrders.add(
                        new DetailedOrders(res.getInt("Id"),
                                i_ItemDAO.getByPK(res.getInt("ModelId")),
                                res.getInt("Count1"), res.getInt("Summ"),
                                res.getInt("OrderId"))
                );

            }

            return listOrders;


        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return null;
        }
    }


}
