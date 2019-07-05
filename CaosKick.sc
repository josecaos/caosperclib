//written by @Ill_Slide
//Simple two amp kick
//Part of CaosPercLib 2.0

CaosKick {

	*new {

		^super.new;

	}

	*ar {|att= 0.01, rel= 0.5, modFreq= 1, modbw= 0.1, freq1= 60, freq2= 66, lowcutfreq= 50,  gate= 0, amp1= 0.75, amp2= 0.75,pan=#[-0.95,0.94]|
		var kick,env;

		kick = this.signal(modFreq,modbw,freq1,freq2,amp1,amp2,lowcutfreq);
		kick = this.comp(kick);
		env = EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);

		^Pan2.ar(kick*env,pan);
	}

	ar {|att= 0.01, rel= 0.5, modFreq= 1, modbw= 0.1, freq1= 60, freq2= 66, lowcutfreq= 50,  gate=1, amp1= 0.75, amp2= 0.75,pan=#[-0.95,0.94]|
		var kick,env;

		kick = this.signal(modFreq,modbw,freq1,freq2,amp1,amp2,lowcutfreq);
		kick = this.comp(kick);
		env = EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);

		^Pan2.ar(kick*env,pan);
	}

	*customSignal {|func = nil,att= 0.01, rel= 0.5,pan=#[-0.95,0.94]|
		var kick,env;

		if (func != nil, {

			kick = func;
			kick = this.comp(kick);
			env = EnvGen.ar(Env.perc(att,rel),1,doneAction:2);

			^Pan2.ar(kick*env,pan);

		}, {

			^"Use of 'func' argument is obligatory";

		});
	}

	customSignal {|func = nil,att= 0.01, rel= 0.5,pan=#[-0.95,0.94]|
		var kick,env;

		if (func != nil, {

			kick = func;
			kick = this.comp(kick);
			env = EnvGen.ar(Env.perc(att,rel),1,doneAction:2);

			^Pan2.ar(kick*env,pan);

		}, {

			^"Use of 'func' argument is obligatory";
		});
	}

	*robot {|att=0.01,rel=0.25,modFreq=2,modbw=0.5,freq1= 60,freq2= 64,lowcutfreq= 50,amp1= 0.75, amp2= 0.75,pan=#[-0.95,0.94],t=1,tp=0|
		var kick,env;

		kick = this.signal(modFreq,modbw,freq1,freq2,amp1,amp2,lowcutfreq);
		kick = this.comp(kick);
		env = EnvGen.ar(Env.perc(att,rel),Impulse.kr(t,tp),doneAction:0);

		^Pan2.ar(kick*env,pan);

	}

	*comp {|in,tresh=0.5,slopeBelow=0.5,slopeAbove=0.9,clampTime=0.01,relaxTime=0.25|

		^CompanderD.ar(in,tresh,slopeBelow,slopeAbove,clampTime,relaxTime);

	}

	comp {|in,tresh=0.5,slopeBelow=0.5,slopeAbove=0.9,clampTime=0.01,relaxTime=0.25|

		^CompanderD.ar(in,tresh,slopeBelow,slopeAbove,clampTime,relaxTime);

	}

	*signal {|modFreq,modbw,freq1,freq2,amp1,amp2,lowcutfreq|

		^RHPF.ar(LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp1/2)+
			LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp2/2),lowcutfreq,0.85);

	}

	signal {|modFreq,modbw,freq1,freq2,amp1,amp2,lowcutfreq|

		^RHPF.ar(LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp1/2)+
			LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp2/2),lowcutfreq,0.85);

	}

}
