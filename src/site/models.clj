(ns site.models)

;; IEEE Spectrum talk

(comment
  (vals projects)
  (get-in projects [:jack-and-the-machine :title])
  )

(def exhibitions
   [{:title "Sound And Vision International Film &amp; Technology Festival"
     :place "Elizabethport, NJ, 2018"
     :piece "The Rhythm of Time"
     }
    {:title "NewFilmmakers"
     :place "New York City, 2018"
     :piece "The Rhythm of Time"
     }
    {:title "The Art of Brooklyn Film Festival"
     :place "New York City, 2018"
     :piece "The Rhythm of Time"
     }
    {:title "Measure, Model, Mix: Computer as Instrument"
     :place "SIGCIS Conference, Philadelphia, 2017"
     :piece "Jack and the Machine"
     }
    {:title "Bushwick Film Festival"
     :place "New York City, 2017"
     :piece "Distant Apologies"
     }
    {:title "Blow-Up Arthouse International Film Festival"
     :place "Chicago, 2017"
     :piece "Distant Apologies"
     }
    {:title "Blow-Up Arthouse International Film Festival"
     :place "Chicago, 2017"
     :piece "The Rhythm of Time"
     }
    {:title "YoFiFest Film Festival"
     :place "Yonkers, New York, 2017"
     :piece "Distant Apologies"
    }
    {:title "Holocenter: Center for the Holographic Arts"
     :place "Installation, New York City, 2017"
     :piece "Borderless IV"
     }
    {:title "Average Art Magazine"
     :place "Published August 2017"
     :piece "Borderless IV"
    }
    {:title "Hack && Tell"
     :place "Presentation, New York City, 2016"
     :piece "The Rhythm of Time"
    }
    {:title "Third Shift @ Third Space Gallery"
     :place "Installation, Saint John, N.B., Canada, 2016"
     :piece "Borderless IV"
    }
    {:title "FUSE @ Livestream Public"
     :place "Performance, New York City, 2015"
     :piece "Borderless"
    }
    {:title "Music Hall of Williamsburg"
     :place "Performance, New York City, 2015"
     :piece "Borderless"
     :highlight "strong"
    }
    {:title "ArtStars* Bits & Beats"
     :place "Berlin 2015"
     :piece "Jack and the Machine"
    }
    {:title "310 XNRD"
     :place "2 Day Workshop & Installation, London, 2015"
     :piece "Borderless"
    }
    {:title "Schusev State Museum of Architecture Now&After Festival"
     :place "Installation, Moscow, 2015"
     :piece "Borderless"
     :highlight "strong"
    }
    {:title "IndieWorks"
     :place "New York City, 2015"
     :piece "The Assassination of Chicago's Mayor"
    }
    {:title "Fonlad International Exhibition of Video Art"
     :place "Cologne, Germany, 2014"
     :piece "Borderless"
    }
    {:title "IFP Made in NY Media Center"
     :place "Installation, New York City, 2014"
     :piece "Borderless"
    }
    {:title "Red Hook Film Festival"
     :place "New York City, 2014"
     :piece "The Assassination of Chicago’s Mayor"
    }
    {:title "ShortsHD Channel - Dish Network, DirecTV, AT&T"
     :place "Broadcast, 2014"
     :piece "The Assassination of Chicago’s Mayor"
     :highlight "strong"
    }
    {:title "Filmstock Film Festival"
     :place "Utah, 2013"
     :piece "A Simple Reminder"
    }
    {:title "Hollyshorts"
     :place "New York City, 2013"
     :piece "The Assassination of Chicago's Mayor"
     :highlight "strong"
    }
    {:title "Filmstock Film Festival"
     :place "New Mexico, 2013"
     :piece "Refuge"
    }
    {:title "IndieFlix"
     :place "Internet Distribution, 2014"
     :piece "The Assassination of Chicago's Mayor"
     :highlight "strong"
    }
    {:title "Beneath the Earth Film Festival"
     :place "Internet Distribution, 2012"
     :piece "Refuge"
    }
    {:title "Filmstock Film Festival"
     :place "Arizona, 2012"
     :piece "The Assassination of Chicago's Mayor"
    }
    {:title "Filmstock Film Festival"
     :place "Arizona, 2012"
     :piece "Refuge"
    }
    {:title "Aesthetica Film Festival"
     :place "York, UK, 2013"
     :piece "The Assassination of Chicago's Mayor"
     :highlight "strong"
    }
    {:title "Hollyshorts"
     :place "Los Angeles, 2012"
     :piece "The Assassination of Chicago's Mayor"
    }
    {:title "MoFest"
     :place "Chicago, 2012"
     :piece "Elements • Spring to Summer to Autumn 2011"
    }
    {:title "Chicago Filmmakers Short Story Showcase"
     :place "Chicago, 2012"
     :piece "Elements • Spring to Summer to Autumn 2011"
    }
    {:title "Moving Pictures Film Festival"
     :place "Quincy, Illinois, 2012"
     :piece "Refuge"
    }
    {:title "ShortsHD Channel - Dish Network, DirecTV, AT&T"
     :place "Broadcast, 2011"
     :piece "Refuge"
     :highlight "strong"
    }
    {:title "Chicago Filmmakers Short Story Film Showcase"
     :place "Chicago, 2011"
     :piece "Refuge"
    }
    {:title "Chicago International Film Festival"
     :place "Chicago, 2010"
     :piece "Refuge"
     :highlight "strong"
    }
    {:title "The New Art Film Festival"
     :place "Champaign-Urbana, Illinois, 2010"
     :piece "I am Concrete"
    }
    {:title "Chicago Filmmakers"
     :place "Chicago, 2008"
     :piece "Aijo"
    }
    {:title "Georgetown Film Fest"
     :place "Washington DC, 2006"
     :piece "Aijo"
    }
    {:title "S.N.O.B Film Festival"
     :place "Boston, 2006"
     :piece "Aijo"
    }
    {:title "River’s Edge International Film Festival"
     :place "Paducah, Kentucky, 2005"
     :piece "Year of the Rooster"
    }
    {:title "Block Museum"
     :place "Evanston, Illinois, 11/2003"
     :piece "Lucid Dream Ensemble"
    }
    {:title "Block Museum"
     :place "Evanston, Illinois, 4/2003"
     :piece "Lucid Dream Ensemble"
    }
    {:title "Chicago Museum of Contemporary Art Version Festival"
     :place "Chicago 2003"
     :piece "Lucid Dream Ensemble"
     :highlight "strong"
    }
    {:title "SEAMUS National Conference"
     :place "Tempe, Arizona, 2003"
     :piece "Lucid Dream Ensemble"
     }])

