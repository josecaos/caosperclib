//written by @joseCao5
//simple like snare
//Part of CaosPercLib 1.1
CaosSnare {
	*ar {|att= 0.01, rel= 0.35, highcutfreq= 520, rq= 0.75, gate= 1, amp1= 1, amp2= 1|
		var sna,env;
   	     		sna=Limiter.ar(RHPF.ar(PinkNoise.ar(amp1)+
   	     									LFTri.ar(Mix(220,480,1125,2220,4218),0,amp2/2)+
   	     										GrayNoise.ar(amp1/1.5),520,0.75),0.7);
        		sna=CompanderD.ar(sna,0.5,0.6,0.7);
        		env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);
    		^Pan2.ar(sna*env,[1, -0.98]);
		}


*robot {|att= 0.01, rel= 0.35, highcutfreq= 520, rq= 0.75, gate= 1, amp1= 1, amp2= 1, t=1|
		var sna,env;
   	     		sna=Limiter.ar(RHPF.ar(PinkNoise.ar(amp1)+
   	     									LFTri.ar(Mix(220,480,1125,2220,4218),0,amp2/2)+
   	     										GrayNoise.ar(amp1/1.5),520,0.75),0.7);
        		sna=CompanderD.ar(sna,0.5,0.6,0.7);
		env=EnvGen.ar(Env.perc(att,rel),Impulse.kr(t),doneAction:0);
    		^Pan2.ar(sna*env,[1, -0.98]);
		}


}
