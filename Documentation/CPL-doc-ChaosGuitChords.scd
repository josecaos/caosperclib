s.boot;
/////////////////////////////////////////////////////////
//Pseudo Class, Chord Generator in the common
//Guitarr disposition.
////////////////////////////////////////////////////////

//Example CaosGuit Chords.ar:
//arguments:
//|chord = 'M', att = 0.1, rel = 1, note = 57, cutf = 120, rq = 0.5, pan = 0, gate = 0, amp = 0.5|
//chord arg types =['M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', 'M9', 'M9m', 'm9', 'm9m'];
y={CaosGuitChords.ar('Mmaj7',0.01,4,48,MouseX.kr(120,1250),0.5,0,1,0.5)};
y.play;
//
(
Tdef(\prog,{
	var chtime = Pseq(['M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', 'M9', 'M9m', 'm9', 'm9m'],inf,1).asStream;
	loop{{CaosGuitChords.ar(chtime.next,0.01,2,48,MouseX.kr(120,12000),0.5,0,1,1)}.play;
	chtime.value.postln;
	2.wait}
})
)
Tdef(\prog).play ;
//
