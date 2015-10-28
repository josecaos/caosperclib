//written by @joseCao5 + @furenku
//simple hihats
//Part of CaosPercLib 1.1
CaosHats {
	*ar {|att= 0.01, rel= 0.15, highcutfreq= 10000, rq= 0.5 ,gate= 1, amp1= 1, amp2= 1|
		var hats,env;
   	     		hats=Limiter.ar(RHPF.ar(WhiteNoise.ar(amp1/3)*GrayNoise.ar(amp2/3),highcutfreq,rq),0.9);
        		hats=CompanderD.ar(hats,0.6,0.58,0.8);
        		env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);
    		^Pan2.ar(hats*env,[1, -0.97]);
		}
*robot {|att= 0.01, rel= 0.15, highcutfreq= 10000, rq= 0.5 ,gate= 1, amp1= 1, amp2= 1, t = 1, tp = 0 |
		var hats,env;
   	     		hats=Limiter.ar(RHPF.ar(WhiteNoise.ar(amp1/3)*GrayNoise.ar(amp2/3),highcutfreq,rq),0.9);
        		hats=CompanderD.ar(hats,0.6,0.58,0.8);
		env=EnvGen.ar(Env.perc(att,rel),Impulse.kr(t,tp),doneAction:0);
    		^Pan2.ar(hats*env,[1, -0.97]);
		}
	}
