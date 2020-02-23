s.boot;
s.scope;
//Kick

(
play{|modFreq=2,modbw=0.15,freq1=60,freq2=60,amp1=1,amp2=1,lowcutfreq=50|

/*	var head1 = amp1;
	var head2 = amp2;
		if((head1.postcln + head2) > 2, {
	amp1 = 1;
	amp2 = 1;
	});*/
	(
		RHPF.ar(LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp1/1.5)+
			LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp2/1.5),lowcutfreq,0.98);
	)* EnvGen.ar(Env.perc(0.01,0.5),1,doneAction:2);

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
		SinOsc.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp2/2)+Saw.ar([240, 960, 2880, 7680],amp1/16)),lowcutfreq,0.98);
	env = EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);

	Out.ar([0,1],sig*env);
}).add;
);
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
//
(
play{RLPF.ar(LFTri.ar(Pulse.kr(1,0.5,20,20),0,1) + LFPulse.ar(Pulse.kr(1,0.5,60,60),0.5,0.15,1/16),12200,1)*EnvGen.ar(Env.perc(0.01,0.5),1,doneAction:2)!2}
)
//
s.boot;
play{CaosKick.ar}
x = CaosKick2.new;
play{x.ar(rel:0.1)}
play{CaosKick2.ar(0.5,0.15)}
ProxySpace.push(s);
~synth = {CaosKick2.robot(0.1,0.5,1,0.1,0.05,Demand.kr(Impulse.kr(1),0,Dseq([60,120,340]),inf),32,60,1,4)}
~synth.quant_(1).play;
