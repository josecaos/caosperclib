p = ProxySpace.push(s.boot);
~t = 4;
~o.play;
~synth = {CaosKick.robot(0.01,0.75,2,0.5,60,60,55,1,1,0,~t)}
// ~synth = {Pulse.ar(220,mul:0.35)*LFTri.kr(0.5)}
~o = ~synth;
// ~o = 0
//
//
s.scope
s.boot;

p = ProxySpace.new;
p.fadeTime = 2; // fadeTime specifies crossfade
p[\out].play; // monitor an empty placeholder through hardware output
// set its source
p[\out] = { SinOsc.ar([350, 351.3], 0, 0.2) };
p[\out] = { Pulse.ar([350, 351.3] / 4, 0.4) * 0.2 };
p[\out] = Pbind(\dur, 0.03, \freq, Pbrown(0, 1, 0.1, inf).linexp(0, 1, 200, 350));

// route one proxy through another:
p[\out] = { Ringz.ar(p[\in].ar, [350, 351.3] * 8, 0.2) * 4 };
p[\in] = { Impulse.ar([5, 7]/2, [0, 0.5]) };

a.clear(3); // clear after 3 seconds
b.clear(3);

//


p.pop;
0.05/2
0.025+0.5
0.525/2
(
a = CaosChords;
Tdef(\test, {
	var	arr = ['M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', '5dim7', '5aug7'] ;
	var chord = Pwrand(arr,[0.15,0.25,0.15,0.25,0.5,0.5,0.2625,0.2625],inf).asStream;
	var time = Pwrand([1,2,3,4,8,0.5,0.25]/2,[0.2,0.2,0.1,0.25,0.05,0.1,0.1],inf).asStream;
	loop {
		play{	a.ar(chord.resume,1,8,Array.series(36,46,1).choose,0.25,0.1)	};
		time.resume.wait;
	}
}).quant_(1);
);
Tdef(\test).play;
Tdef(\test).stop;
