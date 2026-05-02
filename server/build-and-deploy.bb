#!/usr/bin/env bb

(require '[babashka.process :as p]
         '[clojure.java.io :as io])

(def blog-dir   "/mnt/apps/blog")
(def deploy-key "/root/.ssh/id_ed25519_deploy")
(def lock-file  "/tmp/blog-build.lock")
(def log-file   "/var/log/blog-deploy.log")

(defn log [& args]
  (let [msg (str "[" (java.time.Instant/now) "] " (apply str args))]
    (println msg)
    (spit log-file (str msg "\n") :append true)))

;; Prevent concurrent builds
(when (.exists (io/file lock-file))
  (log "ERROR: build already in progress (" lock-file ")")
  (System/exit 1))

(spit lock-file "")

(try
  (log "=== build-and-deploy start ===")

  (p/shell {:dir blog-dir} "git" "pull" "--ff-only")
  (log "git pull done")

  (p/shell {:dir blog-dir :extra-env {"BOOT_AS_ROOT" "yes"}} "boot" "build")
  (log "boot build done")

  (p/shell "lftp" "-c"
           (str "set sftp:connect-program 'ssh -a -x -i " deploy-key " -o StrictHostKeyChecking=no';"
                "open -u schmudm, sftp://188.40.28.20;"
                "mirror --reverse --delete --verbose "
                blog-dir "/target/public/ public_html/"))
  (log "lftp mirror done")

  (log "=== build-and-deploy complete ===")

  (finally
    (io/delete-file lock-file true)))
