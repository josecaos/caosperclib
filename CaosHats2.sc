//written by @Ill_Slide
//rougher hihats / shakes
//Part of CaosPercLib 2.0

CaosHats2 : CaosEnv {

	*new {

		^super.new;

	}

	*ar {|att=0.025,rel=0.5,highcutfreq=10000,rq=0.5,gate=1,amp1=1,amp2=1,pan=0|
		var hats,env;

		hats = this.signal(highcutfreq,rq,amp1,amp2);
		hats = this.comp(hats,0.5,0.49,0.7);
		env = this.envAR(att,rel,gate);
		^Pan2.ar(hats*env,pan);

	}

	ar {|att=0.025,rel=0.5,highcutfreq=10000,rq=0.5,gate=1,amp1=1,amp2=1,pan=0|
		var hats,env;

		hats = this.signal(highcutfreq,rq,amp1,amp2);
		hats = this.comp(hats,0.5,0.49,0.7);
		env = this.envAR(att,rel,gate);
		^Pan2.ar(hats*env,pan);

	}

	*robot {|att=0.025,rel=0.5,highcutfreq=10000,rq=0.5,amp1=1,amp2=1,t=1,tp=0,pan=0|
		var hats,env;

		hats = this.signal(highcutfreq,rq,amp1,amp2);
		hats = this.comp(hats,0.5,0.49,0.7);
		env = this.envAR(att,rel,Impulse.kr(t,tp));
		^Pan2.ar(hats*env,pan);

	}

	*signal {|highcutfreq,rq,amp1,amp2|

		^Limiter.ar(BPF.ar(BrownNoise.ar(amp1/1.5)*PinkNoise.ar(amp2/2).wrap(0.32),highcutfreq,rq),0.8);

	}

	signal {|highcutfreq,rq,amp1,amp2|

		^Limiter.ar(BPF.ar(BrownNoise.ar(amp1/2)*PinkNoise.ar(amp2/2.8).wrap(0.32),highcutfreq,rq),0.8);

	}

}