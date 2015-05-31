s.boot;
/////////////////////////////////////////////////////////
//Pseudo Class, Chord Generator in the common
//key disposition.
////////////////////////////////////////////////////////
//Example CaosChords.ar:
//arguments:
//|chord = 'M', att = 0.1, rel = 1, note = 57, iphase = 0.025, width = 0.25, cutf = 120, rq = 0.5, pan = 0, gate = 0, amp = 0.5|
//chord arg types = ['M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', '5dim7', '5aug7'];
x={CaosChords.ar('M',0.01,10,36,0.1,0.1,220,0.5,0,1,0.5)};
x.play;
//
(
Tdef(\progression,{
	var chordtype  = Pseq(['M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', '5dim7', '5aug7'],inf,).asStream;
	loop{{CaosChords.ar(chordtype.next,0.01,5,60,0.1,0.1,MouseX.kr(60,1200),0.75,0,1,1)}.play;
	chordtype.value.postln;
	5.wait}//0.5.wait
	}).quant_(4);
)
Tdef(\progression).play;
s.scope