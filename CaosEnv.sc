// written by @mixfuckedup
//A multi shape LFO Envelope:
//Part of CaosPercLib 2.0
CaosEnv {

	*new {

		^super.new;

	}

	*ar {|waveform = 'off',att = 0.01, rel = 0.5, freq = 4, tremolo = 2, gate = 1, doneAction = 2|

		if(waveform == 'off', {
			^this.envAR(att,rel,gate,doneAction);
		}, {
			^this.signal(waveform,freq,tremolo)*this.envAR(att,rel,gate,doneAction);
		});

	}

	ar {|waveform = 'off',att = 0.01, rel = 0.5, freq = 4, tremolo = 2, gate = 1, doneAction = 2|

		if(waveform == 'off', {
			^this.envAR(att,rel,gate,doneAction);
		}, {
			^this.signal(waveform,freq,tremolo)*this.envAR(att,rel,gate,doneAction);
		});

	}

	*kr {|waveform = 'off',att = 0.01, rel = 0.5, freq = 4, tremolo = 2, gate = 1, doneAction = 2|

		if(waveform == 'off', {
			^this.envAR(att,rel,gate,doneAction);
		}, {
			^this.signal(waveform,freq,tremolo)*this.envKR(att,rel,gate,doneAction);
		});

	}

	kr {|waveform = 'off',att = 0.01, rel = 0.5, freq = 4, tremolo = 2, gate = 1, doneAction = 2 |

		if(waveform == 'off', {
			^this.envAR(att,rel,gate,doneAction);
		}, {
			^this.signal(waveform,freq,tremolo)*this.envKR(att,rel,gate,doneAction);
		});

	}

	*robot {|waveform = 'off',att = 0.01, rel = 0.5, freq = 4, tremolo = 2, t = 1, tp = 0, doneAction = 0 |

		if(waveform == 'off', {
			^this.envAR(att,rel,1);
		}, {
			^this.signal(waveform,freq,tremolo)*this.envAR(att,rel,1);
		});

	}

	*signal {|waveform ,freq ,tremolo|
		var lfo,env,osc,tag,waveindex,iphase;

		osc=[nil,SinOsc,Saw,Pulse];
		tag=['off','sin','saw','pulse'];
		waveindex=tag.indexOf(waveform);

		switch(waveindex,
        nil, {
            7.do{"Only use: 'off', 'sin', 'saw' or 'pulse' as first CaosEnv argument".warn};
            lfo = nil;
        },
        0, { lfo = nil },
        1, { lfo = osc[waveindex].ar(osc[waveindex].ar([freq,freq], 0, freq, freq+tremolo), 0.75) }, 
        2, { lfo = osc[waveindex].ar(osc[waveindex].ar([freq,freq], 0, freq, freq+tremolo), 0.35) }, 
        3, {
            iphase = 0.25;
            lfo = osc[waveindex].ar(osc[waveindex].ar([freq,freq], 0, freq, freq+tremolo), iphase, 0.125);
        }
    );

		^lfo
	}

	signal {|waveform ,freq ,tremolo|
		var lfo,env,osc,tag,waveindex,iphase;
		osc=[nil,SinOsc,Saw,Pulse];
		tag=['off','sin','saw','pulse'];
		waveindex=tag.indexOf(waveform);

		switch(waveindex,
        nil, {
            7.do{"Only use: 'off', 'sin', 'saw' or 'pulse' as first CaosEnv argument".warn};
            lfo = nil;
        },
        0, { lfo = nil },
        1, { lfo = osc[waveindex].ar(osc[waveindex].ar([freq,freq], 0, freq, freq+tremolo), 0.75) }, 
        2, { lfo = osc[waveindex].ar(osc[waveindex].ar([freq,freq], 0, freq, freq+tremolo), 0.35) }, 
        3, {
            iphase = 0.25;
            lfo = osc[waveindex].ar(osc[waveindex].ar([freq,freq], 0, freq, freq+tremolo), iphase, 0.125);
        }
    );

		^lfo
	}

	*comp {|in,thresh=0.5,slopeBelow=0.5,slopeAbove=0.9,clampTime=0.01,relaxTime=0.25|

		^CompanderD.ar(LeakDC.ar(in), thresh, slopeBelow, slopeAbove, clampTime, relaxTime);

	}

	comp {|in,thresh=0.5,slopeBelow=0.5,slopeAbove=0.9,clampTime=0.01,relaxTime=0.25|

		^CompanderD.ar(LeakDC.ar(in), thresh, slopeBelow, slopeAbove, clampTime, relaxTime);

	}


	*customSignal {|func=nil,att=0.01,rel=0.5,pan=0,gate=1,doneAction=2|
		var sig,env;

		if (func != nil and: {func.isFunction}, {

			sig = func;
			sig = this.comp(sig);
			env = this.envAR(att,rel,gate,doneAction)

			^Pan2.ar(sig*env,pan);

		}, {

			^"Use of 'func' argument is obligatory and must be a Function.";

		});
	}

	customSignal {|func = nil,att= 0.01, rel= 0.5,pan=0,gate=1,doneAction=2|
		var sig,env;

		if (func != nil and: {func.isFunction}, {

			sig = func;
			sig = this.comp(sig);
			env = this.envAR(att,rel,gate,doneAction)

			^Pan2.ar(sig*env,pan);

		}, {

			^"Use of 'func' argument is obligatory and must be a Function.";
		});

	}

	*envAR {|att,rel,gate,doneAction = 2|
		^EnvGen.ar(Env.perc(att,rel),gate,doneAction:doneAction);
	}

	envAR {|att,rel,gate,doneAction = 2|
		^EnvGen.ar(Env.perc(att,rel),gate,doneAction:doneAction);
	}

	*envKR {|att,rel,gate,doneAction = 2|
		^EnvGen.kr(Env.perc(att,rel),gate,doneAction:doneAction);
	}

	envKR {|att,rel,gate,doneAction = 2|
		^EnvGen.kr(Env.perc(att,rel),gate,doneAction:doneAction);
	}

}