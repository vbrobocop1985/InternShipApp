package com.test.parts.service;

import com.test.parts.dao.PartsDAO;
import com.test.parts.model.Parts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by oper on 13.09.2018.
 */
@Service
@Transactional
public class PartsServiceImpl implements PartsService {
    public PartsServiceImpl() {
        System.out.println("PartsServiceImpl()");
    }

    @Autowired
    private PartsDAO partDAO;

    @Override
    public int createPart(Parts part) {
        return partDAO.createPart(part);
    }

    @Override
    public Parts updatePart(Parts part) {
        return partDAO.updatePart(part);
    }

    @Override
    public void deletePart(int id) {
        partDAO.deletePart(id);
    }

    @Override
    public List<Parts> getAllParts() {
        return partDAO.getAllParts();
    }

    @Override
    public Parts getPart(int id) {
        return partDAO.getPart(id);
    }

    @Override
    public List<Parts> getAllParts(String partName) {
        return partDAO.getAllParts(partName);
    }

    @Override
    public List<Parts> getAllParts(int needPart){
        return partDAO.getAllParts(needPart);
    }



}
