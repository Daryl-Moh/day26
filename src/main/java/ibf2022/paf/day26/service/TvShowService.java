package ibf2022.paf.day26.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.paf.day26.models.TvShow;
import ibf2022.paf.day26.repositories.TvShowRepository;

@Service
public class TvShowService {
    
    @Autowired
    private TvShowRepository tvRepo;

    public List<TvShow> getShowsByType(String type){
        return tvRepo.getShowsByType(type)
            .stream()
            /*
			 .filter(v -> {
				 try {
					 TvShow.createSummary(v);
					 return true;
				 } catch (Exception ex) { return false; }
			 })
			 */
            .map(v -> TvShow.createSummary(v))
            .toList();
   
    }

    public List<String> getAllTypes() {
        return tvRepo.getTypes();
    }

    public List<TvShow> findAllTvShowsByLanguage(String lang){
        return tvRepo.findTvShowsByLanguage(lang) 
                .stream()
                .map(v -> TvShow.create(v))
                .toList();
                // Convert from document --> TvShow object --> List of TvShoww object
    }

    
}
