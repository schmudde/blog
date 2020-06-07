---
title: The Big Bang of Cyberspace - Part 1/
description: The impact of a knowledge-based society is emerging. Take a step back to see it emerge.
author: David Schmudde
author-email: d@schmud.de
author-url: http://schmud.de
author-github: schmudde
author-twitter: dschmudde
location: Turin, Italy
date-created: 2020-05-30
date-modified: 2020-05-30
date-published:
in-language: en
keywords: informatics,
tags:
 - humanities
 - science
---

The internet has already changed our everyday lives. It promises to have an even larger impact in the next twenty years. The internet will have a dramatic impact on our sense of memory and privacy (us as individuals) as well as politics and finance (us as nations).

---

No one person invented the idea of a shared electronic knowledge space. However, a useful historical demarcation is the publication of the *[First Draft of a Report on the EDVAC](/papers/first-draft-edvac-vonneumann-1945.pdf)* (1945) by John von Neumann and *A Mathematical Theory of Communication*, parts [I](/papers/math-theory-comm-1948-1.pdf) & [II](/papers/math-theory-comm-1948-2.pdf) (1948) by Claude Shannon. The ideas in these papers were immediatley baked into computing and telecommunications. They are foundational to every product we use today.

## Computer Automation

Von Neumann laid out the three main internal "organs" of a computer in *First Draft of a Report on the EDVAC*:

1. The arithmetic unit
2. The control unit
3. The memory unit

These units described in 1948 are the exact same layout we use in computing today. In fact, the architure of the machine you're reading this on is sometimes referred to as having a "Von Neumann" architecture.

The building blocks are simple. The arithmetic unit receives numbers from the memory, computes, and provides a result which is stores in memory. The control unit coordinates this getting and putting of information based on a strict clock cycle, illustrated below in Neumann's paper.

![](/img/2020-05-28-birth-cyberspace/edvac-1.png)

Notice each pulse is an evenly spaced, either up (on) or down (off). The computer is in a stable state at every click of the clock. In between each tick, the computer is in transition from one state to the next. Electrons are simultaneously rushing to and from the memory unit and through millions of gates in the arithmetic unit. It's chaos that promises to settle at every tick.

### MICR

![](/img/2020-05-28-birth-cyberspace/micr-char.svg.png)

Punch card tabulators automated parts of the finance industry long before Von Neumann's paper. But if time is money, the speed, accuracy, and reliability of electronic computers were a revolution. The first step was to automate paper transactions, the cornerstone of finance. The arrival of Magnetic Ink Character Recognition (MICR) in 1958 was the first step.

Once computers could read paper, money became much faster and easier to move.

### SABRE

Computer automation also made people easier to move. American Airlines concurrently introduced SABRE (Semi-Automated Business Research Environment), making flight reservations instantaneous.

![](/img/2020-05-28-birth-cyberspace/sabre.jpg)

## Information Theory

Binary telecommunications excited for decades before Claude Shannon published his paper. The telegraph relied on a set of pulses known as *Morse Code* to encode and decode letters sent over a distance.

![](/img/2020-05-28-birth-cyberspace/general-com-sys-1948.png)[^bstj]

[^bstj]: {-} The Bell System Technical Journal,Vol. 27, pp. [379–423](https://archive.org/details/bstj27-3-379), [623–656](https://archive.org/details/bstj27-4-623), July, October, 1948

The word *tele-graph* literally means *far-writing*; encoding is a requite of writing over a distance. As seen above in Shannon's illustration above, noise is always introduced into any transmission. *A Mathematical Theory of Communication* not only describes how to systematically encode complex information, but what to do if parts of that information is lost due to noise:

![](/img/2020-05-28-birth-cyberspace/correction-system-1948.png)[^bstj-2]

[^bstj-2]: {-} The Bell System Technical Journal,Vol. 27, pp. [379–423](https://archive.org/details/bstj27-3-379), [623–656](https://archive.org/details/bstj27-4-623), July, October, 1948

Information that is relably encoded can be compressed, addressed, broken up, and reassembled.

The internet relies on the fact that information can be broken up and reassembled. Telegraphs require a direct connection between sender and received. But internet protocols allow us to break up the message, send parts of it over multiple routes, and reassemble it at the destination. It's called packet switching.[^ipfs]

[^ipfs]: The internet of the future may rely on the fact that information, properly encoded, has a specific signature and can be addressed directly. [IPFS](https://ipfs.io/) is one such protocol gaining broad acceptance. Information theory is a deep concept that is still bearing fruit.

### Telstar

Computers were making data easier to process and transmit. IBM transmitted the first data over satellite from New York to France in 1962.[^telstar]

[^telstar]: "[IBM Archives: IBM And Telstar](https://www.ibm.com/ibm/history/exhibits/space/space_9208ph11.html)", ibm.com, 2003.

Moving ideas.

<iframe width="560" height="315" src="https://www.youtube.com/embed/uKH-GijnAGk?start=1510" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

## Outcomes


Our contemporary means of information sharing arrived at the advent of [packet switching](https://en.wikipedia.org/wiki/Packet_switching) in the early 1970s. By this time, computer networks like the NPL network, the ARPANET, and CYCLADES demonstrated the viability of packet switching. The specfic implmentation of packet switching that runs today's internet, TCP/IP, began service on January 1, 1983.[^tcp-ip]

[^tcp-ip]: Arpanet switched to TCP/IP on January 1, 1983. NSF's CSnet (National Science Foundation's Computer Science network) came online that same month, also using TCP/IP. Dennis Jennings came to the NSF in 1985 [to lead the adoption of TCP/IP](https://www.internetsociety.org/internet/history-internet/brief-history-internet/) across the entire program, including the new supercomputing network. Other non-NSF comptuer networks soon followed.

The twenty year period between 1945-1970 is a primordial version of today's information society.


![](/img/2020-05-28-birth-cyberspace/tramiel-kapp.png)[^commodore]

[^commodore]: {-} "Commodore Portable Typewriter Company, Ltd Formation". Government Record. Toronto, 1958. [Commodore Historical Documents](https://archive.org/details/commodorehistory). Commodore International Historical Society.

The digitization of services made it easier to move ideas, people, and money. Even a small business like Commodore could afford to operate globally. Tramiel, who lost his home and country when he was a child, was a good candidate to help usher in the new age of global operation.

- result: emergence of a small business as a global player (picture of Tramiel and Kapp). Soon wire transfers and ATMs. Also Church committee


Tramiel and Kapp founded Commodore Portable Typewriter Limited on October 10, 1958 in Canada to support the new venture with Zbrojovka Brno.1 The Czech typewriters were branded with the Commodore trademark wherever Tramiel could find a willing buyer. Substantial orders for the inexpensive typewriters were placed by Toronto's two largest department stores, T. Eaton Co. Limited and the Robert Simpson Co.2

"Letters Patent Incorporating Commodore Portable Typewriter Company Limited", Government Record (Pittsburgh, 1958), Commodore International Historical Society.

Report Of The Royal Commission Appointed To Inquire Into The Failure Of Atlantic Acceptance Corporation, Limited, 108.







---
