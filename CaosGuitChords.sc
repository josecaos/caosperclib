//Written by @joseCao5
//Chord generator as common Guitar interval disposition
//Part of CaosPercLib 1.1
CaosGuitChords {
	*ar{|chord = 'm', att = 0.05, rel = 1, note = 57, cutf = 1200, rq = 0.5, pan = 0, gate = 0, amp = 0.4|
		var sint,filt,env;
		var interval,notes,chords,ton,third,fifth,seventh,oct,octfifth;
		chords=['M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', 'M9', 'M9m', 'm9', 'm9m'];
		interval = [
			[0,7,12,16,19,24],
			[0,7,12,15,19,24],
			[0,7,10,16,19,24],
			[0,7,10,15,19,24],
			[0,7,10,15,19,24],
			[0,7,11,16,19,24],
			[0,7,11,15,19,24],
			[0,7,14,16,19,24],
			[0,7,13,16,19,24],
			[0,7,14,15,19,24],
			[0,7,13,15,19,24]
		];
		if(chords.includes(chord),{
			notes = note+interval[chords.indexOf(chord)];
			},{7.do{"ERR: Use 'M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', 'M9',  'M9m', 'm9' or 'm9m' only as first CaosChord.ar argument".postln}
		});
		ton=notes[0];
		third=notes[3];
		fifth=notes[1];
		seventh=notes[2];
		octfifth=notes[4];
		oct=notes[5];
		sint=(
			SinOsc.ar(ton.midicps,0,amp/1.8)+
			LFTri.ar(fifth.midicps,0.15,amp/3.4)+
			LFTri.ar(seventh.midicps,0.25,amp/3.2)+
			LFTri.ar(third.midicps,0.5,amp/3.8)+
			LFTri.ar(octfifth.midicps,0.75,amp/4)+
			SinOsc.ar(oct.midicps,1,amp/4.2);
		);
		filt=LPF.ar(sint,cutf,rq);
		env=EnvGen.kr(Env.perc(att,rel),gate,doneAction:2)
		^Pan2.ar(sint*env,pan);//filt
	}
}
