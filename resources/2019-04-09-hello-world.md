---
title: How to use Perun to Create a Static Blog
description: Part 1 of
author: David Schmudde
author-email: d@schmud.de
author-url: http://schmud.de
author-github: schmudde
author-twitter: dschmudde
author-avatar: /images/anton-avatar.png
location: Turin, Italy
date-created: 2013-12-27
date-modified: 2013-12-30
date-published: 2013-12-27
headline:
in-language: en
keywords: clojure, blogging
canonical-url: http://schmud.de
uuid:
tags:
 - clojure
 - tools
---

## Make the Basic Structure

```
 :source-paths #{"src" "stylesheets" "content"}
 :resource-paths #{"resources"}
```

Start with the basic structure at [example-blog](https://github.com/hashobject/perun/blob/master/example-blog/build.boot)

- `resources/2019-04-09-hello-world.md`
- `src/site/index.clj`
- `src/site/post.clj`
- `src/site/about.clj`
- `stylesheets`
- `content/pages`
- `content/posts`

## Basic Build Task

Put together the smallest `build` function and `dev` functions.

`:renderer` expects a sym type to mach what the command line provides

```
(deftask build []
  (comp (perun/markdown)
        (perun/collection :renderer 'site.index/render :page "index.html")))
```

The two composed functions:

1. `perun/collection`: Render a collection of entries through the `:renderer` template in the site's index file.
2. `perun/markdown`: Parse markdown/yaml front matter of the individual files.

```
(deftask dev []
  (comp (watch)
        (build)
        (serve :resource-root "public")))
```

`build` can be used to build the site to upload. Looking closely, it is just a function that is composed in the`dev`elopment process as `(build)`.

Test it in the webbrowser:

1. `boot dev`
2. Load at [localhost](http://localhost:3000/) port 3000.

It should load `index.html` and display the menu and the first entry.

## Customize Post Headers

Kill the boot process using `Ctrl-C`.

```
(deftask build []
  (comp (perun/markdown)
        (perun/render :renderer 'site.post/render)
        (perun/collection :renderer 'site.index/render :page "index.html")))
```

Open `post.clj` and add

```
[:body
  [:h1 (:title post)]
  [:p (:date-published post)]
  [:div (:content post)]]
```

## Add a Global Header to the Blog

Open `index.clj` and add

```
(defn header [global-meta]
  [:header
   [:h1 [:a {:href "/"} (:site-title global-meta)]]
   [:h2 (:description global-meta)]])
```

Add a call to the function `(header global-meta)` in the `:body` of the `site.index`:

```
[:body
  (header global-meta)
  [:ul
    [:li [:a {:href "/about.html"} "About Page"]]
    [:li [:a {:href "/feed.rss"} "RSS"]]
    [:li [:a {:href "/atom.xml"} "Atom Feed"]]]
  [:ul.items.columns.small-12
    (for [post posts]
    [:li
    [:a {:href (:permalink post)}(:title post)]])]]
```

Add a call to the function `(header global-meta)` in the `:body` of the `post.index`:

```
[:body
  (header global-meta)
  [:h1 (:title post)]
  [:p (:date-published post)]
  [:div (:content post)]]
```

## Add Global Metadata &amp; Support For Static Pages

Kill the server. Add a static page at `src/about.clj` and then include it in the page: `(perun/static :renderer 'site.about/render :page "about.html")`.

```
(deftask build []
  (comp (perun/global-metadata :filename "site.base.edn")
        (perun/markdown)
        (perun/collection :renderer 'site.index/render :page "index.html")
        (perun/static :renderer 'site.about/render :page "about.html")))
```

1. `perun/static` renders a single page solely using the render function provided.
2. `perun/global-metadata`: "Read global metadata from `perun.base.edn` or configured file."

`(clojure.repl/dir io.perun)` &rarr; `(clojure.repl/doc io.perun/global-metadata)`

```
io.perun/global-metadata
([& {:as *opts*, :keys [help filename]}])
  Read global metadata from `perun.base.edn` or configured file.

   The global metadata will be attached to fileset where it can be
   read and manipulated by the tasks. Render tasks will pass this
   as the first argument to render functions.

  Keyword Args:
    :help      bool  Print this help info.
    :filename  str   filename to read global metadata from
```
