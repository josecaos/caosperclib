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
~p2 =  CaosPad2.new
play{CaosPad2.ar}//class
play{~p2.ar}//instance
play{~p2.ar(0.01,2,Prand(LCNE.dupOctave(Scale.dorian.degrees,4),inf).asStream.next+60,Prand(LCNE.dupOctave(Scale.dorian.degrees.scramble,4),inf).asStream.next+34,rrand(0.0125,2),12,1,2,0.1)}//instance
//chords
~ch =  CaosChords.new
play{CaosChords.ar}//class
play{~ch.ar}//instance
play{~ch.ar('M')}//Major
play{~ch.ar('m')}//Minor
play{~ch.ar('M7')}//Major seventh
play{~ch.ar('Mmaj7')}//Major major seventh
play{~ch.ar('mmaj7')}//Minor major seventh
play{~ch.ar('5dim7')}//Diminished fifth, minor seventh
play{~ch.ar('5aug7')}//Augmented fifth, minor seventh
//chords guitar style
~ch2 =  CaosGuitChords.new
play{CaosGuitChords.ar}//class
play{~ch2.ar}//instance
play{~ch2.ar('M')}//Major
play{~ch2.ar('m')}//Minor
play{~ch2.ar('M7')}//Major seventh
play{~ch2.ar('Mmaj7')}//Major major seventh
play{~ch2.ar('mmaj7')}//Minor major seventh
play{~ch2.ar('M9')}//Major ninth
play{~ch2.ar('M9m')}//Major, minor ninth
