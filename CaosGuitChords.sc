//Written by @IllSlide
//Chord generator as common Guitar interval disposition
//Part of CaosPercLib  2.0
CaosGuitChords : CaosEnv {

	*new {

		^super.new;

	}

	*ar{|chord='m', att=0.05, rel=1, note=60, cutf=12000, rq=0.5, gate=1, amp=0.4, pan=0|
		var sig, env;

		sig = this.signal(chord,note,cutf,rq,amp);
		env = this.envKR(att,rel,gate);

		^Pan2.ar(sig*env,pan);
	}

	ar{|chord='m', att=0.05, rel=1, note=60, cutf=12000, rq=0.5, gate=1, amp=0.4, pan=0|
		var sig, env;

		sig = this.signal(chord,note,cutf,rq,amp);
		env = this.envKR(att,rel,gate);

		^Pan2.ar(sig*env,pan);
	}

	*robot{|chord='m', att=0.05, rel=1, note=60, cutf=12000, rq=0.5, amp=0.4, pan=0, t=1, tp=0|
		var sig,env;

		sig = this.signal(chord,note,cutf,rq,amp);
		env = this.envKR(att,rel,Impulse.kr(t,tp),0);

		env=EnvGen.kr(Env.perc(att,rel),Impulse.kr(t,tp),0)
		^Pan2.ar(sig*env,pan);
	}

	*signal {|chord,note,cutf,rq,amp|
		var sint,filt;
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
			},{7.do{"ERR: Use 'M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', 'M9',  'M9m', 'm9' or 'm9m' only as first CaosChord.ar argument".warn}
		});
		ton=notes[0];
		third=notes[3];
		fifth=notes[1];
		seventh=notes[2];
		octfifth=notes[4];
		oct=notes[5];
		sint=(
			SinOsc.ar(ton.midicps,0,amp/1.25)+
			LFTri.ar(fifth.midicps,0.15,amp/2.4)+
			LFTri.ar(seventh.midicps,0.25,amp/3.3)+
			LFTri.ar(third.midicps,0.5,amp/3)+
			LFTri.ar(octfifth.midicps,0.75,amp/3.3)+
			Saw.ar(oct.midicps,amp/6);
		);
		filt=LPF.ar(sint,cutf,rq);

		^sint+filt;
	}

	signal {|chord,note,cutf,rq,amp|
		var sint,filt;
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
			},{7.do{"ERR: Use 'M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', 'M9',  'M9m', 'm9' or 'm9m' only as first CaosChord.ar argument".warn}
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

		^sint+filt;
	}

}
