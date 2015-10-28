//written by @joseCao5
//Simple two amp kick
//Part of CaosPercLib 1.1
CaosKick {
	*ar {|att= 0.01, rel= 0.5, modFreq= 1, modbw= 0.1, freq1= 60, freq2= 66, lowcutfreq= 50,  gate= 0, amp1= 0.5, amp2= 0.5,doneaction=2|
		var kick,env;
   	     		kick=RHPF.ar(
				   		LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp1),
        		             lowcutfreq,0.5)+
								SinOsc.ar(Mix(60,82,280),0,amp2)+
        		               		LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp2);
        		kick=CompanderD.ar(kick,0.6,0.59,0.8);
        		env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:doneaction);
    		^Pan2.ar(kick*env,[-1,0.98]);
		}
	*robot {|att= 0.01, rel= 0.5, modFreq= 1, modbw= 0.1, freq1= 60, freq2= 66, lowcutfreq= 50,  gate= 0, amp1= 0.5, amp2= 0.5, t=1,tp=0 |
		var kick,env;
   	     		kick=RHPF.ar(
				   		LFTri.ar(Pulse.ar(t*modFreq,modbw,freq1,freq2),0,amp1),
        		             lowcutfreq,0.5)+
								SinOsc.ar(Mix(60,82,280),0,amp2)+
        		               		LFTri.ar(Pulse.ar(t*modFreq,modbw,freq1,freq2),0,amp2);
        		kick=CompanderD.ar(kick,0.6,0.59,0.8);
		env=EnvGen.ar(Env.perc(att,rel),Impulse.kr( t, tp ), doneAction:0);
    		^Pan2.ar(kick*env,[-1,0.98]);
		}
	}
