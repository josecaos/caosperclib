//written by @mixfuckedup
//simple like snare
//Part of CaosPercLib v1.2.7

CaosSnare : CaosEnv {

	*new {

		^super.new;

	}

	*ar {|att=0.01,rel=0.35,highcutfreq=180,rq=0.9,gate=1,amp1=1,amp2=1,pan=0,doneAction=2,fund=180,fundGlideTime=0.125|
		var sna,env;

		sna = this.signal(highcutfreq,rq,amp1,amp2,fund,fundGlideTime);
		sna = this.comp(sna,0.5,0.6,0.7);
		env = this.envKR(att,rel,gate,doneAction);

		^Pan2.ar(sna*env,pan);

	}

	ar {|att=0.01,rel=0.35,highcutfreq=180,rq=0.9,gate=1,amp1=1,amp2=1,pan=0,doneAction=2,fund=180,fundGlideTime=0.125|
		
		^this.class.ar(att,rel,highcutfreq,rq,gate,amp1,amp2,pan,doneAction,fund,fundGlideTime);

	}

	*robot {|att=0.01,rel=0.35,highcutfreq=180,rq=0.9,amp1=1,amp2=1,t=1,tp=0,pan=0,doneAction=0,fund=180,fundGlideTime=0.125|

		var sna,env;
		sna = this.signal(highcutfreq,rq,amp1,amp2,fund,fundGlideTime);
		sna = this.comp(sna,0.5,0.6,0.7);
		env = this.envKR(att,rel,Impulse.kr(t,tp),doneAction);

		^Pan2.ar(sna*env,pan);

	}


	*signal {|highcutfreq,rq,amp1,amp2,fund,fundGlideTime|

		var pitchEnv, tone, noise, resNoise, toneAmp;

		pitchEnv = XLine.kr(fund * 1.12, fund, fundGlideTime);
        toneAmp = XLine.ar(amp1, amp1 - 0.75, fundGlideTime/1.75).max(0);

        tone = LFTri.ar(pitchEnv,0,toneAmp);
		noise = Silent.ar(1) + PinkNoise.ar(amp2/1.25) + GrayNoise.ar(amp2/1.5);// Silent helps to send a UGEn value to BPF when amp2=0, cause Noises at 0 sends only a number
		resNoise = BPF.ar(noise, pitchEnv * 2, 0.7, toneAmp);

		^Limiter.ar(RHPF.ar(tone + noise + resNoise,highcutfreq,rq),0.9);

	}

	signal {|highcutfreq,rq,amp1,amp2,fund,fundGlideTime|

		^this.class.signal(highcutfreq,rq,amp1,amp2,fund,fundGlideTime);

	}

}
