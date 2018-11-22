package com.ly.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ly.bean.User;
import com.ly.util.DBUtil;

public class UserDAO {
	public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select count(*) from user";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return total;
    }
	
	public void add(User bean) {
        String sql = "insert into user values(null,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getPassword());
            ps.setInt(3, bean.getType());
            ps.setString(4, bean.getEmail());
            ps.execute();
  
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
	
	public void delete(int id) {
		  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "delete from user where id = " + id;
  
            s.execute(sql);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
	
	public void update(User bean) {
		String sql = "update user set name= ?, password=?, type=?, email=? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getPassword());
            ps.setInt(3, bean.getType());
            ps.setString(4, bean.getEmail());
            ps.setInt(9, bean.getId());
            ps.execute();
  
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public User getById(int id) {
		User bean = new User();
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select * from user where id = " + id;
  
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
                String name = rs.getString("name");
                String password = rs.getString("password");
                int type = rs.getInt("type");
                String email = rs.getString("email");
               
                bean.setName(name);
                bean.setPassword(password);;
                bean.setType(type);
                bean.setEmail(email);
                bean.setId(id);
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
	
	public List<User> list() {
        return list(0,Short.MAX_VALUE);
    }
    public List<User> list(int start, int count) {
        List<User> beans = new ArrayList<User>();
 
        String sql = "select * from user limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
            	User bean = new User();
                int id = rs.getInt(1);
                String name = rs.getString("name");
                String password = rs.getString("password");
                int type = rs.getInt("type");
                String email = rs.getString("email");
               
                bean.setName(name);
                bean.setPassword(password);;
                bean.setType(type);
                bean.setEmail(email);
                bean.setId(id);
                
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
    }
    
    public boolean checkNameExist(String name) {
    	try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from user where name = \"" + name+"\"";
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    	return true;
    }
    
    //验证密码是否正确，返回的是用户类型，0代表验证错误
    public User checkPassword(String name,String password) {
    	User user=new User();
    	try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from user where name= \"" + name+"\" and password =\""+password+"\"";
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
                int type = rs.getInt("type");
                int id = rs.getInt("id");
                user.setId(id);
                user.setType(type);
                return user;
            }
            user.setType(0);
            return user;
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    	user.setType(0);
    	return user;
    }
}
