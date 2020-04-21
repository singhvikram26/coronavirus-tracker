package com.example.coronavirustracker.controllers;

import com.example.coronavirustracker.models.LocationStats;
import com.example.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model)
    {
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalCases = 0;
        for(int i=0;i<allStats.size();i++){
            totalCases += allStats.get(i).getLatestTotalCases();
        }
        int totalNewCase = 0;
        for(int i=0;i<allStats.size();i++){
            totalNewCase += allStats.get(i).getDiffFromPrevDay();
        }
        model.addAttribute("locationStats", coronaVirusDataService.getAllStats());
        model.addAttribute("totalReportedCases","+"+ totalCases);
        model.addAttribute("totalNewCase","+"+ totalNewCase);

        return "home";
    }
}
