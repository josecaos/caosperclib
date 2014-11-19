//written by @joseCao5
//aug_2_2013
//Simple kick
CaosKick {
	*ar {|modFreq= 1, modbw= 0.1, freq1= 60, freq2= 66, lowcutfreq= 50, att= 0.01, rel= 0.5, gate= 1, amp1= 0.5, amp2= 0.5|
		var kick,env;
   	     		kick=RHPF.ar(
				   		LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp1),
        		             lowcutfreq,0.5)+
								SinOsc.ar(Mix(60,82,280),0,amp2)+
        		               		LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp2);
        		kick=CompanderD.ar(kick,0.6,0.59,0.8);
        		env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);
    		^Pan2.ar(kick*env,[-1,0.98]);

		}
	}
