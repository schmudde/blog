(ns site.models)

;; IEEE Spectrum talk

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





(def faculty
  [{:location "College of Arts and Letters, Hoboken, NJ"
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
   ])




;; national sevice?
;; university service?

#_(def exhibitions
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

#_(def honors-grants-awards
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
