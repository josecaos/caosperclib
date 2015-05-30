//Written by @joseCao5
//Chord generator as common Guitar interval disposition
//Part of CaosPercLib 1.1
CaosGuitChords {
	*ar{|chord = 'M', att = 0.1, rel = 1, note = 57, cutf = 1200, rq = 0.5, pan = 0, gate = 0, amp = 0.5|
		var sint,filt,env;
		var notes,chords,ton,third,fifth,seventh,oct,octfifth;
		chords=['M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', 'M9', 'M9m', 'm9', 'm9m'];
		if(chords.include(chord),{
			//comprueba el valor del primer argumento
			if(chord==chords[0],{notes = [note,note+7,note+12,note+16,note+19,note+24]});
			if(chord==chords[1],{notes = [note,note+7,note+12,note+15,note+19,note+24]});
			if(chord==chords[2],{notes = [note,note+7,note+10,note+16,note+19,note+24]});
			if(chord==chords[3],{notes = [note,note+7,note+10,note+15,note+19,note+24]});
			if(chord==chords[4],{notes = [note,note+7,note+11,note+16,note+19,note+24]});
			if(chord==chords[5],{notes = [note,note+7,note+11,note+15,note+19,note+24]});
			if(chord==chords[6],{notes = [note,note+7,note+14,note+16,note+19,note+24]});
			if(chord==chords[7],{notes = [note,note+7,note+13,note+16,note+19,note+24]});
			if(chord==chords[8],{notes = [note,note+7,note+14,note+15,note+19,note+24]});
			if(chord==chords[9],{notes = [note,note+7,note+13,note+15,note+19,note+24]});
			},{6.do{"ERR: Use 'M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', 'M9',  'M9m', 'm9' or 'm9m' only as first CaosChord.ar argument".postln}
		});
		ton=notes[0];
		third=notes[3];
		fifth=notes[1];
		seventh=notes[2];
		octfifth=notes[4];
		oct=notes[5];
		sint=(SinOsc.ar(notes.midicps,0,amp/3)*
			SinOsc.ar(fifth.midicps,0,amp/3.6)*
			SinOsc.ar(seventh.midicps,0,amp/3.8)*
			SinOsc.ar(third.midicps,0,amp/4.4)*
			SinOsc.ar(octfifth.midicps,0,amp/4.6)*
			SinOsc.ar(oct.midicps,0,amp/4.8);
		);
		filt=LPF.ar(sint,cutf,rq);
		env=EnvGen.kr(Env.perc(att,rel),gate,doneAction:2)
		^Pan2.ar(sint*env,pan);//filt
	}
}