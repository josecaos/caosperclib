//written by @mixfuckedup
//Rough kick with two amps mix + Oscillator type selection
//Part of CaosPercLib v1.2.6

CaosKick2 : CaosEnv {
 
 	*new {

		^super.new;

	}

	*ar {|att=0.01,rel=0.5,modFreq=1,modbw=0.1,bw=0.1,freq1=60,freq2=58,lowcutfreq=40,gate=1,amp=1,pan=0,doneAction=2,amp2=0.125,oscType="LFTri"| // DEBUG: orden de 'amp2' y  en ultima posicion para no romper 'CaosBox'
		var sig,env;

		sig = this.signal(modFreq,modbw,bw,freq1,freq2,lowcutfreq,amp,amp2,oscType);
		sig = this.comp(sig);
		env = this.envAR(att,rel,gate,doneAction);

		^Pan2.ar(sig*env,pan);

	}

	ar {|att=0.01,rel=0.25,modFreq=1,modbw=0.5,bw=0.5,freq1=60,freq2=62,lowcutfreq=38,gate=1,amp=1,pan=0,doneAction=2,amp2=0.5,oscType="LFTri"|
		
		^this.class.ar(att,rel,modFreq,modbw,bw,freq1,freq2,lowcutfreq,gate,amp,pan,doneAction,amp2,oscType);

	}

	*robot {|att=0.01,rel=0.25,modFreq=1,modbw=0.5,bw=0.5,freq1=60,freq2=62,lowcutfreq=38,amp=1,t=1,tp=0,pan=0,doneAction=0,amp2=0.5,oscType="LFTri"|
		var sig,env;

		sig = this.signal(modFreq,modbw,bw,freq1,freq2,lowcutfreq,amp,amp2,oscType);
		sig = this.comp(sig);
		env = this.envAR(att,rel,Impulse.kr(t,tp),doneAction);

		^Pan2.ar(sig*env,pan);
	}

	*signal {|modFreq,modbw,bw,freq1,freq2,lowcutfreq,amp,amp2,oscType|
        var sig, sig2, mod, mod2;
        
        mod = Pulse.ar(modFreq, modbw, 0.5, mul: 1, add: 1);
        mod2 = LFPulse.ar(modFreq, 0, modbw, mul: modbw, add: 1);

         switch(oscType,
            "LFTri", {
                sig = LFTri.ar(freq1 + freq2 * mod, 0.125, amp);
            },
            "Saw", {
                sig = Saw.ar(freq1 + freq2 * mod ) * amp
            },
            "LFNoise0", {
                sig = LFNoise0.ar(freq1 + freq2 * mod ) * amp
            },
            "SinOsc", {
                sig = SinOsc.ar(freq1 + freq2 * mod ) * amp
            },
            { 
                sig = Pulse.ar(freq1 + freq2 * mod, 0.5 ) * amp
            }
        );

        sig2 = LFPulse.ar(freq1 + freq2 * mod2, bw, 0.5) * amp;

        sig = LeakDC.ar(sig + sig2);

        ^RHPF.ar(sig, lowcutfreq, 0.99);
    }

    signal {|modFreq,modbw,bw,freq1,freq2,lowcutfreq,amp,amp2,oscType|
		
		^this.class.signal(modFreq,modbw,bw,freq1,freq2,lowcutfreq,amp,amp2,oscType);
	
	}

}