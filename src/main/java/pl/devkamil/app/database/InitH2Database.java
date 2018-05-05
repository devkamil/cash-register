package pl.devkamil.app.database;


import java.sql.*;

public class InitH2Database {

    private Connection connection;
    private Statement statement;

    public InitH2Database (){
        try{
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/test", "test", "" );
            statement = connection.createStatement();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void createDatabaseAndRecords()    {
        try
        {
//            statement.executeUpdate( "DROP TABLE product" );

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS product (product_id BIGINT PRIMARY KEY, " +
                            " name VARCHAR(255) NOT NULL, price DECIMAL NOT NULL, barcode VARCHAR(30) NOT NULL)" );

            statement.executeUpdate( " MERGE INTO product (product_id, name, price, barcode) KEY(product_id) VALUES (1, 'Produkt 1', '19.99', '101') ");
            statement.executeUpdate( " MERGE INTO product (product_id, name, price, barcode) KEY(product_id) VALUES (2, 'Produkt 2', '29.99', '202') ");
            statement.executeUpdate( " MERGE INTO product (product_id, name, price, barcode) KEY(product_id) VALUES (3, 'Produkt 3', '39.99', '303') ");
            statement.executeUpdate( " MERGE INTO product (product_id, name, price, barcode) KEY(product_id) VALUES (4, 'Produkt 4', '49.99', '404') ");
            statement.executeUpdate( " MERGE INTO product (product_id, name, price, barcode) KEY(product_id) VALUES (5, 'Produkt 5', '59.99', '505') ");
        }
        catch( Exception e )
        {
            System.out.println( e.getMessage() );
        }
    }

    public void showAllItemsInDatabase(){
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM product");
            while (rs.next()) {
                String id = rs.getString("product_id");
                System.out.println('\n' + "id: " + id);
                String name = rs.getString("name");
                System.out.println(name);
                String price = rs.getString("price");
                System.out.println(price);
                String barcode = rs.getString("barcode");
                System.out.println(barcode);
            }
        }catch (Exception e){
                System.out.println(e.getMessage());
        }
    }

    public void closeDatabase(){
        try {
            statement.close();
            connection.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}