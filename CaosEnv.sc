// written by @josecao5
//A multi shape LFO Envelope:
//Part of CaosPercLib 1.1
CaosEnv {
	*ar {|waveform = 'sin',att = 0.01, rel = 0.5, freq = 4, tremolo = 2, gate = 0 |
		var lfo,env,osc,tag,waveindex,iphase;
		osc=[SinOsc,LFTri,Pulse];
		tag=['sin','tri','pulse'];
		waveindex=tag.find([waveform]);
		if(waveindex==nil,{7.do{"Only use: 'sin', 'tri' or 'pulse' as first CaosEnv argument".warn}});
		if(waveform==tag[2],{iphase=0.25},{iphase=0});
		lfo=osc[waveindex].ar(osc[waveindex].ar([freq,freq],0,freq,freq+tremolo),iphase,0.5);
		env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);
		^lfo*env
	}
	*kr {|waveform = 'sin',att = 0.01, rel = 0.5, freq = 4, tremolo = 2, gate = 1 |
		var lfo,env,osc,tag,waveindex,iphase;
		osc=[SinOsc,LFTri,Pulse];
		tag=['sin','tri','pulse'];
		waveindex=tag.find([waveform]);
		if(waveindex==nil,{7.do{"Only use: 'sin', 'tri' or 'pulse' as first CaosEnv argument".warn}});
		if(waveform==tag[2],{iphase=0.25},{iphase=0});
		lfo=osc[waveindex].kr([freq,freq+tremolo],iphase,0.75);
		env=EnvGen.kr(Env.perc(att,rel),gate,doneAction:2);
		^lfo*env
	}

	*robot {|waveform = 'sin',att = 0.01, rel = 0.5, freq = 4, tremolo = 2, t = 1, tp = 0 |
		var lfo,env,osc,tag,waveindex,iphase;
		osc=[SinOsc,LFTri,Pulse];
		tag=['sin','tri','pulse'];
		waveindex=tag.find([waveform]);
		if(waveindex==nil,{7.do{"Only use: 'sin', 'tri' or 'pulse' as first CaosEnv argument".warn}});
		if(waveform==tag[2],{iphase=0.25},{iphase=0});
		lfo=osc[waveindex].ar(osc[waveindex].ar([freq,freq],0,freq,freq+tremolo),iphase,0.5);
		env=EnvGen.ar(Env.perc(att,rel),Impulse.kr(t,tp),doneAction:0);
		^lfo*env
	}
}