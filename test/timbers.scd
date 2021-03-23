s.boot;
s.scope;
// caosPercLib default timbers
//Kick
~k = CaosKick.new
play{CaosKick.ar}//class
play{~k.ar}//instance
//Kick 2
~k2 = CaosKick2.new
play{CaosKick2.ar}//class
play{~k2.ar}//instance
//snare
~t =  CaosSnare.new
play{CaosSnare.ar}//class
play{~t.ar}//instance
//snare 2
~t2 =  CaosSnare2.new
play{CaosSnare2.ar}//class
play{~t2.ar}//instance
//hihats
~hh =  CaosHats.new
play{CaosHats.ar}//class
play{~hh.ar}//instance
//hihats 2
~hh2 =  CaosHats2.new
play{CaosHats2.ar}//class
play{~hh2.ar}//instance
//bass
~b =  CaosBass.new
play{CaosBass.ar}//class
play{~b.ar}//instance
//bass 2
s.boot;
~b2 =  CaosBass2.new
play{CaosBass2.ar}//class
play{~b2.ar}//instance
//pad
s.boot;
~p =  CaosPad.new
play{CaosPad.ar}//class
play{~p.ar}//instance
play{~p.ar('pulse',0.01,0.5,Prand(LCNE.dupOctave(Scale.dorian.degrees,4),inf).asStream.next)}//instance
//pad 2
s.boot;
~p2 =  CaosPad2.new
play{CaosPad2.ar}//class
play{~p2.ar}//instance
play{~p2.ar(0.01,0.5,Prand(LCNE.dupOctave(Scale.dorian.degrees,4),inf).asStream.next+60)}//instance
