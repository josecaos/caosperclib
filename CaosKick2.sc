//written by @Ill_Slide
//rough  kick
//Part of CaosPercLib 2.0
CaosKick2 : CaosKick {

	*ar {|att= 0.01,rel= 0.5,modFreq= 1,modbw= 0.1,bw= 0.1,freq1= 60,freq2= 62,lowcutfreq= 50, gate= 1, amp=1,pan=#[-9,0.9]|
		var kick,env;

		kick = this.signal(modFreq,modbw,bw,freq1,freq2,lowcutfreq,gate,amp);
		kick = this.comp(kick);
		env = EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);

		^Pan2.ar(kick*env,pan);

	}

	ar {|att= 0.01,rel= 0.5,modFreq= 1,modbw= 0.1,bw= 0.1,freq1= 60,freq2= 62,lowcutfreq= 50, gate= 1, amp=1,pan=#[-9,0.9]|
		var kick,env;

		kick = this.signal(modFreq,modbw,bw,freq1,freq2,lowcutfreq,gate,amp);
		kick = this.comp(kick);
		env = EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);

		^Pan2.ar(kick*env,pan);

	}

	*robot {|att= 0.01, rel= 0.5, modFreq= 1, modbw= 0.1, bw= 0.1, freq1= 60, freq2= 62, lowcutfreq= 50, amp= 1, t=1, tp=0,pan=#[-0.9,0.91] |
		var kick,env;
		kick = this.signal(modFreq,modbw,bw,freq1,freq2,amp,lowcutfreq);
		kick = this.comp(kick);
		env=EnvGen.ar(Env.perc(att,rel),Impulse.kr(t,tp),doneAction:0);
		^Pan2.ar(kick*env,pan);
	}

	*signal {|modFreq,modbw,bw,freq1,freq2,amp,lowcutfreq|

		^RHPF.ar(Pulse.ar(	Pulse.ar(modFreq,modbw,freq1,freq2),bw,amp/4),lowcutfreq,0.95);

	}

	signal {|modFreq,modbw,bw,freq1,freq2,amp,lowcutfreq|

		^RHPF.ar(Pulse.ar(Pulse.ar(modFreq,modbw,freq1,freq2),bw,amp/4),lowcutfreq,0.95);

	}

}
