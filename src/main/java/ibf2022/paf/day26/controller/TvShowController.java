package ibf2022.paf.day26.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.paf.day26.models.TvShow;
import ibf2022.paf.day26.service.TvShowService;

@Controller
@RequestMapping(path="/tvshow")
public class TvShowController {
    
    @Autowired
    private TvShowService tvShowSvc;

    // GET /tvshow/type/{type}
    @GetMapping(path="/type/{type}")
    public String getTvShowByType(@PathVariable String type, Model model){
        List<TvShow> results = tvShowSvc.getShowsByType(type);
        model.addAttribute("tvshows", results);
        model.addAttribute("showTypes", type);
        return "tvshowlisting";
    }


    // GET /tvshow?lang=English
    @GetMapping(path="/tvshows")
    public String getTvShow(@RequestParam(defaultValue = "English") String lang, Model model){

        List<TvShow> results = tvShowSvc.findAllTvShowsByLanguage(lang);
        model.addAttribute("tvshows", results);
        model.addAttribute("language", lang);

        return "tvshows";
    }

    
}
