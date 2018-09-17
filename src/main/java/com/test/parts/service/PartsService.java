package com.test.parts.service;

import com.test.parts.model.Parts;

import java.util.List;

/**
 * Created by oper on 13.09.2018.
 */
public interface PartsService {
    int createPart(Parts part);
    Parts updatePart(Parts part);
    void deletePart(int id);
    List<Parts> getAllParts();
    Parts getPart(int id);
    List<Parts> getAllParts(String partName);
    List<Parts> getAllParts(int needPart);
}
