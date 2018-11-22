package com.ly.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ly.bean.EhtFile;
import com.ly.util.DBUtil;

public class EhtFileDAO {
	public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select count(*) from ehtfile";
 
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
 
            String sql = "select count(*) from ehtfile where mid="+mid;
 
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return total;
    }
	
	public void add(EhtFile bean) {
		 
        String sql = "insert into ehtfile values(null,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, bean.getType());
            ps.setInt(2, bean.getVersion());
            ps.setString(3, bean.getNote());
            ps.setLong(4, bean.getBdate());
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
  
            String sql = "delete from ehtfile where id = " + id;
  
            s.execute(sql);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
	
	public void update(EhtFile bean) {
		String sql = "update ehtfile set type= ?, version=?, note=?,bdate=? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
        	ps.setInt(1, bean.getType());
            ps.setInt(2, bean.getVersion());
            ps.setString(3, bean.getNote());
            ps.setLong(4, bean.getBdate());
            ps.execute();
  
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public EhtFile getById(int id) {
		EhtFile bean = new EhtFile();
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select * from ehtfile where id = " + id;
  
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
                int type = rs.getInt("type");
                int version = rs.getInt("version");
                String note = rs.getString("note");
                long bdate = rs.getLong("bdate");
               
                bean.setType(type);
                bean.setVersion(version);
                bean.setNote(note);
                bean.setBdate(bdate);
                bean.setId(id);
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
	
	public List<EhtFile> list() {
        return list(0,Short.MAX_VALUE);
    }
    public List<EhtFile> list(int start, int count) {
        List<EhtFile> beans = new ArrayList<EhtFile>();
 
        String sql = "select * from ehtfile limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
            	EhtFile bean = new EhtFile();
            	int id = rs.getInt(1);
            	int type = rs.getInt("type");
                int version = rs.getInt("version");
                String note = rs.getString("note");
                long bdate = rs.getLong("bdate");
               
                bean.setType(type);
                bean.setVersion(version);
                bean.setNote(note);
                bean.setBdate(bdate);
                bean.setId(id);
                
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
    }
    
    public List<EhtFile> listByType(int start, int count,int type) {
        List<EhtFile> beans = new ArrayList<EhtFile>();
 
        String sql = "select * from ehtfile where type="+type+" limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
            	EhtFile bean = new EhtFile();
            	int id = rs.getInt(1);
                int version = rs.getInt("version");
                String note = rs.getString("note");
                long bdate = rs.getLong("bdate");
                
                bean.setType(type);
                bean.setVersion(version);
                bean.setNote(note);
                bean.setBdate(bdate);
                bean.setId(id);
                
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
    }
    
    public boolean checkVersionExist(int type,int version) {
    	try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from ehtfile where type=" + type+" and version="+version;
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
