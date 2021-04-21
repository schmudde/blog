---
title: Dog, Robot, or Woman = $
description: On the internet no one knows you’re a dog. Capturing identity is hard, it's dangerous - how others have harnessed it, and where we might go from here.
author: David Schmudde
author-email: d@schmud.de
author-url: http://schmud.de
author-github: schmudde
author-twitter: dschmudde
location: Turin, Italy
date-created: 2021-04-20
date-modified: 2021-04-20
date-published: 2021-04-20
in-language: en
tags:
 - humanities
---



Though a little online detective work, I have pieced together a brief history of best-selling author [Diana Gabaldon](https://en.wikipedia.org/wiki/Diana_Gabaldon)'s online identity. The story starts on July 20, 1988 when she was known as `76530,523` and follows to the present day.

A little identity archaeology peels back the historical layers of the internet - sort of the Triassic, Jurassic, and Cretaceous periods of cyberspace - to reveal our priorities when capturing our lives in data.

Much of what is useful about our identity is paradoxically formed by other people. This includes everything from reputation to certification. Our identity and the benefits it bestows is much more fragile than it appears. A change in your reputation can ripple through the fabric society; a new credential can open new doors but an ad hominem attack can close countless others.

Systematizing identity on computer systems will have radical effects on society on par with the emergence of artificial intelligence. Our trip through cyberspace details how humble beginnings have brought about social credit scores in China and pervasive systems of surveillance capitalism in the West - and what is yet to come.

## Prehistory: Finger

<figure>
![](/img/2021-04-20-dog-robot-woman/lester-earnest-1976.jpg)Lester Earnest via [Visitor to SAIL on Wednesday 21 April 1976](https://www.saildart.org/Visitor_1976/)
</figure>

Finger, originally written in 1971, is the granddaddy of status updates. Running `finger dschmud@heartland.bradley.edu` on TCP port 79 &ldquo;return(ed) a friendly, human-oriented status report on a particular person in depth.&rdquo;

The original version only worked on the DEC 10 at the Stanford Artificial Intelligence Lab. But it was enough to help users &ldquo;locate potential volleyball players when you wanted to play, Chinese food freaks when you wanted to eat, and antisocial computer users when it appeared that something strange was happening on the system.&rdquo;[^earnest]

Once &ldquo;`Plan` files&rdquo; were added, `finger` &ldquo;evolved into a forum for social commentary and amusing observations.&rdquo; The protocol swiftly spread across the Arpanet.[^earnest]

[^earnest]: via Sean Colbath in personal correspondence with Les Earnest on February 19, 1990. [Posted publicly on alt.folklore.computers](https://groups.google.com/g/alt.folklore.computers/c/IdFAN6HPw3k/m/Ci5BfN8i26AJ?pli=1) (Usenet) the following day.

Finger introduced early digital privacy and security concerns. The protocol could divulge when a person was last logged into their computer and when they last checked their email. It also listed full names and eMail addresses in many configurations. In systems that used usernames and passwords to identify individuals, `finger` essentially provided half of what a hacker needed to authenticate. The second Finger RFC, [1288](https://tools.ietf.org/html/rfc1288) (1991), explicitly mentions these concerns.

The shifting priorities from RFC [742](https://tools.ietf.org/html/rfc742) (1977), the first Finger RFC, and RFC 1288 (1991) reflect the widening breadth of the internet community. Different actors with different priorities.

It's worth nothing that `finger`, unlike many privately owned social networks built on the world wide web, still works. Typing `finger schmudde@tilde.club` provides this information:

```
Login: schmudde       			        Name:
Directory: /home/schmudde           	Shell: /bin/bash
Last login Sun Feb 14 22:50 (UTC) on pts/47 from 2001:b07:ae6:7d19:a519:5691:a52a:dc49
No mail.
No Plan.
```

## CompuServe CB Simulator

Communication is Identity

"The history of naming is tied up with the history of messaging on the internet," observed network researcher [Brad Fidler](https://brfid.github.io/). Names are identifiers that help us find the people we want to talk to.


CompuServe was an early entry in networked computing.[^1980] People on the service were identified by a series of digits separated by a comma or a period. This format was a reflection of the DEC PDP-10 architecture where CompuServe was born.

[^1980]: 1970s/1980s

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

CompuServe's identifiers were only usable within the CompuServe system at first. This changed the year after Gabaldon's post. CompuServe ids could be addressed on the internet using the compuserve.com domain (e.g. `76530.523@compuserve.com`).

Assigned numbers are difficult for people to remember. CompuServe made their service more friendly by allowing for user-created handles in the mid-1990s. My original CompuServe number was `103625,1027`, my username was `dschmud`, and my handle on the forums was `snap347`[^snap]. That's three levels of identity: machine readable, account level, and public name.

[^snap]: band

## World Wide Web

<figure>
![](/img/2021-04-20-dog-robot-woman/diana-gabaldon-1997.png) Diana Gabaldon's [original homepage](https://web.archive.org/web/19970727230953/http://www.cco.caltech.edu/~gatti/gabaldon/gabaldon.html) (1997)
</figure>

The World Wide Web helped make the internet available to millions and millions of people around the earth. The Domain Name System (DNS) makes it possible to find a single person out of today's billions of internet citizens.

An author like Diana Gabaldon could have stayed on CompuServe. But CompuServe and contemporary cousins like Facebook limit who a user can address: rather than anyone on the internet, you're limited to a subset of people who have accounts on the system. It's archaic.

Gabaldon had established her presence on the World Wide Web by 1997 at `http://www.cco.caltech.edu/~gatti/gabaldon/gabaldon.html`.[^caltech]

[^caltech]: This caltech.edu URL is still valid, nearly a quarter-century later. [Visiting the link](http://www.cco.caltech.edu/~gatti/gabaldon/gabaldon.html) will helpfully inform you that &ldquo;The Diana Gabaldon website has moved to a new location.&rdquo; This is because the original webmaster, [Rosana Madrid Gatti](https://directory.caltech.edu/personnel/gatti), still has an active Caltech account.

Domain names are the quietly powerful and permissive identity engine of the internet. `dianagabaldon.com` first appeared in 2001 as an anti-abortion website showing graphic imagery. It's almost certain that the author was not involved; the original owners were attempting to leverage the identity of a New York Times best-selling author to spread their message. Records from the Internet Archive show that Diana Gabaldon took over the domain in 2002 redirected the domain to her university website at `http://www.cco.caltech.edu/~gatti/gabaldon/gabaldon.html` (HTTP 302).

Tens of millions of dollars have been spent to control a domain name. That's how valuable a name is to forming an identity.[^privatejet.com]

[^privatejet.com]: https://en.wikipedia.org/wiki/List_of_most_expensive_domain_names The acquisition of privatejet.com for $31 million in 2012 demonstrates the importance of names to a brand's identity. Nations Luxury Transportation, LLC (Nations) from Don’t Look Media Group

Names were not formally specified until [RFC 608](https://tools.ietf.org/html/rfc608), *Host Names On-Line*, in January 1974. These are a little different than domain names. A `<host-name>` originated with a request made to the Network Information Center (NIC) at Stanford. Only alphanumeric (`A`-`Z`, `0`-`9`) and the minus sign (`-`) were valid characters. If approved, it would be added to an ASCII file `HOSTS.TXT`. Now your machine could be accessed by a human-readable name. Other hosts would navigate to `<NETINFO>HOSTS.TXT` using FTP to download updates to the `HOSTS.TXT` file. As published in the RFC, the login was `GUEST` and the password was `ARPA` - such was the security on the network at the time.

`HOSTS.TXT` was essentially maintained by one person, Jake Feinler. Getting a new identity essentially amounted to giving Jake a call. The need for a more advanced system of naming on a global information network is obvious. Today's Domain Name System (DNS) emerged in 1987 (RFCs #1034 and #1035) and was soon adopted across the entire internet.

The domain is an *address* that is machine-friendly. When combined with a *handle*, it can provide meaningful contact information for another person, such as `webmaster@dianagabaldon.com`. An eMail address can also be used as a *username* in another computer system. Finally, with technology such as [IndieAuth](https://indieauth.net/), a domain name can be used to *authenticate* a domain owner in another computer system. All of this works because there is some way to prove that you control either the domain name or the username on the domain name.[^open-id]

[^open-id]: &ldquo;You bring an existing identity (your email address) and then authenticate (usually by clicking a link that was sent to your email). The original version of OpenID was created to solve this problem on the web. People identified themselves with a URL, which they were able to prove they controlled using OpenID. This allows a new user to log in to a site without needing a prior relationship with the site.&rdquo; ~ [OAuth for the Open Web](https://aaronparecki.com/2018/07/07/7/oauth-for-the-open-web) by Aaron Parecki

## Advertising Networks

But DNS isn't the way that most people find most people on today's internet, let alone message. Advertising networks[^2010s] stepped in an created the familiar user silos we have today. Diana Gabaldon snagged the Twitter handle @[`Writer_DG`](https://twitter.com/Writer_DG) in 2010 and the Facebook url [`AuthorDianaGabaldon`](https://www.facebook.com/AuthorDianaGabaldon) on January 31, 2011.

[^2010s]: 2010s-today

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

[^face-server]: A progenitor of Facebook is Rob Pike and David L. Presotto's &ldquo;[face server](http://doc.cat-v.org/bell_labs/face_the_nation/).&rdquo; All computers with permission host the server could display the faces in eMail correspondence, etc.... As Unix hacker [Jack Rusher](https://twitter.com/jackrusher/status/1381967601950060552) pointed out to me, the [big face](https://spinroot.com/pico/pjw.html) in the screenshot below is [Peter J. Weinberger](https://en.wikipedia.org/wiki/Peter_J._Weinberger), perhaps most well-known as the **w** in the Unix `awk` command. ![](/img/2021-04-20-dog-robot-woman/face-server.gif)

As the resolution of the medium increases, so does the power of our online identity. Yesterday's two-dimensional images are today's Snapchat filters and [Memoji](https://www.howtogeek.com/450743/how-to-create-and-use-memoji-and-animoji-on-an-iphone/). These technologies are the foundation of tomorrow's projected self.

Our digital proxies will be granted credentials that impact the privileges of our physical bodies. Locating and authenticating our digital proxy are the essential mechanisms in this process. Today it is largely done through DNS and username/password pairs. Tomorrow it will likely done by a unique address with no central assignment body and a key.

The next two posts will grapple the assumptions and political bias in identity while finally looking at some of the specific emergent technologies.
