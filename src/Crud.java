import java.sql.*;

public class Crud {
    public static Connection con;
    public static PreparedStatement st;
    public static ResultSet rs;

    public static void main(String[] args) throws SQLException {

        String url="jdbc:postgresql://localhost:5432/demo";
        String UserName="postgres";
        String Password="postgres";

        try  {
            con = DriverManager.getConnection(url, UserName, Password);
            System.out.println("Connection established successfully: " + con);
            create();
            update();
            delete();
            read();
            search();

        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());

        } finally {
            con.close();
        }




    }

    public static void create() throws SQLException {
        String createQuery = "Insert Into student values (?,?,?)";

        st = con.prepareStatement(createQuery);
        st.setInt(1,2);
        st.setString(2,"lazz");
        st.setInt(3,93);

        boolean status = st.execute();
        System.out.println("student insert " );

    }

    public static void read() throws SQLException {
        String readQuery = "select * from student";
        st = con.prepareStatement(readQuery);
        rs = st.executeQuery();

        while (rs.next()){
            System.out.println(rs.getInt(1)+ " : " + rs.getString(2) + " : " + rs.getInt(3));
        }

    }

    public static void update() throws SQLException {
        String updareQuery = "update student set sname=? , marks=? where sid=?";

        st = con.prepareStatement(updareQuery);
        st.setString(1,"Roy");
        st.setInt(2,100);
        st.setInt(3,1);
        st.execute();
    }

    public static void delete() throws SQLException {
        String deleteQuery = "Delete from student where sid =?";
        st = con.prepareStatement(deleteQuery);

        st.setInt(1,3);
        st.execute();
    }

    public static void search() throws SQLException {
        String searchQuery = "Select*from student where sid = ?";
        st = con.prepareStatement(searchQuery);
        st.setInt(1,2);

        rs = st.executeQuery();
        while (rs.next()){
            System.out.println(rs.getInt(1)+ " : " + rs.getString(2) + " : " + rs.getInt(3));
        }

    }
}
