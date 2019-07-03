//written by @Ill_Slide
//simple like snare
//Part of CaosPercLib 2.0

CaosSnare : CaosKick {

	*new {

		^super.new;

	}

	*ar {|att=0.01,rel=0.35,highcutfreq=310,rq=0.85,gate=1,amp1=0.4,amp2=1,pan=#[0.97, -0.98]|
		var sna,env;

		sna = this.signal(highcutfreq,rq,amp1,amp2);
		sna = this.comp(sna,0.5,0.6,0.7);
		env = EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);
		^Pan2.ar(sna*env,pan);

	}

	ar {|att=0.01,rel=0.35,highcutfreq=310,rq=0.85,gate=1,amp1=0.4,amp2=1,pan=#[0.97, -0.98]|
		var sna,env;

		sna = this.signal(highcutfreq,rq,amp1,amp2);
		sna = this.comp(sna,0.5,0.6,0.7);
		env = EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);
		^Pan2.ar(sna*env,pan);

	}

	*robot {|att=0.01,rel=0.35,highcutfreq=310,rq=0.95,amp1=0.5,amp2=0.4,t=1,tp=0,pan=#[0.97, -0.98]|

		var sna,env;
		sna = this.signal(highcutfreq,rq,amp1,amp2);
		sna = this.comp(sna,0.5,0.6,0.7);
		env = EnvGen.ar(Env.perc(att,rel),Impulse.kr(t,tp),doneAction:0);
		^Pan2.ar(sna*env,pan);

	}


	*signal {|highcutfreq,rq,amp1,amp2|

		^Limiter.ar(RHPF.ar(PinkNoise.ar(amp1)+	LFTri.ar(320,0,amp2/3)+GrayNoise.ar(amp1/1.5),highcutfreq,rq),0.7);

	}

	signal {|highcutfreq,rq,amp1,amp2|

		^Limiter.ar(RHPF.ar(PinkNoise.ar(amp1)+	LFTri.ar(320,0,amp2/3)+GrayNoise.ar(amp1/1.5),highcutfreq,rq),0.7);

	}

}
