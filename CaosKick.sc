//written by @mixfuckedup
//Simple two amp kick
//Part of CaosPercLib v1.2.3

CaosKick : CaosEnv {

	*new {

		^super.new;

	}

	*ar {|att=0.01,rel=0.5,modFreq=1,modbw=0.5,freq1=60,freq2=60,lowcutfreq=40,gate=1,amp1=0.75,amp2=0.25,pan=0,doneAction=2|
		var sig,env;

		sig = this.signal(modFreq,modbw,freq1,freq2,lowcutfreq,amp1,amp2);
		sig = this.comp(sig);
		env = this.envKR(att,rel,gate,doneAction);

		^Pan2.ar(sig*env,pan);
	}

	ar {|att= 0.01,rel=0.5,modFreq=1,modbw=0.5,freq1=60,freq2=62,lowcutfreq=40,gate=1,amp1=0.75, amp2=0.25,pan=0,doneAction=2|
		var sig,env;

		sig = this.signal(modFreq,modbw,freq1,freq2,lowcutfreq,amp1,amp2);
		sig = this.comp(sig);
		env = this.envKR(att,rel,gate,doneAction);

		^Pan2.ar(sig*env,pan);
	}

	*robot {|att=0.01,rel=0.25,modFreq=2,modbw=0.5,freq1=60,freq2=60,lowcutfreq=58,amp1=0.75,amp2=0.25,pan=0,t=1,tp=0,doneAction=0|
		var sig,env,trig;

		trig = Impulse.kr(t,tp);
		sig = this.signalRobot(modFreq,modbw,freq1,freq2,lowcutfreq,amp1,amp2,trig);
		sig = this.comp(sig);
		env = this.envKR(att,rel,trig,doneAction);

		^Pan2.ar(sig*env,pan);

	}

	*signal {|modFreq,modbw,freq1,freq2,lowcutfreq,amp1,amp2|
    var mod, sig1, sig2, sig, floor;
    
	floor = 20;
	mod = Pulse.ar(modFreq, modbw,freq1,freq2).max(floor);
    sig1 = LFTri.ar(mod, 0, amp1);
    sig2 = LFTri.ar(mod, 0, amp2);
    sig = LeakDC.ar(sig1 + sig2);
    
    ^RHPF.ar(sig, lowcutfreq, 1);
}

	signal {|modFreq,modbw,freq1,freq2,lowcutfreq,amp1,amp2|
    var mod, sig1, sig2, sig, floor;
     
	 floor = 20;
	mod = Pulse.ar(modFreq, modbw,freq1,freq2).max(floor);
    sig1 = LFTri.ar(mod, 0, amp1);
    sig2 = LFTri.ar(mod, 0, amp2);
    sig = LeakDC.ar(sig1 + sig2);
    
    ^RHPF.ar(sig, lowcutfreq, 1);
}

	*signalRobot {|modFreq,modbw,freq1,freq2,lowcutfreq,amp1,amp2,trig=0|
    var mod, sig1, sig2, sig, phase;

    phase = Phasor.ar(trig, modFreq / SampleRate.ir, 0, 1, 0);
    mod   = (phase < modbw) * 2 - 1;

    sig1 = LFTri.ar(freq1 * mod, 0, amp1);
    sig2 = LFTri.ar(freq2 * mod, 0, amp2);
    sig  = LeakDC.ar(sig1 + sig2);

    ^RHPF.ar(sig, lowcutfreq, 0.95);
}

}
