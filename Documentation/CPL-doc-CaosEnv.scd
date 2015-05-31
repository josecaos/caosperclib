s.boot;
s.scope(2,0);
/////////////////////////////////////////////////////////
//Pseudo Class, Chord Generator in the common
//key disposition.
////////////////////////////////////////////////////////
//Example CaosEnv.ar:
//arguments:
//|waveform = 'sin',att = 0.01, rel = 0.5, freq = 4, tremolo = 2, gate = 0 |
//waveform arg types = ['sin', 'tri', 'pulse'];
x={LFTri.ar(120,0,0.25)*CaosEnv.ar('pulse',0.1,10,1,XLine.ar(1,24,5),1)};
x.play;
//
//Example CaosEnv.kr:
//arguments:
//|waveform = 'sin',att = 0.01, rel = 0.5, freq = 4, tremolo = 2, gate = 1 |
//waveform arg types = ['sin', 'tri', 'pulse'];
y={LFTri.ar(120,0,0.25)*CaosEnv.kr('pulse',0.05,10,1,XLine.ar(1,24,5),1)};
y.play;
//
//
(
Tdef(\envs,{
	var wavetype  = Pxrand(['sin', 'tri', 'pulse'],inf,).asStream;
	loop{{LFCub.ar(120,0,0.5)*CaosEnv.ar(wavetype.next,0.05,1,2,(1..8).rand,1)}.play;
	wavetype.value.postln;
	0.5.wait}
	}).quant_(4);
)
Tdef(\envs).play;