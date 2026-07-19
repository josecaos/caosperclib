//written by @mixfuckedup
//simple like snare
//Part of CaosPercLib v1.2.7

CaosSnare : CaosEnv {

	*new {

		^super.new;

	}

	*ar {|att=0.01,rel=0.35,highcutfreq=180,rq=0.9,gate=1,amp1=1,amp2=1,pan=0,doneAction=2,fund=212|
		var sna,env;

		sna = this.signal(highcutfreq,rq,amp1,amp2,fund);
		sna = this.comp(sna,0.5,0.6,0.7);
		env = this.envKR(att,rel,gate,doneAction);

		^Pan2.ar(sna*env,pan);

	}

	ar {|att=0.01,rel=0.35,highcutfreq=180,rq=0.9,gate=1,amp1=1,amp2=1,pan=0,doneAction=2, fund=212|
		
		^this.class.ar(att,rel,highcutfreq,rq,gate,amp1,amp2,pan,doneAction,fund);

	}

	*robot {|att=0.01,rel=0.35,highcutfreq=180,rq=0.9,amp1=1,amp2=0.4,t=1,tp=0,pan=0,doneAction=0, fund=212|

		var sna,env;
		sna = this.signal(highcutfreq,rq,amp1,amp2,fund);
		sna = this.comp(sna,0.5,0.6,0.7);
		env = this.envKR(att,rel,Impulse.kr(t,tp),doneAction);

		^Pan2.ar(sna*env,pan);

	}


	*signal {|highcutfreq,rq,amp1,amp2,fund|

		var pitchEnv, tone, noise;

		pitchEnv = XLine.kr(fund * 1.5, fund, 0.3);
		tone = LFTri.ar(pitchEnv,0,amp2/3);
		noise = PinkNoise.ar(amp1) + GrayNoise.ar(amp1/1.75);
		
		^Limiter.ar(RHPF.ar(tone + noise,highcutfreq,rq),0.9);

	}

	signal {|highcutfreq,rq,amp1,amp2,fund|

		^this.class.signal(highcutfreq,rq,amp1,amp2,fund);

	}

}
