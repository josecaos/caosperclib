p.pop;
s.free;
s.boot;
// function
play{CaosKick2.ar()}
play{CaosKick2.ar(0.15)}
play{CaosKick2.ar(1,1)}
play{CaosKick2.ar(0.01,1,1)}
play{CaosKick2.ar(0.01,2,0.5)}
play{CaosKick2.ar(0.01,4)}
play{CaosKick2.ar(0.01,0.75,modFreq:2, modbw:1)}
play{CaosKick2.ar(0.01,8,modFreq:4,modbw:1,freq1:60,freq2:[60,63,67,72].choose,lowcutfreq:50,gate:1,amp1:0.5,amp2:0.5)}
// freqs sweep test
play{CaosKick2.ar(freq1:60, freq2:60)}
play{CaosKick2.ar(freq1:60, freq2:60*2,oscType:["LFTri","SinOsc","Saw","LFNoise0","Pulse"].choose)}
play{CaosKick2.ar(freq1:60, freq2:60*3,oscType:["LFTri","SinOsc","Saw","LFNoise0","Pulse"].choose)}
play{CaosKick2.ar(freq1:60, freq2:60*4,oscType:["LFTri","SinOsc","Saw","LFNoise0","Pulse"].choose)}
play{CaosKick2.ar(freq1:60, freq2:60*5,oscType:["LFTri","SinOsc","Saw","LFNoise0","Pulse"].choose)}
play{CaosKick2.ar(freq1:60, freq2:60*6,oscType:["LFTri","SinOsc","Saw","LFNoise0","Pulse"].choose)}
play{CaosKick2.ar(freq1:60, freq2:60*8,oscType:["LFTri","SinOsc","Saw","LFNoise0","Pulse"].choose)}

// Fixed robot method test
// ///////////////
p = ProxySpace.push();
~out.play;
~out.quant_(4);
~t = 4;

o = Prand(["LFTri","SinOsc","Saw","LFNoise0","Pulse"],inf).asStream;
o.next.postcln;
~kick = {CaosKick2.robot(t:~t*4)}
~kick = {CaosKick2.robot(0.5,t:~t)}
~kick = {CaosKick2.robot(0.5,modFreq:~t/2,t:~t)}
~kick = {CaosKick2.robot(att:0.01,rel:0.5,modFreq:~t,t:~t,amp:0.5,amp2:0.5)}
~kick = {CaosKick2.robot(0.01,0.25,t:0.5,amp:1,amp2:0)}
~kick = {CaosKick2.robot(0.01,0.25,t:4,amp:0,amp2:1)}
~kick = {CaosKick2.robot(0.01,0.25,0.25,t:2,amp:1,amp2:0)}
~kick = {CaosKick2.robot(0.01,1,8,t:2,amp:1,amp2:0)}
~kick = {CaosKick2.robot(0.01,1,2,t:2,tp:0,amp:1,amp2:0)}
~kick = {CaosKick2.robot(0.01,1,2,t:2,tp:[0,0.5],amp:1,amp2:0)}
~kick = {CaosKick2.robot(0.01,0.125,4,t:8,amp:0.5,amp2:1,oscType:"SinOsc")}
~kick = {CaosKick2.robot(0.01,0.125,4,t:8,amp:0.5,amp2:1,oscType:"LFTri")}
~kick = {CaosKick2.robot(0.01,0.125,4,t:8,amp:0.25,amp2:1,oscType:"LFNoise0")}
~kick = {CaosKick2.robot(0.01,0.125,4,t:8,amp:0.5,amp2:1,oscType:"Saw")}
~kick = {CaosKick2.robot(0.01,0.125,4,t:8,amp:0.5,amp2:1,oscType:"Pulse")}
~kick = {CaosKick2.robot(0.01,0.25,1,t:4,amp:0,amp2:1,oscType:o.next.postcln)}

~rev = {RLPF.ar(FreeVerb.ar(~kick,0.5,0.75,1),10000,1)}

~out = ~kick;
~out = ~ambient+~rev;
~out = ~rev;
~out = 0;
p.pop
s.stop