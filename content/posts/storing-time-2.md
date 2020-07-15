---
title: Storing Time - Part 2
description:
author: David Schmudde
author-email: d@schmud.de
author-url: http://schmud.de
author-github: schmudde
author-twitter: dschmudde
location: Turin, Italy
date-created: 2020-07-15
date-modified: 2020-07-15
date-published: 2020-07-15
in-language: en
keywords: clojure, edn, java-time, chris marker, cinema, archiving
tags:
 - doing
 - science
 - tools
---

![](https://assets.mubicdn.net/images/notebook/post_images/23947/images-w1400.jpg?1501011402)

Chris Marker's *Level Five* (1997) moves through the computer network as a space of memory. The film is something of a travelogue - like Joseph Brodsky's [writings on Venice](https://www.goodreads.com/book/show/13553406-watermark) or Chris Markers' own *[Sans Soleil](https://en.wikipedia.org/wiki/Sans_Soleil)* - but traverses records of events through virtual space.[^mubi] The main character must move through OWL's hierarchhical database as she attempts to finish making the fifth level of a game based on the Battle of Okinawa during World War II.

[^mubi]: *Level Five* is availabe to [watch on Mubi](https://mubi.com/notebook/posts/forms-of-memory-close-up-on-chris-marker-s-level-five).

This effort is both literally and figuratively a puzzle that draws a distinction between information and memory. In his essay, [*Forms of Memory: Close-Up on Chris Marker’s "Level Five"*](https://mubi.com/notebook/posts/forms-of-memory-close-up-on-chris-marker-s-level-five), Benjamin Crais makes this observation:[^crais]

[^crais]: Benjamin Crais, "[Forms Of Memory: Close-Up On Chris Marker’S "Level Five"](https://mubi.com/notebook/posts/forms-of-memory-close-up-on-chris-marker-s-level-five)", MUBI, 2017. Crais is references Jacques Ranciere, “Documentary Fiction: Marker and the Fiction of Memory”, *Film Fables* (157).


> In an essay on Marker, Jacques Rancière makes an opposition between information and memory (with memory consisting of “an orderly collection, a certain arrangement of signs, traces, and monuments” and information a matter of indifferently equivalent facts), but one could say here that Level Five deploys these two terms in a slightly different way, and less as an opposition than a tension or relation: that between memory and information technology, of personal-historical-cultural memory manifesting in, through, and by a database.

Storing memory and modeling time is a **hard problem**. In part 1 of *Storing Time*, I looked at some of the current issues tied to manipulating time and modeling time. It used the popular Java Time library and demonstrated the basics in Clojure.

Part 2 will look at a few basic definitions and the current practice of storing time in plain text so that it can be read and manipulated in the future.

I'll conclude by taking a cursory look at the problems of archiving time in Unix systems. If you're not interested in the specifics of storing and reading Java Time in Clojure/edn, please skip ahead.

## Time Literals

**Literals**

A *literal* is a form that returns itself when evaluated. For example, the *s-expression* `(+ 3 4)` represents both -

1. A *syntax tree* when *printed* as a (list) that contains three atoms `+`, `3`, and `4`.
2. A *form* when *evaluated* by Clojure. It includes a *symbol* for addition, `+`, and two numeric *literals*, `3` and `4`.

The results of evaluating two different forms:

*  `3` ⇒ `3` (the numeric *literal* evaluates to a numeric *literal*)
* `(+ 3 4)` ⇒ `7`  (the *s-expression* evaluates to a numeric *literal*)

```
(str "Evaluate `3`: " 3 ", Evaluate `(+ 3 4)`: " (+ 3 4))
```

Literals go beyond numbers. [They also include](https://clojure.org/reference/reader) strings, characters, `nil`, booleans, keywords, symbolic values (`##Inf` (∞), `##-Inf` (-∞), and `##NaN` (Not a Number)), collections (lists, vectors, maps, and sets), and records (`deftype` and `defrecord`).

### Tagged Literals

Clojure 1.4 introduced custom literals marked by a tag. For example, 1.4 shipped with built-in tagged literals for `#uuid` and `#instant`. Prefixing the literal `"efea38cd-0db8-4f66-b3ab-c50b4c08d907"` with `#uuid` generates a `java.util.UUID` type:

```
#uuid "efea38cd-0db8-4f66-b3ab-c50b4c08d907"
```

Clojure reads the literal string first, `"efea38cd-0db8-4f66-b3ab-c50b4c08d907"`, and then invokes the tagged literal function on the string (or whatever literal happens to follow the tag).

In this example, `#uuid` dispatches `java.util.UUID/fromString` on `"efea38cd-0db8-4f66-b3ab-c50b4c08d907".` The generic string is now a meaningful bit of data.

```
(= "efea38cd-0db8-4f66-b3ab-c50b4c08d907" (java.util.UUID/fromString "efea38cd-0db8-4f66-b3ab-c50b4c08d907"))
```

```
(= #uuid "efea38cd-0db8-4f66-b3ab-c50b4c08d907" (java.util.UUID/fromString "efea38cd-0db8-4f66-b3ab-c50b4c08d907"))
```

`#uuid` cannot prefix a string that does not qualify as a valid UUID.

```
(try
  (java.util.UUID/fromString "eefe") ;; #uuid "efefe"
  (catch IllegalArgumentException e (.getMessage e)))
```

Tagged literals are like a constructor for an object. But they are succinct and easily read by humans - making them a perfect fit for structured data. They are the "extensible" part of the extensible data notation ([edn](https://github.com/edn-format/edn)) specification.

### [time-literals](https://github.com/henryw374/time-literals)

Edn is a preferred format for serializing and deserializing data. Here's how we might store and send time and date information over the wire.

Tag a generic date string with `#inst` to generate a `java.util.Date` object:

```
(print (type #inst "2020-05-11"))
#inst "2020-05-11"
```

Store that information as a string. Later, if is read by a reader that recognizes the `#inst` tag, it will automatically construct a `java.util.Date` object. This happens automatically when reading a string using `clojure.edn`:

```
(prn-str #inst "2020-05-11") ; ⇒ "#inst "2020-05-11T00:00:00.000-00:00" "
(clojure.edn/read-string (prn-str #inst "2020-05-11"))
```

It is now possible to operate on the date and time information - add days, find the difference between two dates, grab the year, etc....

```
(import '(java.text SimpleDateFormat))

;; Grab the year
(->> (prn-str #inst "2020-05-11")
     (clojure.edn/read-string)
     (.format (SimpleDateFormat. "yyyy")))
```

**But there is a big problem!** We don't want to work with `java.util.Date`, otherwise known as Joda Time. This is where the `time-literals` package comes in handy.

As a reminder, I want to operate on an object like this one so I have access to all the handy date and time functions supplied by `cljc.java-time`:

```
(ld/now)
```

Here are some of the tagged literals supplied by `time-literals`:

```
#time/month "JUNE"
#time/period "P1D"
#time/date "2039-01-01"
#time/date-time "2018-07-25T08:08:44.026"
#time/zoned-date-time "2018-07-25T08:09:11.227+01:00[Europe/London]"
#time/offset-date-time "2018-07-25T08:11:54.453+01:00"
#time/instant "2018-07-25T07:10:05.861Z"
#time/time "08:12:13.366"
#time/duration "PT1S"
#time/year "3030"
#time/year-month "3030-01"
#time/zone "Europe/London"
#time/day-of-week "TUESDAY"
```

Printing the date to a string looks like this:

```
(require '[time-literals.read-write :as time-read])

(def time-date-string (time-read/print-date "2015-12-11"))
time-date-string
```

Using the `edn/read-string` function with the `#time/date` tag is almost identical to `#inst`. The only difference is the supplied reader: `{:readers time-read-prn/tags}`. This is necessary because Clojure does not have any built-in `#time/date` functionality.

```
(clojure.edn/read-string {:readers time-read/tags} time-date-string)
```

The tags make it easy to read a string, December 11, 2015, and add 90 days to generate the result March 10, 2016:

```
(require '[cljc.java-time.format.date-time-formatter :as formatter])

(-> (clojure.edn/read-string {:readers time-read/tags} time-date-string)
    (ld/plus-days 90)
    (ld/format  (formatter/of-pattern "MMMM dd, yyyy")))
```

If I want to get the year from a tagged literal string pulled off the wire or a file:

```
(->> "#time/date \"2011-01-01\""
     (clojure.edn/read-string {:readers time-read/tags})
     (ld/get-year))
```

## Archiving Time

There are four types of time-related metadata all types of files may have: birth time, ATime, MTime and CTime [unixtutorial.org, 2008]. These times are expressed in seconds since an epoch [Group, 2018c], a set time in the past from which the counting started. In Unix-based systems, the epoch is traditionally 1970-01-01 00:00:00 [Matthew and Stones, 2008]. One thing to note about time-related metadata is that it is dependent on the correct functioning of the internal clock of the computer that registered the timestamp.

- Sadly birth time is not always present in the metadata of files. Its presence or absence is determined by the filesystem. MacOS uses the Apple File System (APFS) as of 2017 and before that HSF+, both supporting birth time. Ubuntu (a popular Unix-like operating system) by default uses ext4 (fourth extended filesystem), while ext4 does offer a field for birth time, it is not fully supported by the core functionalities [Ubuntu, 2017].
- Note that the presence of birth time is determined by the filesystem. The same operating system can use different filesystems. The operating system does determine if and how users interact with the birth time if present.

- Access time: The ATime can give clues to what the last actions were that have taken place on a system. The files with the most recent ATime will be the last files to be used. By sorting them one can retrace the final steps taken on the system.
- Modify time: Changes in metadata do not result in a different MTime. MTime remains unaltered because it is not a modification to the file, so the file would not be modified and therefore keep its MTime.
- Change time: The CTime of a file stands for its change time, the last time the file or its metadata have been changed. It is different from MTime in that it does not only concern the content of the file but also the metadata. If the CTime is different from the MTime, more insight can be gained. A different CTime and MTime means that the metadata of the file has been altered but not the content of the file. The alterations could be changes to the permissions of a file, a name change, or any other alteration to the metadata.


- Sorting to gain insight into trends
- INteractions: Comparing birth time and ATime can give some insight into how long files stayed in use. Similarly, MTime and ATime can give insights into the use of the files.
- Test the integrity of the archive and filesystem. Do the dates make sense?
