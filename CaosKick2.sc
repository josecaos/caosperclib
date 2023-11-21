//written by @Ill_Slide
//Rough kick
//Part of CaosPercLib 2.0

CaosKick2 : CaosEnv {


	*ar {|att=0.01,rel=0.5,modFreq=1,modbw=0.1,bw=0.1,freq1=60,freq2=60,lowcutfreq=38, gate=1,amp=1,pan=0,doneAction=2,amp2=0.5,wave="LFNoise0"| // DEBUG: orden de 'amp2' y  en ultima posicion para no romper 'CaosBox'
		var kick,env;

		kick = this.signal(modFreq,modbw,bw,freq1,freq2,lowcutfreq,gate,amp,amp2,wave);
		kick = this.comp(kick);
		env = this.envKR(att,rel,gate,doneAction);

		^Pan2.ar(kick*env,pan);

	}

	ar {|att=0.01,rel=0.25,modFreq=1,modbw=0.5,bw=0.5,freq1=60,freq2=62,lowcutfreq=38,gate=1,amp=1,pan=0,doneAction=2,amp2=0.5,wave="LFNoise0"|
		var kick,env;

		kick = this.signal(modFreq,modbw,bw,freq1,freq2,lowcutfreq,gate,amp2,wave);
		kick = this.comp(kick);
		env = this.envKR(att,rel,gate,doneAction);

		^Pan2.ar(kick*env,pan);

	}

	*robot {|att=0.01,rel=0.25,modFreq=1,modbw=0.5,bw=0.5,freq1=60,freq2=62,lowcutfreq=38,amp=1,t=1,tp=0,pan=0,doneAction=0,amp2=0.5,wave="LFNoise0"|
		var kick,env;

		kick = this.signal(modFreq,modbw,bw,freq1,freq2,amp,amp2,lowcutfreq,wave);
		kick = this.comp(kick);
		env = this.envKR(att,rel,Impulse.kr(t,tp),doneAction);

		^Pan2.ar(kick*env,pan);
	}

	*signal {|modFreq,modbw,bw,freq1,freq2,amp, amp2, lowcutfreq, wave|

		var sig,sig2;
TODO: wave debe ser string y mistteriosamente  es un float 0.5
		switch(wave,
			"LFTri",{
				sig = LFTri.ar(Pulse.r(modFreq,modbw,freq1,freq2),0.5,amp/4);
				"LFTri".postcln;
			},
			"Saw",{
				sig = Saw.ar(Pulse.ar(modFreq,modbw,freq1,freq2),amp/4);
				"SAW".postcln;
			},
			"LFNoise0",{
				sig = LFNoise0.ar(Pulse.ar(modFreq,modbw,freq1,freq2),amp/6);
			},
			"SinOsc",{
				sig = SinOsc.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp/2);
				"SINOSC".postcln;
			},
			{
				sig = SinOsc.ar(Pulse.ar(modFreq,modbw,freq1,freq2),0,amp/2);
				"DEFAULT".postcln;
			}
		);

		sig2 = LFPulse.ar(Pulse.kr(modFreq,modbw,freq1*0.24,freq2*48),bw,0.125,amp2/16);

		// ^"Debug OK #nat";
		^RHPF.ar(sig + sig2, lowcutfreq,1);
	}

	signal {|modFreq,modbw,bw,freq1,freq2,amp,amp2 lowcutfreq|

		^RHPF.ar(
			LFTri.ar(Pulse.ar(modFreq,modbw,freq1,freq2) ,0.5,amp/4) +
			LFPulse.ar(LFPulse.kr(modFreq,modbw,freq1,freq2),bw,0.125,amp2/16), lowcutfreq,1);
	}

}