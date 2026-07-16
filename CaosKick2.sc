//written by @mixfuckedup
//Rough kick with two amps mix + Oscillator type selection
//Part of CaosPercLib v1.2.6

CaosKick2 : CaosEnv {
 
 	*new {

		^super.new;

	}

	*ar {|att=0.01,rel=0.5,modFreq=1,modbw=0.1,bw=0.1,freq1=60,freq2=60,lowcutfreq=40,gate=1,amp=1,pan=0,doneAction=2,amp2=0.125,oscType="LFTri"| // DEBUG: orden de 'amp2' y  en ultima posicion para no romper 'CaosBox'
		var sig,env;

		sig = this.signal(modFreq,modbw,bw,freq1,freq2,lowcutfreq,amp,amp2,oscType);
		sig = this.comp(sig);
		env = this.envAR(att,rel,gate,doneAction);

		^Pan2.ar(sig*env,pan);

	}

	ar {|att=0.01,rel=0.25,modFreq=1,modbw=0.5,bw=0.5,freq1=60,freq2=60,lowcutfreq=40,gate=1,amp=1,pan=0,doneAction=2,amp2=0.5,oscType="LFTri"|
		
		^this.class.ar(att,rel,modFreq,modbw,bw,freq1,freq2,lowcutfreq,gate,amp,pan,doneAction,amp2,oscType);

	}

	*robot {|att=0.01,rel=0.25,modFreq=1,modbw=0.5,bw=0.5,freq1=60,freq2=60,lowcutfreq=40amp=1,t=1,tp=0,pan=0,doneAction=0,amp2=0.5,oscType="LFTri"|
		var sig,env;

		sig = this.signal(modFreq,modbw,bw,freq1,freq2,lowcutfreq,amp,amp2,oscType);
		sig = this.comp(sig);
		env = this.envAR(att,rel,Impulse.kr(t,tp),doneAction);

		^Pan2.ar(sig*env,pan);
	}

	*signal {|modFreq,modbw,bw,freq1,freq2,lowcutfreq,amp,amp2,oscType|
        var sig, mod, floor;

		floor = 20;
        
        mod = Pulse.ar(modFreq, modbw, freq1, freq2).max(floor);
         
		switch(oscType,
			"LFTri", {
                mod = LFTri.ar(modFreq, 0, amp2);
            },
            "Saw", {
                mod = Saw.ar(modFreq, amp2);
            },
            "LFNoise0", {
                mod = LFNoise0.ar(modFreq, amp2);
            },
            "SinOsc", {
                mod = SinOsc.ar(modFreq, 0, amp2);
            },
            "Pulse",{ 
                mod = Pulse.ar(modFreq, 0.5, amp2);
            },
            { 
                mod = Pulse.ar(modFreq, 0.5, amp2);
            }
        );

		sig = LFTri.ar(mod, bw, amp); // fundamental

		// oscType error handling
		if(oscType.isString, {

        	sig = LeakDC.ar(sig1 + sig2);

	        ^RHPF.ar(sig, lowcutfreq, 0.99);

		}, {
	
			"CaosKick2: Invalid oscillator type, use only: 'LFTri', 'Saw', 'LFNoise0', 'SinOsc', 'Pulse' strings".warn;
	
		});

    }

    signal {|modFreq,modbw,bw,freq1,freq2,lowcutfreq,amp,amp2,oscType|
		
		^this.class.signal(modFreq,modbw,bw,freq1,freq2,lowcutfreq,amp,amp2,oscType);
	
	}

}