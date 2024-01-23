---
title: Untangling Non-Linearity
description: How the simple link became the foundation for artificial intelligence and all dynamic media.
author: David Schmudde
author-email: d@schmud.de
author-url: http://schmud.de
author-github: schmudde
author-twitter: dschmudde
location: Turin, Italy
date-created: 2024-01-07
date-modified: 2024-01-23
date-published: 2024-01-23
in-language: en
keywords: linkedLists, generativeArt
tags:
 - sts
 - informatics
---


<figure>
![*Achsenparalleler Polygonzug* Frieder Nake (1965)](/img/2024-01-23-untangling-non-linearity/Nake1965AchsenparallelerPolygonzug.jpg) Frieder Nake *[Achsenparalleler Polygonzug](https://dam.org/museum/artists_ui/artists/nake-frieder/compart-er56/)* (1965) photo credit: DAM projects GmbH.
</figure>

The power of computing comes from its idiosyncratic inflexibility. Every programmer will tell you that the computer does exactly what you tell it to - even when it’s not working as expected. But the power of the digital computer remains self-evident in spite of these limitations. There are two primary reasons:

1. Algorithms: computers execute perfectly reproducible runs[^perfect]
2. Data: computers can reliably transform information from one kind to another with perfect fidelity

[^perfect]: Ignoring rare and cosmic events like random [bit flips](https://radiolab.org/podcast/bit-flip).

Theories of computation are mostly centered on the first issue. Alan Turing's foundational 1927 paper focuses on how the Turing Machines execute algorithms. Even though the machines store their state as data, Turing's emphasis is on how the instructions change the machine from one state to another.[^turing]

[^turing]: ![Turing Machine, reconstructed by Mike Davey as seen at Go Ask ALICE at Harvard University](/img/2024-01-23-untangling-non-linearity/turing-machine-model-2012.jpg) The sequence of symbols on a tape that run through Turing's hypothetical machine are data. But the machine's *m-configuration* (Turing's term), which is set after every read from the tape, must also be stored. The outcome of reading the symbol off the tape is co-dependent on the m-configuration. But Turing spends little time describing the physical properties of m-configuration storage. They could be mechanical switches, electrons in silicon, or a configuration of neurons in the brain. The paper is rightfully focused on knowing whether or not an algorithm will terminate in advance of its execution. [Image: Mike Davey *[Turing Machine reconstruction as seen at Go Ask ALICE at Harvard University](https://commons.wikimedia.org/wiki/File:Turing_Machine_Model_Davey_2012.jpg)* (2012)&nbsp;<span property="license" xmlns:cc="http://creativecommons.org/ns#" xmlns:dct="http://purl.org/dc/terms/"><a href="https://creativecommons.org/licenses/by/3.0/deed.en" target="_blank" rel="license noopener noreferrer" class="no-tufte-underline">CC BY 3.0 Deed&nbsp;<i class="fab fa-creative-commons"></i>&nbsp;<i class="fab fa-creative-commons-by"></i>&nbsp;</a></span>]

Algorithmic experimentation was also the focus of early computer art. *Achsenparalleler Polygonzug*, pictured above, was Nake's expression of *generative aesthetics*. One of his contemporaries, Vera Molnár,[^ordre-et-chaos] even produced drawings using a method called *[imaginary machine](https://www.artnews.com/art-news/news/vera-molnar-computer-art-dead-1234688785/)* (i.e. algorithms executed by hand) before she had access to a computer.

Adding a bit of randomness to the algorithm creates an infinite set of aesthetic variations. Generative artists like Nake and Molnár often curate from this point by selecting the best outcomes. This intuitive practice is still common today. But this type of artwork doesn't leverage the second aspect of digital computers that make them so powerful: manipulating information with perfect fidelity.

[^ordre-et-chaos]: ![Vera Molnár *Déambulation entre ordre et chaos* (1975)](/img/2024-01-23-untangling-non-linearity/vera-molnár-déambulation-entre-ordre-et-chaos-1975.png) Vera Molnár *[Déambulation entre ordre et chaos](https://www.centrepompidou.fr/fr/ressources/oeuvre/c6rb9XA)* (1975) <span property="license">&nbsp;<i class="far fa-copyright"></i> Adagp, Paris; photo credit: Georges Meguerditchian - Centre Pompidou</span>

When algorithms are combined with data, something empirical can be said about the information and how it was derived. Explicit connections can be made between bits of information or even between information as it changed over time. The connections may be inferences, causal, or even oblique. In any case, they are deterministic. Meaning it's possible to know how and why the machine independently brought two pieces of information together.

## Linking Ideas

The earliest forays into artificial intelligence by Allen Newell, Cliff Shaw, and Herbert A. Simon studied the way in which a machine could autonomously create inferences between statements. They created the Information Processing Language (IPL) in the mid-1950s to tackle this problem. The language's flexibility laid the foundation to a collection of heuristics that became known as Logic Theorist - a software built to solve mathematical proofs.[^logic-theorist] Not only was this the first time a non-human ever successfully performed such a task, Logic Theorist even came up with novel solutions and refinements to existing proofs.[^logic-theorist-2]

[^logic-theorist]: The JOHNNIAC computer, named after John von Neumann, ran Logic Theorist. The software successfully used heuristic methods to prove 38 of the first 52 theorems in *Principia Mathematica*, a significant feat considering the impracticality of trying every possible proof among trillions of options.

[^logic-theorist-2]: While undoubtedly a landmark in artificial intelligence and our general understanding of intelligence, Logic Theorist did not seem to impress when presented at the first ever Dartmouth Summer Research Project on Artificial Intelligence according to an account in Pamela McCorduck's book, *Machines Who Think*.

They accomplished this feat by representing the rules of inference as elements in a list. More importantly, IPL allowed the researchers to use a flexible type of list called a linked list. This concept will come up again and again in everyday computer technology, so it's important to understand why this simple structure enabled such a significant breakthrough. A typical representation of a list is a series of sequential items:

![](/img/2024-01-23-untangling-non-linearity/array-sequential.svg)

But the IPL team represented every item in the list as a linked element. This made it easy for the machine to traverse the list of inferences and modify each step in a mathematical proof by drawing a new arrow. It's the difference between doing the work of pointing to the thing you want versus actually moving everything around so you can grab the thing you want.

![](/img/2024-01-23-untangling-non-linearity/linked-list-1.svg)

Memory and processing cycles were scarce in early computing. So any optimization was a major victory. But it turns out that flexible links of information are more than just an implementation convenience. Early forays into artificial intelligence are just one point of evidence. *Hypertext* soon emerged as a way for people to create links in networks of information. This idea born of the 1960s was so profound that it would become the foundation of the World Wide Web decades later. As the man who named the concept, Ted Nelson, explains,

> By "hypertext" I mean non-sequential writing -- text that branches and allows choices to the reader, best read at an interactive screen. As popularly conceived, this is a series of text chunks connected by links which offer the reader different pathways.[^nelson]

[^nelson]: Ted Nelson, *Literary Machines*. (Swarthmore, PA: Self-published, 1981) via George P. Landow, *Hypertext 3.0: Critical Theory and New Media in an Era of Globalization* (Baltimore, MA: The Johns Hopkins University Press, 2006), 2-3.

## Non-linear Editing

Linked information pervades the production and consumption of media today. Filmmaking was the last of the legacy media formats to be impacted by this sea change. Although the flexibility of digital filmmaking existed throughout the 1990s, 2003's *Cold Mountain*[^cold-mountain] marks an inflection point. It was the first film of its scale to be edited on off-the-shelf Apple Macintosh G4s using software that cost under $1000. The editor, Walter Murch,[^murch-filmography] was recognized with an Academy Award nomination, but it's really his introspective process that provides the most lasting impact.

[^cold-mountain]: !["The Coldest Mountain" film poster](/img/2024-01-23-untangling-non-linearity/cold-mountain.jpg)

[^murch-filmography]: [Select Cuts by Walter Murch](https://web.archive.org/web/20090304025924/http://www.apple.com/pro/profiles/murch/index2.html): “The Rain People” (1969), sound montage &sect; “The Godfather” (1972), supervising sound editor &sect; “American Graffiti” (1973), sound montage &sect; “The Conversation” (1974), film and sound editing &sect; “Julia” (1977), film editor &sect; “Apocalypse Now” (1979), film editor, sound design; Oscar nomination for picture editing; Oscar for sound editing &sect; “The Unbearable Lightness of Being” (1987), supervising film editor &sect; “The English Patient” (1996), film editor; first editor ever awarded Oscars for film and sound editing in same picture &sect; “Touch of Evil” (1998), restoration film editing and sound &sect; “The Talented Mr. Ripley” (1999), editor &sect; “Apocalypse Now Redux” (2001), film editor

First, let's set a few baseline number to understand what we're talking about when a team edits a film like *Cold Mountain*. Murch estimates[^murch-interview-1] that they

[^murch-interview-1]: Apple, Inc. “[Walter Murch: An Interview with the Editor of ‘Cold Mountain.’](https://web.archive.org/web/20090404083736/http://apple.com/pro/profiles/murch/index3.html.)” Apple - Pro - Profiles - Walter Murch, p. 3. Accessed January 7, 2024.

> shot and printed 600,000 feet of film, which is about 113 hours of material. The film is 2 hours 30 minutes long, so that’s a 30 or 40 to 1 ratio. [The first cut] was over 5 hours long. So you find more inventive ways to compress the story to find out what can be eliminated that not only doesn’t affect the story, but actually, by its elimination, improves things by putting into juxtaposition things that formerly were not. It was a very complex orchestration, shrinking it by half.

Let's first consider how someone had to cut 600,000 feet of film before the advent of digital tooling. A person had to rewind and fast forward through footage, catalog and mount reels of film, and physically splice celluloid in traditional film editing. If two clips were on the same reel, the process is relatively straight forward.

![](/img/2024-01-23-untangling-non-linearity/film-edit-1.svg)

Note that moving from Clip A to Clip B is a physical act of moving through time - both the time of the film (usually in fast-forward) and the time of the editor.[^flatbed]

[^flatbed]: Such edits may be done on a Steenbeck, a popular 20th century film editing station. ![R Freeman "Steenbeck film editing machine" (2007)](/img/2024-01-23-untangling-non-linearity/steenbeck.jpg)R Freeman *[Steenbeck film editing machine](https://www.flickr.com/photos/bbcbob/1572530542/)* (2007)&nbsp;<span property="license" xmlns:cc="http://creativecommons.org/ns#" xmlns:dct="http://purl.org/dc/terms/"><a href="https://creativecommons.org/licenses/by-nc-sa/2.0/" target="_blank" rel="license noopener noreferrer" class="no-tufte-underline">CC BY-NC-SA 2.0 Deed&nbsp;<i class="fab fa-creative-commons"></i>&nbsp;<i class="fab fa-creative-commons-by"></i>&nbsp;<i class="fab fa-creative-commons-sa"></i></a></span>

Adding a clip from another reel means consulting a reference table, mounting the correct reel, and once again physically moving through time to arrive at the desired spot.

![Mounting and editing off a second reel](/img/2024-01-23-untangling-non-linearity/film-edit-2.svg)

This act of moving through film strips *sequentially*, frame-by-frame, relented to digital *non-linear editing* as soon as computers became capable. These days editors like Murch can instantly jump to any moment within any footage captured and cataloged. But the name *non-linear editing* partially obfuscates the power of the technique. Films were already non-linear in some sense. Almost all contemporary films use edits to indicate some instantaneous leap in time. That leap may be to a parallel timeline, a timeline in the past, or a timeline in the future.

The significance of the digital technique is how trivial an edit can be made - dramatically lowering the cost of experimentation. Much like Logic Theorist a half-century earlier, Murch can easily try different routes to optimize a 5 hour long rough cut into a 2.5 hour long final cut.[^ai-psychology]

[^ai-psychology]: In fact, Newell and Simon's work fueled hope that a new field of computational psychology would emerge. Logic Theorist and their subsequent General Problem Solver software allowed a person to observe the exact steps an autonomous being used to solve a problem. They hoped that these techniques would provide insights into how an artist made art, for example. As computer scientist Philip Agre observed in *Computation and Human Experience*, &ldquo;In one of the papers in that volume Newell and Simon pursued the input-output matching view of computational theorizing, comparing the operation of the General Problem Solver (GPS) program step by step against the reports of an experimental subject. This was an extraordinary advance in psychological method: computer programming had made it imaginable to explain singular events, not just statistical averages over large populations.&rdquo;

But 113 hours of film is *a lot* of data. Especially 20 years ago when Murch cut *Cold Mountain* on far less powerful machines than we have today. The trick is that the computer didn't have to move footage around. It simply needed to link to files and add start and stop times as metadata. Here are the same three clips edited on a non-linear system:

![](/img/2024-01-23-untangling-non-linearity/nle.svg)

This diagram looks more complex than the traditional edits above. But all of this detail is automatically happening under the hood. So a film editor doesn't have to think about these things. But let's take a peek and get the gist of what's actually happening.

## Linked Lists

The easiest way to demonstrate the power of linked list is to "edit" an element in memory. As we saw earlier, it's trivial to arrange sequential elements in memory. But what happens if we want to simply take the thing at the end of the list and put it in the beginning? It's at least three edits if we want to keep the order of the sequence.

![](/img/2024-01-23-untangling-non-linearity/array.svg)

The first edit is holding that *G* somewhere while we do the work of moving everything down one space. Then each element must be moved to make room for the next element. And finally we can place the *G* where we want it, at the beginning of the list.

Here's where the flexibility of linking information should become self-evident. Here's our original list:

![](/img/2024-01-23-untangling-non-linearity/linked-list-1.svg)

And here is the updated list. *G* now links to *A* and *F* now terminates as the end of the list.

![](/img/2024-01-23-untangling-non-linearity/linked-list-2.svg)

It's simply less work.[^tradeoffs] Less work and more flexibility affords experimentation for both an early 1950s computer and a seasoned film editor. Murch later reflected,[^murch-interview-2]

[^tradeoffs]: There are trade-offs to all this, of course. But this isn't an introduction to computer science. It's simply meant to show how a simple idea in computer science has propagated across all media, both new (hypertext on the World Wide Web) and old (filmmaking).

[^murch-interview-2]: Apple, Inc. “[Walter Murch: An Interview with the Editor of ‘Cold Mountain.](https://web.archive.org/web/20090404055425/http://www.apple.com/pro/profiles/murch/index4.html)’” Apple - Pro - Profiles - Walter Murch, p. 3. Accessed January 7, 2024.

> When you actually had to make the cut physically on film, you naturally tended to think more about what you were about to do. [...] When I was in grade school they made us write our essays in ink for the same reason. Pencil was too easy to erase. The other “missing” advantage to linear editing was the natural integration of repeatedly scanning through rolls of film to get to a shot you wanted. Inevitably, before you ever got there, you found something that was better than what you had in mind. With random access, you immediately get what you want. Which may not be what you need.

Herein lies the drawback to all this instantaneous, linked data. It doesn't easily provide information. And depending on the editor who presents it to us, we may get what we want, but not what we need.

## Going Beyond the Frame

*Beyond the Frame* was always a place for me to link together disparate ideas. The blogging medium works well because it is faster to publish than a book or a magazine article. And yet I have to refine my ideas because they do, indeed, get published.

My interests and priorities change over time. But the links between old ideas and new endeavors only deepen. The last time I mentioned Murch on *Beyond the Frame* was [over 10 years ago](/posts/archive/5334225085.html) in a completely different context. New perspectives provide fertile ground for new connections. That's why I'm always thinking about how to improve the structure of information in this blog and refine its presentation;[^old-header] some readers may have noticed that the header design and logo[^license] has changed for 2024.

Last year I contracted Torinese designer [Alma Gianarro](https://almagianarro.com/) to come up with something that referenced the boxes and arrows of a linked list. When she showed me her initial concepts, I was surprised to see how much they reminded me of early computational art by Vera Molnár and Frieder Nake. So we took the theme and ran with it.

[^old-header]:![Composition, time dilation, and an opportunity for the sublime and the serendipitous](/img/2024-01-23-untangling-non-linearity/old-btf-header.png) At one point, Beyond the Frame focused squarely on [new media](https://artfilemagazine.com/what-is-new-media-art/); computation was backgrounded. Over time computation became the central theme and culture became the connective tissue. The slogan was "composition, time dilation, and an opportunity for the sublime and the serendipitous."

[^license]: ![Logo For Beyond the Frame](/img/btf-logo.svg)The new Beyond the Frame logo, a work-for-hire by [Alma Gianarro](https://almagianarro.com/).<span property="license" xmlns:cc="http://creativecommons.org/ns#" xmlns:dct="http://purl.org/dc/terms/"><a property="dct:title" rel="cc:attributionURL" href="https://schmud.de/img/btf-logo.svg">The Beyond the Frame Logo</a> by <a rel="cc:attributionURL dct:creator" property="cc:attributionName" href="https://schmud.de">Alma Gianarro</a> is licensed under <a href="http://creativecommons.org/licenses/by-sa/4.0/?ref=chooser-v1" target="_blank" rel="license noopener noreferrer" class="no-tufte-underline">CC BY-SA 4.0&nbsp;<i class="fab fa-creative-commons"></i>&nbsp;<i class="fab fa-creative-commons-by"></i>&nbsp;<i class="fab fa-creative-commons-sa"></i></a></span>

The connection to early digital art was unintentional. As mentioned earlier, this type of art relies heavily on generative processes that don't immediately reflect the themes I wanted to embody in the logo. But then I realized that Alma's design surfaced the link between [culture](/tags/sts.html) and [technology](/tags/informatics.html) that runs through my life and this blog. From computation to composition and fractals to film - all disciplines intersect at some point.
