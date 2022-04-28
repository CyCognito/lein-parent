(ns lein-parent.plugin
  (:require [leiningen.parent :as parent]
            [leiningen.core.project :as project]))

(def meta-merge #'project/meta-merge)

(defn middleware [project]
  (if-let [inherited (parent/inherited-properties project)]
    (let [[left-base right-base] (map (fn [m]
                                        (dissoc m :aliases))
                                      [project inherited])
          [left-aliases right-aliases] (map (fn [m]
                                              (select-keys m [:aliases]))
                                            [project inherited])
          ;; all properties except `:aliases` are merged left-to-right (`inherited` takes precedence),
          ;; since that is lein-parent's traditional behavior:
          merged-bases (meta-merge left-base right-base)
          ;; `:aliases` are merged right-to-left (`project` takes precedence):
          merged-aliases (meta-merge right-aliases left-aliases)
          merged (merge merged-bases merged-aliases)]
      (with-meta merged
        (update (meta project) :profiles merge (:profiles inherited))))
    project))
