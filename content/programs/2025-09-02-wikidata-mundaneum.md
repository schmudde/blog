---
title: Wikidata and Mundaneum - The Triumph of the Commons
description: Using Clojure to query and classify the world's knowledge.
author: David Schmudde
author-email: d@schmud.de
author-url: http://schmud.de
author-github: schmudde
author-twitter: dschmudde
location: Udine, Italy
date-created: 2025-08-27
date-modified: 2025-09-03
date-published: 2025-09-02
in-language: en
keywords: wikidata
tags:
 - clojure
 - informatics
---

<figure class="fullwidth">
![](/img/2025-09-02-wikidata-mundaneum/otlet-mundaneum.webp)<figcaption><small>"[From the Mundaneum to the Internet](https://www.altaplana.be/en/dictionary/mundaneum-a-l-internet)." François Schuiten. Stamp: 38,15 mm x 48,75 mm. Block: 90 mm x 125 mm, January 16, 2010.
&nbsp;(<span property="license"><i class="far fa-copyright"></i>&nbsp;?</span>)</small></figcaption>
</figure>

[Wikidata](https://www.wikidata.org/) is an incredible source of data. The project is powered by open access principles; any human or machine can read and edit its knowledge base. This means that sites as large as Wikipedia or as small as your personal homepage can rely on this global repository of information.

A quick query in the search bar at [wikidata.org](https://www.wikidata.org/) will provide a sense of the knowledge base. But the tool's true power is exposed through its open [SPARQL](https://en.wikipedia.org/wiki/SPARQL) endpoint. A person can write a query like *[Who are the descendants of Johann Sebastian Bach?](https://query.wikidata.org/#SELECT%20%3Fdescendant%20%3FdescendantLabel%0AWHERE%0A%7B%0A%20%20wd%3AQ1339%20wdt%3AP40%2B%20%3Fdescendant.%0A%20%20SERVICE%20wikibase%3Alabel%20%7B%20bd%3AserviceParam%20wikibase%3Alanguage%20%22%5BAUTO_LANGUAGE%5D%22.%20%7D%0A%7D)* or *[What notable people died on August 8, 2001?](https://query.wikidata.org/#%23Humans%20who%20died%20on%20a%20specific%20date%20on%20the%20English%20Wikipedia%2C%20ordered%20by%20label%0ASELECT%20%3Fitem%20%3Farticlename%20%3FitemLabel%20%3FitemDescription%20%3Fsl%0AWHERE%20%7B%0A%20%20%20VALUES%20%3Fdod%20%7B%22%2B2001-08-25%22%5E%5Exsd%3AdateTime%7D%0A%20%20%20%20%3Fdod%20%5Ewdt%3AP570%20%3Fitem%20.%0A%20%20%20%20%3Fitem%20wikibase%3Asitelinks%20%3Fsl%20.%0A%20%20%20%20%3Fitem%20%5Eschema%3Aabout%20%3Farticle%20.%0A%20%20%20%20%3Farticle%20schema%3AisPartOf%20%3Chttps%3A%2F%2Fen.wikipedia.org%2F%3E%3B%0A%20%20%20%20schema%3Aname%20%3Farticlename%20.%0A%20%20SERVICE%20wikibase%3Alabel%0A%20%20%20%20%7B%0A%20%20%20%20%20%20bd%3AserviceParam%20wikibase%3Alanguage%20%22en%22%20.%0A%20%20%20%20%20%20%3Fitem%20rdfs%3Alabel%20%3FitemLabel%20.%0A%20%20%20%20%20%20%3Fitem%20schema%3Adescription%20%3FitemDescription%20.%0A%20%20%20%20%7D%0A%20%20BIND%28REPLACE%28%3FitemLabel%2C%20%22%5E.%2a%28%3F%3C%21%20%5BVv%5D%5Bao%5Dn%7C%20%5BDd%5D%5Baeiu%5D%7C%20%5BDd%5D%5Be%5D%5Blns%5D%7C%20%5BLl%5D%5Bae%5D%29%20%28%3F%21%28%5BSJ%5Dr%5C%5C.%3F%7C%5BXVI%5D%2B%29%24%29%22%2C%20%22%22%29%20AS%20%3Fsortname%29%20%20%0A%7D%20ORDER%20BY%20ASC%28UCASE%28%3Fsortname%29%29%20ASC%28UCASE%28%3FitemLabel%29%29)* and immediately receive  an answer from the Wikidata Query Service.

SPARQL is great but it's a little clumsy if you want to explore the knowledge base. This is the problem that Jack Rusher set out to solve when he created [Mundaneum](https://github.com/jackrusher/mundaneum).[^mundaneum] Mundaneum combines the interactive principles of Clojure programming with the power of the Wikidata Query Service.[^clerk]

[^mundaneum]: Jack provides this context for the library's name in the project's readme, "This is a thin Clojure wrapper around the [Wikidata](https://www.wikidata.org/wiki/Wikidata:Main_Page) project's massive semantic database. It's named after the [Mundaneum](https://en.wikipedia.org/wiki/Mundaneum), which was [Paul Otley](https://en.wikipedia.org/wiki/Paul_Otlet)'s mad and wonderful c. 1910 vision for something like the World Wide Web. (There's a mini-doc about him and it [here](https://www.youtube.com/watch?v=hSyfZkVgasI).)"

[^clerk]: This [Clerk notebook](https://github.com/jackrusher/mundaneum/blob/master/notebooks/basics.clj) by Jack offers a great introduction to this library as well.

This guide uses Mundaneum and Clojure to automatically classify businesses on the World Wide Web.[^getting-started] Give me the name of a business or a URL and if it is a website of some prominence, I'll give you a reasonable classification.

[^getting-started]: If you are new to SPARQL or Mundaneum, the [README](https://github.com/jackrusher/mundaneum) offers an entertaining raison d'être and the getting started.

The scope of this problem is tremendous. The internet hosts at least [1.5 billion websites](https://www.internetlivestats.com/total-number-of-websites/). Even identifying and categorizing a small fraction of these sites using Wikidata means sifting through nearly [100 million distinct items](https://www.wikidata.org/wiki/Special:Statistics).[^items] Those are some big numbers, so let's get started.[^yorba]

[^items]: McDowell, Zachary J., Matthew A. Vetter. &ldquo;[The Realienation of the Commons: Wikidata and the Ethics of 'Free' Data](https://ijoc.org/index.php/ijoc/article/view/20807).&rdquo; *International Journal of Communication*. Vol 18 (2024).


[^yorba]: ![](/img/about/yorba-logo-bg-black.png) [Yorba](https://yorba.co/) illustrates the practical application of this endeavor. We help our customers manage their relationship to organizations online; there are too many requests for our attention, our data, and our money. Meaningful and consistent names and categories for the relationships Yorba discovers are table stakes and Wikidata is an important tool in that effort.

# Categorizing Information

Most entities on Wikidata are assigned a `instance-of` property. Atari and <https://www.atari.com/> are two different ways of referring to the same video game company. We can say that Atari is an *instance of* a video game company.

    (require '[mundaneum.query :refer [query entity describe]])
    (require '[mundaneum.properties :refer [wdt]])
    (describe (entity "Atari"))

&rArr; `: brand name owned by Atari Interactive`

Roblox also makes video games.

    (describe (entity "Roblox Corporation"))

&rArr; `: American video game developer`

The `instance-of` properties tend to be quite granular, so Atari and Roblox aren't quite the same thing.

    (query `{:select-distinct [?categoryLabel]
             :where [[~(entity "Atari") ~(wdt :instance-of) ?category]]})

&rArr;

    | :categoryLabel | enterprise           |
    | :categoryLabel | brand                |
    | :categoryLabel | video game publisher |

Atari is a *video game publisher* while Roblox Corporation is deemed an instance of a *video game developer*.

## Traversing Classes

    (query `{:select-distinct [?categoryLabel]
             :where [[~(entity "Roblox Corporation") ~(wdt :instance-of) ?category]]})

&rArr;

    | :categoryLabel | business             |
    | :categoryLabel | video game developer |
    | :categoryLabel | public company       |

Both video game publisher and video game developer are subclasses of *video game company*. In fact, *video game company* has 6 subclasses.

    (query `{:select-distinct [?wikidataId ?wikidataIdLabel]
             :where [[?wikidataId (+ ~(wdt :subclass-of)) ~(entity "video game company")]]})

&rArr;

    | :wikidataId | :wd/Q210167    | :wikidataIdLabel | video game developer           |
    | :wikidataId | :wd/Q1137109   | :wikidataIdLabel | video game publisher           |
    | :wikidataId | :wd/Q16011789  | :wikidataIdLabel | indie games publisher          |
    | :wikidataId | :wd/Q100588475 | :wikidataIdLabel | video game translation company |
    | :wikidataId | :wd/Q106103510 | :wikidataIdLabel | mobile app developer           |
    | :wikidataId | :wd/Q106104678 | :wikidataIdLabel | mobile app publisher           |

`(+ ~(wdt :subclass-of))`, with the plus sign prefixed, will traverse *all* subclasses, even subclasses of subclasses. Switching the plus with an asterisk, `(* ~(wdt :subclass-of))`, returns 7 results because it includes the top level class itself, *video game company*. Running the query without any prefix, `(wdt :subclass-of)`, will returns only the 3 immediate subclasses of *video game company*.

## Populating a Category

This is everything needed to start a Video Games category. The query will `select` all items (`?wikidataId`, `?wikidataIdLabel`) on Wikidata that have a URL (the `?urlLabel` from the `?url`) `where` two conditions are met. The conditions are:

1.  Each item should be an instance of *video game company*.
2.  Each item must have an `official-website`. This is the URL.

&nbsp;

    (count
     (query `{:select-distinct [?wikidataId ?wikidataIdLabel ?urlLabel]
              :where [[?wikidataId ~(wdt :instance-of) ~(entity "video game company")]
                      [?wikidataId ~(wdt :official-website) ?url]]}))

&rArr; `0`

Oh no, zero results! That's because all the video game companies in Wikidata are actually an instance of one of the subclasses of the *video game company* property. Updating the command from `(wdt :instance-of)` to `(cat ~(wdt :instance-of) (* ~(wdt :subclass-of)))` will yield more results when the query is run again.

As described above, an asterisk before a path element returns "zero or more of this element." `(* ~(wdt :subclass-of))` will return the item itself and the subclass of `~(wdt :instance-of)`. And it will return any subclass of a subclass of an `~(wdt :instance-of)`. And it will return any subclass of a subclass of a subclass of an `~(wdt :instance-of)`. And so on until it exhausts all subclasses.[^documentation]

[^documentation]: This is called [Querying a Class Tree](https://www.wikidata.org/wiki/Wikidata:SPARQL_query_service/queries#Querying_a_class_tree). For more information about chaining property paths (indicated by the slash `/` in SPARQL), see [the Property Paths documentation](https://www.wikidata.org/wiki/Wikidata:SPARQL_tutorial#Property_paths).

&nbsp;

    (count
     (query `{:select-distinct [?wikidataId ?wikidataIdLabel ?urlLabel]
              :where [[?wikidataId (cat ~(wdt :instance-of) (* ~(wdt :subclass-of)))  ~(entity "video game company")]
                      [?wikidataId ~(wdt :official-website) ?url]]}))

&rArr; `4152`

Returning the first item reveals the structure of the items in the collection. The order is arbitrary.

    (first
     (query `{:select-distinct [?wikidataId ?wikidataIdLabel ?urlLabel]
              :where [[?wikidataId (cat ~(wdt :instance-of) (* ~(wdt :subclass-of)))  ~(entity "video game company")]
                      [?wikidataId ~(wdt :official-website) ?url]]}))

&rArr; `{:wikidataId :wd/Q600338, :wikidataIdLabel "Big Huge Games", :urlLabel "http://www.bighugegames.com/"}`

This query can be used for whatever categories you might want to support; everything from travel websites and technology companies to open source initiatives and [the digital commons](https://en.wikipedia.org/wiki/Digital_commons_%28economics%29) can be found on Wikidata. Rather than cutting and pasting the query for each new category, Clojure can make everything easier by turning the query into a reusable function.

But before we get there, a few improvements to the query will provide richer results and demonstrate a bit more about what's actually happening under the hood in Mundaneum.

# Enriching and Sorting Data
## Grab Company Logos

It would be nice to have a logo for each website when displaying the results, but it should not be a requirement. The following query adds the `?logoLabel` variable, the `logo-image` Wikidata property, and the `:optional` keyword. The keyword ensures that entities without a logo are not eliminated from the results.

    (->> (query `{:select-distinct [?wikidataId ?wikidataIdLabel ?urlLabel ?logoLabel]
                  :where [[?wikidataId (cat ~(wdt :instance-of) (* ~(wdt :subclass-of))) ~(entity "video game company")]
                          [?wikidataId ~(wdt :official-website) ?url]
                          [:optional [[?wikidataId ~(wdt :logo-image) ?logo]]]]})
         (sort-by :logoLabel)
         last)

&rArr; `{ :wikidataId :wd/Q205500, :wikidataIdLabel "Zipper Interactive", :urlLabel "http://www.zipperint.com", :logoLabel "http://commons.wikimedia.org/wiki/Special:FilePath/Zipper-logo.svg"}`[^zipper]

[^zipper]: Looks like the metadata attached to zipperint.com on Wikidata actually points to the archived version of the website on the Internet Archive: <https://web.archive.org/web/20120305024510/http://www.zipperint.com/>.

The `sort-by` Clojure code after the query sorts the results into two groups: those who have logos and those who do not. It then returns the last member of the second group. The result is no longer arbitrary. They are sorted alphabetically. However, this is a distinction without meaning. Let's explore a better way to sort.

## Use Ranking to Fine-Tune Results

Ordering the results by their popularity on Wikidata can be very useful. Mundaneum already does this when a person invokes the `entity` function. Jack Rusher's [canonical Mundaneum example](https://github.com/jackrusher/mundaneum) captures the magic nicely.

    (describe (entity "U2"))

&rArr; `: Irish rock band`

This result is correct. But U2 is also the name of a spy plane and a subway line in the city of Berlin. Mundaneum chose the Irish Rock Band because it has the largest number of statements associated to this specific Wikidata entry: [Q396](https://www.wikidata.org/wiki/Q396), which denotes the band "U2". Mundaneum provides idiosyncratic tools to differentiate the various U2 entities.

    (describe (entity "U2" (wdt :transport-network) (entity "Berlin U-Bahn")))

&rArr; `: Underground line in Berlin`

Ordering the category results by relevancy can provide various benefits. Here's a query that finds every service with a website that is an instance/subclass of *video game company*. The query will `count` each `?predicate` associated with every `?wikidataId` item, associate an `:optional` logo, and then `:order-by` the number of `?predicates` - most to least.[^count]

[^count]: The official documentation [recommends using `count` with a wildcard](https://www.wikidata.org/wiki/Wikidata:SPARQL_query_service/query_optimization/ru#Use_COUNT(*)_when_possible), rather than an explicit declaration of `?predicate` (i.e. `(count * :as ?count)` vs. `(count ?predicate :as ?count)`). It should result in better performance.

In other words, count the number of assertions made about every video game company that has a website.

    (query `{:select-distinct [?wikidataId ?wikidataIdLabel ?urlLabel ?logoLabel [(count ?predicate) ?count]]
             :where [[?wikidataId ?predicate ?object]
                     [?wikidataId (cat ~(wdt :instance-of) (* ~(wdt :subclass-of))) ~(entity "video game company")]
                     [?wikidataId ~(wdt :official-website) ?url]
                     [:optional [[?wikidataId ~(wdt :logo-image) ?logo]]]]
             :group-by [?wikidataId ?wikidataIdLabel ?urlLabel ?logoLabel]
             :order-by [(desc ?count)]})

&rArr;

    {:wikidataId :wd/Q8093,
      :wikidataIdLabel "Nintendo",
      ...
      :count 336}
     {:wikidataId :wd/Q173941,
      :wikidataIdLabel "Electronic Arts",
      ...
      :count 279}

[Nintendo](https://www.nintendo.com/us/regionselector/) ends up being the most significant item at the time of this writing, followed by [Electronic Arts](https://www.ea.com).[^logos]

[^logos]: This approach is really a measurement of "editor attention" and not necessarily correlative with actual cultural prominence. ![](http://commons.wikimedia.org/wiki/Special:FilePath/Nintendo.svg)<br />![](http://commons.wikimedia.org/wiki/Special:FilePath/Electronic%20Arts%202020.svg)<small>Corporate logos stored on Wikidata&nbsp;(<span property="license"><i class="far fa-copyright"></i>&nbsp;Respective copyright holders</span>)</small>

Counting all the assertions associated with an entity is tricky. `[?wikidataId ?predicate ?object]` sets the stage. It says to query the universe of all subject/predicate/objects. The next line, `[?wikidataId (cat ~(wdt :instance-of) (* ~(wdt :subclass-of))) ~(entity "video game company")]`, narrows the query to all items which are an instance of *video game company* or one of its subclasses.

Starting with the `[?wikidataId ?predicate ?object]` query is essential for counting. If it was missing, then the `count` would only have access to the universe of predicates related to *video game company* or one of its subclasses. This is much smaller than the universe of all predicates used to make assertions.

Let's look at counting query results in another way. Wikidata asserts that the video game developer Sega is an instance of four different things:

    (let [sega (entity "Sega" (wdt :instance-of) (entity "video game developer"))]
        (query `{:select [?object ?objectLabel]
                 :where [[~sega ~(wdt :instance-of) ?object]]}))

&rArr;

    | :object | :wd/Q210167  | :objectLabel | video game developer |
    | :object | :wd/Q1137109 | :objectLabel | video game publisher |
    | :object | :wd/Q4830453 | :objectLabel | business             |
    | :object | :wd/Q6881511 | :objectLabel | enterprise           |

So this is one type of assertion, the `(wdt :instance-of)` predicate, used to make 4 **statements**. There are 691 statements (e.g. total [triples](https://en.wikipedia.org/wiki/Semantic_triple)) related to "Sega," the video game developer.[^family-name] Each of these have some kind of unique combination of predicate and object associated with the subject (which is Sega).

[^family-name]: `(entity "Sega" (wdt :instance-of) (entity "family name"))` is "more popular" with > 1,000 associated statements across all languages but only 28 distinct predicates (i.e. `:distinct? true`).

&nbsp;

    (let [sega (entity "Sega" (wdt :instance-of) (entity "video game developer"))]
      (query `{:select-distinct [[(count ?predicate) ?count]]
               :where [[~sega ?predicate ?object]]}))

&rArr; `:count 691`

Diving deeper, we can see that Sega has 267 unique properties.

    (let [sega (entity "Sega" (wdt :instance-of) (entity "video game developer"))]
      (query `{:select [[(count ?predicate :distinct? true) ?count]]
               :where [[~sega ?predicate ?object]]}))

&rArr; `:count 267`

So we can see that the ranking query must start with `[?wikidataId ?predicate ?object]` to include the possibility of all plausible combinations of subject, predicate, object for ranking.

Rank-by-count is an imperfect system, but it can be useful to sort out the top two or three synonymous item names in larger categories. For example, Apple is a *[business](https://www.wikidata.org/wiki/Q4830453)*. While I know I don't mean the fruit, "Apple" could indicate a business that manufactures computers or the business that makes records because *[record label](https://www.wikidata.org/wiki/Q18127)* is a subclass of *business*.

Odds are that most people are talking about the computer manufacturer when they are talking about a business named Apple. Ranking codifies this probability.[^record-label]

[^record-label]: Consider a myriad of other businesses named Apple, including one notable record label. ![](/img/2025-09-02-wikidata-mundaneum/Beatles-first-singles-1000x600.jpg)<small>*[Hey Jude](https://www.udiscovermusic.com/stories/d-day-for-apple-records/)*. The Beatles (August 26, 1968)
&nbsp;(<span property="license"><i class="far fa-copyright"></i> Apple Records</span>)</small>

# Reusable Functions
## Querying Classes

The next step is to turn this complete query into a reusable function. Mundaneum makes this easy.

    (defn ^:private query-class-and-rank
      " In: valid wikidata ID (Q####...)
       Out: collection of entities which are instance-of of x or any subclass of x
            They are ranked by the total number of predicates associated with the item.
            Includes logos where available."
      [x]
      (query
       `{:select-distinct [?wikidataId ?wikidataIdLabel ?urlLabel ?logoLabel [(count ?predicate) ?count]]
         :where [[?wikidataId ?predicate ?object]
                 [?wikidataId (cat ~(wdt :instance-of) (* ~(wdt :subclass-of))) ~x]
                 [?wikidataId ~(wdt :official-website) ?url]
                 [:optional [[?wikidataId ~(wdt :logo-image) ?logo]]]]
         :group-by [?wikidataId ?wikidataIdLabel ?urlLabel ?logoLabel]
         :order-by [(desc ?count)]}))

What's the top ranked *package delivery* service on Wikidata?

    (-> (entity "package delivery")
        query-class-and-rank
        first)
&rArr; `{:wikidataId :wd/Q459477, :wikidataIdLabel "FedEx", :urlLabel "https://www.fedex.com/", :logoLabel "http://commons.wikimedia.org/wiki/Special:FilePath/FedEx%20Corporation%20-%202016%20Logo.svg", :count 437}`

## Combining Classes

It's not only trivial to query other classes, it's also easy to combine different classes. For example, "Playstation" is not the name of a video game company, it is a video game console. Video game consoles are not a subclass of abstract companies, they are a subclass of other physical entities like electronic toys and manufactured goods.

Therefore our video game class might need to include several different verticals to capture everything in the domain. Making and combining lists of all these entities is idiomatic to Clojure.[^lazy-eager]

    (def video-games
      (map (comp query-class-and-rank entity)
           ["video game company" "video game distribution platform" "video game console"]))

[^lazy-eager]: The examples are evaluated lazily. YMMV. To avoid SPARQL timeouts it may be better to force evaluation.

&nbsp;

    (ffirst video-games)

&rArr; `{:wikidataId :wd/Q122741, :wikidataIdLabel "Sega", :urlLabel "https://www.sega.com", :logoLabel "http://commons.wikimedia.org/wiki/Special:FilePath/SEGA%20logo%20JPN.svg", :count 1382}`

    (second (first video-games))

&rArr; `{:wikidataId :wd/Q188273, :wikidataIdLabel "Ubisoft", :urlLabel "https://www.ubisoft.com/", :logoLabel "http://commons.wikimedia.org/wiki/Special:FilePath/Ubisoft%20logo.svg", :count 1286}`

# Searching Categories

The final piece of the puzzle is a way to search the categories.

    (defn search-coll
      "In: search-string: the string to search for
           search-keyword: the keyword attribute to search - is it a :wikidataIdLabel, a :urlLabel, etc...?
           search-domain-coll: the search space
      Out: nil or a collection of matches"
      [search-string search-keyword search-domain-coll]
      (when search-string
        (let [conform #(when % (clojure.string/lower-case %))
              matches (keep #(when (= (conform (search-keyword %))
                                      (conform search-string))
                               %)
                            search-domain-coll)]
          (when (seq matches) matches))))

A quick sanity check: is Roblox in the Video Games collection?

    (search-coll "Roblox" :wikidataIdLabel video-games)

&rArr; `{:wikidataId :wd/Q692989, :wikidataIdLabel "Roblox", :urlLabel "https://www.roblox.com/", :logoLabel "http://commons.wikimedia.org/wiki/Special:FilePath/Roblox%20Logo%202022.svg", :count 592}`

Lookup by URL is possible.

    (search-coll "https://itch.io/" :urlLabel video-games)

&rArr; `{:wikidataId :wd/Q22905933, :wikidataIdLabel "itch.io", :urlLabel "https://itch.io/", :logoLabel "http://commons.wikimedia.org/wiki/Special:FilePath/Itch.io%20logo.svg", :count 207}`

# On Names

It should be obvious that company names and URLs vary immensely. "Roblox", "Roblox Corporation", "<http://www.roblox.com>", and "<https://roblox.com>" might all be synonymous to us humans but a machine will not be so forgiving. Wikidata does offer `alt` labels[^alt] which have some Natural Language Processing capabilities. But that's beyond the scope of this already lengthy blog post.

[^alt]: `:skos/altLabel` ([Simple Knowledge Organization System](https://www.w3.org/TR/skos-primer/)) is an alternative lexical label for a resource - synonyms, abbreviations, or variant spellings. The canonical human-readable name of an entity is exposed as `rdfs:label`, defined by [RDF Schema](http://www.w3.org/2000/01/rdf-schema), while `skos:altLabel` supplements it with additional terms. Both are [RDF properties](http://www.w3.org/RDF/), and together they support multilingual and flexible matching.

The most robust solution would classify by domain name. Domain names offer the most consistent, globally unique identifier. Generating quality domain names from URLs is beyond the scope of this article, but I offer a complete tutorial in *[Turning URLs Into Meaningful Names Using Clojure](https://schmud.de/programs/2022-04-25-urls-into-meaningful-names.html)*. The addition of a `:domain` keyword and other meaningful top-level categories like Travel, Finance, Education, Shopping, and Health would lay the foundation for a robust website classification service.

Mundaneum's emphasis on exploration does come with some drawbacks. Wikidata's human-readable labeling service, invoked above when I looked up the `:wikidataIdLabel` and `:urlLabel` results, is an expensive operation. It's necessary for natural-language exploration that Mundaneum offers, but it means that many large queries aren't possible using this library.[^label-service].

[^label-service]: The label service is invoked in a SPARQL query with the following command: `SERVICE wikibase:label { bd:serviceParam wikibase:language "[AUTO_LANGUAGE],en". }`

Wikidata is an unheralded marvel of open software and open data. Mundaneum offers a way of exploring the knowledge base using interactive Clojure code. When you dive into it, you'll wish that more systems worked this way.
