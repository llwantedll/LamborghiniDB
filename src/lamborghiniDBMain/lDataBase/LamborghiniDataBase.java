package lamborghiniDBMain.lDataBase;

import java.sql.*;
import java.util.ArrayList;

public class LamborghiniDataBase {
    private static final String url = "jdbc:mysql://localhost:3306/lamborghiniDB";
    private String user;
    private String password;

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public void setUser(String user){
        this.user = user;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void database(){
        try {
            con = DriverManager.getConnection(url,user,password);
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getTable(String table) throws SQLException {
        String query = "SELECT * from "+table;
        rs = stmt.executeQuery(query);
        return rs;
    }

    public void finish() throws SQLException {
        con.close();
        stmt.close();
        rs.close();
    }

    public ArrayList<Job> getJobs() throws SQLException {
        ResultSet rs = getTable("job");
        ArrayList<Job> jobs = new ArrayList<>();
        while (rs.next()) {
            int index = rs.getInt(1);
            String type = rs.getString(2);
            int employeeN = rs.getInt(3);
            int carSerial = rs.getInt(4);
            String date = rs.getDate(5).toString();
            jobs.add(new Job(index, type, employeeN, carSerial, date));
        }
        return jobs;
    }

    public ArrayList<Employee> getEmployees() throws SQLException {
        ResultSet rs = getTable("employee");
        ArrayList<Employee> employee = new ArrayList<>();
        while (rs.next()) {
            int index = rs.getInt(1);
            String name = rs.getString(2);
            String tNumber = rs.getString(3);
            String cardNumber = rs.getString(4);
            String adress = rs.getString(5);
            String password = rs.getString(6);
            String login = rs.getString(7);
            boolean isAdmin = rs.getBoolean(8);
            String isFired;
            if(rs.getDate(9)==null)
                isFired = "0";
            else
                isFired= rs.getDate(9).toString();
            employee.add(new Employee(index, name, tNumber, cardNumber, adress, password, login, isAdmin, isFired));
        }
        return employee;
    }

    public ArrayList<Car> getCars() throws SQLException {
        ResultSet rs = getTable("car");
        ArrayList<Car> car = new ArrayList<>();
        while (rs.next()) {
            int serialNumber = rs.getInt(1);
            String color = rs.getString(2);
            String model = rs.getString(3);
            int price = rs.getInt(4);
            boolean sold = rs.getBoolean(5);
            car.add(new Car(serialNumber, color, model, price, sold));
        }
        return car;
    }

    public ArrayList<Client> getClients() throws SQLException {
        ResultSet rs = getTable("client");
        ArrayList<Client> clients = new ArrayList<>();
        while (rs.next()) {
            String cardNumber = rs.getString(1);
            String name = rs.getString(2);
            String tNumber = rs.getString(3);
            clients.add(new Client(cardNumber, name, tNumber));
        }
        return clients;
    }

    public ArrayList<ClientOrder> getClientOrders() throws SQLException {
        ResultSet rs = getTable("clientorder");
        ArrayList<ClientOrder> clientOrders = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            int price = rs.getInt(2);
            String date = rs.getDate(3).toString();
            String clientCardNumber = rs.getString(4);
            int jobID = rs.getInt(6);
            boolean executed = rs.getBoolean(5);
            clientOrders.add(new ClientOrder(id,price,date,clientCardNumber, jobID, executed));
        }
        return clientOrders;
    }

    public void addJob(String type, int employeeN, int carSerial, String date) throws SQLException {
        String query = "INSERT INTO job (ID, Type, EmployeePersonnelNumber, CarSerialNumber, JobTime) VALUES (NULL, '"+type+"', "+employeeN+", "+carSerial+", '"+date+"')";
        stmt.executeUpdate(query);
    }

    public void addEmployee(String name, String tNumber, String cardNumber, String address, String password, String login, boolean isAdmin) throws SQLException {
        String query = "INSERT INTO employee (PersonnelNumber, Name, TNumber, CardNumber, Adress, Password, Login, isAdmin, isFired) VALUES (NULL, '"+name+"', '"+tNumber+"', '"+cardNumber+"', '"+address+"', '"+password+"', '"+login+"', "+isAdmin+", NULL)";
        stmt.executeUpdate(query);
    }

    public void addClient(String name, String tNumber, String cardNumber) throws SQLException {
        String query = "INSERT INTO client (Name, TNumber, CardNumber) VALUES ('"+name+"', '"+tNumber+"', '"+cardNumber+"')";
        stmt.executeUpdate(query);
    }

    public void addCar(String color, String model, int price) throws SQLException {
        String query = "INSERT INTO car (SerialNumber, Color, Model, Price) VALUES (NULL, '"+color+"', '"+model+"', "+price+")";
        stmt.executeUpdate(query);
    }

    public void addClientOrder(int price, String data, String clientCardNumber, int jobID) throws SQLException {
        String query = "INSERT INTO clientorder (ID, Price, Data, ClientCardNumber, jobID) VALUES (NULL, "+price+", '"+data+"', '"+clientCardNumber+"', "+jobID+")";
        stmt.executeUpdate(query);
    }

    public void deleteEmployee(int ID) throws SQLException {
        String query = "DELETE FROM employee WHERE PersonnelNumber='"+ID+"'";
        stmt.executeUpdate(query);
    }

    public void editEmployee(int personnelNumber, String name, String tNumber, String cardNumber, String address, String password, String login, boolean isAdmin, String isFired) throws SQLException {
        String query = "UPDATE employee SET Name = '"+name+"', TNumber = '"+tNumber+"', CardNumber = '"+cardNumber+"', Adress = '"+address+"', Password = '"+password+"', Login = '"+login+"', isAdmin= "+isAdmin+", isFired = '"+isFired+"' WHERE PersonnelNumber = "+personnelNumber;
        stmt.executeUpdate(query);
    }

    public void editOrder(int id, int price, String data, String clientCardNumber, boolean executed, int jobID) throws SQLException {
        String query = "UPDATE clientorder SET Price = "+price+", Data = '"+data+"', ClientCardNumber = '"+clientCardNumber+"', Executed = "+executed+", jobID = "+jobID+" WHERE ID = "+id;
        stmt.executeUpdate(query);
    }

    public void editCar(int serial, String color, String model, int price, boolean sold) throws SQLException {
        String query = "UPDATE car SET Color = '"+color+"', Model = '"+model+"', Price = "+price+", sold = "+sold+" WHERE SerialNumber = "+serial;
        stmt.executeUpdate(query);
    }
}
