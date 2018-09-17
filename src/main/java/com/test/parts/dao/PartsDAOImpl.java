package com.test.parts.dao;

import com.test.parts.model.Parts;
import com.test.parts.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oper on 13.09.2018.
 */
@Repository
public class PartsDAOImpl implements PartsDAO {
    public PartsDAOImpl() {
        System.out.println("PartsDAOImpl");
    }

    @Autowired
    private HibernateUtil hibernateUtil;


    @Override
    public int createPart(Parts part) {
        return (int) hibernateUtil.create(part);
    }

    @Override
    public Parts updatePart(Parts part) {
        return hibernateUtil.update(part);
    }

    @Override
    public void deletePart(int id) {
        Parts part = new Parts();
        part.setId(id);
        hibernateUtil.delete(part);
    }

    @Override
    public List<Parts> getAllParts() {
        return hibernateUtil.fetchAll(Parts.class);
    }

    @Override
    public Parts getPart(int id) {
        return hibernateUtil.fetchById(id, Parts.class);
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<Parts> getAllParts(String partName) {
        String query = "SELECT t.id, t.partname, t.partbase, t.partqty, t.parttype FROM parts t WHERE t.partname like '%"+ partName +"%'";
        List<Object[]> partObjects = hibernateUtil.fetchAll(query);
        List<Parts> parts = new ArrayList<>();
        for(Object[] partObject: partObjects) {
            Parts part = new Parts();
            int id = (int) partObject[0];
            String partname = (String) partObject[1];
            boolean partbase = (boolean) partObject[2];
            int partqty = (int) partObject[3];
            int parttype = (int) partObject[4];
            part.setId(id);
            part.setPartname(partname);
            part.setPartbase(partbase);
            part.setPartqty(partqty);
            part.setParttype(parttype);
            parts.add(part);
        }
        System.out.println(parts);
        return parts;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Parts> getAllParts(int needPart){
        String query = "SELECT t.id, t.partname, t.partbase, t.partqty, t.parttype FROM parts t";
        if (needPart==0)
           query = "SELECT t.id, t.partname, t.partbase, t.partqty, t.parttype FROM parts t WHERE t.partbase = 0";
        else if (needPart==1)
           query = "SELECT t.id, t.partname, t.partbase, t.partqty, t.parttype FROM parts t WHERE t.partbase = 1";

        List<Object[]> partObjects = hibernateUtil.fetchAll(query);
        List<Parts> parts = new ArrayList<>();
        for(Object[] partObject: partObjects) {
            Parts part = new Parts();
            int id = (int) partObject[0];
            String partname = (String) partObject[1];
            boolean partbase = (boolean) partObject[2];
            int partqty = (int) partObject[3];
            int parttype = (int) partObject[4];
            part.setId(id);
            part.setPartname(partname);
            part.setPartbase(partbase);
            part.setPartqty(partqty);
            part.setParttype(parttype);
            parts.add(part);
        }
        System.out.println(parts);
        return parts;
    }


}
