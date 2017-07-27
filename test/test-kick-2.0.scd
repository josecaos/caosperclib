s.boot;
(
SynthDef(\test,{|gate,amp1,amp2|

	var kick;

	kick = CaosKick.ar(gate:gate,amp1:amp1,amp2:amp2);

	Out.ar(0,kick);

}).add;
);
Synth(\test,[\gate,1,\amp1,1,\amp2,0]);

play{CaosKick.ar(gate:1)}