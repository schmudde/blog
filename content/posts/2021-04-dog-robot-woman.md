---
title: Dog, Robot, or Woman
description: On the internet no one knows you’re a dog. Capturing identity is hard, it's dangerous - how others have harnessed it, and where we might go from here.
author: David Schmudde
author-email: d@schmud.de
author-url: http://schmud.de
author-github: schmudde
author-twitter: dschmudde
location: Turin, Italy
date-created: 2021-04-06
date-modified: 2021-04-06
date-published: 2021-04-06
in-language: en
tags:
 - humanities
---


![](/img/no-one-knows-youre-a-dog.png)


I’m CTO at Yorba

## Definition

Identity is  the mapping of relationships. There is no 'you' only a sense of self as it exists in different contexts. You don't model this, but you do make certain related facts in time and intrapersonal graphs available in certain situations.

## Communication is Identity

"The history of naming is tied up with the history of messaging on the internet," observed network researcher [Brad Fidler](https://brfid.github.io/). Essentially, we have to know how to find other people.

Usernames are commonly people's first step in creating a digital identity. For the first quarter century of the consumer computer, this was rare. It only happened on remote systems.[^windows] Now your personal computer has a username and your smartphone can act as a tool to verify your identity.

[^windows]: Windows XP (2001) was Microsoft's first multi-user operating system intended for consumers. Before that time, most operating systems only allowed a single user. There was no modern concept of digital identity; usernames and passwords were not a ubquitious requisite for using a machine. (This was based on their NT technology, which first appeared in 1993.)

But these personal tools have a weak concept of who we are when compared to the centralized stores of information located in anonymous warehouses.

In many ways, these warehouses store a more complete picture of identity - which is more about who we know (social graph) and what we do (transactions) than a collection of platonic facts such as our name, our tax id number, and our eye color.

Here is my first law of identity:

As the resolution of our communications medium increases, so does the power of our avatar that exists within that medium.

Primordial avatars were a string of characters. They were essentially synonymous with an address a computer system. Yesterday's two-dimensional images are today's Snapchat filters and [Memoji](https://www.howtogeek.com/450743/how-to-create-and-use-memoji-and-animoji-on-an-iphone/) will become tomorrow's projected self.

Digital identity is already at the heart of debates over surveillance capitalism and social credit scores. This promises to become more intense. This article talks about how we got there and what we need to prepare for the future.

## CompuServe CB Simulator

CompuServe was an early entry in networked computing. People on the service were identified by a series of digits separated by a comma or a period. This format was a reflection of the DEC PDP-10 architecture where CompuServe was born.

These addresses became the foundation of CompuServe's online chat service, the CB Simulator. The 'CB' that the service was simulating was [Citizens Band Radio](https://en.wikipedia.org/wiki/Citizens_band_radio).[^mcluhan] CB Radio was a phenomenon of the 1980s that offered a way for strangers to connect over the airwaves. CompuServe drew the skuomorphic analogy to its novel online chat program.

[^mcluhan]: Marshall McLuhan's the medium is the previous medium

Here's an excerpt from [`76530,523`'s conversation with `73367,1267`](https://web.archive.org/web/20170131232620/http://forums.compuserve.com/n/docs/docDownload.aspx?webtag=ws-books&guid=60cc7bba-ac09-427f-ac4d-0dc9d72e24e3) on July 20, 1988 in a CompuServe forum. [`76530,523`](https://groups.google.com/g/bit.listserv.rra-l/c/FcfSMgWzlsU/m/qZ7LoZFp6loJ) is better know as the best-selling author [Diana Gabaldon](http://www.dianagabaldon.com/):


```
20-Jul-88  02:09:44
Fm: Diana Gabaldon 76530,523
To: Margaret J. Campbell 73367,1267
```

<blockquote>
> Dear Margaret--
>
> Nice premise for a novel (of the more modern type, to be sure). [...] I have the sort of face that induces total strangers to tell me their intimate secrets (which are generally pretty tame, though the lady who spent three hours over the Atlantic Ocean telling me about the ways in which her husband's midlife crisis was manifesting itself was pretty interesting).
>
<footer>-- Diana</footer>
</blockquote>

CompuServe's identifiers were only usable within the CompuServe system at first. This changed the year after Gabaldon's post. CompuServe ids could be addressed on the internet using the compuserve.com domain (e.g. 76530.523@compuserve.com).

Assigned numbers are difficult for people to remember. CompuServe made their service more friendly by allowing for user-created handles in the mid-1990s. My original CompuServe number was 103625,1027, my username was dschmud, and my handle on the forums was snap347[^snap]. That's three levels of identity: machine readable, account level, and public name.

[^snap]: band

## DNS and eMail

The World Wide Web helped make the internet available to millions and millions of people around the earth. The Domain Name System (DNS) makes it possible to find a single person out of today's billions of internet citizens.

An author like Diana Gabaldon could have stayed on CompuServe. But CompuServe and contemporary cousins like Facebook limit who a user can address: rather than anyone on the internet, you're limited to a subset of people who have accounts on the system. It's archaic.

So Gabaldon was like many others who left CompuServe and America OnLine for greener pastures. She created her own vanity domain, dianagabaldon.com, [in 2001](https://web.archive.org/web/20011204174907/http://www.cco.caltech.edu/~gatti/gabaldon/gabaldon.html). Domain name registration offers a comprehensive solution to identity. The domain is an *address* that is machine-friendly. When combined with a *handle*, it can provide meaningful contact information for another person, such as `webmaster@dianagabaldon.com`. An eMail address can also be used as a *username* in another computer system. Finally, with technology such as [IndieAuth](https://indieauth.net/), a domain name can be used to *authenticate* a domain owner in another computer system.[^open-id]

[^open-id]: &ldquo;You bring an existing identity (your email address) and then authenticate (usually by clicking a link that was sent to your email). The original version of OpenID was created to solve this problem on the web. People identified themselves with a URL, which they were able to prove they controlled using OpenID. This allows a new user to log in to a site without needing a prior relationship with the site.&rdquo; ~ [OAuth for the Open Web](https://aaronparecki.com/2018/07/07/7/oauth-for-the-open-web) by Aaron Parecki

Domain names are the quietly powerful and permissive identity engine of the internet. The acquisition of privatejet.com for $31 million in 2012[^privatejet.com] demonstrates the importance of names to a brand's identity.

[^privatejet.com]: Nations Luxury Transportation, LLC (Nations) from Don’t Look Media Group

DNS had modest beginnings. Keeping track of them was originally the job of one person, Elizabeth Feinler. Feinler manually maintained a text file that mapped the location of all these names at the Stanford Research Institute throughout the TODO. Over the years this process became more refined and automated - morphing into the ubiquitous .com and .edu addresses we have today.

Jon Postel began by keeping a track of network protocol numbers [on a scrap of notebook paper](https://news.usc.edu/9865/Jon-Postel-Internet-Pioneer-Dies-at-55/). This evolved in to the Internet Assigned Numbers Authority (IANA), which Postel directed for 30 years.

## Advertising Networks

But DNS isn't the way that most people find most people on today's internet, let alone message. Advertising networks stepped in an created the familiar user silos we have today. Diana Gabaldon snagged the Twitter handle @[Writer_DG](https://twitter.com/Writer_DG) in 2010 and the Facebook url [AuthorDianaGabaldon](https://www.facebook.com/AuthorDianaGabaldon) on January 31, 2011.

Gabaldon has amassed over a million followers across the two services. She is able to [narrowcast](/posts/2021-01-07-truth-storms-the-capitol.html) information to her followers and, presumably, it provides them a conduit to interact with her. Unlike CompuServe and DNS, this arrangement is free to use, it only requires that each user allows their activity to be monitored and exploited by the host advertising network.[^turner]

[^turner]: Advertising-based networks have an advantage over paid networks. Compare the rollouts of TBS and HBO in the early years of narrowcast cable television in the 1970s. &ldquo;In December 1976, Turner [TBS] became the second satellite delivered cable programmer [after HBO]. His channel was particularly appealing to operators and customers because, unlike HBO, it was an advertising-based service that could be offered to subscribers without additional charge.&rdquo; Patrick Parsons, "The Evolution Of The Cable-Satellite Distribution System", *Journal Of Broadcasting & Electronic Media* 47, no. 1 (2003): 1-17, doi:10.1207/s15506878jobem4701_1.

Even though advertising networks reduce the reach of an individual and surveil their every move, they became popular for two reasons: 1. the tools are easier and 2. they presented clear states of identity.

First, registering a domain name and creating a webpage is harder than creating an account on an advertising network. Once an account is created, managing your relationships with your peers is easier than any open web equivalent.

Second, the internet's original anonymity was due, in part, to its lack of state. Identity and security does not exist on the level of the internet protocol. Even higher-level protocols like the World Wide Web eschew state. It wasn't until the invention of the browser cookie that websites could remember what you put your shopping cart.[^jones]

[^jones]:&ldquo;The web was designed to be navigated by information, not host, using links to move through the network. As the first website describes on its “Dos and Don’ts” page: “There are no sessions between client and server. The client may retain state, (i.e. it knows which path it has followed) but the server may not. Each time a link is followed, a connection with the server is established, the request made, the server’s response sent back and the connection closed. Therefore there is no way that the server should know what it did with a client before the current request” (Implementation Guidelines, 1992).&rdquo; Jones, Meg Leta; Ackermann, Kevin (2020). Practicing privacy on other networks: network structures, economic arrangements, and identity strategies before cookies. Internet Histories, (), 1–19. doi:10.1080/24701475.2020.1747344

Advertising networks had an incredible incentive to remember your every move for analysis. The actions you take provides deep insights into who you are. Better insights into identity are incredibly valuable to marketers attempting to sell you products.

## What's Next

How to get the best of both world.

---

# IANA

The history of ICANN traces back to the creation and the evolution of the Internet itself. In 1969, the first message was sent via the Arpanet, a U.S. Department of Defense network and precursor to the global Internet. Three years later, Jon Postel started to record socket numbers for the Arpanet in his notebook. His registry eventually became the Internet Assigned Numbers Authority (IANA), which manages the coordination of the Domain Name System (DNS). ICANN was founded in 1998 and grew out of a U.S. Government commitment to transfer the policy and technical management of the DNS to a non-profit corporation based in the U.S. with global participation. The IANA Stewardship Transition was completed in October 2016 thanks to the work and dedication of the Internet community worldwide


Internet Assigned Numbers Authority (IANA) is responsible for coordinating some of the key elements that keep the Internet running smoothly. Whilst the Internet is renowned for being a worldwide network free from central coordination, there is a technical need for some key parts of the Internet to be globally coordinated, and this coordination role is undertaken by us.

Specifically, they allocate and maintain unique codes and numbering systems that are used in the technical standards (“protocols”) that drive the Internet.

Their various activities can be broadly grouped into three categories:

Domain Names
Management of the DNS Root, the .int and .arpa domains, and an IDN practices resource.

Number Resources
Co-ordination of the global pool of IP and AS numbers, primarily providing them to Regional Internet Registries (RIRs).

Protocol Assignments
Internet protocols’ numbering systems are managed in conjunction with standards bodies.

It is one of the Internet's oldest institutions, with the IANA functions dating back to the 1970s. Today the services are provided by Public Technical Identifiers, a purpose-built organization for providing the IANA functions to the community. PTI is an affiliate of ICANN, an internationally-organized non-profit organization set up by the Internet community to coordinate the areas of responsibilities.

Learn more IANA at https://www.iana.org/

The regional Internet registry system evolved over time, eventually dividing the responsibility for management to a registry for each of five regions of the world. The regional Internet registries have informally liaised through the unincorporated Number Resource Organization (NRO), which is a coordinating body to act on matters of global importance

Source: https://en.wikipedia.org/wiki/Regional_Internet_registry

The Number Resource Organization (NRO) was established in 2003 as a coordinating body for the world’s Regional Internet Registries

The RIRs manage the distribution of Internet number resources (IP address space and Autonomous System Numbers) within their respective regions.

Learn more about the NRO   https://www.nro.net/

The most recent (31 December 2020), Internet Number Resource Status Report follows.



The NRO, as the coordinating body for the five RIRs, fulfills the role, responsibilities, and functions of the ASO. These functions and responsibilities are outlined in the ICANN-ASO Memorandum of Understanding (MoU). The NRO provides secretariat support for the ASO, acts as a coordinating mechanism for the five RIRs, and participates, as the ASO, in the ICANN Empowered Community.
