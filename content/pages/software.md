
---
title: Software
description: Software is eating the world, so you might as use the best of it
author: David Schmudde
author-email: d@schmud.de
author-url: http://schmud.de
author-github: schmudde
author-twitter: dschmudde
location: Berlin, Germany
date-created: 2020-04-15
date-modified: 2020-07-29
date-published: 2020-04-23
in-language: en
canonical-url: http://schmud.de
uuid:
---

Prefer ethical software

Emacs, Weechat

Firefox, Thunderbird

- Images
    - Gimp (apt)
    - Inkscape for vector drawing
- Music
    - Clementine
- PDF
    - Okular (apt) - PDF document viewer: *Review* to edit a PDF
    - Xournal - Sign PDFs: Install Xournal through the Ubuntu Software Center &rarr; Select "Annotate PDF" from the File menu &rarr; &rarr; Click the "Image" button in the toolbar (it looks like a silhouette of a person) &rarr; Select a PNG image of your signature &rarr; select "Export to PDF" from the File menu.
- Markdown
    - [Remarkable](https://remarkableapp.github.io/index.html)
    - [Marp](https://github.com/yhatt/marp/)
    - [Pandoc](http://pandoc.org/)
    - [Prince (HTML &rarr; PDF)](http://www.princexml.com/)
        - Needs [libpng12](http://packages.ubuntu.com/xenial/libpng12-0)
        - To get rid of Prince logo, open Okular, Tools &rarr; Browse Tool, grab Prince logo and hit `Del` or `Backspace`.
    - Joey
        - [Editorial](https://itunes.apple.com/us/app/editorial/id673907758?mt=8) is a good ios markdown editor
        - joey: I also have a "prose mode" set up in vim that makes editing markdown nice, and use this script to quickly open notes from the command line: https://gist.github.com/joeyschoblaska/eb3ef2a60f5ef31d1fc561ce41d0e353
        - schmudde: thanks joey - so editing textfiles in Dropbox on iOS is a good experience? I'm pretty close to going that route.
        - joey: yeah, editorial lets you just set a dropbox folder as your root directory
        - joey: and it's just files in dropbox, so you can do whatever you want with them and you're not tied into some proprietary app and whatever bullshit API and unnecessary features they have
- Utilities
    - *Back in Time* for backups (apt - `backintime`)
    - Midnight Commander (apt - `mc`): File maintenance
    - [TLP](http://linrunner.de/en/tlp/tlp.html) - power saver for laptops: `sudo apt-get install tlp`
    - System 76: [Battery - Increase Battery Life](http://support.system76.com/articles/battery/)
- Video Chat: [Jitsi](https://jitsi.org/): open source video conferencing
- Video
    - VLC (Ubuntu Software Installer)
    - FFmpeg
        - ffmpeg is a very fast video and audio converter that can also grab from a live audio/video source.
        - `ffmpeg -i SJD_Mix04_ForFinal.mov -vcodec libx264 -acodec aac -s hd1080 -b 15000k -ab 320k memoirs-final-2.mp4`
        - [ffmpeg](https://vimeo.com/24111703): `ffmpeg -i SJD_Mix04_ForFinal.mov -vcodec libx264 -acodec aac -s hd1080 -b 15000k -ab 320k memoirs-final-2.mp4`
    - Screen recorders
        - `byzanz-record --duration=5 --x=10 --y=0 --width=1920 --height=400 borderless.gif`
        - `byzanz-record --duration=1 --x=350 --y=200 --width=1220 --height=400 borderless.gif`
    - [youtube-dl](http://rg3.github.io/youtube-dl/download.html)
        - `apt-get` is out of date, very important to keep current
        - `/usr/local/bin`
        - Supports YouTube, [Vimeo, and more](http://rg3.github.io/youtube-dl/supportedsites.html)
        - Commands
            - `youtube-dl -a list.txt` where `list` is a series of URLs in a text document
            - `youtube-dl -s [URL]` where `-s` is for simulate: good for trying to d/l an entire playlist without actually doing it
            - `youtube-dl -F https://www.youtube.com/watch?v=zzk9MsD51s8`: `F` show all available formats
            - `youtube-dl -f 43 https://www.youtube.com/watch?v=Oad8Ro8j_fE` will download format #43
- Terminal
    - libgnome2-bin for `gnome-open`

# Web

- Anki
    - [Flask](https://flask.io/): todo list (no login)
    - [IOU](https://www.iou.ch/)
- FF Send
    - [Send large files through Firefox(ish)](https://send.firefox.com/)
    - `ffsend` is a command line [Firefox Send client](https://www.linuxuprising.com/2019/01/ffsend-secure-file-share-from-command.html)
    - Alternatives
        - [Web Wormhole](https://webwormhole.io/)
        - [Hat Bat](https://hatbat.in/)
        - [Go File](https://gofile.io)
- [⌘ +C](https://copychar.cc): Click to copy special characters to your clipboard, and [HTML Arrows](https://www.toptal.com/designers/htmlarrows/)
- [Scale](https://hihayk.github.io/scale/gallery): a color scale generator
- [IFTTT](https://ifttt.com/)
- [WIFI Sign](https://www.mywifisign.com/en)
- [Remove Background Images](https://www.remove.bg/)
- [PDF tools](https://smallpdf.com/)

Feedly
