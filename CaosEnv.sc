// written by @mixfuckedup
// A multi shape LFO Envelope:
// Part of CaosPercLib v1.2.2
CaosEnv {

	*new {

		^super.new;

	}

	*ar {|waveform = 'off',att = 0.01, rel = 0.5, tremolo = 4, tremoloDepth = 2, gate = 1, doneAction = 2|

		if(waveform == 'off', {
			^this.envAR(att,rel,gate,doneAction);
		}, {
			^this.signal(waveform,tremolo,tremoloDepth)*this.envAR(att,rel,gate,doneAction);
		});

	}

	ar {|waveform = 'off',att = 0.01, rel = 0.5, tremolo = 4, tremoloDepth = 2, gate = 1, doneAction = 2|

		if(waveform == 'off', {
			^this.envAR(att,rel,gate,doneAction);
		}, {
			^this.signal(waveform,tremolo,tremoloDepth)*this.envAR(att,rel,gate,doneAction);
		});

	}

	*kr {|waveform = 'off',att = 0.01, rel = 0.5, tremolo = 4, tremoloDepth = 2, gate = 1, doneAction = 2|

		if(waveform == 'off', {
			^this.envKR(att,rel,gate,doneAction);
		}, {
			^this.signal(waveform,tremolo,tremoloDepth)*this.envKR(att,rel,gate,doneAction);
		});

	}

	kr {|waveform = 'off',att = 0.01, rel = 0.5, tremolo = 4, tremoloDepth = 2, gate = 1, doneAction = 2 |

		if(waveform == 'off', {
			^this.envKR(att,rel,gate,doneAction);
		}, {
			^this.signal(waveform,tremolo,tremoloDepth)*this.envKR(att,rel,gate,doneAction);
		});

	}

	*robot {|waveform = 'off',att = 0.01, rel = 0.5, tremolo = 4, tremoloDepth = 2, t = 1, tp = 0, doneAction = 0 |

		var gate = Impulse.kr(t, tp);
        if(waveform == 'off', {
            ^this.envKR(att, rel, gate, doneAction);
        }, {
            ^this.signal(waveform,tremolo,tremoloDepth) * this.envKR(att, rel, gate, doneAction);
        });

	}

	*signal {|waveform ,tremolo ,tremoloDepth|
		var lfo, osc, tag, waveindex, iphase, depth;

		osc=[nil,SinOsc,Saw,Pulse];
		tag=['off','sin','saw','pulse'];
		waveindex=tag.indexOf(waveform);
		depth = tremoloDepth.clip(0, 1);  // tratar tremoloDepth como “depth” segura

        switch(waveindex,
        	nil, {
        	    "Only use: 'off', 'sin', 'saw' or 'pulse' as first CaosEnv argument".warn;
        	    lfo = nil;
        	},
        	0, { lfo = nil; },
        	1, {lfo = 1 + (osc[waveindex].ar(tremolo, 0, 0.8) * depth);},
        	2, {lfo = 1 + (osc[waveindex].ar(tremolo, 0.45) * depth);},
        	3, {
        	    iphase = 0.25;
        	    lfo = (osc[waveindex].ar(tremolo, iphase, 0.15) * 2) - 1;
        	    lfo = 1 + (lfo * depth);
        	}
        );

		^lfo
	}

	signal {|waveform ,tremolo ,tremoloDepth|
		var lfo, osc, tag, waveindex, iphase, depth;

		osc=[nil,SinOsc,Saw,Pulse];
		tag=['off','sin','saw','pulse'];
		waveindex=tag.indexOf(waveform);
		depth = tremoloDepth.clip(0, 1);

        switch(waveindex,
        	nil, {
        	    "Only use: 'off', 'sin', 'saw' or 'pulse' as first CaosEnv argument".warn;
        	    lfo = nil;
        	},
        	0, { lfo = nil; },
        	1, {lfo = 1 + (osc[waveindex].ar(tremolo, 0, 0.8) * depth);},
        	2, {lfo = 1 + (osc[waveindex].ar(tremolo, 0.45) * depth);},
        	3, { 
        	    iphase = 0.25;
        	    lfo = (osc[waveindex].ar(tremolo, iphase, 0.15) * 2) - 1; 
        	    lfo = 1 + (lfo * depth);
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