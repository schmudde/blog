(ns site.models)

;; IEEE Spectrum talk


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
