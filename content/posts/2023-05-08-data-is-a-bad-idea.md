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
date-published: 2023-05-30
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

[^kay]: Kay has made a career out of challenging conventional wisdom. Perhaps this is best embodied by one of [his most famous quotes](https://www.folklore.org/StoryView.py?project=Macintosh&story=Creative_Think.txt), "A shift in perspective is worth 80 IQ points." ![Alan Kay by Jean Baptiste](/img/2023-05-08-data-is-a-bad-idea/kay.jpg) <span property="license"><a class="link no-tufte-underline" href="https://creativecommons.org/licenses/by/2.0/" rel="license"><i class="fab fa-creative-commons"></i>&nbsp;<i class="fab fa-creative-commons-by"></i></a>Alan Kay by Jean Baptiste</span>

But this is a question worth exploring. Many may premise a discussion on technical details. I'll get to those soon enough. First we must explore the idiosyncratic political dimension of data because it informs everything that follows.

## Data Is an Objectifying Force

The objectifying power of data is an asset when describing inert phenomenon such as the composition of soil or the properties of various metals. We can act upon these things without direct ethical considerations.

The problems become apparent when we start talking about people. Data cannot express a meaningful distinction between **intelligent actors** and the things they **act upon**. A database that tracks widget production can also store information about the people who buy those widgets. Databases then turn actors - who are often human beings - into things to be acted upon. This is where data can quickly become a bad idea. Not in the sense that Kay likely intended, but it has been a characterization of data centers since the dawn of automated census calculations in the 19th century.[^census]

[^census]: Dutch resistance members, including Willem Arondéus, Gerrit van der Veen, and [Dr. Frieda Belinfante](https://www.ushmm.org/collections/the-museums-collections/curators-corner/the-frieda-belinfante-collection), orchestrated [a bombing of the Amsterdam Population Registry](https://www.annefrank.org/en/timeline/128/the-resistance-attacks-the-population-register-of-amsterdam/) on Saturday, March 27, 1943. Their goal was to inhibit the Nazi's ability to track and deport Jews and other targets of terror. The operation managed to destroy over 15% of the records (top image). Many of the participants were later captured and executed by the Nazis. Noted privacy advocate and lawyer Lau Mazirel was also a member of the Dutch resistance (bottom image). ![](/img/2023-05-08-data-is-a-bad-idea/amsterdam-registry.jpg) Amsterdam public registry (28 March 1943) <span property="license"><a class="link no-tufte-underline" href="https://www.annefrank.org/en/timeline/128/the-resistance-attacks-the-population-register-of-amsterdam/" rel="license">[<i class="fab fa-creative-commons-pd-alt"></i>Amsterdam City Archives</a>]</span>![Lau Mazirel](/img/2023-05-08-data-is-a-bad-idea/lau-mazirel.jpg) Lau Mazirel (1907-1974) <span property="license">[<a class="link no-tufte-underline" href="https://www.vn.nl/lau-mazirel-streed-tegen-de-maatschappelijke-comedie/" rel="license">Collectie Van Gennep / IISG</a>]</span>

## Data Is a Centrifugal Force

Data processing is the lifeblood of large bureaucracies. As previously discussed in the article *[Personal Privacy](posts/2020-06-15-personal-privacy.html)*, efforts to mitigate the reach of data collection in democratic nations goes back decades. *Consent* is the latest fashion. It's the idea that drives those cookie popups on websites; a person is granted agency every time they make a choice about which cookies are placed on their machine.

Most people find these popups annoying; we don't want to negotiate every time we encounter a new website. We're used to social structures where consent is often implicit even with people you don't know. But data is too brittle to capture the kind of nuance that is communicated in human interactions.

Alan Kay took note of how everything from simple organisms to complex societies negotiate in novel situations. It lead him to propose an abstract concept of *ambassadors*[^ambassadors] in computer science. Ambassadors might like to follow a protocol, but it isn't required. They act on behalf of a larger autonomous entity. And when two ambassadors meet, both entities they represent retain their autonomy.

[^ambassadors]: Alan Kay is really thinking about objects interacting on a network. Currently, all network interactions follow explicit protocols. Objects of the future, Kay believes, must be able to negotiate the exchange of data even if they come from completely unknown sources. As Kay noted in the conversation with Hickey, "For important negotiations we don't send telegrams, we send ambassadors."

How could *data* be this flexible? How could *data* bring the requisite context along for the ride?[^data] This is what Kay was likely gesturing towards when he mused that data may be a bad idea.

[^data]: Usually data relies on the medium to provide context. The medium is the protocol. I can send you data through a perfect medium with perfect fidelity and all you receive is noise. Indeed - all data is noise unless you know how to interpret it.

Efforts to implement ambassadors haven't gotten very far. Data still seems to work best in aggregate. It continues to be sucked into the private warehouses of powerful organizations, further entrenching their power. This centrifugal force is political in nature. As such it can be countered by a variety of approaches, from law (new forms of regulation, licensing, etc...) to technology (advances in software, hardware, etc...).

Rather than chase after this hazy idea of an ambassador, I'll spend the rest of our time exploring concrete ways to expand our notion of data. I'll specifically work with the idea of using data to represent human identity. I will focus on data delivery over the ubiquitous HTTP protocol using the client/server model. Lots of interesting work is happening on peer-to-peer protocols, but I've found much of the last ~10 years of venture-backed, market-centric crypto solutions to be misguided.[^web3] This is an examination of what it would take to build a more human-centric arrangement by going back to the fundamentals of data - where it lives, how it moves, and what it means.

[^web3]: Most so-called Web3 efforts attempt to graft a marketplace on top of data to decentralize this power structure. But few have an answer for the tendency of marketplaces to consolidate power. Combined with the political dimension of raw data and the proven power of network effects, this can only lead to an unbelievable consolidation of power. For those who are not in power, data will seem like a bad idea.

## Data and Identity

Identity is a natural place to start when talking about people. The following discussion is based on two observations: 1) data naturally reduces complex conceptions of identity into coarse representations and 2) data about identity is generally held in systems far away from the people they identify.

