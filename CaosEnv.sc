// written by @josecao5
//A multi shape LFO Envelope:
//Part of CaosPercLib 2.0
CaosEnv {

	*new {

		^super.new;

	}

	*ar {|waveform = 'off',att = 0.01, rel = 0.5, freq = 4, tremolo = 2, gate = 0 |

		^this.signal(waveform,freq,tremolo)*this.envAR(att,rel,1);

	}

	ar {|waveform = 'off',att = 0.01, rel = 0.5, freq = 4, tremolo = 2, gate = 0 |

		^this.signal(waveform,freq,tremolo)*this.envAR(att,rel,1);

	}

	*kr {|waveform = 'off',att = 0.01, rel = 0.5, freq = 4, tremolo = 2, gate = 1 |

		^this.signal(waveform,freq,tremolo)*this.envKR(att,rel,1);

	}

	kr {|waveform = 'off',att = 0.01, rel = 0.5, freq = 4, tremolo = 2, gate = 1 |

		^this.signal(waveform,freq,tremolo)*this.envKR(att,rel,1);

	}

	*robot {|waveform = 'off',att = 0.01, rel = 0.5, freq = 4, tremolo = 2, t = 1, tp = 0 |

		^this.signal(waveform,freq,tremolo)*this.envKR(att,rel,Impulse.kr(t,tp));

	}

	*signal {|waveform ,freq ,tremolo|
		var lfo,env,osc,tag,waveindex,iphase;
		osc=[SinOsc,LFTri,Pulse];
		tag=['off','sin','tri','pulse'];
		waveindex=tag.find([waveform]);

		if(waveindex==nil,{7.do{"Only use: ''off, 'sin', 'tri' or 'pulse' as first CaosEnv argument".warn}});
		if(waveform==tag[2],{iphase=0.25},{iphase=0});
		lfo=osc[waveindex].ar(osc[waveindex].ar([freq,freq],0,freq,freq+tremolo),iphase,0.5);

		^lfo
	}

	*comp {|in,tresh=0.5,slopeBelow=0.5,slopeAbove=0.9,clampTime=0.01,relaxTime=0.25|

		^CompanderD.ar(in,tresh,slopeBelow,slopeAbove,clampTime,relaxTime);

	}

	comp {|in,tresh=0.5,slopeBelow=0.5,slopeAbove=0.9,clampTime=0.01,relaxTime=0.25|

		^CompanderD.ar(in,tresh,slopeBelow,slopeAbove,clampTime,relaxTime);

	}


	*customSignal {|func = nil,att= 0.01, rel= 0.5,pan=0|
		var kick,env;

		if (func != nil and: {func.isFunction}, {

			kick = func;
			kick = this.comp(kick);
			env = this.envAR(att,rel,1)

			^Pan2.ar(kick*env,pan);

		}, {

			^"Use of 'func' argument is obligatory";

		});
	}

	customSignal {|func = nil,att= 0.01, rel= 0.5,pan=0|
		var kick,env;

		if (func != nil and: {func.isFunction}, {

			kick = func;
			kick = this.comp(kick);
			env = EnvGen.ar(Env.perc(att,rel),1,doneAction:2);

			^Pan2.ar(kick*env,pan);

		}, {

			^"Use of 'func' argument is obligatory";
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