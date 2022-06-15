import java.sql.*;
import java.util.Scanner;

public class Mysql {
    public static void adminlogin(){
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin","root","123456789");
            Statement st= con.createStatement();
            String userid="";
            Scanner sc=new Scanner(System.in);
            System.out.println("Eneter Your User Id:");
            userid=sc.nextLine();
            System.out.println("Enter Your Password:");
            String ps=sc.nextLine();
            ResultSet rs=st.executeQuery("select userid from adminlogin where password="+"'"+ps+"'"+";");
            String check="";
            while(rs.next()) {
                check += rs.getString("userid");
            }
            if(check.equals(userid)){
                System.out.println("Select Action(Add Vaccination Center(1),Get Dosage Details(2),Remove Vaccination Center(3):");
                int sl=sc.nextInt();
                if(sl==1){
                    try{
                        System.out.println("Available Location(coimbator(1),chennai(2)):");
                        int lc=sc.nextInt();
                        if(lc==1) {
                            System.out.println("Enter Vaccination Location:");
                            sc.nextLine();
                            String loc = sc.nextLine();
                            System.out.println(loc);
                            System.out.println("Enter the date in the format dd-mm-yyyy:");
                            String date = sc.nextLine();
                            System.out.println("Enter the dosages");
                            int ds = sc.nextInt();
                            System.out.println("updating.....");
                            st.executeUpdate("insert into coimbatore values(" + "'" + date + "'," + "'" + loc + "'," + "'" + 10 + "'," + "'" + ds + "');");
                            System.out.println("added vaccination centre");
                        }else if(lc==2){
                            System.out.println("Enter Vaccination Location:");
                            sc.nextInt();
                            String loc = sc.nextLine();
                            System.out.println("Enter the date in the format dd-mm-yyyy:");
                            String date = sc.nextLine();
                            System.out.println("Enter the dosages");
                            int ds = sc.nextInt();
                            System.out.println("updating.....");
                            st.executeUpdate("insert into chennai values(" + "'" + date + "'," + "'" + loc + "'," + "'" + 10 + "'," + "'" + ds + "');");
                            System.out.println("added vaccination centre");
                        }else{
                            System.out.println("Invalid Input....");
                        }

                    }catch (Exception e){
                        System.out.println(e);
                    }
                }else if(sl==2){
                      int total=0;
                      rs=st.executeQuery("select SUM(dosage) from coimbatore");
                    System.out.println("In coimbatore:"+rs.getInt(1));
                    rs=st.executeQuery("select SUM(dosage) from chennai");
                    System.out.println("In chennai :"+rs.getInt(1));


                }else if(sl==3){
                    System.out.println("Available Location(coimbator(1),chennai(2)):");
                    int lc=sc.nextInt();
                    if(lc==1) {
                        System.out.println("Enter the location");
                        sc.next();
                        String loc = sc.nextLine();
                        st.executeUpdate("delete from coimbatore where place=" + "'" + loc + "';");
                        System.out.println("location removed");
                    }else if(lc==2){
                        System.out.println("Enter the location");
                        sc.next();
                        String loc = sc.nextLine();
                        st.executeUpdate("delete from chennai where place=" + "'" + loc + "';");
                        System.out.println("location removed");
                    }else{
                        System.out.println("Invalid Input...");
                    }
                }
                con.close();
            }else{
                System.out.println("user not found");
            }
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
          Mysql.adminlogin();
    }
}
