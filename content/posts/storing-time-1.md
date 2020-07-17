---
title: Storing Time - Part 1
description:
author: David Schmudde
author-email: d@schmud.de
author-url: http://schmud.de
author-github: schmudde
author-twitter: dschmudde
location: Turin, Italy
date-created: 2020-07-14
date-modified: 2020-07-14
in-language: en
keywords: clojure, time, modeling
tags:
 - doing
 - science
 - personal
---

As I search for my next position, I had to overhaul my curriculum vitae. I store the information as an edn file[^edn] and use the data to generate specific CVs, resumes, and websites; a single source of truth for all the different formats that people request.

[^edn]: edn is an extensible data notation used to convey values. It is often used where one might use JSON, but it offers several advantages. More at the official [edn readme](https://github.com/edn-format/edn).

Information about time has been the most difficult to store. I want time values that are

1. **Easy to read and edit**: edit them in plain text.
2. **Easy to manipulate programmatically**: calculate a span of time, overlaps in time, etc....
3. **Flexible in how they're displayed**: display incomplete dates as year only, display dates with a month name (July) rather than a number (7).

*Storing Time* parts 1 & 2 will explore the problems of recording time, working with Java Time, and digital memory and archeology. I will also share my solutions in detail - which may be worth a skim even if you're not a programmer.

![](/img/2020-07-15-storing-time/june-cal-royal-psalter-13th-cen.jpg)[^cal]

[^cal]: {-} &ldquo;This manuscript page from a psalter uses the same format as calendar pages found in a typical book of hours. June is written at the top; the text includes the names of saints and feasts to be celebrated during that month.&rdquo; "[Manuscript Leaf With June Calendar, From A Royal Psalter](https://www.metmuseum.org/art/collection/search/466372)". *The Metropolitan Museum Of Art*, 13th century.

## Not All Time is Equal

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

But can the last assertion really true? Can `March 30` = `March 31` if I add a month to both? I'll test it in Clojure using the Java Time package:

```
(= (add-1-month "2015-03-30") (add-1-month "2015-03-31"))
```

&rArr; `true`

It is true, even though the equation has three distinct elements: `add-1-month`, `"2015-03-30"`, and `"2015-03-31"`. If I take each side of the equation as two *literal lists of elements*, they are not equal:[^literal]

[^literal]: To take things *literally* in Clojure, they must be quoted. Think about how we use quotes in English, when I claim Martin Luther King Jr. said "I have a dream," I mean it *literally*. Look closely at the code `'(add-1-month "2015-03-30")` and you'll see it is preceeded by a single quote.

```
(= '(add-1-month "2015-03-30") '(add-1-month "2015-03-31"))
```

&rArr; `false`

March 31st and March 30th are *literally* two different days and their distinction is very important! This is not captured in the time/arithmetic abstraction offered by Java Time. Even if the results are (arguably) correct, the metaphor of addition is imprecise.

## Other Ways of Modeling Time

Eric Evans talks about the difficulty of doing time math in *[Modelling Time](https://www.youtube.com/watch?v=T29WzvaPNc8)*. He suggests that arithmetic is the wrong abstraction. Evans describes taking inspiration from Clojure, a list processing language at heart, to develop a different abstraction.

* *Time* is a list of units. So July holds a list of days `July 1, July 2, ... July 31`.
* *Intervals* are where sequences meet. `July 1` meets `July 2`. `July` meets `August`. `2020` meets `2021` (if we're lucky). So determining the size of the interval is a matter of counting the number of items in a list.

The model is much more detailed and includes an implementation of [Allen's interval algebra](https://en.wikipedia.org/wiki/Allen%27s_interval_algebra). For our purposes, it's worth noting that Evans avoids many of the pitfalls of time arithmetic.

But even something like a regular sequence of intervals - like the tick of the clock - can be unintuitive. In [Date and Time are more difficult than you think](https://mozaicworks.com/blog/date-and-time-are-more-difficult-than-you-think-with-alex-bolboaca-and-adrian-bolboaca/), Alex Bolboaca and Adrian Bolboaca discuss time zones, the international date line, and relativistic time.

Relativistic time is a real mind-bender with practical consequences. For examples, engineers must take into account the speed of orbiting satellites and the curvature of spacetime when estimating GPS coordinates. To stay in lock-step with clocks on the Earth's surface, satellite clocks must tick 38 microseconds faster in aggregate every day. Errors in time-keeping can lead to kilometer-sized positioning errors that get worse over time.

Global positioning on your phone would be worthless if the clocks on the ground and the clocks in orbit did not observe the rules of Einstein's theory of relativity.

## The Basics of Java Time (in Clojure)

It's no stretch to say we deal with time *all the time* in our lives. When recording the dates of my previous exhibitions, teaching assignments, jobs, and publications, I wanted to a more robust format that will work years into the future. I chose Java Time even if the abstraction is imperfect because it is so ubiquitious. I will be able to store and work with my time data well into the future.

The easiest way to work with Java Time across Clojure, ClojreScript, and EDN is [Henry Widd](http://widdindustries.com/)'s `cljc.java-time`.[^interop] Widd dives into the details of Java Time in his talk [Cross Platform DateTime Awesomeness](https://www.youtube.com/watch?v=UFuL-ZDoB2U) at Clojure/north 2019. After adding the library to your `deps.edn` file (currently at `cljc.java-time {:mvn/version "0.1.11"}`), start with a few Java Time `LocalDate` basics.

[^interop]: Along with Widd's `time-literals` library, he provide a way to seamlessly move time data from ClojureScript ↔ Clojure ↔ edn (or any other serialized transmission/storage). This will be important in part 2.

First `require` the namespace and parse a well-formed string. The result is a `java.time.LocalDate`.

```
(require '[cljc.java-time.local-date :as ld])
(def date-string "2015-02-12")

(ld/parse date-string)
```

&rArr; `[java.time.LocalDate, "0x59cd2839", "2015-02-12"]`

Now that the string is a recognizable `java.time` object, it's easy to parse and manipulate.

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
