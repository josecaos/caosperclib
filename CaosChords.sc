//Written by @joseCao5
//Chord generator over LFPulse wave
//Part of CaosPercLib v1.2.2
CaosChords : CaosEnv  {

	*new {
		^super.new;
	}

	*ar {|chord = 'Mmaj7',att=0.05,rel=1,note=60,iphase=0.025,width=0.1,cutf=1200,rq=0.5,gate=1,amp=0.5,pan=0,doneAction=2|
		var sig,env;

		sig = this.signal(chord,note,iphase,width,cutf,rq,amp);
		env = this.envKR(att,rel,gate,doneAction);

		^Pan2.ar(sig*env,pan);

	}

	ar {|chord = 'Mmaj7',att=0.05,rel=1,note=60,iphase=0.025,width=0.1,cutf=1200,rq=0.5,gate=1,amp=0.5,pan=0,doneAction=2|
		var sig,env;
		sig = this.signal(chord,note,iphase,width,cutf,rq,amp);
		env=this.envKR(att,rel,gate,doneAction);

		^Pan2.ar(sig*env,pan);

	}

	*robot{|chord='Mmaj7',att=0.05,rel=1,note=60,iphase=0.025,width=0.1,cutf=1200,rq=0.5,amp=0.5,pan=0,t=1,tp=0,doneAction=0|
		var sig,env;

		sig = this.signal(chord,note,iphase,width,cutf,rq,amp);
		env = this.envKR(att,rel,Impulse.kr(t,tp),doneAction);

		^Pan2.ar(sig*env,pan);
	}
	//
	*signal {|chord,note,iphase,width,cutf,rq,amp|
		var sint,filt;
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
		},{7.do{"ERR: Use 'M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', '5dim7' or '5aug7' only as first CaosChord.sc argument".warn}}
		);
		ton=notes[0];
		third=notes[1];
		fifth=notes[2];
		seventh=notes[3];
		sint=LFPulse.ar(ton.midicps,iphase,width,amp)+LFPulse.ar(third.midicps,iphase,width,amp/1.1)+LFPulse.ar(fifth.midicps,iphase,width,amp/1.05)+LFPulse.ar(seventh.midicps,iphase,width,amp/1.35);
		filt=LPF.ar(sint,cutf,rq)

		^sint+filt;

	}

	signal {|chord,note,iphase,width,cutf,rq,amp|
		var sint,filt;
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
		},{7.do{"ERR: Use 'M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', '5dim7' or '5aug7' only as first CaosChord.sc argument".warn}}//if none of above
		);
		ton=notes[0];
		third=notes[1];
		fifth=notes[2];
		seventh=notes[3];
		sint=LFPulse.ar(ton.midicps,iphase,width,amp)+LFPulse.ar(third.midicps,iphase,width,amp/1.1)+LFPulse.ar(fifth.midicps,iphase,width,amp/1.05)+LFPulse.ar(seventh.midicps,iphase,width,amp/1.35);
		filt=LPF.ar(sint,cutf,rq)

		^sint+filt;

	}

}
