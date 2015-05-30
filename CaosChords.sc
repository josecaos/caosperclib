//Written by @joseCao5
//Chord generator over LFPulse wave
//Part of CaosPercLib 1.1
CaosChords {
	*ar{|chord = 'Mmaj7', att = 0.1, rel = 1, note = 57, iphase = 0.025, width = 0.1, cutf = 1200, rq = 0.5, pan = 0, gate = 0, amp = 0.5|
		var sint,filt,env;
		var notes,chords,ton,third,fifth,seventh;
		chords=['M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', '5dim7', '5aug7'];

		if(chords.includes(chord),{
			if(chord==chords[0],{notes = [note,note+4,note+7,note+12]}); // seventh is actualy the octave
			if(chord==chords[1],{notes = [note,note+3,note+7,note+12]}); //seventh is actualy the octave
			if(chord==chords[2],{notes = [note,note+4,note+7,note+10]});
			if(chord==chords[3],{notes = [note,note+3,note+7,note+10]});
			if(chord==chords[4],{notes = [note,note+4,note+7,note+11]});
			if(chord==chords[5],{notes = [note,note+3,note+7,note+11]});
			if(chord==chords[6],{notes = [note,note+4,note+6,note+12]});// seventh is actualy the octave
			if(chord==chords[7],{notes = [note,note+4,note+8,note+12]});// seventh is actualy the octave
			},{6.do{"ERR: Use 'M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', '5dim7' or '5aug7' only as first CaosChord.sc argument".postln}}//if none of above
		);
		ton=notes[0];
		third=notes[1];
		fifth=notes[2];
		seventh=notes[3];
		sint=LFPulse.ar(ton.midicps,iphase,width,amp)+LFPulse.ar(third.midicps,iphase,width,amp/1.1)+
			LFPulse.ar(fifth.midicps,iphase,width,amp/1.05)+LFPulse.ar(seventh.midicps,iphase,width,amp/1.35);
		filt=LPF.ar(sint,cutf,rq);
		env=EnvGen.kr(Env.perc(att,rel),gate,doneAction:2)
		^Pan2.ar(filt*env,pan);
	}
}