The second point is a result of the centrifugal force of data. We can resist this inertia by changing the physical characteristics of data - from "far" (in your warehouse) to "near" (in my hand) and from "dead" (jealously hoarded on private property) to "living" (flowing through the commons). Such data could exhibit different political dimensions and become more expressive.

Philip Agre enumerated five characteristics of data that will help us achieve this repositioning. Agre argued that "Living Data" must be able to express 1. a sense of *ownership*, 2. *error bars*, 3. *sensitivity*, 4. *dependency*, and 5. *semantics*. Although he originally wrote this in the early 1990s,[^agre] it took some time for technology and policy to catch up. I'm going to break down each using more contemporary context and terminology:

[^agre]: (November 1, 1994, https://www.wired.com/1994/11/agre-if-2/)

1. Provenance and Agency: what is the origin of the data and what can I do with it (ownership)?
2. Accuracy: has the data been validated? If not, what is the confidence of its correctness (error bars)?
3. Data Flow: how is data discovered, updated, and shared (sensitivity to changes)?
4. Auditability: what data and processes were used to generate this data (dependencies)?
5. Semantics: what does this raw data represent?

Each one of these could constitute its own blog post. Their viability today is due to a confluence of cultural priorities, technologies, and policy. I'll mostly gesture towards the specific technologies. Follow the hyperlinks if you want to know more.

### 1. Provenance and Agency

<div class="epigraph">
> Where did this data come from?
>
><footer>
> Philip Agre
></footer>
</div>

Initiatives such as [Verifiable Credentials](https://www.w3.org/TR/vc-data-model/) (VC) and the [Content Authenticity Initiative](https://contentauthenticity.org/) (CAI) both provide certain guarantees about the veracity of data. VCs are data that can be verified by the original issuer. CAI is supported by companies like Adobe[^flash] because they're looking to provide  provenance and attribution for rich media.

[^flash]: Adobe's existing Digital Rights Management (DRM) is used to [treat customers](https://hbr.org/2015/11/the-weird-rules-governing-what-we-download) who buy books [like criminals](posts/2021-12-01-eink.html). Furthermore, when the company purchased Flash (via Macromedia), they inherited an ecosystem that sustained countless digital cultural artifacts. When they discontinued the technology, the only responsible step would have been to subsequently open-source the entire project so [these artifacts could be maintained](posts/2021-09-07-fix-my-code.html). Adobe has failed to demonstrate vision or leadership when dealing with cultural issues.

Ownership and usage rights conventionally support the concept of property. Many futurists, especially those in Web3, want to apply property rights to aspects of our identity. Any attempts to commodify self-hood is an egregious misapplication of technology. Not only is it ethically dubious, it's technologically incoherent. As [Adrian Gropper](https://spectrum.ieee.org/privacy-entrepreneur-adrian-gropper) points out, "control doesn't scale." Individuals simply do not have the time or expertise to manage a portfolio of online personas or a single persona that discloses only essential information to a portfolio of clients.

In some sense we have ownership over the organs in our body but I thankfully don't have conscious control over their management. I would need to outsource the burden of "personal organ management" to an outside entity if I wanted to get anything else done. This is not a path towards serenity and sovereignty.

<div class="epigraph">
> What are we allowed to do with it?
>
><footer>
> Philip Agre
></footer>
</div>

Permissions are a time-tested way to express property rights. In computing, they have been around as long as multi-user operating systems. Cloud filesystems like Dropbox and Google Docs have made these coarse permissions an everyday affair.[^google-sheets]

[^google-sheets]: The ubiquitous Google Sheets permissions error: ![A Google Drive permission error](/img/2023-05-08-data-is-a-bad-idea/google-sheets.png)<span property="license"><a class="link no-tufte-underline" href="https://creativecommons.org/licenses/by/4.0/deed.en" rel="license"><i class="fab fa-creative-commons"></i>&nbsp;<i class="fab fa-creative-commons-by"></i></a></span>

A website's non-negotiable Terms of Service is also a set of permissions. They dictate what an organization is allowed to do with an individual's data and the access rights one has to their own data. I'm doubtful that individual or collective legal action can do much to alter the disproportionate power held by organizations that set these Terms of Service. They hold both the means of computation (usually as an application) and the data to compute.

But technologists are attempting to build infrastructure that separates these two concerns: application and data. This approach changes what a customer or citizen has in hand when they enter into a legal contract with a service and creates market opportunities for competitors. [Solid](https://www.w3.org/community/solid/) and [Web Native File System](https://github.com/wnfs-wg) (WNFS) provide such infrastructure. Solid, in particular, offers an open standard for application interoperability[^RDF] while a person's data remains on their personal filestore.[^filesystem-database]

[^RDF]: Solid supports [RDF](https://www.xml.com/pub/a/2001/01/24/rdf.html), which offers an open way to link data. The web-wide aspirations for linked data have fallen short, but RDF is used successfully in many important projects.

[^filesystem-database]: Solid uses a hierarchical filesystem to organize personal information. RDF can be used in any file to support data queries. The filesystem lets people directly manipulate their information while RDF provides advanced support for data validation, ensuring data consistency, and complex queries for the application. This personal filesystem/database model was popularized in the 1980s with softwaer like dBase III. dBase III stored a single table of information in a single file on a person's hard disk. On the other hand, WNFS is a file system without a database layer. This will likely prevent more sophisticated use.

VCs break out of the filesystem model and allow fine-grained sharing and revocation of permissions. Think of an ID that hides all the details about yourself except for the fact that you are under retirement age. At any point before you retire, you may no longer allow a company to make this inquiry.

These options do not address Gropper's axiom that "control doesn't scale." It's likely that any practical solution will require a separation between the computation service, the data store, and the user interface which allows certain expert administration of certain aspects of personal data.[^ai]

[^ai]: Expert administration could also be a personal AI that lives on a person's local device. Or it could be a data collective that provides expert management.

### 2. Accuracy

<div class="epigraph">
> How reliable is this data?
>
><footer>
> Philip Agre
></footer>
</div>

Verifiable credentials allow a third party to verify the authenticity of data.[^vc-example] There are a myriad of private "Know Your Customer" (KYC) solutions. Although these for-profit KYC solutions likely have a place in the future, their motivation to maximize shareholder value makes them fundamentally problematic.

[^vc-example]: A simple example is a digital diploma VC that I can keep close to me on my personal information store. When I share the diploma with a prospective employer, they can automatically check its authenticity with the granting University.

VCs are based on a "triangle of trust." I can share a credential I hold with someone that wants to see it. That person or institution does not need to trust me, they can check with the 3rd party that originally issued the credential.

### 3. Data Flow

<div class="epigraph">
> How fast does the final answer change as you start modifying the input by plausible amounts?
>
><footer>
> Philip Agre
></footer>
</div>

This is the hard one. Think of a spreadsheet where data flows through the various cells to arrive at an answer. Update one number in the spreadsheet and the dependent cells update automatically.

For this to work, the data must have meaningful semantics. "3 more than 29" is 32. But "3 more days from January 29" is February 1. The semantics make two cells or two computers interoperable. Anything short of these clear semantics would require one of Alan Kay's hypothetical ambassadors.

All data in the flow must also have an address so it can be found. And resilient software would need a contingency plan in the event that data moves or goes missing.

That's a lot of requirements!

Files on WNFS will stay in sync wherever they are stored.[^CFDT] Notifying anyone that depends on those files is not part of WNFS. WNFS does not offer a way to address specific data in a file nor a way to discover data in the first place.

[^CFDT]: A deep dive into what's on the table would also mention CFDTs as a way to propagate changes to files across the internet.

VCs and OAuth/OIDC are discoverable with a so-called `.well-known` endpoint. Solid doesn't really have a good way to discover data but changes to linked data can theoretically propagate. Solid folks are working on a Webhook (v 0.2.0) standard to serve updates to linked data over REST.

This is a deep subject and much work remains to be done.

### 4. Errors: Integrity and Reproducibility

<div class="epigraph">
> Errors propagate a lot faster than they can be repaired. [...] If something is screwy, can we trace the calculation back and figure out which input it depends on?
>
><footer>
> Philip Agre
></footer>
</div>

This problem is related to Data Flow. At first glance, it may make sense to combine the two sections. But focusing exclusively on errors foregrounds another way to handle data: immutability.

Immutability simply means that data can never change. The most famous implementation of an immutable database in the last twenty years is the blockchain. Countless finance startups appeared (and disappeared) to solve the problem of *who* owns which coins on the blockchain.[^ipfs]

[^ipfs]: IPFS CAS is also an important immutable technology. Omitted, like a lot of other technology I didn't mention, only for the sake of brevity.

Fraud is a kind of "intentional error." Immutable data will provide a record of changes in any audit, thus making blockchains tamper-evident. VCs are [also tamper-evident](https://www.w3.org/TR/vc-data-model/); claims made by a person holding a credential can be revoked. Revocation provides essential coverage for both errors made in earnest and malicious fraud.

Solid does not work with immutable data, but folks are working on similar guarantees through a Solid non-repudiation service.

Other technology offers a history for auditing errors but do not promise valid (non-tampered) changes. Think about the record of changes on a text file on Dropbox or Google Docs. WNFS offers file and directory versioning but it functions quite differently than a record of changes on Google Docs.

### 5. Semantics

<div class="epigraph">
> It's very common for two databases to contain columns of data named by the same word - such as "price" or "name" or "approved" - even though that word means subtly different things to the people who created the databases.
>
><footer>
> Philip Agre
></footer>
</div>

This issue is the culmination of all the others. Data cannot even flow between two computers if they do not understand each other. File system metaphors like Dropbox and WNFS use file extensions and MIME types to understand what they are sending and receiving. Solid supports files and also RDF for greater expressivity. At this very moment, basic identity information is exchanged all around the internet using OpenID Connect [scopes](https://auth0.com/docs/get-started/apis/scopes/openid-connect-scopes).

Semantics have a major drawback: [they have to be widely agreed upon](https://people.well.com/user/doctorow/metacrap.htm). Widely-adopted semantic vocabularies are usually refined by a small group of experts until a rough consensus is reached. The method can be seen in contra-distinction to *tags*. Tags also provide meaning to data but they are formed organically by a large group of people generating a large number of tags. Trends emerge over time and provide meaning to the information being tagged.

These tags can lose their meaning in different cultural contexts or on different platforms - `#meta` might imply something different on Flicker than it means on Facebook. But machine learning has flattened these differences. Using existing data sets, algorithms can identify similar non-tagged data across the internet.[^machine-learning]

[^machine-learning]: The black box model of machine learning is particularly insidious here. Like vocabularies and tagging, any solution will have implicit biases but machine learning offers none of the transparency afforded to other methods of structuring and describing information.

## Control Does Not Scale

It's not possible nor desirable to provide each person a simulacrum of their identity online. People don't want to manage their own data any more than (they want to manage their own servers)[TODO: Moxie Marlinspike Link]. But large data centers are political artifacts[^winner] that necessitate both political and technological responses. This response depends on how we situate identity data in our lives - close to home, separate from the processes that compute it.

[^winner]: &lsquo;Conventional wisdom posits technology as a neutral force that can be wielded for &ldquo;evil&rdquo; or for &ldquo;good.&rdquo; But as Langdon Winner has pointed out, technologies can fundamentally embody explicit political arrangements.&rsquo; from *[How the Consumer Computer is Consuming Computing](posts/2022-08-23-the-consumer-computer.md)*.

This arrangement is easier said than done. Data must have some sense of authenticity to be useful, it must have some contextual meaning to be shared, and it must leave room for error if it is to be alive. Agre outlines these principles but their technological implementation is unclear.[^telescript]

[^telescript]: There are many solutions not described in this article. Philip Agree mentions an ill-fated "intelligent agent" solution from the 1990s called [Telescript](http://www.datarover.com/Telescript/Whitepapers/wp4/whitepaper-4.html). Indeed, there are folks who are working on agent solutions today. Their focus on intention rather than data may even be closer to Kay's ambassadors.

Any real political response will require more than introducing shiny new tech. For example, consider all the institutional support needed to manage a currency in your country. And then remember that a person's identity is infinitely more complex than any financial abstraction.

It may seem insurmountable, but the alternative is a single dominate political vision where only one form of data management exists: large data centers filled with coarse data points and countless assumptions about what that data says about who we are. This is a bad idea.
