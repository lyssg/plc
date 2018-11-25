package com.ly.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ly.bean.Device;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		 
        Session s = sf.openSession();
        s.beginTransaction();
 
        Device p = new Device();
        p.setName("LJ-00000016");
        p.setHtype(1);
        p.setStype(1);
        p.setMid(3);
        p.setOid(16);
        p.setLocation(5);
        p.setStatus(1);
        p.setBdate(2);
        s.save(p);
         
        s.getTransaction().commit();
        s.close();
        sf.close();
	}

}
