//written by Mixfuckedup
//Simple two amp kick
//Part of CaosPercLib 2.0

CaosKick : CaosEnv {

	*new {

		^super.new;

	}

	*ar {|att=0.01,rel=0.5,modFreq=1,modbw=0.5,freq1=60,freq2=60,lowcutfreq=58,gate=1,amp1=1,amp2=1,pan=0,doneAction=2|
		var kick,env;

		kick = this.signal(modFreq,modbw,freq1,freq2,amp1,amp2,lowcutfreq);
		kick = this.comp(kick);
		env = this.envKR(att,rel,gate,doneAction);

		^Pan2.ar(kick*env,pan);
	}

	ar {|att= 0.01,rel=0.5, modFreq=1,modbw=0.5,freq1=60,freq2=60,lowcutfreq=58, gate=1,amp1=1, amp2=1,pan=0,doneAction=2|
		var kick,env;

		kick = this.signal(modFreq,modbw,freq1,freq2,amp1,amp2,lowcutfreq);
		kick = this.comp(kick);
		env = this.envKR(att,rel,gate,doneAction);

		^Pan2.ar(kick*env,pan);
	}

	*robot {|att=0.01,rel=0.25,modFreq=2,modbw=0.5,freq1=60,freq2=60,lowcutfreq=58,amp1=1, amp2=1,pan=0,t=1,tp=0,doneAction=0|
		var kick,env;

		kick = this.signal(modFreq,modbw,freq1,freq2,amp1,amp2,lowcutfreq);
		kick = this.comp(kick);
		env = EnvGen.ar(Env.perc(att,rel),Impulse.kr(t,tp));
		// env = this.envkR(att,rel,Impulse.kr(t,tp),doneAction);

		^Pan2.ar(kick*env,pan);

	}

	*signal {|modFreq,modbw,freq1,freq2,amp1,amp2,lowcutfreq|

		^RHPF.ar(LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp1)+
			LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp2),lowcutfreq,0.98);
	}

	signal {|modFreq,modbw,freq1,freq2,amp1,amp2,lowcutfreq|

		^RHPF.ar(LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp1)+
			LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp2),lowcutfreq,0.98);

	}

}