(def awards
  [{:title "2018 Researcher in Residence"
    :type "Residency"
    :org "Signal Culture"
    :project :borderless
    }
   {:title "2017 Space:Light Artist Residency"
    :type "Residency"
    :org "Holocenter: Center for the Holographic Arts"
    :project :borderless
    }
   {:title "Berlin/New York 2015 Media Residency Program"
    :type "New Media Production Grant & Residency"
    :org "Startup Germany, Medienboard"
    :highlight true
    :project :jack-and-the-machine
    }
   {:title "Interactive Co/Lab 2016, Detroit, MI"
    :type "Competitive Selection"
    :org "Tribeca Film Institute"
    }
   {:title "POV Hackathon 9, Chicago, IL"
    :type "Competitive Selection"
    :org "Public Broadcasting Service (PBS)"
    :highlight true
    }
   {:title "POV Hackathon 7, New York, NY"
    :type "Competitive Selection"
    :org "Public Broadcasting Service (PBS)"
    }
   {:title "Audience Choice Award, Best Director: &ldquo;The Assassination of Chicago&#39;s Mayor&rdquo;"
    :type "Film Festival Award"
    :org "Red Hook Film Festival 2014"
    :highlight true
    :project :assassination-of-chicagos-mayor
    }
   {:title "Audience Choice Award: &ldquo;A Simple Reminder&rdquo;"
    :type "Film Festival Award"
    :org "Digipops Community Film Festival 2014"
    :project :simple-reminder
    }
   {:title "Tribeca Hacks 2013, New York, NY"
    :type "Competitive Selection"
    :org "Tribeca Film Institute"
    }
   {:title "Best Director, Best of Fest: &ldquo;Refuge&rdquo;"
    :type "Film Festival Award"
    :org "Filmstock Film Festival 2012"
    :highlight true
    :project :refuge
    }
   {:title "Best Actor: &ldquo;The Assassination of Chicago&#39;s Mayor&rdquo;"
    :type "Film Festival Award"
    :org "Filmstock Film Festival 2012"
    :project :assassination-of-chicagos-mayor
    }
   {:title "Best Editing, Best Acting: &ldquo;Refuge&rdquo;"
    :type "Film Festival Award"
    :org "Beneath the Earth Film Festival 2012"
    :highlight true
    :project :refuge
    }
   {:title "Educator of the Year Nomination"
    :type "Professional Distinction"
    :org "International Academy of Design and Technology 2005"
    }
   {:title "Program for the Study of the Imagination 2003"
    :type "Composition Grant"
    :org "Northwestern University"
    }
   {:title "Interactive Award - The One Show 1998"
    :type "Competitive Showcase (Bronze Placement)"
    :org "One Club"
    }])

(def affiliations
  [{:org "Kitchen Table Coders: A Studio for Creative Coding"
    :title "Member"}
   {:org "The FilmShop: A New York Collective of Independent Filmmakers"
    :title "Member"}
   {:org "Bushwick Film Festival 2013 &amp; 2014"
    :title "New Media Advisor"}
   {:org "Himalaya Project: Education and Healthcare in Dolpo, Nepal"
    :title "Volunteer Media Director and Producer"}
   {:org "&lt;StoryCode&gt;: An Immersive Media Meetup"
    :title "Videography Volunteer"}
   {:org "The Script Lab"
    :title "Screenwriting Analysis for &ldquo;From Script to Screen&rdquo;"}])

(def education
  [{:title "Northwestern University"
    :subtitle "Masters in Music (Magna Cum Laude)"
    :desc "Electroacoustic Performance, Composition, Intermedia Studies"
    :date "2002 - 2004"}
   {:title "University of Northern Iowa"
    :subtitle "Bachelor of Arts in Computer Science"
    :desc "Artificial Intelligence, Creative Computing and the Visual Arts"
    :date "1997 - 2001"}])

(def training
  {:title "School of Machines, Making &amp; Make-Believe"
   :subtitle "Dataism: a class on data science in activism"
   :date "Spring 2020"
   }
  {:title "DID Deutsch-Institut &amp; Sprachsalon, Berlin, Germany"
   :subtitle "German Language Training"
   :date "2005, 2019"
   })


