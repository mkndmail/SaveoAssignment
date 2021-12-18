package com.example.movieappbms.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Mukund Gururani, mkndmail@gmail.com on 12/18/2021
 */

/*
* {
  "id": 694919,
  "page": 1,
  "results": [
    {
      "author": "CyrusPK",
      "content": "Ambition is important in film making and it is always reassuring to see film-makers pushing as hard as they can against their budgets to produce something creative and dynamic.  Unfortunately those behind Money Plane found themselves somewhat defeated by lack of funds.\r\n\r\nTheir name actors including Kelsey Grammar, Denise Richards and Thomas Jane all look like they did a single day’s filming at individual locations.  Like a number of Bruce Willis’ recent films the leads have their scenes spread across the movie but over relatively few scenes and single locations to make their involvement appear more extensive than it actually was.  I would be very surprised if Denise Richards shot her role in more than a few hours.\r\n\r\nAn art gallery exterior is clearly some kind of poorly maintained transport depot and its interior (and several others in the film) are standard rooms dressed up with a few drapes to obscure unwanted details.  Much of the plot takes place inside a plane interior set which looks like one of those frequently rented interiors seen in many Hollywood lensed films and TV series of recent decades.  \r\n\r\nThat said the plot mechanics are reasonably well executed and there are some effective action sequences involving close-up fights in confined areas that bring to mind the editing style and effectiveness of the train compartment fight in From Russia With Love (1963).  \r\n\r\nA strain of very dark humour also runs through the film, with the outcome of a Russian roulette game, a graphic description of one of the things the gamblers can bet on and a misunderstanding between villain and henchman as to intent being especially smile-inducing.  \r\n\r\nMuch of the acting is adequate if not exceptional and Kelsey Grammar in particular devolves into a shouting, self-declamatory approach when it is not really needed.  Katrina Norman is especially good as one of the crew assembled to take down the Money Plane and is an actor I will look out for in the future.\r\n\r\nWith a brisk running time of 82 minutes this mostly overcomes its budget limitations to be a fun and darkly comic action adventure.  As an ambitious B-grade film I found it mostly very satisfying.",
      "id": "5f46f5c1efca000036bc235c",
      "url": "https://www.themoviedb.org/review/5f46f5c1efca000036bc235c"
    }
  ],
  "total_pages": 1,
  "total_results": 1
}
* */
@JsonClass(generateAdapter = true)

/*
* This is a data class that parses the movie review response to it's equivalent kotlin data class
* */

data class MovieReviewResponse(
    val id: Int, val results: List<ReviewResults?>?
)

@JsonClass(generateAdapter = true)
data class ReviewResults(val author: String, val content: String, val id: String, val url: String?)