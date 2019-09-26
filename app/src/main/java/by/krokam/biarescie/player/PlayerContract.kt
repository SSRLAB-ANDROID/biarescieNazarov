package by.krokam.biarescie.player

interface PlayerContract{

    interface Base {
        var currentTime : String
        var currentPlayPercent : Int
        var totalTime : String
        fun play()
        fun pause()
    }

    interface Controller : Base {
        val isStarted : Boolean
        val isPrepared : Boolean
        var view : View?
        fun seekToPercent(value : Int)
    }

    interface View : Base
}