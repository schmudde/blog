---
title: Open Open Source Communities
description:
author: David Schmudde
author-email: d@schmud.de
author-url: http://schmud.de
author-github: schmudde
author-twitter: dschmudde
location: Berlin, Germany
date-created: 2020-08-26
date-modified: 2020-08-26
date-published: 2020-08-26
in-language: en
keywords: open source,
tags:
 - doing
 - suchness
---

![](/img/2020-08-27-open-source-community/mit-mc.jpg)[^kl10-mc]

[^kl10-mc]: {-} The KL10 PDP-10 at MIT. It ran ITS [from 1975-1988](https://gunkies.org/wiki/Incompatible_Timesharing_System). Photograph by [Lars Brinkhoff](http://lars.nocrew.org/).

## Open Collaboration

Wikipedia may be the fullest realization of a truly open community where anyone can contribute. It's proof positive of radical openness, an idea that dates back to the ITS at MIT in the 1960s.[^its] In both systems, no one person owns any one file and the system's content can be improved by people accessing it from the network. Wikipedia brought these ideas out of the lab and into common parlance to astonishing effect.

[^its]: The Incompatible Timesharing System (ITS) was created at the the MIT Artificial Intelligence Laboratory. It first [became operational in 1967](https://gunkies.org/wiki/Incompatible_Timesharing_System). As the name implies, the system was an ideological response to the MIT Compatible Time-Sharing System (CTSS). The influential operating system saw the first versions of Emacs, a text editor used by many to this day, popular games like Zork and the multi-player Maze War, and languages such as Maclisp and Scheme. Astonishingly, you can access a [real ITS system](https://www.livingcomputers.org/Computer-Collection/Online-Systems.aspx) connected to the internet running on an actual PDP-10 at the Living Computer Museum. Simply [click here](https://gunkies.org/wiki/Incompatible_Timesharing_System) and sign up!

But a system that is technically open can be still be closed in spirit. This is where Wikimedia initiatives tend to fall short. On Wikidata, for example, painters that identify as women represent only 17% of all painters. This is the same rate of representation found at the Tate in the United Kingdom.[^nextjournal] Technical openness has provided no meaningful improvement over the notoriously closed world of art.

[^nextjournal]: These are my personal calculations. Work and data is available as a runnable notebook at *[The Guerrilla Girls: Equality and Activism in the Internet Age](https://nextjournal.com/schmudde/the-guerrilla-girls)*.

**Open technology hasn't solved the problem of gatekeeping that reflects conventional power structures**. The closed culture of Wikipedia/Wikimedia is just one prominent example.[^wikimedia] I can't say my own experience editing Wikidata has demonstrated anything different. I wanted to [edit the number of registered users/contributors on Wikidata](https://www.wikidata.org/w/index.php?title=Wikidata:Project_chat&oldid=prev&diff=1257291968#Wikidata:_edit_the_number_of_registered_users/contributors) because the entry is both incorrect and not cited. After finding nothing in the &ldquo;[help:items](https://www.wikidata.org/wiki/Help:Items)&rdquo; documentation, I asked for help editing &ldquo;semi-protected entries&rdquo; such as this one. [Hazard-SJ](https://www.wikidata.org/wiki/User:Hazard-SJ) offered an answer that embodies the generally unhelpful ambivalence towards new users.[^wikidata-answer]

[^wikimedia]: According to Wikimedia's own [2018 survey](https://meta.wikimedia.org/wiki/Community_Insights/2018_Report), only 10% of the contributors are women. ![](/img/2020-08-27-open-source-community/gender-wikimedia-2018.png) This has contributed to accusations of [unfair editing practices](https://www.fastcompany.com/90429161/wikipedia-still-hasnt-fixed-its-colossal-gender-gap) and [an unfriendly community](https://www.wired.com/story/using-artificial-intelligence-to-fix-wikipedias-gender-problem).

[^wikidata-answer]: To be fair, not every Wikimedia member answers this way. [the-erinaceous-one](https://www.wikidata.org/wiki/User:The-erinaceous-one) provided a complete and useful response to Nigeria's Madonna Onwuka when she asked about the [difference between Wikipedia and Wikidata](https://www.wikidata.org/wiki/Wikidata:Project_chat#Difference_between_Wikipedia_and_wikidata) just days later.

> Hi Schmudde, semi-protection is described in [the page protection policy](https://www.wikidata.org/wiki/Special:MyLanguage/Wikidata:Page_protection_policy).

That's it. Their answer is at least one step beyond RTFM.[^rtfm]

[^rtfm]: &ldquo;Read The Fucking Manual&rdquo;

## Open Open Collaboration

**An open open source community must make a coherent effort to include people from a variety of different backgrounds and experience levels.** The process can be frustratingly slow because change takes time. It's important to set realistic short-term goals, look for incentives, and reassess at regular intervals. Lots of strategies will not work, but consistent effort will lead to meaningful moments and eventual change. My personal highlight was as a lead instructor at [ClojureBridge NYC 2017](https://clojurebridge.org/events/2017-05-26-new-york-ny).[^pierre] I spent a weekend sharing a technology I care about with a hundred women eager to learn a new language. Win win!

[^pierre]: [Pierre de Lacaze](https://www.linkedin.com/in/pierre-de-lacaze-b11026b/) and [several organizers at LispNYC](http://www.lispnyc.org/) did much of the hard work of organizing this event.

**A culture of real openness must start early because a community's values become more deeply entrenched over time.** This also holds true for mentorship. While teaching others may cut into time spent coding or contributing, adding empowered contributors pays long term benefits.

[Contributor agreements](https://en.wikipedia.org/wiki/Contributor_License_Agreement) must be stated early and their clarity should not be presumed - especially over the span of a project's life. The [Clojure](https://clojure.org/) community experienced a significant disruption [around this issue](https://github.com/clojure/clojure-site/issues/327) and several valuable members of the community left.[^tellman] This problem [may not be unique to Clojure](https://lispcast.com/cognitect-clojure/), but it is noteworthy because it happened over a decade into the language's existence. It illustrates how a community's expectations can change even if the terms of contributing do not.

[^tellman]: Zach Tellman's exit from the community was particularly disappointing for me. He wrote one of my favorite books on programming, [*Elements of Clojure*](https://elementsofclojure.com/). It should be on every programmer’s reading list, regardless of their language of choice.

## Discourse Does Not Scale But Culture Does

A project's adoption is a prerequisite for any of these concerns.[^killer-app] Maintaining a constructive discourse can become increasingly difficult as more and more people arrive. The internet, with its myriad of tools and approaches, [offers little that makes this problem any easier](https://schmud.de/posts/2020-06-23-internet-community.html).[^missing-stair]

[^killer-app]: The Clojure community is relatively small compared to contemporary languages like Python. There are several reasons for this, but Python's adoption by the machine learning community has skyrocketed the language's popularity. A "killer app" like machine learning is the cornerstone of an open initiative's broad adoption.

[^missing-stair]: New terminology and management efforts appear all the time. [Arne Brasseur](https://lambdaisland.com/) recently brought the concept of a [missing stair](https://en.m.wikipedia.org/wiki/Missing_stair) to my attention. It is a term that identifies a person that everyone in the community knows is a problem, but tend to work around. The missing stair is especially dangerous to newcomers that do not have the institutional knowledge to step around. The problem has now been identified and formalized in various places, included the [Orogene Code of Conduct](https://github.com/orogene/orogene/blob/main/CODE_OF_CONDUCT.md#moderation).

Project stewards must find a way to make their contributors feel heard even though they cannot listen to everyone. This is where culture is everything. There exists no magic bullet and no great tool. There is only hard work and circumstance.

When Audrey Tang was appointed as the digital minister of Taiwan, she set out to build a "listening society."[^tang] I'm cautiously encouraged by her early results. Although the technology's application may be novel, it is rooted in the basic human character. She is finding creative ways to involve average citizens in the process of building a better country. People want to feel heard. They want their hard work to matter. Guiding an open community project is more than just a technological endeavor; it is the responsibility of building a culture larger than its parts.

[^tang]: Tristan Harris, "[Digital Democracy Is Within Reach (Episode 22)](https://www.humanetech.com/podcast/23-digital-democracy-is-within-reach)", podcast, *Your Undivided Attention*, 2020.
