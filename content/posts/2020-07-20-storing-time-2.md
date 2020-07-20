---
title: Storing Time - Part 2
description:
author: David Schmudde
author-email: d@schmud.de
author-url: http://schmud.de
author-github: schmudde
author-twitter: dschmudde
location: Turin, Italy
date-created: 2020-07-20
date-modified: 2020-07-20
in-language: en
keywords: clojure, edn, java-time, chris marker, cinema, archiving
tags:
 - doing
 - science
 - tools
---

![](/img/2020-07-20-storing-time/owl.png)[^level-five]

[^level-five]: {-} The OWL database from *Level Five*. Chris Marker, *Level Five*, film (France: Les Films de l'Astrophore, Argos Films, 1997).

Chris Marker's *Level Five* (1997) moves through a computer network as a space of memory. The film is something of a travelogue - like Joseph Brodsky's [writings on Venice](https://www.goodreads.com/book/show/13553406-watermark) or Chris Markers' own *[Sans Soleil](https://en.wikipedia.org/wiki/Sans_Soleil)* - but it traverses records of events through virtual space. The main character moves through a hierarchical database called OWL as she finishes making the fifth level of an unfinished game she inherited. The events in the game are based on the Battle of Okinawa during World War II; her journey is through these events and the pathways of the game's deceased originator.[^mubi]

[^mubi]: *Level Five* is availabe to [watch on Mubi](https://mubi.com/notebook/posts/forms-of-memory-close-up-on-chris-marker-s-level-five).

This effort is both literally and figuratively a puzzle that draws a distinction between information and memory. In his essay, [*Forms of Memory: Close-Up on Chris Marker’s "Level Five"*](https://mubi.com/notebook/posts/forms-of-memory-close-up-on-chris-marker-s-level-five), Benjamin Crais makes this observation:[^crais]

[^crais]: Benjamin Crais, "[Forms Of Memory: Close-Up On Chris Marker’S "Level Five"](https://mubi.com/notebook/posts/forms-of-memory-close-up-on-chris-marker-s-level-five)", MUBI, 2017. Crais is references Jacques Ranciere, “Documentary Fiction: Marker and the Fiction of Memory”, *Film Fables* (157).

> In an essay on Marker, Jacques Rancière makes an opposition between information and memory (with memory consisting of “an orderly collection, a certain arrangement of signs, traces, and monuments” and information a matter of indifferently equivalent facts), but one could say here that &ldquo;Level Five&rdquo; deploys these two terms in a slightly different way, and less as an opposition than a tension or relation: that between memory and information technology, of personal-historical-cultural memory manifesting in, through, and by a database.

Storing memory and modeling time is a **hard problem**. In [part 1 of *Storing Time*](/posts/2020-07-19-storing-time-1.html), I looked at some of the current issues related to manipulating time and modeling time. I used the popular Java Time library and demonstrated the basics in Clojure.

Part 2 will look at a few basic definitions and the current practice of storing time in plain text so that it can be read and manipulated in the future.

I'll conclude by taking a cursory look at the problems of archiving time in Unix systems. If you're not interested in the specifics of storing and reading Java Time in Clojure/edn, please skip ahead to the last section.


## Time Literals

As stated in Part 1, I want a time format that is:

1. Easy to write and read
2. Flexible in how it's displayed
3. Easy to manipulate programmatically

Java Time objects satisfy the second and third demands. But I want something I can write by hand into a text file and it can be worked with in different systems. Rather than work with (Java) *time objects*, I want to work with *time literals*.

### Literals

Literals return themselves when evaluated.[^nextjournal]

[^nextjournal]: Literals and expressions in this section can be evaluated as [a runnable notebook in Nextjournal](https://nextjournal.com/schmudde/java-time#time-literals). The notebook also includes more detailed information about Java Time and Joda Time in Clojure.

- When `3` is evaluated in Clojure, the system returns `3`.
- When `"I have a dream"` is evaluated in Clojure, the sytem returns `"I have a dream"`.
- When `(+ 3 7)` is evaluated in Clojure, the system returns `7`. `(+ 3 7)` is *not* a literal.

[Clojure literals include](https://clojure.org/reference/reader) numbers (like `7`), strings (like `"I have a dream"`), characters, `nil`, booleans, keywords, symbolic values (`##Inf` (∞), `##-Inf` (-∞), and `##NaN` (Not a Number)), collections (lists, vectors, maps, and sets), and records (`deftype` and `defrecord`).

### Tagged Literals

#### Example 1 -  #uuid

Users can expand the list above and add their own custom literals with a tag. For example, Clojure version 1.4 shipped with built-in tagged literals for `#uuid`[^uuid] and `#instant`[^instant].

[^uuid]: A UUID is a [Universally Unique IDdentifier](https://en.wikipedia.org/wiki/Universally_unique_identifier). It creates unique identifier numbers in computer systems. The type is not natively supported in Clojure, but the `#uuid` tagged literal adds this functionality.
[^instant]: An instant is an [object in Java](https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html) that represents a single point in time.

Prefixing the literal `"efea38cd-0db8-4f66-b3ab-c50b4c08d907"` with `#uuid` generates a `java.util.UUID` type:

```
#uuid "efea38cd-0db8-4f66-b3ab-c50b4c08d907"
```

&rArr; `[java.util.UUID, "0x785d0e2f", "efea38cd-0db8-4f66-b3ab-c50b4c08d907"]`

Clojure reads the literal string first, `"efea38cd-0db8-4f66-b3ab-c50b4c08d907"`, and then invokes the tagged literal function on the string. In this example, `#uuid` dispatches `java.util.UUID/fromString` on `"efea38cd-0db8-4f66-b3ab-c50b4c08d907".` The generic string is now a meaningful bit of data.

```
(= "efea38cd-0db8-4f66-b3ab-c50b4c08d907"
   (java.util.UUID/fromString "efea38cd-0db8-4f66-b3ab-c50b4c08d907"))
```

&rArr; `false`

The two strings of characteres are not equivalent because the first one is not of a UUID type.

```
(= #uuid "efea38cd-0db8-4f66-b3ab-c50b4c08d907"
   (java.util.UUID/fromString "efea38cd-0db8-4f66-b3ab-c50b4c08d907"))
```

&rArr; `true`

Now they are both UUIDs. Conversely, `#uuid` cannot prefix a string that does not qualify as a valid UUID.

```
(try
  (java.util.UUID/fromString "not-a-date") ;; #uuid "not-a-date"
  (catch IllegalArgumentException e (.getMessage e)))
```

&rArr; `"Invalid UUID string: not-a-date"`

Tagged literals are succinct and easily read by humans, making them a perfect fit for structured data. They are the "extensible" part of extensible data notation.[^edn]

[^edn]: edn was covered in [Part 1](/posts/2020-07-19-storing-time-1.html). It is an extensible data notation used to convey values, often used where one might use JSON. More at the official [edn readme](https://github.com/edn-format/edn).

#### Example 2 - #inst aka Joda Time

Using tagged literals in edn is a great way to store and transmit time and date information. Tag a well formatted date string (i.e. something that looks like a date) with `#inst` to generate a `java.util.Date` object:

```
(type #inst "2020-05-11")
```

&rArr; `java.util.Date`

Store that information as a string. Later, if is read by software that recognizes the `#inst` tag, it will construct a `java.util.Date` object.

If the tagged literal is stored as a string, it will automatically be read as a `java.util.Date` object when read using `clojure.edn`:

```
(clojure.edn/read-string (prn-str #inst "2020-05-11"))
```

&rArr; `#inst "2020-05-11T00:00:00.000Z"`

It is now possible to operate on the date and time information: add days, find the difference between two dates, display the day of the week, display the year, etc&hellip;.

```
(import '(java.text SimpleDateFormat))

;; Grab the year
(->> (prn-str #inst "2020-05-11")
     (clojure.edn/read-string)
     (.format (SimpleDateFormat. "yyyy")))
```

&rArr; `"2020"`

**But there is a big problem!** I don't want to work with `java.util.Date`, otherwise known as Joda Time. Joda Time's [official site](https://www.joda.org/joda-time/) asks that people migrate to, or start new projects with, Java Time.[^joda] So rather than working with built-in `#inst` tags, the rest of this demonstration will work with `#time/date` tags as part of `cljc.java-time`. This is the same package I used in part 1 to work with Java Time.

[^joda]: &ldquo;Joda-Time is the de facto standard date and time library for Java prior to Java SE 8. Users are now asked to migrate to java.time (JSR-310).&rdquo;

### [time-literals](https://github.com/henryw374/time-literals)

Here's how `cljc.java-time` was used in Part 1:

```
(require '[cljc.java-time.local-date :as ld])

(ld/now)
```

&rArr; `[java.time.LocalDate, "0x61da6671", "2020-07-12"]`

With `time-literals`, I can prefix `#time/date` to my string and work with it as a `LocalDate`.

```
(require '[time-literals.read-write :as time-read])

(def time-date-string (time-read/print-date "2015-12-11"))
time-date-string
```

&rArr; `"#time/date "2015-12-11""`

Using the `edn/read-string` function with the `#time/date` tag is almost identical to `#inst`. The only difference is the supplied reader: `{:readers time-read-prn/tags}`. This is necessary because Clojure only ships with two user defined tags,`#uuid` and `#inst`. But adding the `#time/date` tag to the reader is easy:

```
(clojure.edn/read-string {:readers time-read/tags} time-date-string)
```

&rArr; `[java.time.LocalDate, "0x3d6a5fc1", "2015-12-11"]`

Now it's possible to read a string, `"2015-12-11"`, and add 90 days to generate the result, `March 10, 2016`:

```
(require '[cljc.java-time.format.date-time-formatter :as formatter])

(-> (clojure.edn/read-string {:readers time-read/tags} time-date-string)
    (ld/plus-days 90)
    (ld/format  (formatter/of-pattern "MMMM dd, yyyy")))
```

&rArr; `"March 10, 2016"`

Displaying the year from any tagged literal date is simple:

```
(->> "#time/date \"2011-01-01\""
     (clojure.edn/read-string {:readers time-read/tags})
     (ld/get-year))
```

&rArr; `2011`

## Curriculum Vitae

This effort was originally in service of my CV. Dates are now tagged and recorded with the greatest precision available. I decide how I will display them later.

### A Presentation

```
{:org "Curry On"
 :geo "London, UK"
 :date "#time/date \"2019-07-15\""
 :title "Say What You Mean"
 :subtitle "Exploring Declarative Computation in Art"
 :link "https://www.curry-on.org/2019/sessions/say-what-you-mean-exploring-declarative-computation-in-art.html"
 ...}
```

### Employment

```
{:org "Stevens Institute of Technology"
:title "Adjunct Professor"
:desc "College of Arts and Letters"
:geo "Hoboken, NJ, US"
:date-bgn "#time/year-month \"2017-01\""
:date-end "#time/year-month \"2019-05\""
...}
```

You can see the finished product [on this website](/cv.html). The data is also mixed, matched, and formatted for specific CVs and resumes as a PDF. Peruse any of the `.edn` files [on my repository](https://github.com/schmudde/blog/tree/master/src/site) to see how the data is structured in context.

## Archiving Time

A final note on recording time in digital records for future posterity. Even the most widely used systems are prone to pitfalls. [Robert Jansma](https://www.linkedin.com/in/robert-jansma-883230a8/?originalSubdomain=nl) explores this in his forthcoming master's thesis, *Scoops and Brushes for Software Archaeology: Metadata Dating*.

Jansma dives deep into the four types of time-related metadata that appear in many Unix systems: birth time, ATime, MTime and CTime. These are not Western date and time records, rather they are seconds since an epoch, traditionally 1970-01-01 00:00:00. Sounds simple enough, but there are pitfalls.

- Birth time: Is presence depends on the filesystem. The same flavor of Unix operating system can use different filesystems.
    - MacOS: Apple File System (APFS) (supported)
    - MacOS: HSF+ (supported)
    - Ubuntu: ext4 (supported, but not fully utilized by Ubutnu)
- Access time (ATime): The ATime can give clues to what the last actions were that have taken place on a system. The files with the most recent ATime will be the last files to be used. By sorting them one can retrace the final steps taken on the system.
- Modify time (MTime): Changes in metadata do not result in a different MTime. MTime remains unaltered because it is not a modification to the file, so the file would not be modified and therefore keep its MTime.
- Change time (CTime): The CTime of a file stands for its change time, the last time the file or its metadata have been changed. It is different from MTime in that it does not only concern the content of the file but also the metadata. If the CTime is different from the MTime, more insight can be gained. A different CTime and MTime means that the metadata of the file has been altered but not the content of the file. The alterations could be changes to the permissions of a file, a name change, or any other alteration to the metadata.

These timestamps are critical when attempting to understand how a system evolved. Files can be sorted to reveal trends in development. Comparing different timestamps, like birth time and ATime, can demonstrate how long a file remained a critical component. Unusual timestamps can also call into question the integrity of the filesystem.
