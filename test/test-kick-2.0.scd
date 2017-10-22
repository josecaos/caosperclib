p.pop;
s.boot;
(
// modFreq= 1, modbw= 0.1, freq1= 60, freq2= 66, lowcutfreq= 50,
SynthDef(\test,{|att,rel,modFreq,modbw,lowcutfreq,gate,amp1,amp2|

	var kick;

	kick = CaosKick.ar(att:att,rel:rel,modFreq:modFreq,modbw:modbw,lowcutfreq:lowcutfreq,gate:gate,amp1:amp1,amp2:amp2);
	Out.ar(0,kick);

}).add;
);
Synth(\test,[att:0.5,rel:0.5,\gate,1,\amp1,1,\amp2,0]);
Synth(\test,[att:0.5,rel:0.5,\gate,1,\amp1,0,\amp2,1]);
Synth(\test,[\att,1,\rel,8,\modFreq,rrand(120,3400),\modbw,0.5,\lowcutfreq,60,\gate,1,\amp1,0.5,\amp2,0.5]);
CaosKick.text();
play{CaosKick.ar(gate:1)}
play{CaosKick.comp(SinOsc.ar(SinOsc.kr(4,0,42,66),0,0.5));}
play{SinOsc.ar(SinOsc.kr(4,0,42,66),0,0.5)}
play{GrainSin.ar(2,Impulse.kr(1),0.3,120)}
play{GrainSin.ar(2,Impulse.kr(1),0.6,220)}
play{GrainSin.ar(2,Impulse.kr(1),1.2,320)}
play{GrainSin.ar(2,Impulse.kr(1),2.4,420)}

// robot test
// ///////////////
p = ProxySpace.push();
~out.play;
~out.quant_(4);
s.scope(0,2);
~t = 16;
~kontrol = {Demand.kr(Impulse.kr(~t),0,Dxrand((60..8400),inf))};

~ambient.quant_(4)[0] = {CaosKick.robot(att:1,rel:32,modFreq:~kontrol.kr,modbw:0.5,lowcutfreq:~kontrol.kr/1.25,t:~t,amp1:0.5,amp2:0.5)}

~rev = {GVerb.ar(~ambient.ar,48,4,0.5,0.75,15,0.2,0.5,0.8,300,0.5)};

~out.fadeTime = 16;
~out = ~ambient+~rev;
~out = ~rev;
~out = 0;
