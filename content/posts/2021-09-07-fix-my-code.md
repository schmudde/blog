---
title: Fix My Code
description: Engineering alone can't fix what's wrong with the internet.
author: David Schmudde
author-email: d@schmud.de
author-url: http://schmud.de
author-github: schmudde
author-twitter: dschmudde
location: Berlin, Germany
date-created: 2021-09-03
date-modified: 2022-03-28
date-published: 2021-09-07
in-language: en
keywords: software preservation, net.art, digital art, engineering, programming
tags:
 - informatics
 - sts
 - suchness
 - review
---

**Engineering alone can't fix what's wrong with the internet - a short reflection on the lessons we learn by caring for the software we create.**

<figure class="fullwidth">
![Net Art Generator Output](/img/2021-09-07-fix-my-code/double-nag.png)Images from the *Net Art Generator* by the author
</figure>

Interest in interactive digital art paralleled the growth of the internet throughout the 1990s. The arrival of *Wired* magazine signaled a broad interest in our emerging networked society, while the debut of *ArtByte* five years later narrowed the focus to networked culture. Rhizome, an organization established to help explore the internet as a creative medium, built the ArtBase in 1999 to preserve net.art[^netart] artworks.

[^netart]: net.art is artwork that is made for the internet. net.art experiences happen in cyberspace - an ephemeral place between machines. See [About Gallery 404](http://www.netart.today/pages/about.html) for more historical context.

1999 also marked the third version of Cornelia Sollfrank's *Net Art Generator* (NAG). This particular work is the focus of a new book by Cornelia Sollfrank and Winnie Soon titled *[Fix My Code](https://eeclectic.de/produkt/fix-my-code/)*.[^fix-my-code] The artist (Sollfrank) and the creative technologist and coder (Soon) confront issues of authenticity and ownership through a series of insightful conversations as they update the NAG's code for the 21st century.

[^fix-my-code]: Sollfrank, Cornelia and Winnie Soon, *[Fix My Code](https://eeclectic.de/produkt/fix-my-code/)*, EECLECTIC, 2021.<br /> !["Fix My Code" cove](/img/2021-09-07-fix-my-code/fix-my-code.jpg)

The effort chronicled in *Fix My Code* parallels the [acquisition of the original server](https://zkm.de/en/artwork/net-art-generator) by The ZKM Center for Art and Media. Although the institution now holds the responsibility of caring for the artifact and its code, the public version at [net.art-generator.com](https://net.art-generator.com/) still requires Sollfrank's attention. This is the nature of software as a medium.[^problems]

[^problems]: The NAG, and other net.art artworks, present three preservation challenges. 1. The original hardware can fail. Even components less than two decades old can be difficult to replace. 2. While the experience of the NAG doesn't necessarily rely on the original hardware - it is essentially a webpage on the internet - adapting the software to a new execution environment will require changes in the code. 3. The NAG relies on other services on the internet. When they change, it breaks the NAG, thus requiring more updates to the code.

*Fix My Code* revels in the fragility of all our digital services. It takes issues that normally seem removed from everyday life - intellectual property, the preservation of cultural objects, and the way social thinking influences computer languages - and turns them into practical problems the two women must solve as they attempt to keep the NAG running well into the 21st century.

## The Boundaries of Utopia...

**... and the unnatural limits of intellectual property**

Sollfrank is a net.art artist who embraced the 1990s supposition that the internet was a new independent space. "For me, working with the internet had a lot to do with institutional critique," she reflects in *Fix My Code*.

The unique properties of cyberspace have been observed by many. The *institution* and the *individual* have the same structural affordances on the internet: a website, an address, an eMail account, etc.... When searching for "Jackson Pollock" on DuckDuckGo today, his *individual*  website ranks ahead of the website of the Museum of Modern Art (MoMA), [the first *institution* to acquire a Pollock painting](https://www.moma.org/artists/4675). This generation's Jackson Pollock no longer needs the support of MoMA and Peggy Guggenheim[^guggenheim] to get their work in front of other people. At least in theory.

[^guggenheim]: &ldquo;Pollock’s first solo show was held at Peggy Guggenheim’s Art of This Century museum/gallery, New York, in 1943. Peggy Guggenheim gave him a contract that lasted through 1947, permitting him to devote all his time to painting.&rdquo; ~ "[Jackson Pollock](https://www.guggenheim-venice.it/en/art/artists/jackson-pollock/)". 2021. *Peggy Guggenheim Collection*.

In practice, the fresh internet landscape was terraformed throughout the 1990s to look like the old world. It became another territory to be conquered and many net.art artists played along. Borders were drawn along the old lines of authorship and intellectual property.[^scarcity] The Guggenheim-types arrived and speculative money created artificial boundaries. After all, there is nothing in the laws of physics nor in the discoveries of computer science nor in the human-made software that underpins the internet itself that prevents Facebook messages showing up in Twitter feeds. It's simply the result of medieval thinking that creates new kings and new kingdoms.[^internet]

[^scarcity]: The most absurd borders continue to be those that enforce an artificial digital scarcity where none actually exists.

[^internet]: The internet existed for about a quarter century before the arrival of speculative capital. I am not suggesting that the old internet was somehow better. But I am suggesting that a different internet can exist in the future. Let history be our guide.

The NAG is produced from a fundamentally different social perspective. This perspective is implicitly embodied by the artwork and explicitly stated in the artwork’s license:[^license]

> The nag is under the GPL and as free software it does not really correspond to traditional notions of property, especially the ones in the art world. The idea behind free software is that is belongs to the public, and everybody who has the skills to help maintain or improve it can and should do so.

[^license]: Sollfrank, Cornelia and Winnie Soon, *Fix My Code* (EECLECTIC, 2021), chapter 4.

## Notions of Care...

**... and the preservation of cultural objects**

Networked software operates in an incredibly dynamic ecosystem. A small change in one place can necessitate a code fix somewhere else. The tools that help programmers mitigate residual issues are largely ineffective.

The forces behind the ebbs and flows of the network are bigger than the network itself. The NAG bared witness to the [dot-com](https://en.wikipedia.org/wiki/Dot-com_bubble) boom and bust[^dot-com], the fall of the popular AltaVista search engine, and the capitalization of its rival, Google.[^altavista-google] The NAG tossed around like a small buoy in the ocean; its original reliance on AltaVista would eventually necessitate an update to work with Google to stay afloat.[^system-map]

[^dot-com]: The market reset (aka &ldquo;bust&rdquo;) happened between 2000-2002

[^altavista-google]: At the advent of the dot-com bust, AltaVista was more than twice as popular as Google. &ldquo;While AltaVista reaches 17.7% of Web users, Google gets just 7%, according to the latest Media Metrix numbers.&rdquo;&dagger; Sometimes a technology wins on the merits of its performance and usability. Sometimes a technology wins based on a myriad of other social, cultural, and economic factors. Google's fortunes in the search engine marketplace changed dramatically after the post-bust reallocation of capital. &dagger;Patsuris, Penelope. "Don't Count AltaVista Out Yet". *Forbes*. (October 20, 2000).

[^system-map]: A diagram of the Net Art Generator's API interface from *Fix My Code*. The current Google integration was once programmed to work with AltaVista. ![](/img/2021-09-07-fix-my-code/nag-05-search-api.png)

Building software is a bounded process in theory. But in practice, keeping software running requires an open-ended commitment to maintenance. Updating software is not limited to the professionals. Even laypeople are bombarded by endless "software updates." Rather than pretending to take these as finite events with well-engineered boundaries, it's more honest to humanize the term - software requires a continual practice of *care*.[^care] Sollfrank introduces the notion of care by embracing all the fuzziness and humanity of the term:[^care-quote]

[^care]: See Annet Dekker's “Networks of Care,” in *Collecting and Conserving Net Art* (London: Routledge, 2018).

> Caring means worrying, being ready to take responsibility, being on the lookout for new types of relations. This attitude of care also contributes to the establishment of new forms of knowledge, knowledge that is interested not only in observation and representation but also in transformation – in forging relations with things, in being affected, and thus in changing itself and the world in a process of co-transformation. Joan C. Tronto and Berenice Fisher have defined caring as a way of maintaining, continuing, and repairing our world for a better life.

[^care-quote]: Sollfrank, Cornelia and Winnie Soon, *Fix My Code* (EECLECTIC, 2021), chapter 4.

Software-based artworks are a perfect place to apply this type of thinking. Cultural objects offer value that beguile bean counters; their only explicit value is in the communal activity around them. Cultural objects are things we must all care for if we want them to endure.

Today ZKM is caring for a custom server that can host the NAG.[^nag-2008] The software remains free and is maintained by a handful of dedicated individuals. But some software preservationists see a future where care is a distributed resource. It can be seen in the preservation of [video games](https://archive.org/details/classicpcgames), [operating systems](https://github.com/PDP-10/its), and [activity on internet protocols](https://www.usenetarchives.com). Cultural objects on the internet are our inheritance. It is up to us to maintain them.[^archive-team]

[^nag-2008]: ![The NAG at ZKM](/img/2021-09-07-fix-my-code/MM_00134_sollfrank_nag-kunstmaschine_001.jpg)<br /> The "Net Art Generator" exhibited at ZKM in 2008. The server and its logs were presented in a wooden box as part of a larger presentation of the work. (Photo: Nina Pieroth)

[^archive-team]: This may be practically embodied by the [Archive Team](https://wiki.archiveteam.org). This group, a self-described &ldquo;loose collective of rogue archivists, programmers, writers and loudmouths,&rdquo; has saved much of our digital heritage from certain oblivion.<br /> ![Archive Team logo](https://wiki.archiveteam.org/images/Archiveteamsmall.png?959ea)

These sentiments run afoul of traditional notions of intellectual property. *Fix My Code* is forced to address this head on as the NAG bumps into institutional conventions from both active collaborators like ZKM and passive partners like Google.

Google was born from the same generation as the NAG, but it has very different cultural values. The company hasn't approved requests for a cultural use exemption made by the NAG team. Thus the NAG can only generate about 100 net.art artworks a day before being cut off by the search engine giant. The company's inaction is yet another reminder that they are no more a library than Facebook is a town square.

## Analog Society...

**... and the shaping of digital technology**

The burst of the dot-com bubble is often seen as an important demarcation in the network's history - a dose of reality that tempered the [irrational exuberance](https://en.wikipedia.org/wiki/Irrational_exuberance). The fallout is still present on today's internet. For instance, the [landing page of *ArtByte* magazine](https://www.artbyte.com/) remains unchanged since the bubble burst:

> Thank you for visiting. As many of their readers are aware, ArtByte ceased publication at the end of 2001. As the digital culture overwhelmed, and has eventually become the mainstream, the digital world is now part of our everyday lives.

The digital world has only become more woven into our daily activity since this was written. The capital returned to Silicon Valley in short order to take advantage and internet-enabled technology companies are among the most profitable in the world.

This was supposed to be the era of the egalitarian internet - a network that brings opportunity far and wide, where no one knows you're a dog.[^dog] But wealth inequality continues to soar. We live in the era of free information that was promised. We also live in an era where democratic processes buckle under the onslaught of misinformation. These misinformation initiatives rely on manipulating the  algorithms - secret intellectual property - that drive engagement on sites like Facebook.

[^dog]: &ldquo;On the Internet, nobody knows you're a dog.&rdquo; Anonymity on the 1990s internet represented a disembodied utopia where people could be whoever they wanted, enjoy the free exchange of ideas, and equal access to opportunities. What mattered was the amount of personal initiative and the quality of the individual output. ![On the Internet, nobody knows you're a dog.](/img/2021-09-07-fix-my-code/nobody-knows-youre-a-dog.jpg)

Compare this to the tactics of information warfare from the early 21st century. On the open internet, the goal was to restrict the flow of information rather than flood the network with poor information. This strategy is captured in an interview between Sollfrank and an artificial hacker named S0pht in the [July/August 2000 issue](http://archive.neural.it/init/default/show/2338) of *ArtByte*.[^artbyte]

> Power and its representations are no longer located in the real world but have been shifted onto the Net. That's why the resistance against power has also got to take place within the Net. [...] So that's what I do, a kind of electronic civil disobedience. What it's about is blocking the flow of information.

[^artbyte]: "[Unauthorized Access: Cornelia Sollfrank's hunt for the mythical female hacker](https://web.archive.org/web/20030609170624/http://www.artbyte.com/mag/jul_aug_00/femalehackers.html)". *ArtByte*. July/August 2000. ![Cover of ArtByte July/August 2000](/img/2021-09-07-fix-my-code/artbyte-2020.jpg)

The internet described by Sopht and embodied by the NAG *illustrates* the socio-technical peculiarities of the network nearly twenty-five years ago. An act of care - updating the NAG - *animates* the distinction.

Winnie Soon, the co-author of *Fix My Code*, worked with the material of the era when she updated the NAG's codebase. The NAG was written in a programming language called Perl and Soon was unfamiliar with the syntax.

The hardest part about learning a new language is finding a good place to ask questions. Perl has a core community of passionate developers like many languages. And like any community, they have their quirks, their local celebrities, and their in-jokes.

Perl was a trendy language when the NAG was originally written, but it has since lust its luster. Historical antecedents can both impede and enrich a language. Soon describes the process of navigating the community and this history in *Fix My Code*, reminding us that the priorities of a particular language often have more to do with its communal dynamics than a list of empirical findings from the field of computer science.

Through a multi-layered, honest self-assessment, *Fix My Code* takes a holistic and convivial view of maintaining software. Software is woven into our daily lives. It has been shaping us for more than a generation. To care for these artifacts is to understand them. And to understand them is to understand our shared cultural context.