(def publications
  [{:title "Computing For the Masses (chapter from &lsquo;Prophets Of Computing&rsquo;)"
    :publication "Association for Computing Machinery"
    :date "2021"}
   {:title "Chapter on Software Preservation"
    :publication "PROGRAMme: Research Into the History and Philosophy of Computing"
    :date "in progress"}
   {:title "Beyond the Frame"
    :publication "My independent blog with tens of thousands of readers"
    :link "https://schmud.de/"
    ;; :link "http://beyondthefra-me.tumblr.com, 57,000 Subscribers"
    :date "ongoing"}
   {:title "The Shape of Intuition"
    :publication "Nextjournal"
    :link "https://nextjournal.com/schmudde/the-shape-of-intuition"
    :date "December 16, 2019"}
   {:title "How to Version Control Jupyter Notebooks"
    :publication "Nextjournal"
    :link "https://news.ycombinator.com/item?id=18740197"
    :date "December 11, 2018"}
   {:title "Our Lives, Encoded"
    :publication "Broken Toilets"
    ;; shared by <a href='http://epodharvard.tumblr.com/post/146951238165/five-favorites-7516' target='_blank'>Evidence for Policy Design at Harvard University</a>
    :link "http://brokentoilets.org/article/year-twenty-megabytes/"
    :date "May 20, 2016"}
   {:title "The Computer Revolution Has Yet to Happen"
    :publication "Endless Magazine/The Absurdist"
    :link "https://medium.com/absurdist/the-computer-revolution-has-yet-to-happen-f1dbf983d477"
    :date "November 24, 2015"}
   {:title "A Brief History of Machine Personalities"
    :subtitle "The Character &amp; Ephemera From a Century of Machine Thinking"
    :publication "Self-Published"
    :link "https://medium.com/@dschmudde/a-brief-history-of-machine-personalities-f7116c85c2ed"
    :date "May 15, 2015"}
   {:title "From Script to Screen: &quot;Five Easy Pieces&quot; - Bobby &amp; Rayette"
    :publication "The Script Lab"
    :link "http://thescriptlab.com/screenwriting-101/screenplay/from-script-to-screen/1228-five-easy-pieces-1970-bobby-a-rayette"
    :date "July 27, 2011"}
   {:title "From Script to Screen: &quot;The Matrix&quot; - Lobby Scene"
    :publication "The Script Lab"
    :link "http://thescriptlab.com/screenwriting-101/screenplay/from-script-to-screen/1231-the-matrix-1999-lobby-scene"
    :date "August 9, 2011"}])

