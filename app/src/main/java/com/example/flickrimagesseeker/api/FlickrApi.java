package com.example.flickrimagesseeker.api;

import com.example.flickrimagesseeker.api.objects.ImageSearchResult;


import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrApi {

    String API_BASE_URL = "https://www.flickr.com/services/";
    String REST_METHOD = "rest/?method=flickr.photos.";
    String SEARCH = "search", INFO = "getInfo";

    String qAPI_KEY = "api_key", qTEXT = "text", qPAGE = "page", qPER_PAGE = "per_page", qFORMAT = "format",
            qNOJSONCALLBACK = "nojsoncallback", qPHOTO_ID = "photo_id";

    @GET(REST_METHOD + SEARCH)
    Single<ImageSearchResult> searchPhotos(@Query(qAPI_KEY) String apiKey,
                                           @Query(qTEXT) String text,
                                           @Query(qPAGE) int page,
                                           @Query(qPER_PAGE) int perPage
                                         ); // @Query(qNOJSONCALLBACK) String nojsoncallback

    @GET(REST_METHOD + INFO)
    Single<ImageSearchResult> getPhotoInfo(@Query(qAPI_KEY) String apiKey, @Query(qPHOTO_ID) String photoId,
                                                @Query(qFORMAT) String format, @Query(qNOJSONCALLBACK) String nojsoncallback);

}
