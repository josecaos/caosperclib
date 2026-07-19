//written by @mixfuckedup
//kick with two amps mix + Oscillator type selection
//Part of CaosPercLib v1.2.7

CaosKick2 : CaosEnv {
 
 	*new {

		^super.new;

	}

	*ar {|att=0.01,rel=0.5,modFreq=1,modbw=0.1,bw=0.1,freq1=60,freq2=60,lowcutfreq=40,gate=1,amp=1,pan=0,doneAction=2,amp2=0.75,oscType="Pulse"|
		var sig,env;

		sig = this.signal(modFreq,modbw,bw,freq1,freq2,lowcutfreq,amp,amp2,oscType);
		sig = this.comp(sig);
		env = this.envAR(att,rel,gate,doneAction);

		^Pan2.ar(sig*env,pan);

	}

	ar {|att=0.01,rel=0.25,modFreq=1,modbw=0.5,bw=0.5,freq1=60,freq2=60,lowcutfreq=40,gate=1,amp=1,pan=0,doneAction=2,amp2=0.75,oscType="Pulse"|
		
		^this.class.ar(att,rel,modFreq,modbw,bw,freq1,freq2,lowcutfreq,gate,amp,pan,doneAction,amp2,oscType);

	}

	*robot {|att=0.01,rel=0.25,modFreq=1,modbw=0.5,bw=0.5,freq1=60,freq2=60,lowcutfreq=40,amp=1,t=1,tp=0,pan=0,doneAction=0,amp2=0.75,oscType="Pulse"|
		var sig,env, trig;

		trig = Impulse.kr(t,tp.wrap(0,1));
		sig = this.signalRobot(modFreq,modbw,bw,freq1,freq2,lowcutfreq,amp,amp2,oscType,trig);
		sig = this.comp(sig);
		env = this.envAR(att,rel,trig,doneAction);

		^Pan2.ar(sig*env,pan);
	}

	*signal {|modFreq,modbw,bw,freq1,freq2,lowcutfreq,amp,amp2,oscType|
        var sig, sig1, sig2, mod, floor;

		floor = 20;

		mod = Pulse.ar(modFreq, modbw, freq1, freq2).max(floor);
        
		switch(oscType,
			"LFTri", {sig2 = LFTri.ar(mod, 0, amp2)},
            "Saw", {sig2 = Saw.ar(mod, amp2)},
            "LFNoise0", {sig2 = LFNoise0.ar(mod, amp2)},
            "SinOsc", {sig2 = SinOsc.ar(mod, 0, amp2)},
            "Pulse",{sig2 = Pulse.ar(mod, 0.5, amp2)},
            {// default 
                sig2 = Pulse.ar(mod, 0.5, amp2);
				"CaosKick2: Wrong oscType value.\n\nUsing default secondary oscillator 'Pulse'. \n\nUse only: 'LFTri', 'Saw', 'LFNoise0', 'SinOsc', 'Pulse' strings".warn;
            }
        );

		sig1 = SinOsc.ar(mod, bw, amp); // Fundamental
		
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

	*signalRobot{|modFreq,modbw,bw,freq1,freq2,lowcutfreq,amp,amp2,oscType,trig=0|
		var sig, sig1, sig2, mod, phase, low, high, floor;

		floor = 20;
		low = freq2 - freq1;
		high = freq2 + freq1;

		phase = Phasor.ar(trig, modFreq / SampleRate.ir, 0, 1, 0);
		mod = Select.ar(phase < modbw, [DC.ar(low), DC.ar(high)]).max(floor);

		switch(oscType,
			"LFTri", {sig2 = LFTri.ar(mod, 0, amp2)},
            "Saw", {sig2 = Saw.ar(mod, amp2)},
            "LFNoise0", {sig2 = LFNoise0.ar(mod, amp2)},
            "SinOsc", {sig2 = SinOsc.ar(mod, 0, amp2)},
            "Pulse",{sig2 = Pulse.ar(mod, 0.5, amp2)},
            {// default 
                sig2 = Pulse.ar(mod, 0.5, amp2);
				"CaosKick2: Wrong oscType value.\n\nUsing default secondary oscillator 'Pulse'. \n\nUse only: 'LFTri', 'Saw', 'LFNoise0', 'SinOsc', 'Pulse' strings".warn;
            }
        );


		sig1 = SinOsc.ar(mod, bw, amp);
		
		if(oscType.isString, {

        	sig = LeakDC.ar(sig1 + sig2);

	        ^RHPF.ar(sig, lowcutfreq, 0.99);

		}, {
	
			"CaosKick2: Invalid oscillator type, use only: 'LFTri', 'Saw', 'LFNoise0', 'SinOsc', 'Pulse' strings".warn;
	
		});

	}

}