(def projects
  {:jack-and-the-machine
   {:title "Jack and the Machine"
    :synopsis "Once known as #148445 within the Third Reich, Jack Tramiel rose from the Nazi concentration camps and built the information age's great equalizer: the world's first 'computer for the masses, not the classes.'"
    :status "In Production"
    :link "http://www.jackandthemachine.com"
    :tags ["documentary" "interactive"]
    :video "https://player.vimeo.com/video/74458487"
    :technology "Clojure, PHP, JavaScript"
    :desc "Interactive documentary based on the life and times of Jack Tramiel, the founder of Commodore Business Machines."}
   :borderless
   {:title "Borderless"
    :synopsis "&ldquo;Borderless&rdquo; is an exploration of unconscious interactions among strangers. The piece examines the way we shape and are shaped by our surroundings, creating invisible movement within society's social fabric. &ldquo;Borderless&rdquo; tests the idea that by recreating unconscious interactions within a tactile and visible medium, we can unveil nuances in social behavior and response. A collaboration with artist Kim Burgas."
    :status "Performance Premier: Music Hall of Williamsburg 2015 (USA); Exhibition Premier: Schusev State Museum of Architecture Now&After Festival 2015 (World)"
    :link "http://www.beyondthefra.me/borderless.html"
    :tags ["installation"]
    :video "https://player.vimeo.com/video/119274499"
    :technology "Clojure, Overtone, Kinect, TSPS, Blackmagic Cinema Camera, portable projection"
    :desc "Dance installation that consists of a video projection accompanied by soundscapes generated in real-time using data acquired from a Kinect."}
   :rhythm-of-time
   {:title "The Rhythm of Time"
    :synopsis "&ldquo;The Rhythm of Time&rdquo; weaves past and future in musical counterpoint as it explores the life and times of an enigmatic instrumentalist hailing from an unexpected place."
    :status "New Release"
    :link "http://rhythmoftime.xyz"
    :tags ["documentary" "interactive"]
    :image "rhythm-16x9.jpg"
    :technology "ClojureScript, FigWheel, Quil, JavaScript, Canon 7D, Final Cut X, Logic X"
    :desc "Interactive documentary on the life and times of enigmatic synthesist Suit & Tie Guy."}
   :distant-apologies
   {:title "Distant Apologies"
    :synopsis "The closer you are, the more difficult it is to say, &quot;I'm sorry.&quot;"
    :status "New Release"
    :link "http://schmud.de/distant"
    :tags ["narrative" "interactive"]
    :video "https://player.vimeo.com/video/160771838"
    :technology "Clojure, Leiningen, Tomcat, Twitter API, Super 8 Kodak Film, Final Cut X, Logic X"
    :desc "Short film mixes rich NASA archival, original Super 8 footage, and a stream of recent apologies scraped from Twitter."}
   :assassination-of-chicagos-mayor
   {:title "The Assassination of Chicago's Mayor"
    :synopsis "A 19th-century newspaper peddler named Patrick Prendergast has delusions of greatness. Evidently slighted by the mayor, this self-anointed messenger prepares for a glorious rise from ignored lobbyist to influential icon. Prendergast sees himself as a martyr, serving God with a noble purpose. The reality is far different."
    :status "Festival Premier: Hollyshorts 2012 (USA), Aesthetica Film Festival 2012 (World); Broadcast Premier: ShortsHD Channel - Dish Network, DirecTV, AT&T; Internet: IndieFlix"
    :link "https://indieflix.com/indie-films/the-assassination-of-chicagos-mayor-34358/"
    :tags ["narrative"]
    :video "https://player.vimeo.com/video/30803843"
    :desc "A police interrogation reenactment of a Chicago Tribune account that uses archival letters written by Patrick Prendergast himself"}
   :simple-reminder
   {:title "A Simple Reminder"
    :synopsis "The essence of memory - a possessor, a liberator."
    :status "Festival Premier: Filmstock Film Festival 2013"
    :tags ["narrative"]
    :video "https://player.vimeo.com/video/46622645"}
   :invisible-majority
   {:title "The Invisible Majority"
    :synopsis "Every single day, people volunteer, search for cures, and engineer leaps in efficiency. Unfortunately, media is often usurped by fearmongers and fundamentalists who spread false conceptions as facts. It is up to us, the majority of us who do good day-in and day-out, to guide the larger narrative."
    :status "Online"
    :tags ["documentary"]
    :video "https://player.vimeo.com/video/52299617"}
   :refuge
   {:title "Refuge"
    :synopsis "Grant is trapped with his conscious on the long, lonely drive across the American Midwest. As the endless road stretches on, he is confronted by those he has loved and those he has hurt. &ldquo;Refuge&rdquo; is a metaphysical journey to truth, quickly turning the open road into a claustrophobic asylum of haunting self-examination."
    :status "Festival Premier: Chicago International Film Festival 2010; Broadcast Premier: ShortsHD Channel - Dish Network, DirecTV, AT&T"
    :tags ["narrative"]
    :video "https://player.vimeo.com/video/8247688"}
   :music-retrospective
   {:title "Schmudde: A Retrospective"
    :synopsis "A Selection of Music Written Between 1998 and 2012."
    :status "Online"
    :link "https://soundcloud.com/schmudde/sets/schmudde-a-retrospective"
    :tags ["sound"]
    :sound "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/playlists/23618316&amp;auto_play=false&amp;hide_related=false&amp;show_comments=true&amp;show_user=true&amp;show_reposts=false&amp;visual=true"}
   :film-music-retrospective
   {:title "Schmudde: Film Music"
    :synopsis "Music for Films"
    :status "Online"
    :link "https://soundcloud.com/schmudde/sets/film-scores"
    :tags ["sound"]
    :sound "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/playlists/144853848&amp;auto_play=false&amp;hide_related=false&amp;show_comments=true&amp;show_user=true&amp;show_reposts=false&amp;visual=true"}
   :elements-spring-summer-autumn-2011
   {:title "Elements: Spring to Summer to Autumn 2011"
    :synopsis "Human-kind is essential. Without us, who would admire the flower? Who would discover new worlds and pay tribute to old ones?"
    :status "Festival Premier: Chicago Filmmakers Short Story Showcase 2011"
    :tags ["documentary"]
    :video "https://player.vimeo.com/video/34661968"}
   :elements-winter-2011
   {:title "Elements: Winter 2011"
    :synopsis "The four elements in winter."
    :status "Online"
    :tags ["documentary"]
    :video "https://player.vimeo.com/video/21351915"}
   :elements-fall-2010
   {:title "Elements: Fall 2010"
    :synopsis "The beginning of fall as told through the elements."
    :status "Online"
    :tags ["documentary"]
    :video "https://player.vimeo.com/video/15209878"}
   :lucid-dream-ensemble
   {:title "Lucid Dream Ensemble - 3. Red (2003)"
    :synopsis "A live digital arts performance ensemble. Performers: Virgil Moorefield: Founder, V-Drums; D. Schm&uuml;dde: Virtual Analog Synthesizers, Analogue Sequencing; Nathan Woleck: Max/MSP, Granular Synthesis; Jeff Weeter: Jitter Visuals."
    :status "Performance Premier: SEAMUS National Conference; Noted Performance: Museum of Contemporary Art in Chicago"
    :tags ["documentary"]
    :media "https://player.vimeo.com/video/84801854"}
   :reflections
   {:title "Reflections"
    :synopsis "&ldquo;Continuing to explore human truths in a vox populi format, Ginsburg trains his camera on Chicago citizens to ask &#39;What is freedom?&#39; The answer is always surprising. Like their previous films &#39;Aijo&#39; and &#39;The Year of the Rooster,&#39; narrative elements add suspense and a poignant dimension to the interviews featured on screen.&rdquo; -- Todd Lillethum, August 2009, Programming Director, Chicago Filmmakers"
    :status "Online"
    :link "https://www.youtube.com/watch?v=5yB2LWTfSYs"
    :tags ["documentary"]
    :image "reflections.jpg"}})


