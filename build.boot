
(set-env!
  :resource-paths #{"src"}
  :dependencies '[])

(def +version+ "0.1.1")

(deftask build []
  (comp
    (pom :project     'mvc-works/keycode
         :version     +version+
         :description "Keycode library"
         :url         "https://github.com/mvc-works/keycode.cljc"
         :scm         {:url "https://github.com/mvc-works/keycode.cljc"}
         :license     {"MIT" "http://opensource.org/licenses/mit-license.php"})
    (jar)
    (install)
    (target)))

(deftask deploy []
  (set-env!
    :repositories #(conj % ["clojars" {:url "https://clojars.org/repo/"}]))
  (comp
    (build)
    (push :repo "clojars" :gpg-sign (not (.endsWith +version+ "-SNAPSHOT")))))
