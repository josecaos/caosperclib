//written by @Ill_Slide
//simple like snare
//Part of CaosPercLib 2.0

CaosSnare : CaosEnv {

	*new {

		^super.new;

	}

	*ar {|att=0.01,rel=0.35,highcutfreq=310,rq=0.85,gate=1,amp1=0.4,amp2=1,pan=0,doneAction=2,fund=212|
		var sna,env;

		sna = this.signal(highcutfreq,rq,amp1,amp2,fund);
		sna = this.comp(sna,0.5,0.6,0.7);
		env = this.envKR(att,rel,gate,doneAction);

		^Pan2.ar(sna*env,pan);

	}

	ar {|att=0.01,rel=0.35,highcutfreq=310,rq=0.85,gate=1,amp1=0.4,amp2=1,pan=0,doneAction=2, fund=212|
		var sna,env;

		sna = this.signal(highcutfreq,rq,amp1,amp2,fund);
		sna = this.comp(sna,0.5,0.6,0.7);
		env = this.envKR(att,rel,gate,doneAction);

		^Pan2.ar(sna*env,pan);

	}

	*robot {|att=0.01,rel=0.35,highcutfreq=310,rq=0.95,amp1=0.5,amp2=0.4,t=1,tp=0,pan=0,doneAction=0, fund=212|

		var sna,env;
		sna = this.signal(highcutfreq,rq,amp1,amp2,fund);
		sna = this.comp(sna,0.5,0.6,0.7);
		env = this.envKR(att,rel,Impulse.kr(t,tp),doneAction);

		^Pan2.ar(sna*env,pan);

	}


	*signal {|highcutfreq,rq,amp1,amp2,fund|

		^Limiter.ar(RHPF.ar(PinkNoise.ar(amp1)+	LFTri.ar(fund,0,amp2/3)+GrayNoise.ar(amp1/1.5),highcutfreq,rq),0.7);

	}

	signal {|highcutfreq,rq,amp1,amp2,fund|

		^Limiter.ar(RHPF.ar(PinkNoise.ar(amp1)+	LFTri.ar(fund,0,amp2/4)+GrayNoise.ar(amp1/1.5),highcutfreq,rq),0.7);

	}

}
