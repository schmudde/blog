# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**Beyond the Frame** is a static blog site generated with Clojure using [Boot](https://github.com/boot-clj/boot) and [Perun](https://github.com/hashobject/perun). Content is written in Markdown with YAML frontmatter, rendered via Pandoc, and templated with Hiccup. The live site is at https://schmud.de/.

`build.boot` is the canonical build file, but it is generated from `build.boot.org` (a literate Org-Babel document, symlinked as `README.org`). Do not edit `build.boot` directly if `build.boot.org` is the source of truth — tangle with `C-c v t` in Emacs/CIDER.

## Commands

```bash
boot build    # Generate the full static site into target/
boot dev      # Start dev server with live reload and watch
boot dev repl # Start REPL in dev profile (use with Emacs CIDER)
```

### External Tool Requirements

- `boot` — install from https://github.com/boot-clj/boot
- `pandoc` — `sudo apt-get install pandoc`
- `pandoc-sidenote` — `sudo apt-get install pandoc-sidenote`

## Architecture

### Build Pipeline

Perun orchestrates a sequential pipeline in `build.boot`:

1. Load global metadata from `resources/site.base.edn`
2. Pandoc converts Markdown → HTML5 (with sidenote support)
3. Content is classified by predicate functions (`post?`, `book?`, `program?`, `page?`, `published?`, `tagged-clojure?`, `archive?`) and filtered into collections
4. Collection index pages are rendered (`index.html`, `books.html`, `programs.html`)
5. Individual post/book/program pages are rendered
6. Tag pages generated under `public/tags/{tag}.html`
7. RSS feeds generated (`feed.rss`, `btf-clojure-feed.rss`)
8. Static assets (CSS, images, JS) bundled into `target/`

### Source Namespaces (`src/site/`)

| File | Role |
|---|---|
| `core.clj` | Primary rendering: posts, books, tag pages, index pages, snippets |
| `layout.clj` | Master layout: `<head>` (SEO/OG/analytics), header nav, footer h-card, `body-template` |
| `timeline.clj` | Information history timeline page; indexes events to posts via `timeline.edn` |
| `cv.clj` | CV page rendered from multiple EDN data files in `src/site/` |
| `previous_entries.clj` | Archive page aggregating all posts, tutorials, and reviews |
| `about.clj` | About page with Tufte sidenote/marginnote helpers |
| `time_utils.clj` | Formats `java.time` types (Year, YearMonth, LocalDate) to strings |

### Content Structure

```
content/
  posts/      # Blog posts
  books/      # Book reviews
  programs/   # Clojure tutorials
  pages/      # Static pages
  archive/    # Archived content
```

### Frontmatter Fields

```yaml
---
title: Post Title
description: Brief summary
author: David Schmudde
date-created: YYYY-MM-DD
date-modified: YYYY-MM-DD
date-published: YYYY-MM-DD   # required for publication
keywords: comma-separated
tags:
 - tag1
 - tag2
---
```

Content with no `date-published` is treated as a draft and excluded from rendered output.

### Data Files (`src/site/*.edn`)

CV and timeline data live as EDN files: `recognition.edn`, `projects.edn`, `talks-workshops.edn`, `employment-faculty.edn`, `bio.edn`, `timeline.edn`. Date values use `java.time` literals (e.g., `#time/year "2020"`, `#time/year-month "2020-01"`).

### Styling

- **Tufte CSS** — typography and sidenotes
- **Tachyons** — utility-first responsive layout (`-ns`, `-l`, `-xl` breakpoints)
- **btf.css** — custom site overrides
- **Font Awesome 5.14.0** — icons

Pandoc footnotes (`[^1]`) are automatically converted to Tufte sidenotes via `pandoc-sidenote`.

### Site Configuration

Global metadata is in `resources/site.base.edn`:
```edn
{:base-url "https://schmud.de/"
 :site-title "Beyond the Frame"
 :description "The metaphysics of information, art, and narrative"
 :author "David Schmudde"}
```

### Deployment

Uses `GITHUB_DEPLOY_TOKEN` from `.env` (git-ignored). The `rename-post.sh` script assists with moving posts and their associated image directories.

**No deploy script currently exists in the repo.** See the Server section below for the intended rsync-based approach.

---

## Server

- **Host:** `root@172.104.31.114` (Hetzner)
- **OS:** Debian Bookworm
- **Key services:** `apache2`, `docker`, `sshd`, `mariadb`, `redis`, `notify_push`

### Remote Command Conventions

Non-interactive remote commands:
```bash
ssh root@172.104.31.114 "command here"
```

Multi-line files via heredoc:
```bash
ssh root@172.104.31.114 "cat > /path/to/file << 'EOF'
file contents here
EOF"
```

File uploads:
```bash
scp /local/path/file.org root@172.104.31.114:~/file.org
```

