//written by @joseCao5
//oct_2_2013
//simple hihats
//
CaosHats {
	*ar {|att= 0.01, rel= 0.15, highcutfreq= 10000, rq= 0.5 ,gate= 1, amp1= 1, amp2= 1|
		var hats,env;
   	     		hats=Limiter.ar(RHPF.ar(WhiteNoise.ar(amp1/4)*GrayNoise.ar(amp2/4),highcutfreq,rq),0.5);
        		hats=CompanderD.ar(hats,0.6,0.6,0.8);
        		env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);
    		^Pan2.ar(hats*env,[1, -0.97]);
		}

	}
//
