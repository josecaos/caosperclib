//written by @Ill_Slide
//Simple ambience pad
//Part of CaosPercLib v1.2.3
CaosPad : CaosEnv {

	*new {

		^super.new;

	}

	*ar {|waveform='sin',att=3,rel=3,note=57,semi=0,phase=0.5,cutf=2920,rq=0.85,gate=1,amp=0.5,pan=0,doneAction=2|
		var sig,env;

		sig = this.signal(waveform,note,semi,phase,cutf,rq,amp);
		sig = this.comp(sig,0.95,0.75,0.76,0.001);
		env = this.envKR(att,rel,gate,doneAction);

		^Pan2.ar(sig*env,pan);

	}

	ar {|waveform='sin',att=3,rel=3,note=57,semi=0,phase=0.5,cutf=2920,rq=0.85,gate=1,amp=0.5,pan=0,doneAction=2|
		var sig,env;

		sig = this.signal(waveform,note,semi,phase,cutf,rq,amp);
		sig = this.comp(sig,0.95,0.75,0.76,0.001);
		env = this.envKR(att,rel,gate,doneAction);

		^Pan2.ar(sig*env,pan);

	}

	*robot {|waveform = 'sin',att=3,rel=3,note=57,semi=0,phase=0,cutf=920,rq=0.7,amp=0.5,pan=0,t=0.25,tp=0,doneAction=0|
		var sig,env;

		sig = this.signal(waveform,note,semi,phase,cutf,rq,amp);
		sig = this.comp(sig,0.95,0.75,0.76,0.001);
		env = this.envKR(att,rel,Impulse.kr(t,tp),doneAction)

		^Pan2.ar(sig*env,pan);
	}

	*signal {|waveform,note,semi,phase,cutf,rq,amp|
		var sig,filt,osc,tag,waveindex;

		osc = [SinOsc,LFTri,Pulse];
		tag = ['sin','tri','pulse'];
		waveindex = tag.find([waveform]);

		if(waveindex != nil, {

			if(waveform == tag[2],{
				if(phase == 0,{
					phase = 0.25;
					"Can't use 0 for 'phase' argument in 'Pulse' waveform, setted to 0.25".warn;
				});
			});

			sig = osc[waveindex].ar(Mix([note.midicps,(note+semi).midicps]),phase,amp);
			filt = RLPF.ar(sig,cutf,rq);
			^filt;
		}, {
			^"Use only: 'sin', 'tri' or 'pulse' as first CaosPad argument".warn;
		});

	}

	signal {|waveform,note,semi,phase,cutf,rq,amp|
		var sig,filt,osc,tag,waveindex;

		osc = [SinOsc,LFTri,Pulse];
		tag = ['sin','tri','pulse'];
		waveindex = tag.find([waveform]);

		if(waveindex != nil, {

			if(waveform == tag[2],{
				if(phase == 0,{
					phase = 0.25;
					"Can't use 0 for 'phase' argument in 'Pulse' waveform, setted to 0.25".warn;
				});
			});

			sig = osc[waveindex].ar(Mix([note.midicps,(note+semi).midicps]),phase,amp);
			filt = RLPF.ar(sig,cutf,rq);
			^filt;
		}, {
			^"Use only: 'sin', 'tri' or 'pulse' as first CaosPad argument".warn;
		});

	}

}
