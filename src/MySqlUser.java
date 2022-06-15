import java.sql.*;
import java.util.Scanner;

public class MySqlUser {
    public static void signup(){
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/USER","root","123456789");
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter your phno:");
            String ph=sc.nextLine();
            System.out.println("Enter your aadhar number :");
            String an=sc.nextLine();
            System.out.println("Enter your name:");
            String nm=sc.nextLine();
            System.out.println("Enter your password:");
            String ps=sc.nextLine();
            Statement st=con.createStatement();
              st.executeUpdate("insert into signup values('"+ph+"','"+an+"','"+nm+"','"+ps+"');");
            System.out.println("Registered......");
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void login(){
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/USER","root","123456789");
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter your phno:");
            String nm=sc.nextLine();
            System.out.println("Enter your password:");
            String ps=sc.nextLine();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select phno from signup where password="+"'"+ps+"'"+";");
            rs.next();
            String snm=rs.getString("phno");
            System.out.println(snm.equals(nm));
            System.out.println("Login successfully......");
            con.close();
            System.out.println("Available District Coimabtore(1),Chennai(2):");
             int dst=sc.nextInt();
             if(dst==1){
                 try {
                     Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/ADMIN","root","123456789");
                     Statement st1=con1.createStatement();
                     ResultSet rs1 = st1.executeQuery("select date,place from coimbatore where slots>0;");
                     int cnt=0;
                     while(rs1.next()){
                         System.out.println(rs1.getString("date")+"->"+rs1.getString("place"));
                         cnt+=1;
                     }
                     if(cnt==0){
                         System.out.println("slots not availabe.....");
                     }else{
                         System.out.println("select your place and date:");
                         System.out.println("Enter in the same format:");
                         sc.nextLine();
                         System.out.println("Enter the date:");
                         String dt=sc.nextLine();
                         System.out.println("Enter the location:");
                         String lc=sc.nextLine();
                         ResultSet rs2=st1.executeQuery("select slots from coimbatore where date='"+dt+"' AND place='"+lc+"';");
                         rs2.next();
                         int sl=rs2.getInt("slots");
                         sl-=1;
                         st1.executeUpdate("update coimbatore set slots= '"+sl+"' where place ='"+lc+"' ;");
                         System.out.println("slot booked....");
                         con1.close();

                     }
                 }catch (Exception e){
                     System.out.println(e);

                 }


             }else if(dst==2){
                 try {
                     Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/ADMIN","root","123456789");
                     Statement st1=con1.createStatement();
                     ResultSet rs1 = st1.executeQuery("select date,place from chennai where slots>0;");
                     int cnt=0;
                     while(rs1.next()){
                         System.out.println(rs1.getString("date")+"->"+rs1.getString("place"));
                         cnt+=1;
                     }
                     if(cnt==0){
                         System.out.println("slots not availabe.....");
                     }else{
                         System.out.println("select your place and date:");
                         System.out.println("Enter in the same format:");
                         sc.nextLine();
                         System.out.println("Enter the date:");
                         String dt=sc.nextLine();
                         System.out.println("Enter the location:");
                         String lc=sc.nextLine();
                         ResultSet rs2=st1.executeQuery("select slots from chennai where date='"+dt+"' AND place='"+lc+"';");
                         rs2.next();
                         int sl=rs2.getInt("slots");
                         sl-=1;
                         st1.executeUpdate("update chennai set slots= '"+sl+"' where place ='"+lc+"' ;");
                         System.out.println("slot booked....");
                         con1.close();
                     }
                 }catch (Exception e){
                     System.out.println(e);

                 }
             }else{
                 System.out.println("Invalid Input....");
             }

        }catch(Exception e){
            System.out.println(e);
            System.out.println("user not found...");
        }
    }
    public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
        System.out.println("Login(1),Signup(2):");
         int d=sc.nextInt();
         if(d==1){
             login();
         }else if(d==2){
             signup();
         }else{
             System.out.println("Invalid Input....");
         }
    }
}