(def employment-db
  {:employers
   [{:title "Nextjournal"
     :date "9/2017 - 1/2020"
     :subtitle "Developer Advocate"
     :technology "Clojure, ClojureScript, Datomic, Python, R, Docker"
     :desc "An open science platform for reproducible research using automatically versioned polyglot notebooks."
     :synopsis "Developed the user group management feature from database calls to front end layout~ Developed user analytics notebooks to set and achieve platform traffic and conversion goals~ Increased reach in organic search through popular, runnable notebooks like &quot;How to Version Control Jupyter Notebooks&quot;, second only to machine learning notebooks~ Worked cross-functionally to enhance user on-boarding~ Presented topics in computer science using live code in Nextjournal at Curry On, PyCon, and Creative Coding NYC~ Wrote deep dives into core product concepts, &quot;Computational Environments,&quot; &quot;Transclusions,&quot; and &quot;Remixing&quot; (branching and version control)~ Helped structure and write Nextjournal's documentation~"
     :misc "I was the developer advocate at Nextjournal - tasked with a broad range of communication, planning, and implementation responsibilities across the organization. I helped scientists and engineers understand the unique benefits of our platform through direct interaction, conference talks, technical tutorials, and creative notebooks. These initiatives helped the company prioritize new features and commit to growth strategies that were both in line with our core mission and best served our users."
     }
    {:title "Beyond the Frame"
     :date "1/2005 - 8/2019"
     :subtitle "Creative Technologist"
     :technology "Clojure, Apache, Tomcat, Digital and 35mm Cameras, Final Cut X, Logic X"
     :desc "A new media company that produces interactive narrative experiences."
     :synopsis "Built University of California's interactive &quot;Pathways Online Tutorial&quot; for new students~ Negotiated distribution and exhibition agreements for interactive installations, online experiences, and short films~ Worked with the Elks National Foundation to establish a video presence online that indoctrinates new members and documents philanthropic efforts~ Granted residencies for new media creative work at Signal Culture (New York), Holocenter: Center for the Holographic Arts (New York), and Startup Germany/Medienboard (Berlin)~ Established Bushwick Film Festival's &quot;New Media Day&quot; in 2013, reprised in 2014~ Produced documentary pilot, &quot;Souvenir Junk Drawer,&quot; for Soulpancake~ Developed and reviewed feature film scripts for Throughline Films~ Awarded multiple &quot;Best of Festival&quot; distinctions~"
     }
    {:title "Penguin Random House"
     :date "10/2015 - 4/2016"
     :subtitle "Freelance Developer"
     :technology "PHP, JavaScript, Wordpress"
     :synopsis "Developer of high-profile author sites like Arianna Huffington and Carol Burnett as well as imprints like Hogarth Shakespeare for Crown Publishing Group~ Created accurate bids for work with a structured client review and delivery process~ Wrote custom code for Wordpress sites and conformed to corporate security standards~"
     }
    {:title "NetGalley"
     :date "12/2013 - 4/2015"
     :subtitle "Front End Developer"
     :technology "Symfony 2, Symfony 1, PHP, JavaScript, JQuery, Less, Doctrine, GitHub"
     :desc "A publishing web site with 40,000+ members and 100K+ uniques/month."
     :synopsis "Released every two weeks, accurately hitting sprint targets while committing quality code~ Moved to new templating technologies and stylesheet best practices to support faster development and extended support for Japanese, German, and French~"
     }
    {:title "F#"
     :date "9/2012 - 12/2012"
     :subtitle "Digital Project Manager/Digital Producer"
     :technology "Jira, Spotify API"
     :desc "A digital audio marketing firm."
     :synopsis "Managed the creation of interactive advertising campaigns~ Led ideation process for wireframe, development, creative, and launch~ Led agile production process that included teams on three continents highlighting cultural awareness, language, and time zone management concerns~ Clients included Universal Pictures, Chevrolet, Philips, Gap, and Spotify~"
     }]
})

(defn generate-markup [position row-position]
  (case position
        1 ({:row-start "<div class='row'>" :row-end ""} row-position)
        2 ({:row-start "" :row-end ""} row-position)
        3 ({:row-start "" :row-end "</div><!-- /row -->"} row-position)))

(defn set-column-number [talk talks row]
  (conj talks (assoc talk :row-start (generate-markup row :row-start)
                          :row-end (generate-markup row :row-end))))

(defn create-rows [talks]
  (loop [raw-talks talks
         formatted-talks []
         row 1]
    (if (empty? raw-talks)
      formatted-talks
      (recur (rest raw-talks)
             (set-column-number (first raw-talks) formatted-talks row)
             (if (< row 3) (inc row) 1)))))

