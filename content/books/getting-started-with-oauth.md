---
title: Getting Started with OAuth 2.0
description: This is a fine cookbook, but you won't learn much about how to cook.
author: David Schmudde
author-email: d@schmud.de
author-url: http://schmud.de
author-github: schmudde
author-twitter: dschmudde
location: Turin, Italy
date-created: 2022-03-11
date-modified: 2022-03-11
date-published: 2022-03-11
in-language: en
keywords: oauth
tags:
 - review
 - tools
---

by Ryan Boyd (2012) O’Reilly

**This is a fine cookbook, but you won't learn much about how to cook.**

The OAuth 2.0 specification has not gone under a major revision since it was introduced TODO years ago.[^aaronpk] But the development community remains active; security and accessibility concerns have lead them to advise against using two authorization flows in the specification and the development of several notable extensions.

[^aaronpk]: aaronpk has suggested a minor version bump, OAuth 2.1, which essentially rolls in several of the extensions to the standard.

I didn't expect *Getting Started with OAuth 2.0* (2012) to age well when I sat down to read it. Books on specific implementations never do. But older books in programming and computer science that explain the "why behind the how"[^why-how] can be enlightening. *Getting Started with OAuth 2.0* is essentially a how-to book, so there isn't much here in the year 2022.

[^why-how]: "This is *how* you do it" does not age well in programming. "This is *why* you do it this way" will lead to deep insights because authors from another time and place can offer a novel perspective.

## The Why

The goal is federated or distributed identity. We don't want identity to be centralized on someone else's server. This is fundamentally difficult because, as [Moxy Marlinspike explained](TODO), people don't want to run their own servers.

OAuth is merely a step in this direction. A person can use it to authorize accesses to personal data between different applications.

Before a person is able to authorize access, they must prove that the request is authentic. The process of authentication can be done with the familiar username and password pair.[^authentication] OAuth doesn't help here, but it can be coupled with something like OpenID Connect. The combination of the two essentially federate the verification of identity[^oidc] and the sharing of privileged information.

[^authentication]: Usernames and passwords have traditionally been used for both authentication and authorization on the web. The `Authorization: <type> <credentials>` pattern was introduced all the way back in [HTTP 1.0](https://www.rfc-editor.org/rfc/rfc1945). The pattern has been used in various auth standards in HTTP. The `HTTP Basic` header sends the user name and password as unencrypted base64 encoded text. `HTTP Digest` sends more securely hashed passwords. But in both cases, this information should only be sent over HTTPS and not HTTP. OAuth/OpenID Connect can provide the same access to information as these HTTP standards, but without making the user enter their username/password from one application in the context of another application.

[^oidc]: The book's author makes a good point about the connection between OAuth and OpenID Connect, &ldquo;Passing permission to access authentication information (the user’s identity) to a site is very similar to passing along delegated access to a user’s data (such as their calendar). Developers shouldn’t have to use entirely different protocols for these two different use cases—especially because many developers need to handle both in their applications.&rdquo; In practice, federated user authentication can roughly be divided into two camps. Corporate tools include Active Directory servers, LDAP servers, and SAML providers. On the other hand, [OpenID providers like Google and Yahoo!](https://en.wikipedia.org/wiki/List_of_OAuth_providers) are commonly used for personal accounts.

## The How

Many of the example API calls in the book are not very helpful. And some are now downright problematic. For example, *Getting Started with OAuth 2.0* shows its age when going through the Implicit Flow step-by-step, which is now considered insecure and not recommended.

The author is clearly an expert. So specific insights are valuable. For example:

> When implementing OAuth 2.0, calling any APIs, or using a library, you should verify that it properly handles SSL/TLS certificate chain validation by doing the following things: Checking that the hostname on the certificate returned by the server matches the hostname in the URL being accessed Verifying each certificate in the chain properly chains up to a valid and trusted certificate authority (CA) Ensuring that the certificate authority bundle on your server is secure and not able to be modified by potential

> Typically the refresh token is stored securely in a server-side database, associated with the user account. Access tokens can also be stored in a database, but they may also be cached in a server-side session to improve performance.

Ryan Boyd deftly explains the reason the OAuth spec calls for both long-lived access tokens and short-lived refresh tokens. An access token is sent to an API along with a request for some information. That API service might have access to several different scopes of information - a person's calendar, their eMail inbox, etc.... - but use only one access token. If the API service is compromised, the "blast radius" of this access token can encompass quite a bit of information.

However, the access token expires relatively quickly and will become worthless when it does. The refresh token provides a way for the service to provide continuous access to the API; it permits the service to issue a new access token. This is a significant reduction in harm if the attacker only had one-time access. If the attacker has consistent access to the system than there are likely greater concerns than the integrity of the access tokens.

There is another benefit to these short-lived access tokens. Boyd points out that many implementations forgo checking OAuth every time an access token is used to grab a resource.[^oauth-tip] It is faster to cache an access token in the system and verify it against a encrypted access token string sent in an HTTP request. If the system administrators are not careful, this can lead to inconsistencies in the system if a user revokes an access token using OAuth. Programmers should be careful to guard against this, but if a mistake does happen, the access token will expire and another one will not be issued.

[^oauth-tip]: Boyd offers another performance tip when checking these access tokens, &ldquo;If trying to optimize for latency in your application, it’s best to store the access token along with the time when the access token expires. When making an API call, first check to see if the current time is greater than the expiration time. If so, refresh the access token first, instead of waiting for the API server to reject your request because of an invalid access token. This will result in reduced latency because of fewer HTTP requests being made when the token expires.&rdquo;

These are all great insights but they are scattered between page after page of implementation details. And as I said above, those details aren't provided with much context. In the end, this is a fine cookbook, but you won't learn much about how to cook.
