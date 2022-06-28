package IVR_STACK_DB;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public class IVR_STACK
{
    static Stack<String> stack = new Stack<>();
    static int level=-1;
    static int dummy=0;
    public static void main(String[] args)
    {
        new IVR_STACK().showOptionWithRespectToStack();
    }
    public void displayOption()
    {
        try
        {
            String userChoice = "";int count=0;int max_count=0;
            String name ="";ResultSet rs1;PreparedStatement ps;String query;
            Scanner sc = new Scanner(System.in);
            String url = "jdbc:mysql://localhost:3306/IVR_DB";
            String username = "root";
            String pwd = "password";
            Connection con = DriverManager.getConnection(url, username, pwd);
            Statement stmt = con.createStatement();
            String sql = "show tables";
            ResultSet rs = stmt.executeQuery(sql);
            String topElement = stack.peek();
            loop1:while(rs.next()) {
                if (rs.getString(1).equals(topElement)) {
                    //System.out.println("level:"+level);
                    name = rs.getString(1);
                    String strQuery = "select * from $tableName";
                    query = strQuery.replace("$tableName", name);
                    ps = con.prepareStatement(query);
                    rs1 = ps.executeQuery(query);
                    System.out.println("Choose any of the below option :");
                    while (rs1.next()) {
                        ++count;
                        level = rs1.getInt(3);
                        System.out.println(rs1.getInt(1) + "." + rs1.getString(2));
                    }
                    max_count = count;
                    // System.out.println("level repeated:"+level);
                    if (level == 1) {
                        System.out.println("*.TO GOT TO MAIN MENU");
                    } else if (level != 0) {
                        System.out.println("#.TO GOT TO PREVIOUS MENU");
                        System.out.println("*.TO GOT TO MAIN MENU");
                    }
                    userChoice = sc.next();
//                    int dummy=0;
//                    if(((Objects.equals(userChoice, "*"))||(Objects.equals(userChoice, "#")))&&(level==0))
//                    {
//                        dummy=1;
//                    }
//                    else if((Objects.equals(userChoice, "*"))&&(level==1))
//                    {
//                        dummy=1;
//                    }
//                    if(((!Objects.equals(userChoice, "*"))||(userChoice!="#"))&&((Integer.parseInt(userChoice)<1)||(Integer.parseInt(userChoice)>max_count))&&((level==0)||(level==1)))
//                    {
//                        dummy=1;
//                    }
//                    else if((userChoice!="*")&&(userChoice!="#")&&((Integer.parseInt(userChoice)<1)||(Integer.parseInt(userChoice)>max_count))&&(level>1))
//                    {
//                        System.out.println("else if entry");
//                        dummy=1;
//                    }
//
//                    if(dummy==1)
//                    {
//                        System.out.println("wrong option is entered...please enter the option again");
//                        new IVR_STACK().showOptionWithRespectToStack();
//                    }
//                    System.out.println("dummy"+dummy);
//                }
//            }
//            if(dummy == 0)
//            {
                }
            }
                if (Objects.equals(userChoice, "*")) {
                    stack.clear();
                    new IVR_STACK().showOptionWithRespectToStack();
                } else if (Objects.equals(userChoice, "#")) {
                    stack.pop();
                    new IVR_STACK().showOptionWithRespectToStack();
                } else {
                    //System.out.println("else entry");
                    name = stack.peek();
                    //System.out.println(name);
                    String strQuery = "select * from $tableName";
                    query = strQuery.replace("$tableName", name);
                    ps = con.prepareStatement(query);
                    rs1 = ps.executeQuery(query);
                    while (rs1.next()) {
                        // System.out.println("string :"+rs1.getString(2));
                        if (rs1.getInt(1) == Integer.valueOf(userChoice)) {
                            if (rs1.getInt(3) != 2) {
                                stack.push(rs1.getString(2));
                                //System.out.println("stack" + stack);
                                new IVR_STACK().showOptionWithRespectToStack();
                            } else if (rs1.getInt(3) == 2) {
                                System.out.println("connecting with " + rs1.getString(2) + " team customer care executive.....Hold on to the line");
                                break;
                            }
                        }

                    }
                }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void showOptionWithRespectToStack()
    {
        if(stack.isEmpty())
        {
            stack.push("language_table");
            new IVR_STACK().displayOption();
        }
        else
        {
            new IVR_STACK().displayOption();
        }
    }
}