(def talks-db
  {:talks
   [{:location "Curry On, London, England"
     :date "July 15, 2019"
     :title "Say What You Mean"
     :subtitle "Exploring Declarative Computation in Art"
     :synopsis "&quot;Say What You Mean&quot; parallels the production of art with approaches to exploratory programming. 20th century art practices were clearly influenced by the milieu of computational thought. Expressionist artists like Jackson Pollock declared intent with few implementation details in mind. Pollock’s paintings have since been proven fractal in nature. Conceptual artists like Sol LeWitt wrote imperative instructions where &quot;the idea becomes the machine that makes the work.&quot; The results of both artists are computational in nature, but very different in quality. This is partially due to approach. This talk examines creative approaches to exploring unknown or open-ended domains. It emphasizes declarative programming and the tried-and-true techniques of abstraction and composition. Rather than showing a sliver of a sprawling codebase, approaches are illustrated more immediately through computational art. Parallel code examples are written in Clojure."
     :link-status true
     :link "https://www.curry-on.org/2019/sessions/say-what-you-mean-exploring-declarative-computation-in-art.html"
     :link-name "Curry On Profile"
     :media "https://www.youtube.com/watch?v=HalZfFdWuP4"
     :video true
     :image false
     }
    {:location "PyCon/PyData, Berlin, Germany"
     :date "October 10, 2019"
     :title "Dr. Schmood's Notebook of Python Calisthenics and Orthodontia"
     :subtitle "Don't get bit by misaligned state and output, keep your notebooks running with these functional tips!"
     :synopsis "This talk will explain functional programming using practical language and provide historical context. Some initial concepts will be demonstrated in Clojure or Julia, but ultimately translated to Python. Further consideration will be given to the impact of immutability, a core concept in the functional style, on creating reproducible notebooks."
     :link-status true
     :link "https://de.pycon.org/program/pyconde-f3tczn-dr-schmood-s-notebook-of-python-calisthenics-and-orthodontia-david-schmudde/"
     :link-name "PyConDE & PyData Profile"
     :media "https://www.youtube.com/watch?v=BAsL_cbkQDc"
     :video true
     :image false
     }
    {:location "Society for the History of Technology, Milan, Italy"
     :date "October 26, 2019"
     :title "The Emergence of Computational Thinking in Art"
     :subtitle ""
     :synopsis "Early computational art provides insight into the nature of creativity and human discovery. Artists created these works before the computer's ubiquity. Many had never even seen a computer. The work exhibits pure computational thinking outside of the engineer or the machine. It suggests a new perspective that has broadly shaped our perception of the world."
     :link-status true
     :link "https://www.historyoftechnology.org/wp-content/uploads/2019/10/SHOT-2019-Program_FINAL.pdf"
     :link-name "SHOT 2019 Program"
     :media "shot-logo.png"
     :video false
     :image true
     }
    {:location "Strange Loop, St. Louis, MO"
     :date "September 27, 2018"
     :title "Misuser"
     :subtitle "The Case For a Steady Diet of Creative Misuse in Computing"
     :synopsis "The software I feature in this talk operated at the edge of digital systems. Artists often behaved like hackers: working off hours, pushing a system beyond its specification, and inventing new possibilities for human interaction along the way. &quot;Misuser&quot; will illustrate the connection between art and engineering, building a framework to examine the value of conventions and what we can learn by working outside of them."
     :link-status true
     :link "https://thestrangeloop.com/2018/misuser.html"
     :link-name "Strange Loop profile"
     :media "https://www.youtube.com/embed/SU6NvkkF4Xk"
     :video true
     :image false
     }
    {:location "I T.A.K.E Unconference (Keynote), Bucharest, Romania"
     :date "May 11, 2017"
     :title "Aesthetics and Narrative"
     :subtitle "Programming What Cannot Be Programmed"
     :synopsis "Declarative programming has been the style of choice for implementing countless creative applications, from &quot;Zork&quot; to Harold Cohen's &quot;AARON.&quot; We'll explore why it helps to reason about machine creativity in this way and use Clojure's Overtone toolkit and clojure.spec to illustrate abstract concerns and domain intelligence."
     :link-status true
     :link "http://itakeunconf.com/sessions/aesthetics-and-narrative-programming-what-cannot-be-programmed/"
     :link-name "I T.A.K.E Unconference profile"
     :media "https://www.youtube.com/embed/loksl7ED0Hg"
     :video true
     :image false
     }
    {:location "New York City"
     :date "2017 - 2019"
     :title "The Internet Lives Here"
     :subtitle "A Telecom Tour"
     :synopsis "&quot;The Internet Lives Here&quot; is a multi-site telecommunications tour of Lower Manhattan. Participants explore the relationship between global telecommunications and power. The walk starts at the old AT&T Long Lines building in Tribeca and concludes south of Wall St. at the original ITT Building - spanning the 19th, 20th, and 21st centuries."
     :link-status false
     :link ""
     :link-name ""
     :media "itt-m.jpg" ;; (cc) Christoph Anton Mitterer on Flickr
     :video false
     :image true
     }
    {:location "Creative Coding NYC"
     :date "February 28, 2018"
     :title "Sound, Motion, Notation"
     :subtitle "The Work of Channa Horwitz"
     :synopsis "Channa Horwitz worked on her &quot;Sonakinatography&quot; series from 1968 until 2012. The pieces are a prescient expression of a hidden order that transcends artistic medium and forms the world around us. My presentation will investigate her algorithmic process by running code and placing the output in a broader historical context across the domains of art, gaming, and computation. I’ll also touch on the concepts of literate computing in computational notebooks using Nextjournal."
     :link-status false
     :link ""
     :link-name ""
     :media "https://www.youtube.com/embed/yvbKpaN1_7M"
     :video true
     :image false
     }
    {:location "Clojure/conj, Austin, TX"
     :date "December 1, 2016"
     :title "Aesthetics and Narrative"
     :subtitle "Programming What Cannot Be Programmed"
     :synopsis "Declarative programming has been the style of choice for implementing countless creative applications, from &quot;Zork&quot; to Harold Cohen's &quot;AARON.&quot; We'll explore why it helps to reason about machine creativity in this way and use Clojure's Overtone toolkit and clojure.spec to illustrate abstract concerns and domain intelligence."
     :link-status true
     :link "http://2016.clojure-conj.org/aesthetics-and-narrative/"
     :link-name "Clojure/conj"
     :media "https://www.youtube.com/embed/UJ1pD-Z6PEY"
     :video true
     :image false
     }
    {:location "Vintage Computer Festival Midwest 11, Elk Grove, IL"
     :date "September 10, 2016"
     :title "Accidentally Arming a Hacker Revolution"
     :subtitle "The Role of Commodore and Jack Tramiel"
     :synopsis "The popularity of Commodore computers and modems hastened the spread and associated public fears of hacking. This is partially due to Jack Tramiel's ethos, which inadvertently aligned with hacker principles."
     :link-status true
     :link "https://www.youtube.com/watch?v=1lBaqC6kKEo"
     :link-name "Vintage Computer Festival on YouTube"
     :media "https://www.youtube.com/embed/1lBaqC6kKEo"
     :video true
     :image false
     }
    {:location "C-Base: Home of the Chaos Computer Club, Berlin, Germany"
     :date "July 7, 2015"
     :title "Harvesting Human Intelligence"
     :subtitle "Reframing the Surveillance Discourse"
     :synopsis "The power of large government and corporate surveillance systems feel like an intractable part of our everyday digital lifestyle. However, the raw intelligence of centralized computer systems pale in comparison to decentralized personal computers, each augmented by a human actor. This talk examines how the PC threatens traditional power structures and how those systems have responded."
     :link-status true
     :link "https://digitalegesellschaft.de/2015/07/npa039/"
     :link-name "Digitalegesellschaft: Netzpolitischen Abend #38"
     :media "https://player.vimeo.com/video/132857801"
     :video true
     :image false
     }
    {:location "History of Digital Cultures at the University of Amsterdam"
     :date "January 21, 2019"
     :title "Enabling Digital Anthropology"
     :subtitle "On Methods and Practices of Digital Archaeology"
     :synopsis "A lecutre on preserving software and techniques of emulation."
     :link-status false
     :link ""
     :link-name ""
     :media "uva.png"
     :video false
     :image true
     }
    {:location "Vossius Seminar's History of Knowledge series at the University of Amsterdam"
     :date "January 22, 2019"
     :title "Intended Knowledge?"
     :subtitle ""
     :synopsis "The act of discovering and displaying digital art created for 20th century computing and telecommunication environments places the character of these systems in stark relief. Most exhibitions require an archeological undertaking and an enormous effort to execute a single artifact from forty years ago. &quot;Intended Knowledge?&quot; examines what computational art archeology can teach us about software engineering and its culture"
     :link-status true
     :link "http://vossius.uva.nl/"
     :link-name "Vossius Center for the History of Humanities and Sciences"
     :media "uva.png"
     :video false
     :image true
     }
    {:location "Stored In Memory: The 10th Annual SIGCIS Conference, St. Louis, MO"
     :date "October 14, 2018"
     :title "Misuser"
     :subtitle "Artists Working at the Edge of Computing"
     :synopsis "The software I feature in this talk operated at the edge of digital systems. Artists often behaved like hackers: working off hours, pushing a system beyond its specification, and inventing new possibilities for human interaction along the way. &quot;Misuser&quot; will illustrate the connection between art and engineering, building a framework to examine the value of conventions and what we can learn by working outside of them."
     :link-status true
     :link "http://meetings.sigcis.org/uploads/6/3/6/8/6368912/program_18_final.pdf"
     :link-name "Stored In Memory Program"
     :media "sigcis.png"
     :video false
     :image true
     }
    {:location "RIXC Art Science Festival, Riga, Latvia"
     :date "October 20, 2017"
     :title "Manifesting Human Relationships in Art and Technology"
     :subtitle ""
     :synopsis "The internet has produced a tangibly interconnected world. In hindsight, its grammar of nodes, edges, and protocols is as organic as natural language. This presentation examines the discovery of packet-switched networks from a mid-century context and reveals the metaphors for human interaction and consciousness baked within; concepts that provide a foundation for discussing post-digital contemporary art."
     :link-status true
     :link "http://festival2017.rixc.org/"
     :link-name "RIXC: Virtualities and Realities"
     :media "rixc.jpg"
     :video false
     :image true
     }
    {:location "Society for the History of Technology, Philadelphia, PA"
     :date "October 28, 2017"
     :title "Unlikely Harbingers"
     :subtitle "Office Machinery in the Mainframe Era"
     :synopsis "This paper focuses on the significance of common mid-century desktop tools through the lens of Commodore Business Machines, a small company with a penchant for international deal-making. Commodore competed with very little capital against much larger players, and in doing so utilized emerging business practices that would come to define today's entrepreneurial climate. Part of a series on &quot;Computers and Futures.&quot;"
     :link-status true
     :link "http://sites.library.queensu.ca/transmissions/computers-and-futures/"
     :link-name "Computers and Futures Retrospective on Transmissions"
     :media "shot-logo.png"
     :video false
     :image true
     }
    {:location "ClojureBridge New York City"
     :date "June 3-4, 2017"
     :title "Workshop Instructor"
     :subtitle "The Fundamentals of Functional Programming and Clojure: A Workshop for Programmers"
     :synopsis "ClojureBridge is an all-volunteer organization dedicated to increasing diversity in the Clojure community. I lectured and provided hands-on support for experienced engineers looking to learn functional programming based on a collaboratively prepared curriculum."
     :link-status true
     :link " http://www.clojurebridge.org/events/2017-05-26-new-york-ny"
     :link-name "ClojureBridge Event Information"
     :media "clojurebridge.png"
     :video false
     :image true
     }
    {:location "Codes & Modes Symposium hosted by Hunter College"
     :date "March 18, 2017"
     :title "The Grammar of the Internet"
     :subtitle ""
     :synopsis "Cinematic language is robust enough to clearly depict the flow of time through a collection of shots - whether they span centuries, seconds, or instant conceptual leaps. Early experiments in hypertext and interactive media often attempted to reconcile the new mediums by adopting the rules of cinema. This talk nods to the elegance of cinematic grammar and examines an idiosyncratic path forward for interconnected, interactive, nonfiction media."
     :link-status true
     :link "http://www.hunterintegratedmedia.org/reframe/speaker-lineup/dschmudde/"
     :link-name "Codes & Modes profile"
     :media "hunter-sm.jpg"
     :video false
     :image true
     }
    {:location "New York City Digital Humanities Festival"
     :date "February 8, 2017"
     :title "Workshop Instructor"
     :subtitle "Strategies for Interactive and Immersive Dance"
     :synopsis "This workshop focuses on strategies for creating cross-domain experiences. We dive deep into visual projection, software, sound, and dance while respecting each medium's idiosyncratic strengths."
     :link-status true
     :link "http://dhweek.nycdh.org/event/strategies-for-interactive-and-immersive-dance/"
     :link-name "NYCDH Workshop Page"
     :media "nycdh.png"
     :video false
     :image true
     }
    {:location "College of Arts and Letters, Hoboken, NJ"
     :date "January 2017 - May 2019"
     :title "Stevens Institute of Technology"
     :subtitle "Adjunct Professor"
     :desc ""
     :synopsis "<small>HSS-371</small> <em>Computers and Society</em>: An examination of the politics and culture embodied by technology. This includes internet governance, ethical issues in computing, privacy, intellectual property, and the global digital divide.~ <small>HST-495</small> <em>Cyberspace and National Security</em>: This course explores the concept of power in the digital age. Topics include cyberwarfare, espionage, propaganda, surveillance, social media, cryptography, hacking, and perhaps most importantly, culture.~"
     :link-status true
     :link "http://beyondtheframe.digital/computersandsociety/"
     :link-name "<em>Computers and Society</em> class website"
     :media "stevens.png"
     :video false
     :image true
     }
    {:location "Pecha Kucha, Berlin, Germany"
     :date "July 8, 2015"
     :title "Computers & Intimacy"
     :subtitle "The Story of the Largest Machines and the Smallest Details"
     :synopsis "Our thoughts are the material of the Internet. In the midst of ever-evolving man/machine integration, I’ll use declassified documents to show how politicians have used computers to monitor these thoughts in the past, and suggest new ways of thinking about intimacy and computers in the future."
     :link-status true
     :link "http://pechakucha.de/berlin/player/?event=PK_39_1&position=03&presenter=D.%20Schm%C3%BCdde&presentation=Computers%20&%20Intimacy%20%E2%80%93%20The%20Story%20of%20the%20Largest%20Machines%20and%20the%20Smallest%20Details"
     :link-name "Pecha Kucha Slides &amp; Audio"
     :media "intimacy.jpg"
     :video false
     :image true
    }
    {:location "Department of Digital Film and Video, Chicago, IL"
     :date "January 2006 - January 2012"
     :title "Illinois Institute of Art"
     :subtitle "Associate Professor"
     :desc ""
     :synopsis "Designed the curriculum for the Bachelor of Audio Engineering~ Core member of faculty committee for senior film portfolio defence~ Courses: <small>DFV-365</small> <em>Sound Design for Film</em>, <small>DFV-100</small> <em>Survey of Film</em>, <small>DFV-330</small> <em>Digital Filmmaking Portfolio Preparation I</em>, <small>DFV-430</small> <em>Digital Filmmaking Portfolio Preparation II</em>, <small>DFV-120</small> <em>Fundamentals of Audio</em>, <small>AUD-100</small> <em>Survey of the Music Industry</em>, <small>AUD-201</small> <em>Music Theory</em>~"
     :link-status false
     :link ""
     :link-name ""
     :media "ai.png"
     :video false
     :image true
    }
    {:location "MFA: Music Composition for the Screen, Chicago, IL"
     :date "October 2009"
     :title "Columbia College Chicago"
     :subtitle "Visiting Lecturer"
     :desc "<em>Hybrid Scores and Atonality</em>: A master class on the intersection between sound design and traditional notions of composing music for the screen."
     :link-status true
     :link "http://beyondthefra-me.tumblr.com/post/5545059622"
     :link-name "D. Schm&uuml;dde: &quot;Composing for <em>The Assassination of Chicago&#39;s Mayor</em>&quot;"
     :media "ccc.jpg"
     :video false
     :image true
    }
    {:location "Video and Animation Department, Chicago, IL"
     :date "January 2005 - June 2007"
     :title "International Academy of Design and Technology"
     :subtitle "Adjunct Faculty"
     :desc ""
     :synopsis "Designed the curriculum for the Interactive Multimedia Track~ IADT kiosk: class developed and implemented public informational kiosk~ Courses: <em>Directing and Producing, Application Design, Digital Production I, Advanced Post Production, Digital Video Editing, Audio Production, Digital Audio Editing, Senior Project for Visual Communications, Career Portfolio Development for Visual Communications, Independent Study</em>~"
     :link-status false
     :link ""
     :link-name ""
     :media "iadt.png"
     :video false
     :image true
    }
    {:location "Midwest Independent Film Festival, Chicago, IL"
     :date "September 2009"
     :title "Moderator"
     :subtitle "&quot;Low Budget Filmmaking&quot;"
     :synopsis "A discussion of low budget filmmaking techniques for independent filmmakers."
     :link-status true
     :link "http://beyondthefra-me.tumblr.com/post/53206393777"
     :link-name "D. Schm&uuml;dde: &quot;The Magic of M&#233;li&#232s: Low Budget Filmmaking&quot;"
     :media "miff.png"
     :video false
     :image true
    }
    {:location "Chicago Actors Casting Summit, Chicago, IL"
     :date "July 2008 &amp; July 2009"
     :title "Panelist"
     :subtitle "&quot;Casting in Student Films&quot;"
     :synopsis "A discussion with actors on the benefits and concerns associated with working on student-run film sets."
     :link-status true
     :link "http://beyondthefra-me.tumblr.com/post/28430696505"
     :link-name "D. Schm&uuml;dde: &quot;Reader Question: Student/Actor Expectations&quot;"
     :media "16x9spacer.gif"
     :video false
     :image true
    }]
   :page-title "Talks"})

(def database
  {:talks {:talks (create-rows (second (first talks-db))) :page-title "Talks"} ;;talks-db
   :projects projects-db
   :exhibitions exhibitions-db
   :employment employment-db
})
