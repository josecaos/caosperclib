//written by Mixfuckedup
//sharper like snare
//Part of CaosPercLib 2.0

CaosSnare2 : CaosEnv {

	*new {

		^super.new;

	}

	*ar {|att=0.01,rel=0.35,iphase=0.01,bw=0.5,highcutfreq=4920,rq=0.95,gate=1,amp1=0.5,amp2=0.4,pan=0,doneAction=2|
		var sna,env;

		sna = this.signal(iphase,bw,highcutfreq,rq,amp1,amp2);
		sna = this.comp(sna,0.4,0.39,0.7);
		env = this.envKR(att,rel,gate,doneAction);

		^Pan2.ar(sna*env,pan);

	}

	ar {|att=0.01,rel=0.35,iphase=0.01,bw=0.5,highcutfreq=2920,rq=0.95,gate=1,amp1=0.5,amp2=0.4,pan=0,doneAction=2|
		var sna,env;

		sna = this.signal(iphase,bw,highcutfreq,rq,amp1,amp2);
		sna = this.comp(sna,0.4,0.39,0.7);
		env = this.envKR(att,rel,gate,doneAction);

		^Pan2.ar(sna*env,pan);

	}

	*robot {|att= 0.01,rel= 0.35,iphase=0.01,bw=0.5,highcutfreq=220,rq=0.5,amp1=0.5,amp2=0.4,t=1,tp=0,pan=0,doneAction=0|
		var sna,env;

		sna=Limiter.ar(RHPF.ar(PinkNoise.ar(amp1)+
			LFPulse.ar(Mix(220,480,1125,2220,4218),iphase,bw,amp2/4)+
			GrayNoise.ar(amp1/1.5),highcutfreq,rq),0.7);
		sna = CompanderD.ar(sna,0.4,0.39,0.7);
		env = this.envKR(att,rel,Impulse.kr(t,tp),doneAction);

		^Pan2.ar(sna*env,pan);

	}

	*signal {|iphase,bw,highcutfreq,rq,amp1,amp2|

		^Limiter.ar(RHPF.ar(PinkNoise.ar(amp1)+
			LFPulse.ar(220,iphase,bw,amp2)+
			GrayNoise.ar(amp1),highcutfreq,rq),0.8);

	}

	signal {|iphase,bw,highcutfreq,rq,amp1,amp2|

		^Limiter.ar(RHPF.ar(PinkNoise.ar(amp1)+
			LFPulse.ar(220,iphase,bw,amp2)+
			GrayNoise.ar(amp1),highcutfreq,rq),0.8);

	}

}