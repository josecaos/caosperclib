// CaosPercLib default timbres
// Declare each line
s.boot;
s.scope;
//Kick
~k = CaosKick.new
play{CaosKick.ar}//class
play{~k.ar}//instance
//Kick 2
~k2 = CaosKick2.new
play{CaosKick2.ar}//class
play{~k2.ar}//instance
//snare
~t = CaosSnare.new
play{CaosSnare.ar}//class
play{~t.ar}//instance
//snare 2
~t2 = CaosSnare2.new
play{CaosSnare2.ar}//class
play{~t2.ar}//instance
//hihats
~hh = CaosHats.new
play{CaosHats.ar}//class
play{~hh.ar}//instance
//hihats 2
~hh2 = CaosHats2.new
play{CaosHats2.ar}//class
play{~hh2.ar}//instance
//bass
~b = CaosBass.new
play{CaosBass.ar}//class
play{~b.ar}//instance
//bass 2
~b2 = CaosBass2.new
play{CaosBass2.ar}//class
play{~b2.ar}//instance
play{~b2.ar(0.01,2,Prand(LCNE.dupOctave(Scale.dorian.degrees,4),inf).asStream.next+34,1,60,12000,0.25)};
//pad
~p = CaosPad.new
play{CaosPad.ar}//class
play{~p.ar}//instance
play{~p.ar('pulse',0.01,0.5,Prand(LCNE.dupOctave(Scale.dorian.degrees,4),inf).asStream.next)}//instance
//pad 2
~p2 = CaosPad2.new
play{CaosPad2.ar}//class
play{~p2.ar}//instance
play{~p2.ar(0.01,2,Prand(LCNE.dupOctave(Scale.dorian.degrees,4),inf).asStream.next+60,Prand(LCNE.dupOctave(Scale.dorian.degrees.scramble,4),inf).asStream.next+34,rrand(0.0125,2),12,1,2,0.1)};
//chords
~ch = CaosChords.new
play{CaosChords.ar}//class
play{~ch.ar}//instance
play{~ch.ar('M',0.01,2)}//Major
play{~ch.ar('m',0.01,2)}//Minor
play{~ch.ar('M7',0.01,2)}//Major seventh
play{~ch.ar('Mmaj7',0.01,2)}//Major major seventh
play{~ch.ar('mmaj7',0.01,2)}//Minor major seventh
play{~ch.ar('5dim7',0.01,2)}//Diminished fifth, minor seventh
play{~ch.ar('5aug7',0.01,2)}//Augmented fifth, minor seventh
//chords guitar style
~ch2 = CaosGuitChords.new
play{CaosGuitChords.ar}//class
play{~ch2.ar}//instance
play{~ch2.ar('M',0.01,2)}//Major
play{~ch2.ar('m',0.01,2)}//Minor
play{~ch2.ar('M7',0.01,2)}//Major seventh
play{~ch2.ar('Mmaj7',0.01,2)}//Major major seventh
play{~ch2.ar('mmaj7',0.01,2)}//Minor major seventh
play{~ch2.ar('M9',0.01,2)}//Major ninth
play{~ch2.ar('M9m',0.01,2)}//Major, minor ninth
play{~ch2.ar('m9',0.01,2)}//Minor ninth
play{~ch2.ar('m9m',0.01,2)}//Minor, minor ninth
// wave driven envelope
~e = CaosEnv.new
play{LFTri.ar!2*CaosEnv.ar}//class
play{LFTri.ar!2*~e.ar}//instance
play{LFTri.ar!2*~e.ar(\off,0.01,4)}
play{LFTri.ar*~e.ar(\sin,0.01,4,2,1)}//sine
play{LFTri.ar*~e.ar(\saw,0.01,4,2,1)}//saw
play{LFTri.ar*~e.ar(\pulse,0.01,4,2,1)}//square