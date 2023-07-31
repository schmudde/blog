---
title: What If Data Is a Bad Idea?
description: TBD
author: David Schmudde
author-email: d@schmud.de
author-url: http://schmud.de
author-github: schmudde
author-twitter: dschmudde
location: Turin, Italy
date-created: 2023-04-30
date-modified: 2023-04-30
date-published:
in-language: en
keywords: kay, hickey, clojure
tags:
 - sts
 - informatics
 - tools
 - suchness
---

<figure class="fullwidth">
![](/img/2023-05-08-data-is-a-bad-idea/floppy-header-2.png)<span property="license"><a class="link no-tufte-underline" href="https://creativecommons.org/licenses/by/2.5/" rel="license"><i class="fab fa-creative-commons"></i>&nbsp;<i class="fab fa-creative-commons-zero"></i></a> [Software, Erweiterte Diagnose](https://www.si.edu/object/software-erweiterte-diagnose:nmah_1281092), [Software, IBM DOS Version 1.10](https://www.si.edu/object/software-ibm-dos-version-110:nmah_1281122)
</span>
</figure>

<div class="epigraph">
> What if "data" is a really bad idea?
>
><footer>
> ~ Alan Kay on *[Hacker News](https://news.ycombinator.com/item?id=11945722)* (June 21, 2016)
></footer>
</div>

The discussion between Alan Kay and Rich Hickey, two esteemed computer scientists, opened with this provocative question from Kay. It immediately derailed the conversation - like a physicist asking, "What if gravity is a bad idea?"[^kay]

[^kay]: Kay has made a career out of challenging fundamental assumptions. One of his Most famous quotes, "A shift in perspective is worth xx IQ points," embodies his guiding wisdom.![Alan Kay by Jean Baptiste](/img/2023-05-08-data-is-a-bad-idea/kay.jpg) <span property="license"><a class="link no-tufte-underline" href="https://creativecommons.org/licenses/by/2.0/" rel="license"><i class="fab fa-creative-commons"></i>&nbsp;<i class="fab fa-creative-commons-by"></i></a>Alan Kay by Jean Baptiste</span>

But I think there is something to this question about data. Before diving into the technical details, it's worth setting the stage by considering the political dimensions of data.

Data is an objectifying force. This characteristic is an asset when describing inert phenomenon such as the composition of soil or the properties of various metals. These are things we can act upon without ethical considerations.

In the natural world there is a distinction between **intelligent actors** and those things they **act upon**. But data cannot inherently express this distinction; a database that tracks widget production can also store information about the people who buy those widgets.

Databases turn actors - who are often human beings - into things to be acted upon. This is where data is definitely a bad idea. Not in the sense that Kay likely intended, but this tension underlies the technology since the Hollerith machine was first used to automate the population census in the 19th century.[^census]

[^census]: Dutch resistance members, including Willem Arondéus, Gerrit van der Veen, and Dr. [Frieda Belinfante](https://www.ushmm.org/collections/the-museums-collections/curators-corner/the-frieda-belinfante-collection), orchestrated [a bombing of the Amsterdam Population Registry](https://www.annefrank.org/en/timeline/128/the-resistance-attacks-the-population-register-of-amsterdam/") on Saturday, March 27, 1943. Their goal was to inhibit the Nazi's ability to track and deplort Jews and other targets of terror. The operation managed to destroy over 15% of the records but many of the particpants were later captured and executed by the Nazis (top). Noted privacy advocate and lawyer Lau Mazirel was a member of the Dutch resistance. (below)![](/img/2023-05-08-data-is-a-bad-idea/amsterdam-registry.jpg) Amsterdam public registry (28 March 1943) <span property="license"><a class="link no-tufte-underline" href="https://www.annefrank.org/en/timeline/128/the-resistance-attacks-the-population-register-of-amsterdam/" rel="license">[<i class="fab fa-creative-commons-pd-alt"></i>Amsterdam City Archives</a>]</span>![](/img/2023-05-08-data-is-a-bad-idea/lau-mazirel.jpg) Lau Mazirel (1907-1974) <span property="license">[<a class="link no-tufte-underline" href="https://www.vn.nl/lau-mazirel-streed-tegen-de-maatschappelijke-comedie/" rel="license">Collectie Van Gennep / IISG</a>]</span>

Representing human beings is hard. If you manage personal data and you're not struggling, you're not doing your job. While the underlying tension likely unresolvable, the rest of this
article will consider what concepts and tools are available to help mediate this problem. By the end of the article we may be able to answer Kay's question in the case of human identity and expression - which is, after all, why we use computers.

# What Kay Was Actually Saying

Granting agency to an intelligent actor mitigates the objectifying nature of data. For better or for worse, this conceit is what drives "cookie consent" popups on websites; you are granted agency every time you make a choice about which cookies are placed on your machine.

In practice we don't want to negotiate every time we encounter a new website. But the brittleness of data requires necessitates a strict protocol agreed upon in advance. Kay's notion of ambassadors[^ambassadors] are an advanced technology that would enable negotiation between two wholly independent automatons. Even if they don't use the same protocol; even if the presentation of the information is novel.

Both actors can preserve independence and agency if they initiate communication through ambassadors. Although many natural systems are this adaptive, ambassadors are difficult to implement in practice. How could *data* be this flexible? How could *data* bring the requisite context along for the ride?[^data] This is what Kay was likely gesturing towards when he mused that data may be a bad idea.

[^ambassadors]: Alan Kay is really thinking about objects interacting on a network. Currently, all network interactions  follow explicit protocols. Objects of the future, Kay believes, must be able to negotiate the exchange of data even if they come from completely unknown sources. in Eric Normand

[^data]: Usually data relies on the medium to provide context. The medium is the protocol. I can send you data through a perfect medium with perfect fidelity and all you receive is noise. Indeed - all data is noise unless you know how to interpret it.

It's fitting that Kay used a political term, ambassador, to position his idea. Few technologists are ready to grapple with the inherent political dimensions of their work. Data has a political dimension when amassed in large volumes that enables centralized control. I'm not sure if the centrifugal nature of data is "bad" or "good" but it is undoubtedly political.

Creating digital ambassadors addresses some of these core political issues of data. I'm not aware of anyone who has built a viable solution, including Key himself. However, new technologies have emerged that put us in a better position to address the political dimensions of data. These technologies don't focus on markets, like so many Web3[^web3] solutions, they focus on people.

[^web3]: Most so-called Web3 efforts attempt to graft a marketplace on top of data to decentralize this power structure. But few have an answer for the tendency of marketplaces to consolidate power. Combined with the political dimension of raw data and the proven power of network effects, this can  only lead to an unbelievable consolidation of power. For those who are not in power, data will seem like a bad idea.

# Data and Identity

Identity is a natural place to start when talking about people. This discussion assumes two premises: 1) data is naturally reductive due to the nature of the medium and 2) identity is almost always held in systems far outside the reach of the people they identify.

The second point is a natural result of the centrifugal force of data. But if we alter our relationship to data from "far" (in your warehouse) to "near" (in my hand) and "dead" (jealously hoarded in private warehouses)  to "living" (flowing through the commons), data could exhibit different political dimensions and be more expressive. A network of databases and data interpreters make data multi-dimensional, like the people the data is attempting to describe.

Philip Agre enumerated five characteristics of data that will help us achieve this repositioning. So-called "Living Data," must exhibit 1. a sense of *ownership*, 2. *error bars*, 3. *sensitivity*, 4. *dependency*, and 5. *semantics*. Although he originally wrote this in the early 1990s,[^agre] it took some time for technology and policy to catch up. I'm going to break down each are using more contempory context and terminology:

[^agre]: (November 1, 1994, https://www.wired.com/1994/11/agre-if-2/)

1. Provenance and Agency: what is the origin of the data and what can I do with it (ownership)?
2. Accuracy: has the data been validated? If not, what is the confidence of its correctness (error bars)?
3. Data Flow: how is data discovered, updated, and shared (sensitivity to changes)?
4. Auditability: what data and processes were used to generate this data (dependencies)?
5. Semantics: what does this raw data represent?

Each one of these could constitute its own blog post. Their viability is due to a confluence of cultural priorities, technologies, and policy. I'll mostly gesture to the technologies. Link out if you want to know more.

## 1. Provenance and Agency

<div class="epigraph">
> Where did this data come from?
>
><footer>
> Philip Agre
></footer>
</div>

Initiatives such as [Verifiable Credentials](https://www.w3.org/TR/vc-data-model/) (VC) and the [Content Authenticity Initiative](https://contentauthenticity.org/) (CAI) both provide certain guarantees about the veracity of data. VCs are data that can be verified by the original issuer. CAI is supported by companies like Adobe[^flash] because they're looking to provide  provenance and attribution for media content like images.

[^flash]: Adobe's existing Digital Rights Management (DRM) is used to [treat customers who buy books like criminals](https://hbr.org/2015/11/the-weird-rules-governing-what-we-download). Furthermore, when the company purchased Flash (via Macromedia), they inherited an ecosystem that contained countless digital cultural artifacts. When they discontinued the technology, the only responsible subsequent step would have been to open-source the entire project so [these artifacts could be maintained](/posts/2021-09-07-fix-my-code.html). Adobe has failed to demonstrate the vision or the leadership when dealing with cultural issues.

Ownership and usage rights are conventional methods that support the concept of property. Many futurists, especially those in Web3, want to apply property rights to aspects of our identity. Any attempts to commodify self-hood is an egregious misapplication of technology. Not only is it ethically dubious, it's technologically incoherent. As [Adrian Gropper](https://spectrum.ieee.org/privacy-entrepreneur-adrian-gropper) points out, "control doesn't scale." Individuals simply do not have the time or expertise to manage a portfolio of online personas or a single persona that discloses only essential information to a portfolio of clients.

In some sense, we have ownership over the organs in our body, but I thankfully don't have conscious control over their management. If I had that burden I would certainly need to outsource that information to a (profit-seeking) entity that manages my body's data so I could focus on something else. This is not a path towards serenity and sovereignty.

<div class="epigraph">
> What are we allowed to do with it?
>
><footer>
> Philip Agre
></footer>
</div>

Permissions are a time-tested way to express property rights. In computing, they have been around as long as multi-user operating systems. Cloud filesystems like Dropbox and Google Docs have made these coarse permissions an everyday affair. TODO: image of Google Drive permissions error.

A website's non-negotiable Terms of Service is also a set of permissions. They dictate what a service is allowed to do with an individual's data and how an individual is allow to access their data. Companies are heavily incentivized to retrain the control so it's unlikely that collective legal action will do much to improve this relationship.

Technologists can build a practical foundation that separates the data from the service. This approach changes what a customer or citizen has in hand when they enter into a legal agreement with a service and creates market opportunities for competitors. [Solid](https://www.w3.org/community/solid/) and [Web Native File System](https://github.com/wnfs-wg) (WNFS) provide such infrastructure. Solid, in particular, offers an open standard for application interoperability[^RDF] while the data remains on personal filestore.[^filesystem-database]

[^RDF]: Solid supports [RDF](https://www.xml.com/pub/a/2001/01/24/rdf.html), which offers an open way to link data. The web-wide aspirations have fallen short, but RDF is used successfully in many important projects.

[^filesystem-database]: Solid uses a hierarchical filesystem to organize personal information. RDF can be used in any file to support data queries. The filesystem lets people directly manipulate their information while RDF provides advanced support for data validation, ensuring data consistency, and complex queries for application. This personal filesystem/database model was popularized in the 1980s with softwaer like *dBase III*. dBase III stored a single table of information in a single file on a person's hard disk. WNFS is a file system without a database layer. This will likely prevent more sophisticated use.

VCs break out of the filesystem model and allow fine-grained sharing and revocation of permissions. Think of an ID that hides all the details about yourself except for the fact that you are under retirement age. At any point before you retire, you may no longer allow a company to make this inquiry.

Of course, none of these options address Gropper's axiom that "control doesn't scale." It's likely that any practical solution will require a separation between the computation service, the data store, and the user interface which allows certain expert administration of certain aspects of personal data.[^ai]

[^ai]: Expert administration could also be a personal AI that lives on a person's local device. Or it could be a data collective that provides expert management.

## 2. Accuracy

<div class="epigraph">
> How reliable is this data?
>
><footer>
> Philip Agre
></footer>
</div>

Verifiable credentials allow a third party to verify the authenticity of data.[^vc-example] There are a myriad of private "Know Your Customer" (KYC) solutions. Although these for-profit KYC solutions likely have a place in the future, their motivation to maximize shareholder value makes them fundamentally problematic.

[^vc-example]: A simple example is a digital diploma VC that I can keep close to me on my personal information store. When I share the diploma with a prospective employer, they can automatically check its authenticity with the granting University.

Both examples establish a “triangle of trust.“ You don't need to trust me that the data is true, you can check with the issuing institution.

## 3. Data Flow

<div class="epigraph">
> How fast does the final answer change as you start modifying the input by plausible amounts?
>
><footer>
> Philip Agre
></footer>
</div>

This is the hard one. Think of a spreadsheet. Data flows through formulas - when a person changes a number at cell A9, the new value propagates through the sheet, updating any referent along the way in real time.

For this to work, the data must have meaningful semantics. "3 more than 29" is 32. But "3 more days from January 29" is February 1. The semantics make two cells or two computers interoperable. [TODO: Alan Kay ambassadors]

The referent must also be findable (with an address like A9) and available. That's a lot of requirements!

File systems like WNFS and Dropbox don't have a standard way of discovering data or notifying changes. VCs and OAuth/OIDC are discoverable with a so-called '.well-known' endpoint. Solid folks are working on a Webhook (v 0.2.0) standard to serve updates to linked data over REST.

A deep dive into what's on the table would also include CFDTs as a way to propagate changes to files across the internet. Suffice to say, this is a hard problem and a lot of work remains.

## 4. Errors: Integrity and Reproducibility

<div class="epigraph">
> Errors propagate a lot faster than they can be repaired. [...] If something is screwy, can we trace the calculation back and figure out which input it depends on?
>
><footer>
> Philip Agre
></footer>
</div>

This problem is deeply related to Data Flow. At first glance, it may make sense to combine the two sections. But focusing exclusively on errors foregrounds another way to handle data: immutability.

Immutability simply means that data can never change. The most famous implementation of an immutable database in the last twenty years is the blockchain. Countless finance startups appeared (and disappeared) to solve the problem of *who* owns which coins.[^ipfs]

[^ipfs]: IPFS CAS is also an important immutable technology. Omitted only for the sake of brevity.

Blockchains are tamper-evident. If a malicious change is made, the error will show up in an audit. Verifiable Credentials are [tamper-evident](https://www.w3.org/TR/vc-data-model/). Solid is working on the same guarantees through a non-repudiation service.

Revocation provides important coverage for both errors made in earnest and data manipulated to intentionally mislead people.

Other services after a history for auditing , but do not promise valid (non-tampered) changes. Think about the record of changes on a text file on Dropbox or Google Docs. WNFS offers file and directory versioning (TODO: without timestamps?) as well.

## 5. Semantics

<div class="epigraph">
> It's very common for two databases to contain columns of data named by the same word - such as "price" or "name" or "approved" - even though that word means subtly different things to the people who created the databases.
>
><footer>
> Philip Agre
></footer>
</div>

This is really about supporting a protocol with semantics. The aforementioned RDF is a perfect fit here. And protocols such as Solid and SPARQL provide advanced transport methods. Since we're talking about IDs in this article, a widely adopted technology like OpenID Connect also fits the bill using [scopes](https://auth0.com/docs/get-started/apis/scopes/openid-connect-scopes).

VCs also support semantic information while there are many solutions for exchanging meaningful files from WNFS to Dropbox.

Semantics have a drawback: [they have to be widely agreed upon](https://people.well.com/user/doctorow/metacrap.htm). Semantic vocabularies are largely developed like protocols. A small group of experts refine the vocabulary until a rough concensus  is reached and it is released to the pubic. Tagging, or the other hand, also describes in formation foot from
the bottom-up.

Tagging can also be seen as a way to form widely distributed meaning. Sharing has been difficult across cultural and platform boundaries (think Pinboard vs Mastodon vs Flickr) but machine learning has changed the game on this casual tagging. Using the data sets, algorithms can also identify similar non-tagged data across the internet.[^machine-learning]

[^machine-learning]: The black box model of machine learning is particularly insidious here. Like vocabularies and tagging, any solution will have implicit biases but machine learning offers none of the transparency afforded to other methods of structuring
and describing information.

# Conclusion

These attributes would allow data to live closer to the person it is intended to represent; they would make data more expressive - allowing us to be our own ambassadors and participate in a powerful commons.

There are many Solutions not described in this article. Philip Agree mentions an ill-fated "intelligent agent" solution from the 1990s called [Telescript](http://www.datarover.com/Telescript/Whitepapers/wp4/whitepaper-4.html). Indeed, there are folks who are working on agent solutions today. Their focus on intention rather than data may even be closer to Kay's ambassadors.

Hickey struggled to seriously entertain the line of inquiry. "How could date be a bad idea?" he mused. But my recent struggles with personal data have caused me to take it quite seriously.

As this article shows, data-oriented solutions are both complex and fail to capture important aspects of identity. Perhaps data is a bad idea and agents inspired by biological systems are a good idea. But data is currently the most practical idea.
