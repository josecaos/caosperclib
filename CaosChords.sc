//Written by @joseCao5
//Chord generator over LFPulse wave
//Part of CaosPercLib 1.1
CaosChords {
	*ar{|chord = 'Mmaj7', att = 0.05, rel = 1, note = 57, iphase = 0.025, width = 0.1, cutf = 1200, rq = 0.5, pan = 0, gate = 0, amp = 0.5|
		var sint,filt,env;
		var interval,notes,chords,ton,third,fifth,seventh;
		chords=['M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', '5dim7', '5aug7'];
		interval = [
			[0,4,7,12],
			[0,3,7,12],
			[0,4,7,10],
			[0,3,7,10],
		    [0,4,7,11],
			[0,3,7,11],
			[0,4,6,12],
			[0,4,8,12]
		];
		if(chords.includes(chord),{
			notes = note+interval[chords.indexOf(chord)];
			},{7.do{"ERR: Use 'M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', '5dim7' or '5aug7' only as first CaosChord.sc argument".postln}}//if none of above
		);
		ton=notes[0];
		third=notes[1];
		fifth=notes[2];
		seventh=notes[3];
		sint=LFPulse.ar(ton.midicps,iphase,width,amp)+
		    LFPulse.ar(third.midicps,iphase,width,amp/1.1)+
		    LFPulse.ar(fifth.midicps,iphase,width,amp/1.05)+
		    LFPulse.ar(seventh.midicps,iphase,width,amp/1.35);
		filt=LPF.ar(sint,cutf,rq);
		env=EnvGen.kr(Env.perc(att,rel),gate,doneAction:2)
		^Pan2.ar(filt*env,pan);
	}
}