**Operational conventions:**
- Always verify changes after making them (e.g. `cat` the file back, `grep` the result)
- Validate configs before reloading services (`apache2ctl configtest`, `sshd -t`)
- Use `systemctl reload` where possible (graceful) vs `restart` only when required
- Back up files before editing (`cp file file.bak`)
- Chain related commands with `&&` to fail fast if a step errors

---

## Place Pages (`/places/<slug>`)

Planned feature modelled on [tantek.com/2023/114/t1/venues-reviews-personal-pages](https://tantek.com/2023/114/t1/venues-reviews-personal-pages). Each place gets its own static page at `/places/<slug>.html` with IndieWeb microformats2 markup, suitable for check-in syndication.

### Source of Truth

Place files live in `content/places/` as Markdown with YAML frontmatter — the same format as posts/books/programs. One file per place, slug = filename stem.

```
content/places/
  ferry-building-farmers-market.md   → /places/ferry-building-farmers-market.html
  rote-bar-berlin.md                 → /places/rote-bar-berlin.html
```

### Place Frontmatter Fields

```yaml
---
title: Ferry Building Farmers Market
description: Weekly farmers market at the Ferry Building, San Francisco.
author: David Schmudde
author-email: d@schmud.de
author-url: http://schmud.de
date-created: 2025-01-01
date-modified: 2025-01-01
date-published: 2025-01-01
in-language: en
place-type: farmers-market          # OSM-style amenity tag
address: 1 Ferry Building
city: San Francisco
country: US
latitude: 37.7955
longitude: -122.3937
notes: Great Saturday morning market.
website: https://example.com
tags:
 - place
---
```

Fields `date-published`, `title`, `latitude`, and `longitude` are required. All others are optional.

### Build Pipeline Integration

`build.boot` additions:

1. A `place?` predicate matching `original-path` starting with `"places/"`:
   ```clojure
   (defn place? [{:keys [original-path]}]
     (when original-path (.startsWith original-path "places/")))
   ```

2. A `perun/render` step using `site.place/render-place-pages` with `:out-dir "public/places"`:
   ```clojure
   (perun/render :renderer 'site.place/render-place-pages
                 :filterer (apply every-pred [place? published?])
                 :out-dir "public/places"
                 :meta {:type "place"})
   ```

### Renderer (`src/site/place.clj`)

New namespace `site.place` providing `render-place-pages`. Wraps `body-template` from `site.layout`. Produces microformats2-compliant HTML:

| mf2 class | Maps to frontmatter field |
|---|---|
| `h-entry` | outer wrapper |
| `p-name` | `title` |
| `p-adr` > `p-street-address` | `address` |
| `p-adr` > `p-locality` | `locality` |
| `p-adr` > `p-country-name` | `country` |
| `p-latitude` | `latitude` |
| `p-longitude` | `longitude` |
| `dt-published` | `date-published` |
| `u-url` | canonical URL |

Opening hours are displayed with an OSM-style label. Lat/lon also generate an OpenStreetMap link.

### Write Endpoint (`places-api/`)

A small standalone Clojure/Ring HTTP service (separate `deps.edn` project) runs on the server at port **4242**. It provides:

- `POST /place` — accepts JSON, writes a `.md` file to `content/places/`, triggers build
- `GET /health` — liveness check

**Security:** `X-Api-Key` header checked against `PLACES_API_KEY` environment variable.

**Required JSON fields:** `name`, `slug`, `latitude`, `longitude`
**Optional JSON fields:** `address`, `locality`, `region`, `country`, `place-type`, `opening-hours`, `website`, `description`

Slug is sanitized to lowercase kebab-case (alphanumeric + hyphens only).

The service runs as a systemd unit (`api.service`), reads secrets from `/etc/api/env`, and restarts on failure. Apache proxies `https://cloud.schmud.de/api/` → `localhost:4242`.

### Build + Deploy Script (`scripts/build-and-deploy.sh`)

```bash
set -euo pipefail
boot build && rsync -avz --delete \
  -e "ssh -i /root/.ssh/id_ed25519_deploy" \
  /srv/blog/target/public/ \
  schmudm@188.40.28.20:public_html/
```

- Does **not** deploy if `boot build` fails
- Uses `rsync --delete` to remove stale files from the server
- Uses the deploy key at `/root/.ssh/id_ed25519_deploy` for SFTP access to `188.40.28.20`
- Intended to be triggered by the write endpoint after a successful file write

### Failure Handling

| Scenario | Behaviour |
|---|---|
| `boot build` fails | Script exits; rsync never runs; old site stays live |
| Bad POST input | API returns `400`; no file written |
| Missing/wrong API key | API returns `401` |
| Concurrent writes | File write is atomic (write to tmp, rename); one build at a time via a lock file |
