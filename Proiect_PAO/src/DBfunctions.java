import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBfunctions {
    public Connection connect_to_db( String dbname, String user, String pass){
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname, user, pass);
            if(conn != null) {
                System.out.println("Connection Established");
            }else{
                System.out.println("Connection Failed");
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }

    public void delete_table(Connection conn, String table_name){
        Statement statement;
        try {
            String query = "drop table " +table_name+ ";";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Dropped");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void insert_col(Connection conn,String table_name, String col_name){
        Statement statement;
        try{
            String query = String.format("alter table %s add %s DOUBLE PRECISION;",table_name,col_name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Column added");
        }catch(Exception e){
            System.out.println(e);
        }
    }



    //TABELUL GAME
    public void createTable(Connection conn, String table_name){
        Statement statement;
        try {
            String query = "create table " +table_name+"(game_id SERIAL,game_name varchar(200),game_genre varchar(200),developer varchar(200),price DOUBLE PRECISION,platform varchar(200),primary key(game_id));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");
        }catch(Exception e){
            System.out.println(e);
        }
    }



    //TABELUL CLIENT
    public void create_table(Connection conn, String table_name){
        Statement statement;
        try {
            String query = "create table " +table_name+"(client_id SERIAL,client_name varchar(200),primary key(client_id));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");
        }catch(Exception e){
            System.out.println(e);
        }
    }




    //TABELUL ADMIN
    public void create_admin(Connection conn, String table_name){
        Statement statement;
        try {
            String query = "create table " +table_name+"(admin_id SERIAL,admin_name varchar(200), email_admin varchar(200), password varchar(200),primary key(admin_id));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    //TABLE STOCK
    public void create_stock(Connection conn, String table_name){
        Statement statement;
        try {
            String query = "create table " +table_name+"(stock_id SERIAL,product_quantity INTEGER,game_id INTEGER, primary key(stock_id),CONSTRAINT fk_game FOREIGN KEY(game_id) REFERENCES Game(game_id));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    //TABLE REVIEWS
    public void create_reviews(Connection conn, String table_name){
        Statement statement;
        try {
            String query = "create table " +table_name+"(review_id SERIAL,client_id INTEGER,game_id INTEGER, review_text varchar(200), primary key(review_id),CONSTRAINT fk_game FOREIGN KEY(game_id) REFERENCES Game(game_id), CONSTRAINT fk_client FOREIGN KEY(client_id) REFERENCES Client(client_id));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    //TABLE PURCHASE
    public void create_purchase(Connection conn, String table_name){
        Statement statement;
        try {
            String query = "create table " +table_name+"(purchase_id SERIAL,client_id INTEGER,game_id INTEGER,transaction_type VARCHAR(50), total_price DOUBLE PRECISION, primary key(purchase_id ),CONSTRAINT fk_game FOREIGN KEY(game_id) REFERENCES Game(game_id), CONSTRAINT fk_client FOREIGN KEY(client_id) REFERENCES Client(client_id));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    //TABLE PointsTransaction
    public void create_points_trans(Connection conn, String table_name){
        Statement statement;
        try {
            String query = "create table " +table_name+"(points_id SERIAL,purchase_id INTEGER,points INTEGER, primary key(points_id ),CONSTRAINT fk_purchase FOREIGN KEY(purchase_id) REFERENCES Purchase(purchase_id));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    //TABLE Card Transaction
    public void create_card_trans(Connection conn, String table_name){
        Statement statement;
        try {
            String query = "create table " +table_name+"(card_id SERIAL,purchase_id INTEGER,card_number VARCHAR(20), primary key(card_id),CONSTRAINT fk_purchase FOREIGN KEY(purchase_id) REFERENCES Purchase(purchase_id));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    //INSERARE TABEL
    public void insert_row(Connection conn,String table_name, String game_name,String game_genre,String developer,Double price, String platform){
        Statement statement;
        try{
            String query = String.format("insert into %s(game_name,game_genre,developer,price,platform) values('%s','%s','%s',%f,'%s');",table_name, game_name,game_genre,developer,price,platform);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Insered");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void insert_client(Connection conn,String table_name,String client_name, String email){
        Statement statement;
        try{
            String query = String.format("insert into %s(client_name,email) values('%s','%s');",table_name,client_name,email);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Insered");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void insert_admin(Connection conn,String table_name,String admin_name, String password){
        Statement statement;
        try{
            String query = String.format("insert into %s(admin_name,password) values('%s','%s');",table_name,admin_name,password);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Insered");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void insert_purchase(Connection conn,String table_name,Integer client_id,Integer game_id, String transaction_type, Double total_price){
        Statement statement;
        try{
            String query = String.format("insert into %s(client_id,game_id,transaction_type,total_price) values('%d','%d','%s','%f');",table_name,client_id,game_id,transaction_type,total_price);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Insered");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void insert_stock(Connection conn,String table_name,Integer game_id ,Integer product_quantity){
        Statement statement;
        try{
            String query = String.format("insert into %s(product_quantity,game_id) values('%d','%d');",table_name,game_id,product_quantity);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Insered");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void insert_card(Connection conn,String table_name,Integer purchase_id ,String card_number){
        Statement statement;
        try{
            String query = String.format("insert into %s(purchase_id,card_number) values('%d','%s');",table_name,purchase_id,card_number);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Insered");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void insert_points(Connection conn,String table_name,Integer purchase_id ,Integer points){
        Statement statement;
        try{
            String query = String.format("insert into %s(purchase_id,points) values('%d','%d');",table_name,purchase_id,points);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Insered");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void insert_review(Connection conn,String table_name,Integer client_id ,Integer game_id,String review_text){
        Statement statement;
        try{
            String query = String.format("insert into %s(client_id,game_id,review_text) values('%d','%d','%s');",table_name,client_id,game_id,review_text);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Insered");
        }catch(Exception e){
            System.out.println(e);
        }
    }




}
