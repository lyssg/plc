package com.ly.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ly.bean.ApkFile;
import com.ly.util.DBUtil;
//import com.ly.util.DateUtil;


public class ApkFileDAO {

	public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select count(*) from apkfile";
 
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
 
            String sql = "select count(*) from apkfile where mid="+mid;
 
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return total;
    }
	
	public void add(ApkFile bean) {
		 
        String sql = "insert into apkfile values(null,?,?,?,?)";
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
  
            String sql = "delete from apkfile where id = " + id;
  
            s.execute(sql);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
	
	public void update(ApkFile bean) {
		String sql = "update apkfile set type= ?, version=?, note=?,bdate=? where id = ?";
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
	
	public ApkFile getById(int id) {
		ApkFile bean = new ApkFile();
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select * from apkfile where id = " + id;
  
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
	
	public List<ApkFile> list() {
        return list(0,Short.MAX_VALUE);
    }
    public List<ApkFile> list(int start, int count) {
        List<ApkFile> beans = new ArrayList<ApkFile>();
 
        String sql = "select * from apkfile limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
            	ApkFile bean = new ApkFile();
            	int id = rs.getInt(1);
            	int type = rs.getInt("type");
                int version = rs.getInt("version");
                String note = rs.getString("note");
                long bdate = rs.getLong("bdate");
               
                bean.setType(type);
                bean.setVersion(version);
                bean.setNote(note);
                bean.setBdate(bdate);
                bean.setBddate(new Date(bdate));
                bean.setId(id);
                
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
    }
    
    public List<ApkFile> listByType(int start, int count,int type) {
        List<ApkFile> beans = new ArrayList<ApkFile>();
 
        String sql = "select * from apkfile where type="+type+" limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
            	ApkFile bean = new ApkFile();
            	int id = rs.getInt(1);
                int version = rs.getInt("version");
                String note = rs.getString("note");
                long bdate = rs.getLong("bdate");
                
                bean.setType(type);
                bean.setVersion(version);
                bean.setNote(note);
                bean.setBdate(bdate);
                bean.setBddate(new Date(bdate));
                System.out.println("bdate:"+bean.getBddate().toLocaleString());
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
            String sql = "select * from apkfile where type=" + type+" and version="+version;
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
    
    public int getMaxVersionId(int type) {
    	int id=0;
    	int maxversion=0;
    	int nowversion=0;
    	try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from apkfile where type=" + type;
            ResultSet rs = s.executeQuery(sql);
  
            while (rs.next()) {
            	nowversion=rs.getInt("version");
            	id=rs.getInt("id");
            	System.out.println("nowversion:"+nowversion+"   maxversion:"+maxversion);
            	System.out.println();
            	if(nowversion>maxversion) { 
            		System.out.println("update "+"nowversion:"+nowversion+"   maxversion:"+maxversion);
            		maxversion=nowversion;
            		
            	}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	return id;
    	
    }
    
    public int getTypeById(int id) {
    	int type=0;
    	try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from apkfile where id=" + id;
            ResultSet rs = s.executeQuery(sql);
  
            while (rs.next()) {
            	type=rs.getInt("type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	return type;
    	
    }
}
