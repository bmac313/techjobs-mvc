package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "results", method = RequestMethod.GET)
    public String results(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("columns", ListController.columnChoices);
        ArrayList<HashMap<String, String>> searchResults;

        if (searchType.equals("all")) {
            searchResults = JobData.findByValue(searchTerm);
        } else {
            searchResults = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        model.addAttribute("searchResults", searchResults);

        return "search";
    }

}
