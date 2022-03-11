---
title: The Identity Trap
description:
author: David Schmudde
author-email: d@schmud.de
author-url: http://schmud.de
author-github: schmudde
author-twitter: dschmudde
location: Turin, Italy
date-created: 2021-04-21
date-modified: 2021-04-21
date-published:
in-language: en
tags:
 - sts
---

Leaving no one behind.


# The Identity Trap

- *The Identity Trap*: consciously  controlling your own data is a trap.
    - There will be too much. the middle path to data ownership.
    - intro: signals - the collective vs. differences. the sense of self, datafied, quantified. Find different collectives that are empirically useful or neferuosuly exploitable.
    - Ask yourself, What about the world can my readers better appreciate thanks to my article? - interdependence, interpersonal data
    - Share where they go next to continue the journey they started with you.


Software and data is fundamentally different than other things.

To build a better future, we must figure out the essence of what data is. Then we must work within it. And the essence of data is fundamentally interdependent. Personal data is a trap, just like the cult of the individual that always leads to suffering. Except this time the illusion is not so strong. We can imagine ourselves operating in isolation. But data is totally useless in isolation.

---

<figure>
![Buddhist monks in meditation](/img/identity-trap/monks.jpg)*Untitled* [Honey Kochphon Onshawee](https://pixabay.com/users/suc-379056/) (2012) <span property="license"><a class="link" href="https://pixabay.com/service/license/" rel="license">Pixabay License</i></a></span>
</figure>


Dualistic notions of identity are situated as independence versus conformity.

Independence requires us to assert a sense of self. Selfhood is a slippery concept. It is inherently ephemeral. We know we are connected our childhood selves, but we are certainly not the same person.

In a world of individuals, we are constantly asked to prove our individual identity. ID numbers, driver's licenses, credit cards, usernames, and passwords are part of our everyday activity. Each captures some part of our identity in this moment but makes no promises for the next.

Identifiers help individuals move through society and gatekeepers manage communities. Naturally, there have been many efforts to implement similar systems online. They have failed because they are working against two idiosyncratic qualities of data:

1. Data is quantified while identities are overwhelmingly qualified.
2. Data's value comes from its interdependence while identity's value comes from its independence.

**It's an ego trap to think that identity can be modeled.**

## The Quantified Self

<figure>
![Lucas by Chuck Close](/img/identity-trap/lucas.jpeg)[*Lucas I*](https://www.metmuseum.org/art/collection/search/484760) Chuck Close (1986-87) (L); [*Lucas*](https://www.metmuseum.org/art/collection/search/499115) Chuck Close (2011) (R) &copy; Chuck Close
</figure>


As William Kent illustrates in *Data and Reality*, capturing algebraic concepts such as "oneness" and "sameness" in a database is riddled with trade-offs. What is one thing? What are its formal boundaries? How many things are actually the same thing? I am my father's son. I am also my son's father. Myself, father, and son can be the same thing in one context, but different things in a different context.[^data-and-reality]

[^data-and-reality]: (pg 38-43)

Even the easy attributes are hard to capture in a database. The Buddhists were aware of the elusive nature of identity millennia ago. It's called dependent arising. As the *The Lankavatara Sutra* illustrates: we know a rabbit with horns does not exist. Our discernment between a rabbit with horns and a real rabbit *depends* on the horns. All reality, whether it be real or imagined, arises from a interdependent state.[^lankavatara-sutra]

[^lankavatara-sutra]: &ldquo;Discriminations arise in dependence on something. They arise in dependence on horns. And because a discrimination arises in dependence on horns, they are said to be its cause. Hence, it is not because observation doesn’t result in their discrimination that they say horns do not exist, rather it is because they are neither separate nor not separate.&rdquo; (loc. 1293-1296) The ultimate goal of &ldquo;dependent arising&rdquo; is to step out of conditionality (this causes that) altogether.

There is no "I" without "us".

All databases necessarily start by asserting a platonic identifier. It's the original sin, but also the only option when capturing information in the quantified digital domain. Several strategies have emerged to mitigate the limitations of digital data.

1. A set of platonic, sovereign facts such as your eye color or your age. (table)
2. A set of platonic credentials or titles issued by an abstract entity. (table)
3. A network of associations with other people. (social graph)
4. A narrative about your history that informs who you are today. (knowledge graph)

While they all remain predicated on basic assumptions about individual identity, these techniques can be combined to form powerful portraits of people.

One important nuance is the development of database technology itself. The first two techniques almost certainly represented in a tabular form, like a spreadsheet. A set of tables that assert facts can be related to form a Relational Database.

Graph databases may be used to capture the last two techniques. Each data point is a node; each relationship is a connecting edge. Distant relationships can be drawn and the strength and type of those relationships can be inferred or explicit.

Conventional wisdom has declared that the structure of the data is the most important decision in a given project. As Rob Pike stated in 1989, &ldquo;If you've chosen the right data structures and organized things well, the algorithms will almost always be self-evident.&rdquo;[^pike]

[^pike]: But the axiom surely goes back further Pike continues, &ldquo;Data structures, not algorithms, are central to programming.  (See The Mythical Man-Month: Essays on Software Engineering by F. P. Brooks, page 102.)&rdquo;

Different databases enforce different fictions of identity. The disconnect between reality as it is experienced and reality as it is represented is where the problems arise.

## The State of Identity

Philip E. Agre argues in *Computation and Human Experience* that an achievable disembodied sense of identity exist in the roots of computer science. Alan Turing, the father of Artificial Intelligence, saw it as a real possibility.[^agre] The possible became widely practical in the 1990s when &ldquo;each user on the network, according to Barlow and his fellow thinkers, [was declared] 'free'.&rdquo;[^kraynak] John Perry Barlow was a child of the 1960s, a member of the Grateful Dead, and the author of *[A Declaration of the Independence of Cyberspace](https://www.eff.org/cyberspace-independence)* (1996).

Kraynak and other critics have argued that the freedom Barlow was espousing inadvertently pushed aside the social inequalities rooted in our embodied experience. The counterpoint is a competing set of ideas largely known as "identity politics."[^kraynak2]

[^agre]: &ldquo;Embodied computation The internal space of computation, then, like the Cartesian soul, is a not simply partitioned off from the outside world but actually different in kind. [...] Turing idealized disembodied thought as he suffered homophobic oppression in modern England. As physics changed and explanatory metaphors multiplied, **the soul's identity as a realm of pure thought evolved and grew sharper**.&rdquo; (pg 121)

[^kraynak]: pg 46: the rising of non-identity digital utopianism on the net and identity politics at the same time.

[^kraynak2]: Kraynak frames the zeitgeist with the 1993 Whitney Biennial in New York City. Joseph Henry [describes the event](https://esse.ca/en/compte-rendu/81/NewYork-3) as thus, &ldquo;Dubbed in shorthand as the 'identity politics biennial,' the show focused explicitly on issues around gender, race, and sexuality and was summarily panned. In 2013, the ’93 Biennial became historical, lauded as a brave step in museum programming that set the tenor for much American art of the 1990s.&rdquo;

Digital identity and its associated reputation are perhaps most prominent on today's major social platforms. These platforms are rooted in the ideas of the early internet pioneers such as J.C.R. Licklider. As covered in [*On the Internet, We Are Either Artists or Bureaucrats*](/posts/2020-06-23-internet-community.html), Licklider was bullish on the idea of online communities of shared interest where good ideas were surfaced in a meritocracy. But he spoke very little about the *communities* in his technocratic vision.

Social networks built tools of identity and reputation based on these principles. They even retained the mid-century language of &ldquo;computer users.&rdquo; The term is alarmingly honest. Users don't build communities, people do. Users cannot move freely, they are tied to what they are using. In this case, users are restricted to using the service based on the service's terms. The service controls a user's identity and their reputation.

The social networks have trapped each user's digital identity. This information has little value on its own. But when it's correlated with millions of other identities and refined for advertisers, its worth underpins the value of some of the world's biggest companies.

Data has two characteristics that set it apart from material commodities like crude oil.[^oil]

1. Consumption by one party does not prevent the same data to be consumed again by another party.
2. The usefulness of any single datapoint increases the more the dataset is grown, shared, combined, and modified.

[^oil]: Data is not the new oil as asserted by Clive Humby in 2006. (see Arthur, Charles; editor, technology (2013-08-23). "Tech giants may be huge, but nothing matches big data". The Guardian. ISSN 0261-3077. Retrieved 2019-04-30.)

The diminishing power of individuals in this scheme has inspired activism, legislation, and new technology. [Salomé Viljoen](https://www.salomeviljoen.com) asserts that many of the proposed solutions treat data on the continuum from "person-like" to "object-like."[^viljoen] The former asserts that data is an extension of the self and should be protected by inaliable human rights. The later asserts that data that we produce - whether as a byproduct of our labor or our biological functions - is something we own. As our property, we should have the ability to control and trade this information.

[^viljoen]: Democratic Data: A Relational Theory For Data Governance

In either case, arguments normally boil down to more individual control over our individual data.

## Cult of the Individual

But the limitations of consent and individual agency are increasingly self-evident. The threats are. To make matters more complex, the dangers posed may be more common or different in nature depending on the ---- of the individuals being identified.

Here is an anonymous author, presumably identifying as a woman in the article *I Can't Stop Thinking About That Emily Article*, about her experience:

> It goes beyond ownership. It's about having collective agency in a space that wasn't built for me. I'm not equipped to deal with it. Neither is the feminism I grew up with. Because that feminism focused on the individual choice and how great it was to have that choice. [...] We need new models of feminism, [ones] that don't focus on our individual choices - those are failing. Our identities are not up for sale, for circulation or distortion
>
><footer>Identity 2.0 (pg 10)</footer>

The author is pointing to a familiar helplessness that increased agency is supposed to help. They paint a picture of what this agency looks like today.

> And you see these ideas mimicked in the way privacy is constructed online. Don't like how we are doing things? Then of course you can opt out. [...] Want to understand what is happening online? Then you can study for 4 years, get a law degree, then spend up to 32 minutes per website reading our privacy policy.
>
><footer>Identity 2.0 (pg 10)</footer>

Therefore Viljoen suggests a third way forward. Democratic Data. Democratic because it is fundamentally relational. There is no way to reduce it to the individual.

> Data production in the digital economy is fundamentally relational: a basic purpose of data production is to relate people to one another on the basis of relevant shared population features.

She even goes on to assert that data collection is fundamentally "irreducible to individual legal interests".

> data’s relationality results in widespread population-level interests in data collection and use that are irreducible to individual legal interests within a given data exchange


---

The more incentives and technology align to place more autonomy in the hands of the individual, the more likely that data management will become stratified like financial management. Data and finance are both abstract domains. Mastering either requires extensive training. Fraudsters and criminals will prey on the most vulnerable. Those who excel in this ecosystem will endlessly promote its virtues.

The unique properties of data offers many new possibilities and countless trappings of our past. The proposals to treat data as personal property e

The technologies that provide people with more control over their data are imbued with a certain political dimension. But their implementation is still an open question. Choices we make today establish important cultural norms for the first generation of adopters. The goal of self-sovereign identity is not enough; [but mutual freedom].

Unfortunately, like any new technology, it's rather opaque. This essay will look at some current options through person-centered lens.

[ONCE it is broken out, then the democratization of data is possible... only because it can be used in public service and not just the benefit of corporations]


## Interdependence Project

> argues for governing many types of data as a collective resource that necessitates far more democratic, as opposed to personal, forms of institutional governance.


Source: The age of digital interdependence, Report of the UN Secretary-General’s High-level Panel on Digital Cooperation

- declaration of Interdependence
- leaving no one behind
- Human Rights and Human Agency

Data's value comes from its interdependence while identity's value comes from its independence.

Monastic life, on the other hand, is a group of people relinquishing their differences. Fewer identifiers are required; core similarities are more important than individualistic traits.

Programming is essentially the act of transforming information again and again. When we add our "personal data" to a system, we must expect it to be interpreted and mashed into new representations over and over again. The process will occur over countless systems.



--- --- ---

> It turns out that there is very few data that may be described as purely personal data. That lunch date, that genome map, those photos, that joint bank account — all turn out to be interpersonal data. In fact, a personal bank account also records the relationship between two parties / persons (the bank and its customer), and lists transactions between the account owner and other parties / persons.

~ https://akasha.org/blog/2019/01/17/interpersonal-data-1-of-3/

> Human rights  —  in stark contrast to property rights  —  are universal, indivisible, and inalienable. They attach to each of us individually as humans, cannot be divided into sticks in a bundle, and cannot be surrendered, transferred, or sold. ... While they may be codified or legally recognized by external sources when protected through constitutional or international laws, they exist independent of such legal documents. The property law paradigm for data ownership loses sight of these intrinsic rights that may attach to our data. Just because something is property-like, does not mean that it is  —  or that it should be  —  subject to property law.

~ [Elizabeth M. Renieris](https://medium.com/@hackylawyER/do-we-really-want-to-sell-ourselves-the-risks-of-a-property-law-paradigm-for-data-ownership-b217e42edffa)

Going beyond [Self-Soveign Identity](https://akasha.org/blog/2019/09/02/generative-identity-beyond-self-sovereignty).

> The challenge with "personal data" is that this is a legal concept, and doesn't reflect how data works in the real work, so to speak. In reality, nearly all personal data is actually interpersonal data. It describes a relationship, informing or resulting from an interaction with others.

> The challenge with "digital identity" is that it represents a digital equivalent to legal identity, and not how identity works in the real world (there's a pattern here!) Identity and personal data and relationships and co-constitutive and reciprocally defining.... are co-constitutive and reciprocally defining.

Philip Sheldrake

> "There might well be a market for personal data, just like there is, tragically, a market for live human organs, but that does not mean that we can or should give that market the blessing of legislation. One cannot monetise and subject a fundamental right to a simple commercial transaction, even if it is the individual concerned by the data who is a party to the transaction."

~ The European Data Protection Supervisor, 2017

> For more on why we must avoid the propertization of data: https://akasha.org/blog/2019/01/21/interpersonal-data-2-of-3

~ Philip Sheldrake

> @Philip Sheldrake Them and us already exists - and the "us" in this relationship is already the weak party. The relationship can easily become "all mine is mine and all yours is mine...". :-) And the stronger actors in this context only think in and respect property approaches...

~ Sten-Erik Björling

> Yes, I appreciate that, hence "propagates". Perhaps I should have written "sustains" :-) The US platform giants have a property mentality, I agree. The next generation internet can move beyond this extremely limited mentality, per the current speaker's emphasis on the social value.

> This is how I like to introduce this topic myself … Put starkly, many millions of people have been excluded, persecuted, and murdered with the assistance of prior identity architectures, and no other facet of information technology smashes into the human condition in quite the same way as “digital identity”. Therefore, if ever there’s a technological innovation for which “move fast and break things” is not the best maxim, this is it.

~ Philip Sheldrake

# ... The New Oil

https://link.medium.com/iy1SJ8bAtfb

One of the major ideas on Digital Colonialism

Back in the dark ages of 2016, data was touted as the new oil. Although the metaphor was quickly debunked it’s still a helpful way to understand the global digital economy. Now, as international negotiations over data flows intensify, the oil comparison helps explain the economics of what’s called “data localisation” – the bid to keep citizens’ data within their own country.

‘Digital colonialism’: why some countries want to take control of their people’s data from Big Tech

Source https://theconversation.com/digital-colonialism-why-some-countries-want-to-take-control-of-their-peoples-data-from-big-tech-123048

---

Me:
In exchange for mobility and access, the individual agrees to hand over their data. There is a tangible freedom gained from computational quantification. More rewards will be conferred to the individual as more aspects of our lives are quantified. Reputation will likely become the most lucrative because its benefits can expand far beyond the boundaries of a single lifetime.

World wide web message boards, dialup/telnet bulletin boards, and usenet newsgroups were unconsciously creating new cliques, acceptable norms called netiquette, and  re-introducing old norms based on existing hierarchies in the real world.

The internet is a network of networks. Today's social platforms such as Facebook and TikTok are privately-owned public networks built on top of the network of networks. But their networks stop at the edges of the domain name.




<blockquote>
> Oil is rivalrous (its consumption by one consumer prevents simultaneous consumption by other consumers), whereas data is naturally non-rivalrous and quite possibly anti-rivalrous in some contexts (where the value is all the greater the more it's shared around)
>
<footer>Philip Sheldrake, [*The misleading name, metaphor defiance, and awesome potential of "personal data"* — part 2 of 3](https://akasha.org/blog/2019/01/21/interpersonal-data-2-of-3/)</footer>
</blockquote>
