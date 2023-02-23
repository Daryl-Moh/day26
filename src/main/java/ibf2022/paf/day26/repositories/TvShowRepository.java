package ibf2022.paf.day26.repositories;

import java.util.Collections;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import static ibf2022.paf.day26.Constants.*;

@Repository
public class TvShowRepository {

    @Autowired
    private MongoTemplate template;

    /*
	    db.tvshows.find({
			type: { $regex: 'Animation', $options: 'i' }
		})
		.sort({ "rating.average": -1 })
		.projection({ _id: 0, id: 1, name: 1, "rating.average": 1 })
		.limit(20)
	*/

    public List<Document> getShowsByType(String type) {
        return getShowsByType(type, 20, 0);
    }

    public List<Document> getShowsByType(String type, int limit, int skip) {
        Criteria criteria = Criteria.where(FIELD_TYPE)
                .regex(type, "i");
        Query query = Query.query(criteria)
                .with(Sort.by(Direction.DESC, FIELD_RATING_AVERAGE))
                .limit(limit)
                .skip(skip);

        query.fields()
                .exclude(FIELD_UNDERSCORE_ID)
                .include(FIELD_ID, FIELD_NAME, FIELD_RATING_AVERAGE);

        return template.find(query, Document.class, COLLECTION_TV);

    }

    /*
     * db.tvshows.distinct("type")
     */
    public List<String> getTypes() {
        List<String> result = template.findDistinct(new Query(), FIELD_TYPE, COLLECTION_TV, String.class);
        Collections.sort(result);
        return result;
    }

    /*
     * db.tv.find({language: "English"}) <-- convert this from Mongo to Java
     */
    public List<Document> findTvShowsByLanguage(String lang) {

        // Create a criteria --> filter
        // { language: {$regex: "english", $options.i} }
        Criteria criterial = Criteria.where(FIELD_LANGUAGE).regex(lang, "i");

        // Creat a query
        Query query = Query.query(criterial);

        // Run the query
        // List<Document> results =
        return template.find(query, Document.class, COLLECTION_TV);

    }

}
