---
title: The Mothers of the Mother of All Demos
description: Bill English was part of a team that revolutionized computing. A look at the shoulders they stood on.
author: David Schmudde
author-email: d@schmud.de
author-url: http://schmud.de
author-github: schmudde
author-twitter: dschmudde
location: Berlin, Germany
date-created: 2020-05-30
date-modified: 2020-08-05
date-published: 2020-08-04
in-language: en
keywords: informatics, information theory, computation, STS, Engelbart, Von Neumann, Shannon
tags:
 - humanities
 - science
---

![](/img/2020-08-04-mother-of-mothers/bill-english.jpg)[^english]

[^english]: {-} Bill English (image via the [English family](https://www.nytimes.com/2020/07/31/technology/william-english-who-helped-build-the-computer-mouse-dies-at-91.html))

*The Mother of All Demos* (1968) marks the introduction of several seminal computer technologies such as the mouse, hypertext, and the graphical user interface. The person [credited with directing the event](https://www.nytimes.com/2020/07/31/technology/william-english-who-helped-build-the-computer-mouse-dies-at-91.html), Bill English, passed away just two days ago. The demonstration is still striking today because of its breakthrough technologies and dramatic, almost ominous presentation.[^demo-description]

[^demo-description]: The presentation was given by Douglas Engelbart during his time at the Augmentation Research Center Lab in SRI International. Engelbart was generally focused on augmenting human intelligence through collaborative tools of knowledge.

<figure class="iframe-wrapper">
<iframe width="853" height="480" src="https://www.youtube.com/embed/qI8r8D46JOY" frameborder="0" allowfullscreen></iframe>
</figure>

The demo is almost platonic in its concept of computing; today's remote office runs on collaborative applications that look remarkably similar to what was shown a half-century ago. The tools were built upon two essential foundations: the **programmable electronic computer** and the **transmission of digital information**. No single person came up with these ideas, but two papers arguably bridge the gap from concept to applied engineering:

1. The programmable electronic computer: *[First Draft of a Report on the EDVAC](/papers/first-draft-edvac-vonneumann-1945.pdf)* by John von Neumann (1945)
2. The transmission of digital information: *A Mathematical Theory of Communication*, parts [I](/papers/math-theory-comm-1948-1.pdf) & [II](/papers/math-theory-comm-1948-2.pdf) by Claude Shannon (1948)

Within ten years of their publication, work started on the technologies that form the foundation for *The Mother of All Demos*. Many companies that applied these mid-century inventions endure to this day. What follows is a brief survey.

## The Programmable Electronic Computer

Von Neumann laid out the three main internal "organs" of a computer in *First Draft of a Report on the EDVAC*:

1. The arithmetic unit
2. The control unit
3. The memory unit

These units as described in 1945 essentially remain the foundation for computing today. The **arithmetic unit** receives numbers from the **memory unit**, computes them, and then provides a result which is once again stored. The **control unit** coordinates the getting and putting of information based on a strict clock cycle, illustrated below in Neumann's paper:

![](/img/2020-08-04-mother-of-mothers/edvac-1.png)[^clock]

[^clock]: {-} Illustration from *First Draft of a Report on the EDVAC*. Notice each pulse is an evenly spaced, either up (on) or down (off).

The computer promises to be in a stable state at every tick of the clock. In between each tick, the computer is in transition from one state to the next - electrons rush to and from the memory unit and through millions of gates in the arithmetic unit. The newly stabilized arrangement will determine what happens during the next clock cycle.

An entire computer industry was established shortly after the publication of *First Draft of a Report on the EDVAC*. MICR and SABRE are just two examples of their early application. Making the movement of money and people easier helped establish the modern global economy.

### 1. MICR (~1958)

![](/img/2020-08-04-mother-of-mothers/micr-char.svg.png)[^micr]

[^micr]: {-} An example of the MICR E-13B font - both machine and human-readable.

Punch card tabulators automated parts of the finance industry long before Von Neumann's paper. But if time is money, the speed, accuracy, and reliability of electronic computers were a revolution. Magnetic ink character recognition (MICR) was a critical step towards transaction automation.[^electronics-in-banking]

[^electronics-in-banking]:Ken Gilmore, "Electronics In Banking", *Electronics World*, April 1963, pp. 29.

> The giant Bank of America was already having trouble hiring enough people to keep up with its soaring paper work. So in 1957 it hired Stanford Research Institute to work out the details of the magnetic ink proposal. Stanford scientists designed a series of magnetic characters which would be readable by both human beings and magnetic sensing devices. [...] Bank of America cautiously put ERMA (Electronic Recording Machine Accounting) to work in 1958, but kept bookkeepers on the job - just in case. Within a few months, however, it was obvious that ERMA could not only do the job faster than people, but more accurately as well. [...] Already, nearly 70% of the checks now flowing through the Federal Reserve System bear the small magnetic numbers on the lower edge.
>
> <footer>
> Ken Gilmore *Electronics World* (1963)
> </footer>

Once computers could read paper and automatically calculate transactions, money became much faster and easier to move.[^micr-burroughs]

[^micr-burroughs]: The initial work on the system was done by General Electric in conjunction with SRI, but it was the computing firm Burroughs that truly exploited MICR's adoption. It was a cornerstone of the company for decades. The founder of Burroughs is the grandfather of writer William S. Burroughs. The author helped popularize a [cut-up technique](https://en.wikipedia.org/wiki/Cut-up_technique) that could be executed as a mechanical process.

### 2. SABRE (~1960)

Computer automation also made people easier to move. SABRE (Semi-Automated Business Research Environment), an airline reservation system built by IBM, helped American Airlines capitalize on the growing demand for air travel. The company still operates today, handling [a third of all air travel bookings in the world](https://www.forbes.com/sites/thomasbrewster/2020/07/16/the-fbi-is-secretly-using-a-2-billion-company-for-global-travel-surveillance--the-us-could-do-the-same-to-track-covid-19/).[^database]

[^database]: Gloria Guevara, former CEO of Sabre’s Mexican business, claims that between 1995 and 2010 Sabre operated one of the two largest privately-owned databases in the world. This makes it a rich resource for state surveillance - having publicly complied with American investigators in 2016, 2017, and 2019. [Thomas Brewster, "[The FBI Is Secretly Using A $2 Billion Travel Company As A Global Surveillance Tool](https://www.forbes.com/sites/thomasbrewster/2020/07/16/the-fbi-is-secretly-using-a-2-billion-company-for-global-travel-surveillance--the-us-could-do-the-same-to-track-covid-19/)", *Forbes*, 2020.]

![](/img/2020-08-04-mother-of-mothers/sabre.jpg)

Creating a database for flights and reserved seats was only part of the problem. Transmitting that information to other systems was just as crucial.

## Information Theory

Claude Shannon's *A Mathematical Theory of Communication* describes how to systematically encode complex information and deal with its loss when noise is inevitably introduced into its transmission:[^morse]

[^morse]: Digital telecommunications excited for decades before Claude Shannon published his paper. For example, the telegraph relied on a set of pulses known as *Morse Code* to encode and decode letters sent over a distance. The word *tele-graph* literally means *far-writing*; encoding is a requite for speedy communication over a distance.

![](/img/2020-08-04-mother-of-mothers/general-com-sys-1948.png)[^bstj]

[^bstj]: {-} The Bell System Technical Journal,Vol. 27, pp. [379–423](https://archive.org/details/bstj27-3-379), [623–656](https://archive.org/details/bstj27-4-623), July, October, 1948

If noise corrupts the information, the system must know how to correct the error:

![](/img/2020-08-04-mother-of-mothers/correction-system-1948.png)[^bstj-2]

[^bstj-2]: {-} The Bell System Technical Journal,Vol. 27, pp. [379–423](https://archive.org/details/bstj27-3-379), [623–656](https://archive.org/details/bstj27-4-623), July, October, 1948

Information encoded with certain resiliency guarantees can be compressed or broken up before it is sent out. A promise of data integrity means that fragments can addressed from anywhere in the world and ultimately reassembled into a meaningful whole.

Information theory immediately made the movement of people, money, and ideas easier. John Ranke, the former chair of [American National Standards Institute (ANSI)](https://www.ansi.org/about_ansi/structure_management/board_directors/rankine) and an [early actor in the field of digital privacy](/posts/2020-06-15-personal-privacy.html) in the information age, testified about this power to the United States Congress in the 1980s:[^personal-privacy]

> Neither the United States nor European aerospace industries could have progressed as they have without a high degree of information exchange, thus ushering in a whole new era in air transportation. Similar examples exist in the fields of medicine, education, communications, energy, information processing, and consumer services.
>
> <footer>
> John Rankine, Director of Standards, Product Safety and Data Security, IBM Corp., *Statement to the United States House of Representatives*, March 10, 1980.
> </footer>

[^personal-privacy]: I originally cited John Rankine in [*Personal Privacy*](/posts/2020-06-15-personal-privacy.html), prompted by the IBM advertisement seen below. [*Personal Privacy*](/posts/2020-06-15-personal-privacy.html) examines why digital privacy laws have been so ineffective after a half century of legislation. ![](/img/2020-06-15-personal-privacy/privacy-begins-in-home.jpeg)

### SABRE (~1960)

The SABRE system would not have been possible without a modem to transmit flight reservations between computers. AT&T introduced their first commercial modem, Bell 101 Dataset Modem, in 1958. These computers used a communications protocol that was eventually displaced with the invention of [packet switching](https://en.wikipedia.org/wiki/Packet_switching), seen in early computer networks like the NPL network, the ARPANET, and CYCLADES.[^tcp-ip]

[^tcp-ip]: The specific implementation of packet switching that runs today's internet, TCP/IP, began service on the ARPANET on January 1, 1983. NSF's CSnet (National Science Foundation's Computer Science network) came online that same month, also using TCP/IP. Dennis Jennings came to the NSF in 1985 [to lead the adoption of TCP/IP](https://www.internetsociety.org/internet/history-internet/brief-history-internet/) across the entire program, including the new supercomputing network. Other non-NSF computer networks soon followed and the protocol is now ubiquitous.

### Telstar (1962)

SABRE aided the speedy movement of people, but ideas can move much faster. Satellite communication makes their transmission nearly instantaneous and much more flexible over vast distances.

IBM transmitted the first data over satellite from New York to France in 1962.[^telstar] This endeavor also marks the first video broadcast via satellite in human history.

[^telstar]: "[IBM Archives: IBM And Telstar](https://www.ibm.com/ibm/history/exhibits/space/space_9208ph11.html)", ibm.com, 2003.

Fifty years later, Brian Williams signs off NBC Nightly News by showing live footage from Cairo, Egypt and thanking &ldquo;Telstar, version 12.&rdquo;

<figure class="iframe-wrapper">
<iframe width="560" height="315" src="https://www.youtube.com/embed/2ZqgOiyFiy8?controls=0" frameborder="0" allowfullscreen></iframe>
</figure>

<!-- <iframe width="853" height="480" src="https://www.youtube.com/embed/uKH-GijnAGk?start=1510" frameborder="0" allowfullscreen></iframe> -->

The *Mother of All Demos* is the synthesis of speedy information processing and transmission, conceived in a way to augment human intelligence and aid collaboration over a distance. We stand on the shoulders of giants who stood on the shoulders of giants.
