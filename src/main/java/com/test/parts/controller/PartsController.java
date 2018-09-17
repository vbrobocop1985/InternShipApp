package com.test.parts.controller;


import com.test.parts.model.Parts;
import com.test.parts.service.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by oper on 13.09.2018.
 */
@RestController
public class PartsController {
    private static final int MAX_ROWS_PER_PAGE = 10;
    private final PartsService partsService;

    @Autowired
    public PartsController(PartsService partsService) {
        this.partsService = partsService;
    }

    @RequestMapping("createPart")
    public ModelAndView createPart(@ModelAttribute Parts part) {
        return new ModelAndView("partsForm");
    }

    @RequestMapping("editPart")
    public ModelAndView editPart(@RequestParam int id, @ModelAttribute Parts part) {
        part = partsService.getPart(id);
        return new ModelAndView("partsForm", "partsObject", part);
    }

    @RequestMapping("savePart")
    public ModelAndView savePart(@ModelAttribute Parts part) {
        if(part.getId() == 0){
            partsService.createPart(part);
        } else {
            partsService.updatePart(part);
        }
        //return new ModelAndView("redirect:/");
        List<Parts> partList = partsService.getAllParts();
        return new ModelAndView("partsList", "partsList", partList);
    }

    @RequestMapping("deletePart")
    public ModelAndView deletePart(@RequestParam int id) {
        partsService.deletePart(id);
        //return new ModelAndView("redirect:/");
        List<Parts> partList = partsService.getAllParts();
        return new ModelAndView("partsList", "partsList", partList);
    }

    @RequestMapping(value = {"getAllParts"})
    public ModelAndView getAllParts(){
        List<Parts> parts = partsService.getAllParts();
        return new ModelAndView("partsList", "partsList", parts);

    }


    @RequestMapping("filterPart")
    public ModelAndView searchPart(@RequestParam("filterName") String filterName){
        List<Parts> partsList = partsService.getAllParts(filterName);
        return new ModelAndView("partsList", "partsList", partsList);
    }
    @RequestMapping("sortNeedParts")
    public ModelAndView sortNeedPart(@RequestParam("sortNeedPart") int needPart){
        List<Parts> partsList = partsService.getAllParts(needPart);
        return new ModelAndView("partsList", "partsList", partsList);
    }


    @RequestMapping(value = "/")
    public ModelAndView listOfParts(@RequestParam(required = false) Integer page) {
        ModelAndView modelAndView = new ModelAndView("partsList");

        List<Parts> parts = partsService.getAllParts();
        PagedListHolder<Parts> pagedListHolder = new PagedListHolder<>(parts);
        pagedListHolder.setPageSize(MAX_ROWS_PER_PAGE);
        modelAndView.addObject("maxPages", pagedListHolder.getPageCount());

        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            page=1;
        }
        modelAndView.addObject("page", page);
        if(page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            modelAndView.addObject("partsList", pagedListHolder.getPageCount());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            modelAndView.addObject("partsList", pagedListHolder.getPageCount());
        }
        return modelAndView;
    }

}
