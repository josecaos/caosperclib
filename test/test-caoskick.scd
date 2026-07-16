p.pop; // para terminar ProxySpace
s.free;
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
// function
play{CaosKick.ar()}
play{CaosKick.ar(0.15)}
play{CaosKick.ar(1,1)}
play{CaosKick.ar(0.01,1,1)}
play{CaosKick.ar(0.01,2,0.5)}
play{CaosKick.ar(0.01,4)}
play{CaosKick.ar(0.01,0.75,modFreq:2, modbw:1)}
play{CaosKick.ar(0.01,8,modFreq:4, modbw:1, freq1:60, freq2:66, lowcutfreq:50, gate:1, amp1:0.5, amp2:0.5)}
// freqs sweep test
play{CaosKick.ar(freq1:60, freq2:60)}
play{CaosKick.ar(freq1:60, freq2:60*2)}
play{CaosKick.ar(freq1:60, freq2:60*3)}
play{CaosKick.ar(freq1:60, freq2:60*4)}
play{CaosKick.ar(freq1:60, freq2:60*5)}
play{CaosKick.ar(freq1:60, freq2:60*6)}
play{CaosKick.ar(freq1:60, freq2:60*7)}

play{CaosKick.ar(0.01,8,2,freq1:60, freq2:60*4)}
// Fixed robot method test
// ///////////////
p = ProxySpace.push();
~out.play;
~out.quant_(4);
// s.scope(0,2);
~t = 1;
~kontrol = {Demand.kr(Impulse.kr(~t),0,Dxrand((60..8400),inf))};

~kick = {CaosKick.robot()}
~kick = {CaosKick.robot(0.5)}
~kick = {CaosKick.robot(att:0.01,rel:0.5,modFreq:~t,t:~t,amp1:1,amp2:0.5)}
~kick = {CaosKick.robot(0.01,0.25,t:0.5,amp1:1,amp2:0)}
~kick = {CaosKick.robot(0.01,0.25,t:4,amp1:1,amp2:0)}
~kick = {CaosKick.robot(0.01,0.25,0.125,t:2,amp1:1,amp2:0)}
~kick = {CaosKick.robot(0.01,1,8,t:2,amp1:1,amp2:0)}
~kick = {CaosKick.robot(0.01,1,2,t:2,tp:[0.1,0.25],amp1:1,amp2:0)}
~kick = {CaosKick.robot(0.01,0.5,4,t:4,amp1:0.5,amp2:1)}

~ambient = {CaosKick.robot(att:1,rel:0.1,modFreq:~kontrol.kr,modbw:0.5,lowcutfreq:~kontrol.kr/1.25,t:~t,amp1:0.5,amp2:0.5)}

~rev = {GVerb.ar(~ambient.ar,48,4,0.5,0.75,15,0.2,0.5,0.8,300,0.5)};

// ~out.fadeTime = 16;
~out = ~kick;
~out = ~ambient+~rev;
~out = ~rev;
~out = 0;
p.pop
s.stop