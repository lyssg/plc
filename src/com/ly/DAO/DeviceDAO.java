package com.ly.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ly.bean.Device;
import com.ly.util.DBUtil;

import com.ly.bean.Device;
//import com.ly.util.DateUtil;


public class DeviceDAO {

	public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select count(*) from device";
 
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return total;
    }
	
	public int getTotalByMid(int mid) {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select count(*) from device where mid="+mid;
 
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return total;
    }
	
	public void add(Device bean) {
		 
        String sql = "insert into device values(null,?,?,?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setString(1, bean.getName());
            ps.setInt(2, bean.getHtype());
            ps.setInt(3, bean.getStype());
            ps.setInt(4, bean.getMid());
            ps.setInt(5, bean.getOid());
            ps.setInt(6, bean.getLocation());
            ps.setInt(7, bean.getStatus());
            ps.setLong(8, bean.getBdate());
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
  
            String sql = "delete from device where id = " + id;
  
            s.execute(sql);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
	
	public void update(Device bean) {
		String sql = "update device set name= ?, htype=?, stype=?,mid=?,oid=?, location = ?, status=?, bdate=? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, bean.getName());
            ps.setInt(2, bean.getHtype());
            ps.setInt(3, bean.getStype());
            ps.setInt(4, bean.getMid());
            ps.setInt(5, bean.getOid());
            ps.setInt(6, bean.getLocation());
            ps.setInt(7, bean.getStatus());
            ps.setLong(8, bean.getBdate());
            ps.setInt(9, bean.getId());
            ps.execute();
  
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public Device getById(int id) {
		Device bean = new Device();
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select * from device where id = " + id;
  
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
                String name = rs.getString("name");
                int htype = rs.getInt("htype");
                int stype = rs.getInt("stype");
                int mid = rs.getInt("mid");
                int oid = rs.getInt("oid");
                int location = rs.getInt("location");
                int status = rs.getInt("status");
                long bdate = rs.getInt("bdate");
               
                bean.setName(name);
                bean.setHtype(htype);
                bean.setStype(stype);
                bean.setMid(mid);
                bean.setOid(oid);
                bean.setLocation(location);
                bean.setBdate(bdate);
                bean.setStatus(status);
                bean.setId(id);
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
	
	public List<Device> list() {
        return list(0,Short.MAX_VALUE);
    }
    public List<Device> list(int start, int count) {
        List<Device> beans = new ArrayList<Device>();
 
        String sql = "select * from device limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
            	Device bean = new Device();
                int id = rs.getInt(1);
                String name = rs.getString("name");
                int htype = rs.getInt("htype");
                int stype = rs.getInt("stype");
                int mid = rs.getInt("mid");
                int oid = rs.getInt("oid");
                int location = rs.getInt("location");
                int status = rs.getInt("status");
                long bdate = rs.getInt("bdate");
 
                bean.setName(name);
                bean.setHtype(htype);
                bean.setStype(stype);
                bean.setMid(mid);
                bean.setOid(oid);
                bean.setLocation(location);
                bean.setBdate(bdate);
                bean.setStatus(status);
                bean.setId(id);
                
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
    }
    public List<Device> listByMid(int start, int count,int mid) {
        List<Device> beans = new ArrayList<Device>();
 
        String sql = "select * from device where mid="+mid+" limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
            	Device bean = new Device();
                int id = rs.getInt(1);
                String name = rs.getString("name");
                int htype = rs.getInt("htype");
                int stype = rs.getInt("stype");
                int oid = rs.getInt("oid");
                int location = rs.getInt("location");
                int status = rs.getInt("status");
                long bdate = rs.getInt("bdate");
 
                bean.setName(name);
                bean.setHtype(htype);
                bean.setStype(stype);
                bean.setMid(mid);
                bean.setOid(oid);
                bean.setLocation(location);
                bean.setBdate(bdate);
                bean.setStatus(status);
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
            String sql = "select * from device where name = \"" + name+"\"";
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
}
