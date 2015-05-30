//written by @joseCao5
//rougher hihats / shakes
//Part of CaosPercLib 1.1
CaosHats2 {
	*ar {|att= 0.05, rel= 0.2, highcutfreq= 8000, rq= 0.25, gate= 1, amp1= 1, amp2= 1|
		var hats,env;
   	     		hats=Limiter.ar(BPF.ar(BrownNoise.ar(amp1/4)*PinkNoise.ar(amp2/3).wrap(0.32),highcutfreq,rq),0.5);
        		hats=CompanderD.ar(hats,0.5,0.49,0.5);
        		env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);
    		^Pan2.ar(hats*env,[1, -0.97]);

		}

	}