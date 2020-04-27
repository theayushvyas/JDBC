/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ins;

/**
 *
 * @author win7
 */
import java.sql.*;
import java.io.*;
import java.util.Scanner;


public class Ins{

	public static void main(String[] args) 
        {
            Scanner sc = new Scanner(System.in) ; 
            Ins obj = new Ins();
            System.out.print("Enter Choice \n 1-CreateTable \n 2-Enter Record \n 3-Read Table Data \n 4-Delete Record \n");
            int i = sc.nextInt();
            if(i == 1)
            {
                obj.CreateTable();
            }
            else if(i == 2)
            {
                
               obj.InsertRecord();
            }
            else if(i==3)
            {
                obj.ReadData();
            }
            else if(i==4)
            {
                obj.DeleteRecord();
            }
	    }
        
        public void CreateTable()
        {
            try
            {
                
                Connection con = GetConnection("jdbc:mysql://localhost:3306/ayush", "avyas", "avyas");
                String q = "create table info(id_no int(20) ,name varchar(20) , city varchar(20), UNIQUE(id_no))";
                Statement st = con.createStatement();
                st.executeUpdate(q);
                System.out.println("Table  Created in database");
                con.close();
            }
		catch(Exception e)
                {
                   System.out.println(e.getMessage());			
		}
        }
        
        public void InsertRecord()
        {
                try{
                    
                    Connection con = GetConnection("jdbc:mysql://localhost:3306/ayush", "avyas", "avyas");
                    String q = "Insert into Login(User_name , Password) values (?,?)";
                    PreparedStatement st = con.prepareStatement(q);
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    System.out.println("Enter Id_NO");
                    int id_no = Integer.parseInt(br.readLine());
                    System.out.println("Enter Your Name");
                    String name = br.readLine();
                    System.out.println("Enter your City");
                    String city = br.readLine();
                    br.close();
                    st.setInt(1,id_no);
                    st.setString(1,name);
                    st.setString(2,city);
                    st.executeUpdate();
                    System.out.println("Values inserted in table");
                    con.close();

                }

                catch(Exception e){
                    System.out.println(e.getMessage());
              
               }
        }
                  
         public void ReadData()
          {
              try{
                  
                  Connection con = GetConnection("jdbc:mysql://localhost:3306/ayush", "avyas", "avyas");
                  String q = "select * from info";
                  Statement st = con.createStatement();
                  ResultSet rs = st.executeQuery(q);
                  while(rs.next())
                  {
                  int id_no = rs.getInt("id_no");
                  String name = rs.getString("name");
                  String city = rs.getString("city");
                  System.out.println("Id_no: " + id_no + "\n name :" + name + "\n city :" + city);
                  }
                  st.close();
                  con.close();
              }
             catch(Exception e)
                {
                  System.out.println(e.getMessage());
                  
                }
                    
                }
         
         public void DeleteRecord()
         {
             try
                {
                  
                  Connection con = GetConnection("jdbc:mysql://localhost:3306/ayush", "avyas", "avyas");
                   Scanner sc = new Scanner(System.in);
                   System.out.println("Enter Id_no to delete");
                   int id_no = sc.nextInt();
                   String r = "Select id_no ,name,city from info where id_no =" + id_no + " ";
                   Statement st = con.createStatement();
                   ResultSet rs = st.executeQuery(r);
                   rs.next();
                   id_no = rs.getInt("id_no");
                   String name = rs.getString("name");
                   String City = rs.getString("city");
                   System.out.println("Id_no : "+ id_no +"\n Name :" + name +"\n City :" + City);
                   System.out.println("Confirm Deleting This Record \n 1- Confirm Delete \n 2- To cancle Deletion");
                   int a = sc.nextInt();
                   if(a==1)
                   {
                   String q = "delete from info where id_no = " + id_no + " ";
                   PreparedStatement pst = con.prepareStatement(q);
                   pst.executeUpdate(q);
                   System.out.println("Record deleted");
                   }
                   else
                   {
                    System.out.println("Deletion Cancelled");
                   }
                }

              catch(Exception e)
              {
                      System.out.println(e.getMessage());
              }
         }            
         
         public static Connection GetConnection(String url, String un, String pwd)
         {
             try
             {
                Class.forName("com.mysql.jdbc.Driver");
                 url = "jdbc:mysql://localhost:3306/ayush";
                 un  = "avyas";		
                 pwd = "avyas";
                Connection con = DriverManager.getConnection(url,un,pwd);
                return con;
             }
             catch(Exception ex)
             {
                 System.out.print(ex.toString());
                 return null;
             }
         }
       }
