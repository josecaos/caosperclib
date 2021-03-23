//written by @Ill_Slide
//rough  kick
//Part of CaosPercLib 2.0

CaosKick2 : CaosEnv {

	*ar {|att=0.01,rel=0.25,modFreq=1,modbw=0.5,bw=0.5,freq1=60,freq2=62,lowcutfreq=38, gate=1,amp=1,pan=0|
		var kick,env;

		kick = this.signal(modFreq,modbw,bw,freq1/2,freq2/2,lowcutfreq,gate,amp);
		kick = this.comp(kick);
		env = this.envKR(att,rel,gate);

		^Pan2.ar(kick*env,pan);

	}

	ar {|att=0.01,rel=0.25,modFreq=1,modbw=0.5,bw=0.5,freq1=60,freq2=62,lowcutfreq=38,gate=1,amp=1,pan=0|
		var kick,env;

		kick = this.signal(modFreq,modbw,bw,freq1,freq2,lowcutfreq,gate,amp);
		kick = this.comp(kick);
		env = this.envKR(att,rel,gate);

		^Pan2.ar(kick*env,pan);

	}

	*robot {|att=0.01,rel=0.25,modFreq=1,modbw=0.5,bw=0.5,freq1=60,freq2=62,lowcutfreq=38,amp=1,t=1,tp=0,pan=0|
		var kick,env;

		kick = this.signal(modFreq,modbw,bw,freq1,freq2,amp,lowcutfreq);
		kick = this.comp(kick);
		env = this.envKR(att,rel,Impulse.kr(t,tp),0);

		^Pan2.ar(kick*env,pan);
	}

	*signal {|modFreq,modbw,bw,freq1,freq2,amp,lowcutfreq|

		^RHPF.ar(
			Limiter.ar(LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0.5,amp) +
			LFPulse.ar(Pulse.kr(modFreq,modbw,freq1,freq2),bw,0.125,amp/2.5),0.9),
			lowcutfreq,1);
	}

	signal {|modFreq,modbw,bw,freq1,freq2,amp,lowcutfreq|

		^RHPF.ar(
			LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2) ,0.5,amp) +
			LFPulse.ar(Pulse.kr(modFreq,modbw,freq1,freq2),bw,0.125,amp/2.5)
			,lowcutfreq,
		1);
	}

}