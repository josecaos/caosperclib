//written by @joseCao5
//Simple two amp kick
//Part of CaosPercLib 2.0

CaosKick {

	*new {

		^super.new;

	}

	*ar {|att= 0.01, rel= 0.5, modFreq= 1, modbw= 0.1, freq1= 60, freq2= 66, lowcutfreq= 50,  gate= 0, amp1= 0.75, amp2= 0.75|
		var kick,env;

		kick=this.signal(modFreq,modbw,freq1,freq2,amp1,amp2,lowcutfreq);
		kick=this.comp(kick);
		env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);

		^Pan2.ar(kick*env,[-1,0.98]);
	}

	inputSignal {|func = nil,att= 0.01, rel= 0.5|
		var kick,env;

		if (func != nil, {

			kick=func;
			kick=this.comp(kick);
			func.postcln;
			env=EnvGen.ar(Env.perc(att,rel),1,doneAction:2);

			^Pan2.ar(kick*env,[-97,1]);

		}, {

			^"Use of 'func' argument is obligatory";
		});
	}

	*robot {|att= 0.01, rel= 0.25, modFreq= 2, modbw= 0.5, freq1= 60, freq2= 64, lowcutfreq= 50, amp1= 0.75, amp2= 0.75, t= 1,tp= 0 |
		var kick,env;
		kick=this.signal(modFreq,modbw,freq1,freq2,amp1,amp2,lowcutfreq);
		kick=this.comp(kick);
		env=EnvGen.ar(Env.perc(att,rel),Impulse.kr(t,tp),doneAction:0);
		^Pan2.ar(kick*env,[-1,0.98]);
	}

	*comp {|in|

		^CompanderD.ar(in,0.5,0.5,0.9,0.01,0.25);

	}

	comp {|in|

		^CompanderD.ar(in,0.5,0.5,0.9,0.01,0.25);

	}

	*signal {|modFreq,modbw,freq1,freq2,amp1,amp2,lowcutfreq|

		^RHPF.ar(LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp1/2)+
			SinOsc.ar(Mix(60,120, 180, 300, 480),0,amp2/1.75)+
			LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp2/2),lowcutfreq,0.85);

	}

	signal {|modFreq,modbw,freq1,freq2,amp1,amp2,lowcutfreq|

		^RHPF.ar(LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp1/2)+
			SinOsc.ar(Mix(60,120, 180, 300, 480),0,amp2/1.75)+
			LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp2/2),lowcutfreq,0.85);

	}

}
