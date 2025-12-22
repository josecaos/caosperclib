// CaosPercLib default timbres
// Declare each line
(
s.boot;
)
(
s.scope;
)
//Kick
play{CaosKick.ar}//class
~k = CaosKick.new//instance
play{~k.ar};
//Kick 2
s.boot;
play{CaosKick2.ar()}//class
play{CaosKick2.ar(modFreq:1, amp2: 0.01)}
play{CaosKick2.ar(1,1,1)}//class
play{CaosKick2.ar(modFreq:rrand(32,2))};// La fundamental de CaosKick2 solo modula si el valor de 'modFreq' es mayora a '1'
play{CaosKick2.ar(modFreq:rrand(32,6),wave:"LFNoise0")};// Controla la fundamental con 'wave'
play{CaosKick2.ar(modFreq:rrand(32,2),wave:"SinOsc")};
play{CaosKick2.ar(modFreq:rrand(32,2),wave:"LFTri")};
play{CaosKick2.ar(modFreq:rrand(32,2),wave:"Saw")};
play{CaosKick2.ar(0.1,32,2,0.5,0.45,60,66,36,1,1,0,2,0.25,"Saw")}//class
~k2 = CaosKick2.new//instance
play{~k2.ar};
//snare
// TODO: Nivelar amp hacia arriba / revisar timbre default
s.boot;
s.scope;
play{CaosSnare.ar}//class
play{CaosSnare.ar(fund:60)}//class
play{CaosSnare.ar(fund:666)}//class
play{CaosSnare.ar(rq:1,amp2:0.25,fund:666)}//class
~t = CaosSnare.new
play{~t.ar}//instance
//snare 2
~t2 = CaosSnare2.new
play{CaosSnare2.ar}//class
play{~t2.ar}//instance
//hihats
// TODO: Nivelar amp hacia arriba
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
play{CaosBass.ar(note:rrand(28,29))}//class
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
// TODO: Revisar release
~p2 = CaosPad2.new
play{CaosPad2.ar}//class
play{~p2.ar}//instance
play{~p2.ar(0.01,8,Prand(LCNE.dupOctave(Scale.dorian.degrees,4),inf).asStream.next+12,Prand(LCNE.dupOctave(Scale.dorian.degrees.scramble,4),inf).asStream.next+24,rrand(0.0125,2),12,1,2,0.1)};
//chords
// TODO: nivelar amp
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
// TODO: nivelar amp
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
(
s.boot;
)
(
~e = CaosEnv.new;
)
(
play{LFTri.ar!2*CaosEnv.ar}//class
)
(
play{LFTri.ar!2*CaosEnv.ar('sin',0.01,4,2,1)}//class
)
(
play{LFTri.ar!2*~e.ar}//instance
)
( // waveform argument error handling
play{LFTri.ar!2*~e.ar('XXX',0.01,4)}
)
(
play{LFTri.ar!2*~e.ar('off',0.01,4)}
)
(
play{LFTri.ar*~e.ar('sin',0.01,4,2,1)}//sine
)
(
play{LFTri.ar*~e.ar(\saw,0.01,4,2,1)}//saw
)
(
play{LFTri.ar*~e.ar(\pulse,0.01,4,3,1)}//square
)
