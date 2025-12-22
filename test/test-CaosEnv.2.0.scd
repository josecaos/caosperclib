s.boot
220/12
play{LFTri.ar(Array.series(24,220,18.333333333333).choose)*CaosEnv.ar('tri',0.01,8,0,1,1)}
play{CaosKick.ar()}
play{CaosKick2.ar()}
play{CaosSnare.ar()}
play{CaosSnare2.ar()}
play{CaosHats.ar()}
play{CaosHats2.ar()}
play{CaosBass.ar()}
play{CaosBass2.ar()}
play{CaosPad.ar()}
play{CaosPad2.ar(1,60,60,2,0.1,2)}
// play{CaosChords.ar()}
// play{CaosGuitChords.ar()}

// proxyspace test
p = ProxySpace.push(s.boot);
p.quant_(1);
(~o.play;)
(~o = 0;)
(~t = 1;)
~play = {LFTri.ar(Array.series(12,220,18.333333333333).choose)*CaosEnv.robot('sin',0.01,0.25,2,1,~t)};
~play = {LFTri.ar(Array.series(12,220,18.333333333333).choose)*CaosEnv.robot('tri',0.01,0.125,0,0.5,~t)};
~kick[0] = {CaosKick.robot(0.01,0.25,8/16,0.5,60,80,55,pan:0.02,t:~t*8)};
~kick[1] = {CaosKick.robot(0.01,0.25,2,0.5,60,80,55,pan:0.02,t:~t*4)};
~snare[0] = {CaosSnare2.robot(pan:0,t:1)};
~snare[1] = {CaosSnare2.robot(0.01,0.15,0.35,0.2,240,0.25,pan:0,t:~t*1*0.48)};
~hihats[0] = {CaosHats.robot(0.05,0.085,12320,0.85,0.75,0.15,t:~t*8,pan:0.1)};
~hihats[1] = {CaosHats.robot(0.05,0.25,11320,0.15,0.25,0.75,t:~t*2,pan:0)};
// fx
~rev = {GVerb.ar(Mix([~play.ar,~hihats.ar]),42,0.85,0.5,mul:0.7)}
~rev = {GVerb.ar(~hihats.ar,42,2,0.5,mul:0.7)}
//
(
~o = ~kick[0]+~snare[1]+~kick[1]+~snare[0];
)
~o = ~play+~kick[0]+~snare[1]+~kick[1]+~snare[0];
~o = ~rev+~kick[0]+~snare[1]+~kick[1]+~snare[0];
~o = ~rev+~kick[0]+~snare[1]+~kick[1]+~snare[0]+~hihats;
~o = ~rev+~kick[0]+~snare[1]+~kick[1]+~snare[0]+~hihats;
~o = ~rev+~hihats;
~o = ~rev;
~o = 0;
p.fadeTime = 0.25;
p.fadeTime = 45;
//
(
Tdef(\test,{
	loop {

		~play = {LFTri.ar(Array.series(12,220,18.333333333333).choose)*CaosEnv.robot('tri',0.01,0.02,0,2,1)};

		0.5.wait
	}
}).play.quant_(1);
)
Tdef(\test).stop;