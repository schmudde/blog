---
title: Storing Time - Part 1
description:
author: David Schmudde
author-email: d@schmud.de
author-url: http://schmud.de
author-github: schmudde
author-twitter: dschmudde
location: Turin, Italy
date-created: 2020-07-19
date-modified: 2020-07-19
date-published: 2020-07-19
in-language: en
keywords: clojure, time, modeling
tags:
 - doing
 - science
 - personal
---


Time is a struggle for everyone. Most of us wish we had more time. Managing it can be a challenge. Recording and analysising how we use time is difficult in spite of time's relentless forward march.

I ran into this issue while updating my curriculum vitae.[^job] Several years ago, I switched to recording professional events in data structures. It was frustring to update my cv, my resume, and my website, at minimum, every time something happened in my life. Consistent data structures means that one source of truth, an edn file in my case,[^edn] can be used to update everything automatically.

[^job]: The CV update is part of my ongoing job search. Dear readers, if you know of a position that would benefit from my background - please send me an eMail. Positions I'm looking for: *Digial Humanities Scientist*, *Developer Advocate*, and *Researcher* on the structure and transmission of information over time and space. See [my CV](/cv.html) for my bona fides. I'd also consider a position as a *Clojure programmer*.

[^edn]: edn is an extensible data notation used to convey values. It is often used where one might use JSON, but it offers several advantages. More at the official [edn readme](https://github.com/edn-format/edn).

I took this as an opportunity to properly encode the various dates I have worked at a job, received awards, or published papers. This sounds trivial, but dates can be a single year, a month and a year, a month, day, and year. They can be displayed in a myriad of different ways, depending on the convetion preferred by a culture, nation, or institution. It can also be useful to calculate spans of time. &ldquo;I worked at this job for 2 years and 8 months,&rdquo; for example.

I want to record the most precise time and date available about an event in a format that that is:

1. **Easy to write and read**: I add and update events in plain text
2. **Easy to manipulate programmatically**: so I calculate a span of time, overlaps in time, etc&hellip;
3. **Flexible in how it's displayed**: e.g. display some dates as a year only, display dates with a month name (July) rather than a number (7), etc&hellip;

*Storing Time* part 1 and part 2 will explore the problems of recording time, working with Java Time, and digital memory and archeology. I have included code where necessary. But there is enough context so non-programmers can understand the complexity of the issue and the scope of the problem.[^y2k]

[^y2k]: Most non-programmers probably haven't thought about the issue of computer time since the &ldquo;[Y2K Bug](https://www.howtogeek.com/671087/what-was-the-y2k-bug-and-why-did-it-terrify-the-world/).&rdquo;

## Not All Time is Equal

![](/img/2020-07-15-storing-time/june-cal-royal-psalter-13th-cen.jpg)[^cal]

[^cal]: {-} &ldquo;This manuscript page from a psalter uses the same format as calendar pages found in a typical book of hours. June is written at the top; the text includes the names of saints and feasts to be celebrated during that month.&rdquo; "[Manuscript Leaf With June Calendar, From A Royal Psalter](https://www.metmuseum.org/art/collection/search/466372)". *The Metropolitan Museum Of Art*, 13th century.

Java Time and its predecessor Joda Time are the workhorses of countless systems around the world. Accounting for leap years and timezones when calculating payroll and coordinating bank transfers is surprisingly difficult. While most programmers agree that it works well enough, the abstraction leaves something to be desired.

Take something simple like **adding a month to a date**. The results may be surprising. According to Java:

* March 15, 2015 plus one month is April 15, 2015.
* March 30, 2015 plus one month is April 30, 2015.
* March 31, 2015 plus one month is April 30, 2015.

Here is the same logic, written and run in Clojure:[^add-1-month]

[^add-1-month]: These results rely on the `plusMonths` method that works on a Java Time `LocalDate` object. `add-1-month` is a Clojure function that includes the Java Time `plusMonths`. More on this later, but here is the function I'm using: `(defn add-1-month [date] (-> (java.time.LocalDate/parse date) (.plusMonths 1) str))`.

* `(add-1-month "2015-03-15")` &rArr; `"2015-04-15"`
* `(add-1-month "2015-03-30")` &rArr; `"2015-04-30"`
* `(add-1-month "2015-03-31")` &rArr; `"2015-04-30"`

It simultaneously makes sense and is quite troubling. In part because Java uses a function called `plusMonths` and we expect addition to be transitive: <em>a=b &and; b=c &rArr; a=c</em>.

1. **If** <em>a = b</em>
2. **and** <em>b = c</em>
3. **then** <em>a = c</em>

Said another way, where <em>b = April 30, 2015</em>

1. **If** `one-month + March 30, 2015 = April 30, 2015` <em>(a = b)</em>
2. **and** `April 30, 2015 = one-month + March 31, 2015` <em>(b = c)</em>
3. **then** `one-month + March 30, 2015 = one-month + March 31, 2015` <em>(a = c)</em>

But can the last assertion really true? Can `March 30` = `March 31` if I add a month to both? The equality can be tested in Clojure:

```
(= (add-1-month "2015-03-30") (add-1-month "2015-03-31"))
```

&rArr; `true`

It is true, even though the equation has three distinct elements: `add-1-month`, `"2015-03-30"`, and `"2015-03-31"`. If `(add-1-month "2015-03-30")` is compared to `(add-1-month "2015-03-31")` as two *literal lists of elements*, they are not equal:[^literal]

[^literal]: To take things *literally* in Clojure, they must be quoted. Think about how quotes are used in English. When I claim Martin Luther King Jr. said "I have a dream," I use quotes because I mean it *literally*. Look closely at the code `'(add-1-month "2015-03-30")` and you'll see it is similarly preceeded by a single quote.

```
(= '(add-1-month "2015-03-30") '(add-1-month "2015-03-31"))
```

&rArr; `false`

March 31st and March 30th are *literally* two different days and their distinction is very important! This is not captured in the time/arithmetic abstraction offered by Java Time. Even if the results are (arguably) correct, the metaphor of addition is imprecise.

## Other Ways of Modeling Time

Eric Evans talks about the difficulty of doing time math in *[Modelling Time](https://www.youtube.com/watch?v=T29WzvaPNc8)*. He suggests that arithmetic is the wrong abstraction. Evans describes taking inspiration from Clojure, a list processing language at heart, to develop a different abstraction.

* *Time* is a list of units. So July holds a list of days `July 1, July 2, ... July 31`.
* *Intervals* are where sequences meet. `July 1` meets `July 2`. `July` meets `August`. `2020` meets `2021` (if we're lucky). So determining the size of the interval is a matter of counting the number of items in a list.

Evans' model includes an implementation of [Allen's interval algebra](https://en.wikipedia.org/wiki/Allen%27s_interval_algebra) that is beyond the scope of this post. Suffice to say, Java Time is incredibly popular, but there are other approaches that may be more accurate and less complex.

Even something like a regular sequence of intervals - like the tick of the clock - can be unintuitive. In [Date and Time are more difficult than you think](https://mozaicworks.com/blog/date-and-time-are-more-difficult-than-you-think-with-alex-bolboaca-and-adrian-bolboaca/), Alex Bolboaca and Adrian Bolboaca discuss time zones, the international date line, and relativistic time.

Relativistic time is a real mind-bender with practical consequences. For examples, engineers must take into account the speed of orbiting satellites and the curvature of spacetime when estimating GPS coordinates. To stay in lock-step with clocks on the Earth's surface, satellite clocks must tick 38 microseconds faster in aggregate every day. Errors in time-keeping can lead to kilometer-sized positioning errors that get worse over time. The GPS on your phone would be worthless if these clocks did not observe the rules of Einstein's theory of relativity.

## The Basics of Java Time (in Clojure)

Coming back to Earth and dealing within the confines of my own lifetime still poses a challenge. When recording the dates of my previous exhibitions, teaching assignments, jobs, and publications, I wanted to a more robust format that will work years into the future. I chose Java Time even if the abstraction is imperfect because it is so ubiquitious. I will be able to store and work with my time data well into the future.[^nextjournal]

[^nextjournal]: [This section](https://nextjournal.com/schmudde/java-time#clj-%E2%86%94-cljs-%E2%86%94-edn) is can be consumed as runnable notebook on Nextjournal. The notebook also includes more detailed information about Java Time and Joda Time in Clojure.

The easiest way to work with Java Time across Clojure, ClojreScript, and EDN is [Henry Widd](http://widdindustries.com/)'s `cljc.java-time`.[^interop] Widd dives into the details of Java Time in his talk [Cross Platform DateTime Awesomeness](https://www.youtube.com/watch?v=UFuL-ZDoB2U) at Clojure/north 2019. After adding the library to your `deps.edn` file (currently at `cljc.java-time {:mvn/version "0.1.11"}`), start with a few Java Time `LocalDate` basics.

[^interop]: Along with Widd's `time-literals` library, he provide a way to seamlessly move time data from ClojureScript ↔ Clojure ↔ edn (or any other serialized transmission/storage). This will be important in Part 2 of *Storing Time*.

First `require` `cljc.java-time` and parse a string of numbers in a date format, `2015-02-12` in this case.

```
(require '[cljc.java-time.local-date :as ld])
(def date-string "2015-02-12")

(ld/parse date-string)
```

&rArr; `[java.time.LocalDate, "0x59cd2839", "2015-02-12"]`

Now that the string is a `java.time.LocalDate` object, it's easy to parse and manipulate. Add 90 days to `2015-02-12`:

```
(defn add-90-days [date]
  (-> (ld/parse date)
      (ld/plus-days 90)
      str))

(add-90-days date-string)
```

&rArr; `"2015-05-13"`

Getting today's year is straight forward, `(ld/get-year (ld/now))` &rArr; `2020`, but getting today's *[day of the week](https://docs.oracle.com/javase/8/docs/api/java/time/DayOfWeek.html)* requires a little more work because these operations all return different types of objects.

* `(ld/get-year (ld/now))` returns a `java.lang.Integer`
* `ld/now` returns a `java.time.LocalDate`
* `ld/get-day-of-week` returns a `java.time.DayOfWeek`

Therefore: `(ld/get-day-of-week (ld/now))` &rArr; `[java.time.DayOfWeek, "0x3555cd3d", "SUNDAY"]`, which isn't pretty. To work with this [Enum type](https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html), `day-of-week` must also be required:

```
(require '[cljc.java-time.day-of-week :as dow])
(dow/to-string (ld/get-day-of-week (ld/now)))
```

&rArr; `"SUNDAY"`

Those are the basics of working with Java Time. Part II will explore the nuances of storing this time in a portable text format, `edn`. It will also look at issues related to stored time when conducting digital archeology and the depiction of time digital time and memory in cinema.

<!-- Now see [Part II](/posts/2020-07-20-storing-time-2.html) of Storing Time. It will cover the depiction of digital time in cinema, more specifics on encoding time in an `edn` file, and issues of stored time when conducting digital archeology. -->
