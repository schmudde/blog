---
title: Turning URLs Into Meaningful Names Using Clojure
description: URLs are globally unique names, but the flexibility of the subdomain assignment makes turning them into meaningful identifiers tricky. Here are some practical solutions using Clojure.
author: David Schmudde
author-email: d@schmud.de
author-url: http://schmud.de
author-github: schmudde
author-twitter: dschmudde
location: Turin, Italy
date-created: 2021-12-01
date-modified: 2022-04-24
date-published: 2022-04-24
in-language: en
keywords: dns, apache, java
tags:
 - clojure
---


![](/img/2022-04-24-urls-into-meaningful-names/tree-cover.png)

URLs are wonderful to work with because they are structured, human meaningful, globally unique identifiers. But they do have a few thorny edge cases.

The following examples explore the domain name system using Clojure. The goal is to derive meaningful information about a resource owner's identity from any URL. For example, the URLs [`http://wikipedia.org/wiki/Peoria`](http://wikipedia.org/wiki/Peoria) and [`https://en.wikipedia.org/wiki/Peoria`](https://en.wikipedia.org/wiki/Peoria) look different but they both resolve to the same document and they are both managed by the Wikimedia Foundation. They do not pass the threshold of being meaningfully different.

There is some subjectivity in this analysis. The exploration in this article goes all the way back to 1987 in an effort to automatically determine the unique identity of a domain's owner. The work primarily relies on two libraries, the [Apache Commons Validator API](https://commons.apache.org/proper/commons-validator/) and Java's [Uniform Resource Identifier (URI)](https://docs.oracle.com/javase/7/docs/api/java/net/URI.html) reference. The latter is our parser while the former is, unsurprisingly, our validator.

First, create the Clojure namespace with the requisite libraries. Java's URI library, java.net.URI, comes with Clojure. To use the Apache Commons Validator API, add `commons-validator/commons-validator {:mvn/version "1.7"}` to the project's `deps.edn` file.

```
(ns domain-names
  (:import [org.apache.commons.validator.routines UrlValidator DomainValidator]
           [java.net URI])
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))
```

;; With that out of the way, it's time for some exploration.

Domain Names Explained
----------------------

### The URL

A Uniform Resource Locator (URL) is the addresse of a unique resource on the web. A URL can be validated using the `.isValid` [method](https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/routines/UrlValidator.html).  It's easy to create a custom function in Clojure.

```
(defn url? [url]
   (-> (UrlValidator.)
       (.isValid url)))
```

`(url? "http://schmud.de")` &rArr; `true`

eMails won't pass. Neither will domain names.

`(url? "email@example.com")` &rArr; `false`

`(url? "schmud.de")` &rArr; `false`

### What Is a Domain Name?

[RFC 1034](https://www.ietf.org/rfc/rfc1034.txt) laid the foundation for domain names in November 1987. The domain name indicates which server hosts a web resource. Domain names are owned by a person or an organization. Like all proper names, they are a form of identification.

This identification is what we're after. Who is the owner of this resource? It turns out answering that question is not so simple because subdomains can indicate meaningful distinctions. Consider these subdomains:

- `www.tumblr.com` is not meaningfully different from `tumblr.com`. They are both used to indicate the entity known as Tumblr.
- [`schmudde.tumblr.com`](https://schmudde.tumblr.com/) is meaningfully unique from [`journalofanobody.tumblr.com`](https://journalofanobody.tumblr.com). Within the domain of Tumblr, there is only one subdomain named schmudde, which points to a distinct person (myself).
- [`https://azure.microsoft.com`](https://azure.microsoft.com) and [`https://www.microsoft.com/windows`](https://www.microsoft.com/windows) shows how Microsoft thinks about two different products. The `www` subdomain does not indicate a meaningful distinction while the `azure` prefix reflect's Microsoft's corporate structure; Azure makes up one of Microsoft's four distinct engineering divisions.

Depending on your purpose, subdomain distinctions may be significant.  Some further understainding of domain name is necessary before moving on.

The Root Domain, the Top Level Domain, and the Subdomain
--------------------------------------------------------

### The Root Domain and Subdomains

*Subdomains* are defined relatively. RFC 1034 uses this example:

> `A.B.C.D` is a subdomain of `B.C.D`, `C.D`, `D`, and `" "`.

Thus the *root domain* is the only absolute component of a URL. It is `" "`. A more concrete example from the RFC may help illustrate:

                                   |
                                   |
             +---------------------+------------------+
             |                     |                  |
            MIL                   EDU                ARPA
             |                     |                  |
             |                     |                  |
       +-----+-----+               |     +------+-----+-----+
       |     |     |               |     |      |           |
      BRL  NOSC  DARPA             |  IN-ADDR  SRI-NIC     ACC
                                   |
       +--------+------------------+---------------+--------+
       |        |                  |               |        |
      UCI      MIT                 |              UDEL     YALE
                |                 ISI
                |                  |
            +---+---+              |
            |       |              |
           LCS  ACHILLES  +--+-----+-----+--------+
            |             |  |     |     |        |
            XX            A  C   VAXA  VENERA Mockapetris


The RFC explains:

> The root domain has three immediate subdomains: MIL, EDU, and ARPA.  The [LCS.MIT.EDU](http://LCS.MIT.EDU) domain has one immediate subdomain named [XX.LCS.MIT.EDU](http://XX.LCS.MIT.EDU). All of the leaves are also domains.

These days the root domain sometimes refers to the domain name registered with a domain name registrar. For example, I registered `netart.today`, not `www.netart.today`. The more precise definition is the domain which all other subdomains branch from.

### The Top Level Domain

`mil` and `edu` eventually became known as generic Top Level Domains (gTLD) along with other familiar extentions like `com` and `org`. [The Apache Commons Validator](https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/routines/DomainValidator.html) can help identify the Top Level Domains (TLDs) as currently defined and maintained by the Internet Assigned Numbers Authority (IANA).

The familiar gTLDs:
`(.isValidGenericTld (DomainValidator/getInstance) ".edu")` &rArr; `true`

Country codes as TLDs:
`(.isValidCountryCodeTld (DomainValidator/getInstance) ".cn")` &rArr; `true`

TLDs reserved for internet infrastructure:
`(.isValidInfrastructureTld (DomainValidator/getInstance) ".arpa")` &rArr; `true`

Now use the `DomainValidator`'s general `.isValid` method to see if a gTLD + two subdomains is valid:
`(.isValid (DomainValidator/getInstance) "www.schmud.de")` &rArr; `true`

Add a protocol to the subdomains and the gTLD to form a URL:
`(.isValid (DomainValidator/getInstance) "http://www.schmud.de")` &rArr; `false`

A URL ≠ a domain name.

Getting the Domain Name
-----------------------

### Parsing URLs

Getting the domain name from a URL means we need to parse the URL string. Parsing URLs in Clojure and Java can be done with [java.net.URI](https://docs.oracle.com/javase/7/docs/api/java/net/URI.html). The URI object is parsed into these components: `[scheme:][//authority][path][?query][#fragment]`.

### The Authority

The Authority is the component that will hold the domain name (aka host name) in java.net.URI objects. It can also hold an authentication section (username and password) and an optional port number precededby a `:`. But I don't want those. To exclusively get the host, use `.getHost`.

Here's a URL with a port number, scheme, and path to demonstrate:

```
(-> (java.net.URI/create "https://schmud.de:443/books.html")
        .getHost)
```

&rArr; `"schmud.de"`

The `java.net.URI/create` method expects strings that it can parse as a URI object. If you just give it a domain name, it will not fail but it also will not parse the host.

```
(-> (java.net.URI/create "schmud.de")
        .getHost)
```

&rArr; `nil`

But it will fail if you give it something that it cannot parse.

```
(try
  (-> (java.net.URI/create "://schmud.de/books.html")
      .getHost)
  (catch Exception e e))
```
&rArr; `error`

The best solution probably includes validation. The code won't even try to parse bad input.

```
(#(when-let [valid-url (when (url? %) %)]
    (-> valid-url
        java.net.URI/create
        .getHost)) "http://schmud.de/books.html")
```

&rArr; `"schmud.de"`

```
(#(when-let [valid-url (when (url? %) %)]
    (-> valid-url
        java.net.URI/create
        .getHost)) "://schmud.de/books.html")
```

&rArr; `nil`

The URL Validator also guards against TLDs that do not exist. In this case, the fictitious `.ded` TLD.

```
(#(when-let [valid-url (when (url? %) %)]
    (-> valid-url
        java.net.URI/create
        .getHost)) "http://schmud.ded")
```

&rArr; `nil`

The `.getHost` method itself suffers one drawback: it can trip up when interpreting URL schemes that include alphabetic characters outside the Latin alphabet (essentially URL schemes after [RFC 2396](https://datatracker.ietf.org/doc/html/rfc2396)). Consider the difference:

`(.getHost (java.net.URI/create "http://köpönyeg.hu"))` &rArr; `nil`

`(.getRawAuthority (java.net.URI/create "http://köpönyeg.hu"))` &rArr; `"köpönyeg.hu"`

`.getRawAuthority` returns the value without interpreting any escaped octets. The strings returned by these methods may contain both escaped octets and other characters, and will not contain any illegal characters.

### Parsing Subdomains

It's important to remember the goal when deciding how to deal with subdomains. I'm looking for any meaningfully unique identity. `azure.microsoft.com` and `schmudde.tumblr.com` are meaningfully unique in a way that `en.wikipedia.org` and `www.schmud.de` are not.

A more strict solution may consider all known [public suffixes](https://publicsuffix.org/list/) and only accept a top level domain + one subdomain.

My implementation takes the opposite approach. It removes [common subdomains](https://github.com/danielmiessler/SecLists/blob/master/Discovery/DNS/subdomains-top1million-5000.txt) compiled by SecLists. Most of the 5000+ common subdomains on the list do not provide meaningful identity differentiation, such as `www.`, `en.`, `m.`, `shop.`, and `www.news.`

```
(def common-subdomains
  "Slurp and sort then drop the first 200 longest names because
   they seem especially esoteric."
  (->> (slurp "resources/subdomains-top1million-5000.txt")
                            clojure.edn/read-string
                            (sort-by count)
                            reverse
                            (drop 200)))

(defn remove-subdomain-prefix
  "In: Domain name (string)
   Out: Host without a prefix.

   has-more-than-one-subdomain? ensures that shop.google.com -> google.com and shop.com -> shop.com"
  [domain-name]
  (let [has-more-than-one-subdomain? (when (not= (str/index-of domain-name ".")
                                                 (str/last-index-of domain-name "."))
                                       true)
        matching-prefix (-> (partial str/starts-with? domain-name)
                            (filter common-subdomains)
                            first)]
    (if (and has-more-than-one-subdomain? matching-prefix)
      (str/replace-first domain-name matching-prefix "")
      domain-name)))
```

Common subdomains:

`(remove-subdomain-prefix "www.company.com")` &rArr; `"company.com"`

`(remove-subdomain-prefix "news.company.com")` &rArr; `"company.com"`

Common subdomain compounds are also caught. `remove-subdomain-prefix` sorts the list of common subdomain prefixes from longest to shortest. Therefore the function removes `www.news.` (longer) rather than just `www.` or `news.`.

`(remove-subdomain-prefix "www.news.company.com")` &rArr; `"company.com"`

Common subdomain prefixes do not get confused with the primary subdomain:

`(remove-subdomain-prefix "shop.google.com")` &rArr; `"google.com"`

`(remove-subdomain-prefix "shop.com")` &rArr; `"shop.com"`

Significant subdomains are retained:

`(remove-subdomain-prefix "www.news.chevrolet.gm.com")` &rArr; `"chevrolet.gm.com"`


Getting a Meaningful Identifier
-------------------------------

Put it all together to get meaningful domain names from any URL.

```
(defn get-host [x]
  (if (url? x)
    (-> (java.net.URI/create x)
        .getRawAuthority
        remove-subdomain-prefix)
   (str "`" x "` is not a valid url. Expecting [scheme:][//authority][path][?query][#fragment]")))
```

`(get-host "http://m.google.com/community")` &rArr; `"google.com"`

`(get-host "ttp://m.google.com/community")` &rArr; `"ttp://m.google.com/community is not a valid url. Expecting [scheme:][//authority][path][?query][#fragment]"`

`(get-host "http://schmudde.tumblr.com/")` &rArr; `"schmudde.tumblr.com"`

I believe that the `get-host` function makes the right trade offs when considering the stated goals. The next step could be to turn these domain name rules into reusable Clojure specs. Check out Conan Cook's [*A spec for URLs in Clojure*](https://conan.is/blogging/a-spec-for-urls-in-clojure.html) and associated [generators](https://gist.github.com/conan/2edca210999b96ad26d38c1ee96dfe40) for a good introduction.

Most of my work deals with identity and time in computing systems. See my posts [*Storing Time - Part 1*](https://schmud.de/posts/2020-07-21-storing-time-1.html) and [*Storing Time - Part 2*](https://schmud.de/posts/2020-07-22-storing-time-2.html) for a deep dive into computational memory and recording time in Clojure and Java. Thanks for reading!
