//written by @Ill_Slide
//rougher hihats / shakes
//Part of CaosPercLib 2.0

CaosHats2 {

	*new {

		^super.new;

	}

	*ar {|att= 0.025, rel= 0.5, highcutfreq= 10000, rq= 0.5, gate= 1, amp1= 1, amp2= 1|
		var hats,env;
		hats=Limiter.ar(BPF.ar(BrownNoise.ar(amp1/2)*PinkNoise.ar(amp2/3).wrap(0.32),highcutfreq,rq),0.8);
		hats=CompanderD.ar(hats,0.5,0.49,0.7);
		env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);
		^Pan2.ar(hats*env,[1, -0.97]);

	}

	*robot {|att= 0.025, rel= 0.5, highcutfreq= 10000, rq= 0.5, amp1= 1, amp2= 1, t = 1, tp = 0 |
		var hats,env;
		hats=Limiter.ar(BPF.ar(BrownNoise.ar(amp1/2)*PinkNoise.ar(amp2/3).wrap(0.32),highcutfreq,rq),0.8);
		hats=CompanderD.ar(hats,0.5,0.49,0.7);
		env=EnvGen.ar(Env.perc(att,rel),Impulse.kr(t,tp),doneAction:0);
		^Pan2.ar(hats*env,[1, -0.97]);

	}


	*signal {|modFreq,modbw,bw,freq1,freq2,amp,lowcutfreq|

		// ^RHPF.ar(Pulse.ar(Pulse.ar(modFreq,modbw,freq1,freq2),bw,amp/4),lowcutfreq,0.95);

	}

	signal {|modFreq,modbw,bw,freq1,freq2,amp,lowcutfreq|

		// ^RHPF.ar(Pulse.ar(Pulse.ar(modFreq,modbw,freq1,freq2),bw,amp/4),lowcutfreq,0.95);

	}

}