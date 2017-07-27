//written by @joseCao5
//Simple two amp kick
//Part of CaosPercLib 1.1
CaosKick {
	*ar {|att= 0.01, rel= 0.5, modFreq= 1, modbw= 0.1, freq1= 60, freq2= 66, lowcutfreq= 50,  gate= 0, amp1= 0.75, amp2= 0.75, doneaction= 2|
		var kick,env;
		kick=this.signal(modFreq,modbw,freq1,freq2,amp1,amp2,lowcutfreq);
		kick=this.comp(kick);
		env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:doneaction);
		^Pan2.ar(kick*env,[-1,0.98]);
	}

	*robot {|att= 0.01, rel= 0.25, modFreq= 2, modbw= 0.5, freq1= 60, freq2= 64, lowcutfreq= 50, amp1= 0.75, amp2= 0.75, t= 1,tp= 0 |
		var kick,env;
		kick=this.signal(modFreq,modbw,freq1,freq2,amp1,amp2,lowcutfreq);
		kick=this.comp(kick);
		env=EnvGen.ar(Env.perc(att,rel),Impulse.kr(t,tp),doneAction:0);
		^Pan2.ar(kick*env,[-1,0.98]);
	}

	*signal {|modFreq,modbw,freq1,freq2,amp1,amp2,lowcutfreq|

		^RHPF.ar(LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp1/2),lowcutfreq,0.5)+
		SinOsc.ar(Mix(60,82,280),0,amp2/2)+
		LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp2/3);

	}

	*comp {|in|

		^CompanderD.ar(in,0.5,0.59,0.8,0.01,0.52);
	}

	*grain {|in,trigger=1,dur=0.25|

		/* var x = GrainFM.ar(2,Impulse.kr(trigger),dur,720,500,1);
		^thisThread*x */
		^in*GrainFM.ar(2,Impulse.kr(trigger),dur,720,500,1);

	}

}





/*
CaosKick {
*ar {|att= 0.01, rel= 0.5, modFreq= 1, modbw= 0.1, freq1= 60, freq2= 66, lowcutfreq= 50,  gate= 0, amp1= 0.75, amp2= 0.75, doneaction= 2|
var kick,env;
kick=RHPF.ar(
LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp1/2),
lowcutfreq,0.5)+
SinOsc.ar(Mix(60,82,280),0,amp2/2)+
LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp2/3);
kick=CompanderD.ar(kick,0.6,0.59,0.8,0.01,0.52);
env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:doneaction);
^Pan2.ar(kick*env,[-1,0.98]);
}
*robot {|att= 0.01, rel= 0.25, modFreq= 2, modbw= 0.5, freq1= 60, freq2= 64, lowcutfreq= 50, amp1= 0.75, amp2= 0.75, t= 1,tp= 0 |
var kick,env;
kick=RHPF.ar(
SinOsc.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp1/2),
lowcutfreq,0.75)+
SinOsc.ar(Mix(60,82,280),0,amp2/2)+
LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp2/3);
kick=CompanderD.ar(kick,0.5,0.59,0.8,0.01,0.52);
env=EnvGen.ar(Env.perc(att,rel),Impulse.kr(t,tp),doneAction:0);
^Pan2.ar(kick*env,[-1,0.98]);
}
}
*/