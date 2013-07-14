package com.sapient.mapreduce.example.hadoop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateHiveTables
{

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    public static void main(String[] args) throws SQLException
    {
        try
        {
            Class.forName(driverName);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "", "");
        Statement stmt = con.createStatement();
        String tableName = "TRADES_CUSTOMER";
        //stmt.executeQuery("drop table " + tableName);

        try
        {
            System.out.println(fileToString("res/create_tables"));
            ResultSet res = stmt.executeQuery(fileToString("res/create_tables"));
            
            // select * query
            String sql = "select * from " + tableName;
            System.out.println("Running: " + sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
              System.out.println(String.valueOf(res.getInt(1)) + "\t" + res.getString(2));
            }
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String fileToString(String filename) throws IOException
    {
        String result = "";
        File file = new File(filename);
        if (file.exists())
        {
            FileInputStream inStream = new FileInputStream(file);
            byte bytes[] =  new byte[(int)file.length()];
            inStream.read(bytes);
            result = new String(bytes,"UTF-8").replace("\n", " ");
        }
        return result;
    }
}
