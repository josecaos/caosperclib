//written by @Ill_Slide
//simple hihats
//Part of CaosPercLib 2.0
CaosHats : CaosKick {

	*new {

		^super.new;

	}

	*ar {|att=0.01,rel=0.15,highcutfreq=10000,rq=0.5,gate=1,amp1=0.95,amp2=0.9,pan=#[-0.9,0.91]|
		var hats,env;

		hats=this.signal();
		hats=this.comp(hats,0.6,0.58,0.8);
		env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);
		^Pan2.ar(hats*env,pan);

	}

	ar {|att=0.01,rel=0.15,highcutfreq=10000,rq=0.5,gate=1,amp1=0.95,amp2=0.9,pan=#[-0.9,0.91]|
		var hats,env;

		hats=this.signal(highcutfreq,rq,amp1,amp2);
		hats=this.comp(hats,0.6,0.58,0.8);
		env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);
		^Pan2.ar(hats*env,pan);

	}

	*robot {|att=0.01,rel=0.15,highcutfreq=10000,rq=0.5,amp1=1,amp2=1,t=1,tp=0,pan=#[-0.9,0.91] |
		var hats,env;
		hats=this.signal(highcutfreq,rq,amp1,amp2)
		hats=this.comp(hats,0.6,0.58,0.8);
		env=EnvGen.ar(Env.perc(att,rel),Impulse.kr(t,tp),doneAction:0);
		^Pan2.ar(hats*env,pan);
	}


	*signal {|highcutfreq,rq,amp1,amp2|

		^Limiter.ar(RHPF.ar(WhiteNoise.ar(amp1/2)*GrayNoise.ar(amp2/2),highcutfreq,rq),0.8);

	}

	signal {|highcutfreq,rq,amp1,amp2|

		^Limiter.ar(RHPF.ar(WhiteNoise.ar(amp1/2)*GrayNoise.ar(amp2/2),highcutfreq,rq),0.8);

	}

}