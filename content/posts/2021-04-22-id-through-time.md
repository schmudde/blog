---
title: An Identity Through Time
description: From port 79 and citizen's band radio to facebook.com, some lessons learned about digital identity from the time-traveling Outlander.
author: David Schmudde
author-email: d@schmud.de
author-url: http://schmud.de
author-github: schmudde
author-twitter: dschmudde
location: Turin, Italy
date-created: 2021-04-22
date-modified: 2022-05-14
date-published: 2021-04-22
in-language: en
keywords: identity, dns, did, computerhistory, earnest
tags:
 - sts
 - suchness
---


Decade after decade our identity becomes increasingly integrated with computer systems. It is at the core of our life on the internet. This systematization will have radical effects on society on par with the emergence of artificial intelligence.

Much of what is useful about our identity is paradoxically formed by other people. This includes everything from reputation to certification. Our identity and the benefits it bestows is more fragile than it appears. A change in your reputation can ripple through the fabric society; a new credential can open new doors but an ad hominem attack can close countless others.

A trip through time on the public networks will reveal the evolution of priorities when capturing our lives in data. This could be done with any identity, but I chose to focus on the history of the best-selling author [Diana Gabaldon](https://en.wikipedia.org/wiki/Diana_Gabaldon).[^outlander] The story starts on July 20, 1988 when she was known as `76530,523` and continues to the present day.

[^outlander]: Diana Gabaldon's best known work is probably *Outlander* (1991). It was adapted for television on Starz in 2014. ![Outlander](/img/2021-04-22-id-through-time/Outlander-1st-ed-1991.jpg)

Identity archaeology peels back the historical layers of the internet - sort of the Triassic, Jurassic, and Cretaceous periods of cyberspace. Humble beginnings have brought about social credit scores in China and pervasive systems of surveillance capitalism in the West. A little perspective will help project what is on the horizon and how it can be met with the most ethical and humanitarian approaches.

## Prehistory: Finger

<figure>
![Lester Earnest via "Visitor to SAIL" on Wednesday 21 April 1976](/img/2021-04-22-id-through-time/lester-earnest-1976.jpg)[^lester]
</figure>

[^lester]: {-} Lester Earnest in 1976 via [Visitor to SAIL on Wednesday 21 April 1976](https://www.saildart.org/Visitor_1976/)

Finger, originally created in 1971 by Lester Earnest, is the granddaddy of status updates. Running `finger /a-persons-username/`[^port-79] &ldquo;return(ed) a friendly, human-oriented status report on a particular person in depth.&rdquo;

[^port-79]: The command can be run on TCP port 79 to find someone on another network via the internet.

The original version only worked on the DEC 10 at the Stanford Artificial Intelligence Lab. But it was enough to help users &ldquo;locate potential volleyball players when you wanted to play, Chinese food freaks when you wanted to eat, and antisocial computer users when it appeared that something strange was happening on the system.&rdquo;[^earnest]

Once &ldquo;`Plan` files&rdquo; were added, `finger` &ldquo;evolved into a forum for social commentary and amusing observations.&rdquo; The protocol swiftly spread across the Arpanet and later, the internet.[^earnest-2]

[^earnest]: From Sean Colbath in personal correspondence with Les Earnest on February 19, 1990. [Posted publicly on alt.folklore.computers](https://groups.google.com/g/alt.folklore.computers/c/IdFAN6HPw3k/m/Ci5BfN8i26AJ?pli=1) (Usenet) the following day.

[^earnest-2]: Ibid.

Finger introduced early digital privacy and security concerns. The protocol could divulge when a person last logged into their computer and when they last checked their email. It also listed full names and eMail addresses in many configurations. In systems that used usernames and passwords to identify individuals, `finger` essentially provided half of what a hacker needed to authenticate (the username). The second Finger RFC[^rfc], #[1288](https://tools.ietf.org/html/rfc1288) (1991), explicitly mentions these concerns.

[^rfc]: [Request For Comments](https://en.wikipedia.org/wiki/Request_for_Comments). RFCs are one of the most important documents for setting new standards on the internet.

The shifting priorities from RFC #[742](https://tools.ietf.org/html/rfc742) (1977), the first Finger RFC, and RFC #1288 (1991) reflect the widening breadth of the internet community. Privacy was becoming an increasing concern across the growing internet as a wider variety of people came online.

It's worth nothing that `finger`, unlike many privately owned social networks built on the world wide web, still works. Typing `finger schmudde@tilde.club` at the command line provides this information:

```
Login: schmudde       			        Name:
Directory: /home/schmudde           	Shell: /bin/bash
Last login Sun Feb 14 22:50 (UTC) on pts/47 from 2001:b07:ae6:7d19:a519:5691:a52a:dc49
No mail.
No Plan.
```

## CompuServe CB Simulator

<figure>
![](/img/2021-04-22-id-through-time/compuserve-communications.png)[^compuserve-communications]
</figure>

[^compuserve-communications]: {-} CompuServe's communications offerings [in 1987](https://archive.org/details/CompuserveIntroductorySubscription1987-07).

<div class="epigraph">
> The history of naming is tied up with the history of messaging on the internet.
>
> <footer> ~ Network researcher [Brad Fidler](https://brfid.github.io/)</footer>
</div>

Finger was one of the first tools for finding other people on a computer network; eMail was one of the first ways to send them messages.[^finger-cost] Public messaging came in the form of message boards (such as Usenet) and real time chat networks. A person's identity is the core component of each of these technologies.

[^finger-cost]: The cost of maintaining an identity is a fundamental concern here. Early on, only appointees in the US Department of Defense, related research institutions, and universities could use `finger` and eMail. These digital services were largely funded by the American taxpayer.

The first broadly public chat hangout was CompuServe's [CB Simulator](http://www.cbsimulator.com/), launched in 1980.[^1980] The *CB* that the service was simulating was [Citizens Band (CB) Radio](https://en.wikipedia.org/wiki/Citizens_band_radio).[^cb-simulator] CB Radio was a phenomenon of the 1980s that offered a way for strangers to connect over the airwaves. CompuServe drew the skuomorphic analogy to its novel online chat program.

[^1980]: Maher, Jimmy. “[» A Net Before the Web, Part 2: Service to Community](https://www.filfre.net/2017/11/a-net-before-the-web-part-2-service-to-community/).” *The Digital Antiquarian* (blog)

[^cb-simulator]: &ldquo;Modeled after CB Radio, CB Simulator is the hottest electronic, interactive communication medium online today. Seventy-two channels host CBers of all backgrounds, ages and intellects. [...] If you like, you can chat in private with a new friend or scramble a conversation for all but invited guests.&rdquo; ~ [CompuServe IntroPak (February 1987)](https://archive.org/details/CompuServeIntroPak1987-02)

But CompuServe didn't feel like a CB Radio; the people using the service were very close to the underlying DEC PDP-10 computer. In the early days, some users would sometimes accidentally login to another person's session because the TOPS-10 operating system did not automatically logout a user if someone disconnected. The essence of their identity on CompuServe - all their files and privileges - would be available.[^trevor]

[^trevor]: Sandy Trevor [70000,130] recalled this in a correspondence with Joe Dempster on 31-Aug-88 15:44 EDT. See [*We Call Them 10's: A Brief History of 36-bit Computing at CompuServe*](http://www.inwap.com/pdp10/compuserve.txt)

Furthermore, people's identifiers online were a reflection of the addressing architecture of the DEC PDP-10. Here's an excerpt from [`76530,523`'s conversation with `73367,1267`](https://web.archive.org/web/20170131232620/http://forums.compuserve.com/n/docs/docDownload.aspx?webtag=ws-books&guid=60cc7bba-ac09-427f-ac4d-0dc9d72e24e3) on July 20, 1988 in a CompuServe forum. [`76530,523`](https://groups.google.com/g/bit.listserv.rra-l/c/FcfSMgWzlsU/m/qZ7LoZFp6loJ) is better know as the best-selling author [Diana Gabaldon](http://www.dianagabaldon.com/):

```
20-Jul-88  02:09:44
Fm: Diana Gabaldon 76530,523
To: Margaret J. Campbell 73367,1267
```

> Dear Margaret--
>
> Nice premise for a novel (of the more modern type, to be sure). [...] I have the sort of face that induces total strangers to tell me their intimate secrets (which are generally pretty tame, though the lady who spent three hours over the Atlantic Ocean telling me about the ways in which her husband's midlife crisis was manifesting itself was pretty interesting).
>
> <footer>-- Diana</footer>

<figure class="fullwidth">
![CompuServe Information Manager 1.0 screenshot](/img/2021-04-22-id-through-time/compuserve-span.png) [CompuServe Information Manager 1.0](https://winworldpc.com/product/compuserve-information-manager/1x-dos) (November 20, 1989)
</figure>

CompuServe's identifiers were only usable within the CompuServe system at first. This changed the year after Gabaldon's post. CompuServe IDs could be addressed on the internet using the `compuserve.com` domain (e.g. `76530.523@compuserve.com`).[^compuserve-cost]

[^compuserve-cost]: Cost must be considered when calling something "broadly public." I'll provide 1994 numbers to give some sense of the cost. CompuServe was $8.95/month for unlimited use of the standard services. Incoming Internet electronic mail, including spam, cost $0.15 per item. Other services ranged from $4.80 to as much as $22.80 an hour, for use of "extended" services. (Lewis, Peter H. “[The Compuserve Edge: Delicate Data Balance](https://www.nytimes.com/1994/11/29/science/personal-computers-the-compuserve-edge-delicate-data-balance.html).” *The New York Times*, November 29, 1994, sec. Science.)

Assigned numbers are difficult for people to remember. CompuServe made their service more friendly by allowing user-created handles in the mid-1990s. My original CompuServe number was `103625,1027`, my username was `dschmud`, and my handle on the forums was `snap347`. That's three levels of identity: machine readable, account level, and public name.

## World Wide Web

<figure>
![](/img/2021-04-22-id-through-time/diana-gabaldon-1997.png)[^gabaldon-website]
</figure>

[^gabaldon-website]: {-} Diana Gabaldon's [original homepage](https://web.archive.org/web/19970727230953/http://www.cco.caltech.edu/~gatti/gabaldon/gabaldon.html) (1997)

The World Wide Web helped make the internet available to millions and millions of people around the earth. The Domain Name System (DNS) makes it possible to find a single person or entity out of the billion+ people online today.

An author like Diana Gabaldon could have stayed on CompuServe. But CompuServe and contemporary cousins like Facebook limit who a user can address: rather than anyone on the internet, you're limited to a subset of people who have accounts on the system. It's archaic.

Gabaldon had established her presence on the World Wide Web by 1997 at `http://www.cco.caltech.edu/~gatti/gabaldon/gabaldon.html`.[^caltech]

[^caltech]: This caltech.edu URL is still valid, nearly a quarter-century later. [Visiting the link](http://www.cco.caltech.edu/~gatti/gabaldon/gabaldon.html) will helpfully inform you that &ldquo;The Diana Gabaldon website has moved to a new location.&rdquo; This is because the original webmaster, [Rosana Madrid Gatti](https://directory.caltech.edu/personnel/gatti), still has an active Caltech account.

Domain names are the quietly powerful and permissive identity engine of the internet. `dianagabaldon.com` first appeared in 2001 as an anti-abortion website showing graphic imagery.[^domain-cost] It's almost certain that the author was not involved; the original owners were attempting to leverage the identity of a New York Times best-selling author to spread their message. Records from the Internet Archive show that Diana Gabaldon took over the domain in 2002 redirected the domain to her university website at `http://www.cco.caltech.edu/~gatti/gabaldon/gabaldon.html` (HTTP 302).

[^domain-cost]: Most domain names are relatively affordable today. But `.com` domain names were originally free, reflecting the values held by early internet citizens. Only 1151 `.com` domains were registered by [October 1990](https://www.iaps.com/internet-history-october-1990.html). Registration happened through an [eMail form](https://web.archive.org/web/20081202111346/https://w2.eff.org/Net_culture/Net_info/Technical/Registration/domain.template) to the InterNIC Domain Registrar. When InterNIC became a private company, Network Solutions in 1995, it became [$100 for two years](https://mashable.com/2014/03/10/domain-names-history/?europe=true). By 2000, some domain names were as inexpensive as [$9.99/year](https://web.archive.org/web/20001018000039/http://namebargain.com:80/).

<figure>
![The original `dianagabaldon.com` webpage](/img/2021-04-22-id-through-time/dianagabaldon-2001.png)[^gabaldon-website-2001]
</figure>

[^gabaldon-website-2001]: {-} The original `dianagabaldon.com` [webpage](https://web.archive.org/web/20010223214830/http://www.dianagabaldon.com/) (2001)

Having a memorable name to host your online identity has become incredibly valuable. Tens of millions of dollars have been spent to control a domain name.[^voice.com]

[^voice.com]: Voice.com was sold for [$30 million in cash](https://www.businesswire.com/news/home/20190618005248/en/MicroStrategy-Sells-Voice.com-Domain-30-Million) in 2019.

Names were not formally specified until RFC #[608](https://tools.ietf.org/html/rfc608), *Host Names On-Line*, in January 1974. These were a little different than domain names. A `<host-name>` originated with a request made to the Network Information Center (NIC) at Stanford. Only alphanumeric (`A`-`Z`, `0`-`9`) and the minus sign (`-`) were valid characters. If approved, it would be added to an ASCII file `HOSTS.TXT`. Now your machine could be accessed by a human-readable name. Other hosts would navigate to `<NETINFO>HOSTS.TXT` using FTP to download updates to the `HOSTS.TXT` file. As published in the RFC, the login was `GUEST` and the password was `ARPA` - such was the security on the network at the time.

`HOSTS.TXT` was essentially maintained by one person, Jake Feinler. Getting a new identity amounted to giving Jake a call. The need for a more advanced system of naming on a global information network is obvious. Today's Domain Name System (DNS) emerged in 1987 (RFCs #1034 and #1035) and was soon adopted across the entire internet.

The domain is an *address* that is machine-friendly. When combined with a *handle*, it can provide meaningful contact information for another person, such as `webmaster@dianagabaldon.com`. An eMail address can also be used as a *username* in another computer system. Finally, with technology such as [IndieAuth](https://indieauth.net/), a domain name can be used to *authenticate* a domain owner in another computer system. All of this works because there is some way to prove that you control either the domain name or the username associated with the domain name.

## Advertising Networks

But DNS isn't the way that most people find most people on today's internet, let alone message. Advertising networks stepped in an created the familiar user silos we have today.[^2010s] Diana Gabaldon snagged the Twitter handle @[`Writer_DG`](https://twitter.com/Writer_DG) in 2010 and the Facebook url [`AuthorDianaGabaldon`](https://www.facebook.com/AuthorDianaGabaldon) on January 31, 2011.

[^2010s]: ![](/img/2021-04-22-id-through-time/facebook-2007.png) November 6, 2007: [Facebook introduces targeted ads](https://about.fb.com/news/2007/11/facebook-unveils-facebook-ads/). &ldquo;&lsquo;Facebook Ads represent a completely new way of advertising online,&rsquo; Zuckerberg told an audience of more than 250 marketing and advertising executives in New York. &lsquo;For the last hundred years media has been pushed out to people, but now marketers are going to be a part of the conversation. And they’re going to do this by using the social graph in the same way our users do.&rsquo;&rdquo;

Gabaldon has amassed over a million followers across the two services. She is able to [narrowcast](/posts/2021-01-07-truth-storms-the-capitol.html) information to her followers and, presumably, it provides them a conduit to interact with her. Unlike CompuServe and DNS, this arrangement is free to use, it only requires that each user allows their activity to be monitored and exploited by the host advertising network.[^turner]

[^turner]: Advertising-based networks have an advantage over paid networks. Compare the rollouts of TBS and HBO in the early years of narrowcast cable television in the 1970s. &ldquo;In December 1976, Turner [TBS] became the second satellite delivered cable programmer [after HBO]. His channel was particularly appealing to operators and customers because, unlike HBO, it was an advertising-based service that could be offered to subscribers without additional charge.&rdquo; Patrick Parsons, "The Evolution Of The Cable-Satellite Distribution System", *Journal Of Broadcasting & Electronic Media* 47, no. 1 (2003): 1-17, doi:10.1207/s15506878jobem4701_1.

Even though advertising networks reduce the reach of an individual and surveil their every move, they became popular for two reasons: 1. the tools are easier and 2. they presented clear states of identity.

First, registering a domain name and creating a webpage is harder than creating an account on an advertising network. Once an account is created, managing your relationships with your peers is easier than any open web equivalent.

Second, the internet's original anonymity was due, in part, to its lack of state. Identity and security does not exist on the level of the internet protocol. Even higher-level protocols like the World Wide Web eschew state. It wasn't until the invention of the browser cookie that websites could remember what you put your shopping cart.[^jones]

[^jones]:&ldquo;The web was designed to be navigated by information, not host, using links to move through the network. As the first website describes on its “Dos and Don’ts” page: “There are no sessions between client and server. The client may retain state, (i.e. it knows which path it has followed) but the server may not. Each time a link is followed, a connection with the server is established, the request made, the server’s response sent back and the connection closed. Therefore there is no way that the server should know what it did with a client before the current request” (Implementation Guidelines, 1992).&rdquo; Jones, Meg Leta; Ackermann, Kevin (2020). Practicing privacy on other networks: network structures, economic arrangements, and identity strategies before cookies. Internet Histories, (), 1–19. doi:10.1080/24701475.2020.1747344

Advertising networks had an incredible incentive to remember your every move for analysis. The actions you take provides deep insights into who you are. Better insights into identity are incredibly valuable to marketers attempting to sell you products.

## What's Next

Tools for identification promise to increase in resolution and precision in the coming decades.

At one point we were identified by a string of numbers: `103625,1027`. Then a natural language username: `dschmud`. Soon thereafter we could have graphic avatars, leading to a book of faces that asked who our friends are and our relationship status.[^face-server] A tracking of purchases made, websites visited, emails received, and how many steps were taken in which locations have become quiet noise in the background. Fingerprints and models of our faces from every angle imaginable are now stored in machines.

[^face-server]: A progenitor of Facebook is Rob Pike and David L. Presotto's &ldquo;[face server](http://doc.cat-v.org/bell_labs/face_the_nation/).&rdquo; All computers with permission host the server could display the faces in eMail correspondence, etc.... As Unix hacker [Jack Rusher](https://twitter.com/jackrusher/status/1381967601950060552) pointed out to me, the [big face](https://spinroot.com/pico/pjw.html) in the screenshot below is [Peter J. Weinberger](https://en.wikipedia.org/wiki/Peter_J._Weinberger), perhaps most well-known as the **w** in the Unix `awk` command. See also Steve Kinzler's [Picons](https://legacy.cs.indiana.edu/ftp/faces/picons/). ![](/img/2021-04-22-id-through-time/face-server.gif)

As the resolution of the medium increases, so does the power of our online identity. Yesterday's two-dimensional images are today's Snapchat filters and [Memoji](https://www.howtogeek.com/450743/how-to-create-and-use-memoji-and-animoji-on-an-iphone/). These technologies are the foundation of tomorrow's projected self.

Our digital proxies will be granted credentials that impact the privileges of our physical bodies. Locating and authenticating our digital proxy are the essential mechanisms in this process. Today it is largely done through DNS and username/password pairs. Tomorrow it will likely done by a unique address with no central assignment body and a key.

This has grave political and humanitarian consequences, which will be explored in the forthcoming post, *The Identity Trap*. Subscribe to the **Beyond the Frame** <i class="fas fa-rss"></i> <a href="/feed.rss">RSS</a> or mailing list to make sure you don't miss the next update.
