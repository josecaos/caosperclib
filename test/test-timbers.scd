s.boot;
s.scope;
//Kick
(
play{|modFreq,modbw,freq1,freq2,amp1,amp2,lowcutfreq|

	^RHPF.ar(LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp1/2)+
		LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp2/2),lowcutfreq,0.98);

}
)
// s.boot
// multiplos de 60
(f = {
	var fundamental = 60;
	Array.series(5,0,1).collect({|item|
		fundamental = fundamental + fundamental;
		if(fundamental > 0,{
			item*fundamental;
		});
	});
}).value;
(
SynthDef(\test, {|att=0.01,rel=0.5,modFreq=1,modbw=0.5,freq1=60,freq2=60,lowcutfreq=80,gate=1,amp1=1,amp2=1,pan=0|
	var sig, env;

	sig = RHPF.ar(LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp1/2)+(
		SinOsc.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp2/2)+Saw.ar([240, 960, 2880, 7680],amp1/16);

	),lowcutfreq,0.98);
	env = EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);

	Out.ar([0,1],sig*env);
}).add;
)
Synth(\test,[\amp1,1,\amp2,1]);
Synth(\test,[\amp1,1,\amp2,0]);
Synth(\test,[\amp1,0,\amp2,1]);
(
t = Task({
	loop {
		Synth(\test);
		1.wait;
	}
});
);
t.play;
t.stop;
