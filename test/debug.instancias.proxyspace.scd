p = ProxySpace.push(s.boot);
a = CaosChords
~o.play;

~synth = {a.robot(t:1)}

~o = ~synth
// ~o = 0


p.pop;
0.05/2
0.025+0.5
0.525/2
(
a = CaosChords;
Tdef(\test, {
	var	arr = ['M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', '5dim7', '5aug7'] ;
	var chord = Pwrand(arr,[0.15,0.25,0.15,0.25,0.5,0.5,0.2625,0.2625],inf).asStream;
	var time = Pwrand([1,2,3,4,8,0.5,0.25],[0.2,0.2,0.1,0.25,0.05,0.1,0.1],inf).asStream;
	loop {
		play{	a.ar(chord.resume,1,8,Array.series(36,46,1).choose,0.25,0.1)	};
		time.resume.wait;
	}
}).quant_(1);
);
Tdef(\test).play;
Tdef(\test).stop;
