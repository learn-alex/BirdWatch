(ns birdwatch.state.data
  (:require [birdwatch.state.initial :as i]
            [birdwatch.state.comm :as c]))

(defn init-state
  "Init app state and wire all channels required in the state namespace. The app
   atom is held inside the let binding of this function and thus protected from
   outside access / alteration. The only way to interact with it is by sending
   messages on channels, such as the provided data channel for adding new data or
   sending commands on the cmd-chan."
  [state-in-chan qry-chan state-pub-chan]
  (let [app (atom {})]
    (i/init app)
    (c/state-in-loop state-in-chan qry-chan app)
    (c/broadcast-state state-pub-chan app)))
