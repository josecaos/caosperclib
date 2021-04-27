//written by @joseCao5
//Pad of trippy ambience
//Part of CaosPercLib 1.1
CaosPad2 :CaosEnv {

	*new {

		^super.new;

	}

	*ar {|att=0.5,rel=2,note=60,maxNote=60,fm=0.125,harm=1,gate=1,amp1=0.5,amp2=0.5,pan=0|
		var sig,env;

		sig = this.signal(att,rel,note,maxNote,fm,harm,gate,amp1,amp2);
		sig = this.comp(sig);
		env = this.envKR(att,rel,gate);

		^Pan2.ar(sig,pan);

	}

	ar {|att=0.5,rel=2,note=60,maxNote=60,fm=0.125,harm=1,gate=1,amp1=0.5,amp2=0.5,pan=0|
		var sig,env;

		sig = this.signal(att,rel,note,maxNote,fm,harm,gate,amp1,amp2);
		sig = this.comp(sig);
		env = this.envKR(att,rel,gate);

		^Pan2.ar(sig*env,pan);

	}

	*robot {|att=0.5,rel=2,note=220,maxNote=60,fm=0.125,harm=1,amp1=0.5,amp2=0.5,pan=0,t=0.25,tp=0|
		var sig,env;

		sig = this.signal(att,rel,note,maxNote,fm,harm,amp1,amp2);
		sig = this.comp(sig);
		env = this.envKR(att,rel,Impulse.kr(t,tp),0);

		^Pan2.ar(sig*env,pan);
	}

	*signal {|att,rel,note,maxNote,fm,harm,amp1,amp2|
		var sig1,sig2,notes;

		notes=[note,maxNote].midicps;
		sig1=SinOsc.ar(Pulse.kr(fm,0.25,notes[0],notes[1]),0,amp1/4);
		sig2=Blip.ar(Blip.ar(fm,harm/3,notes[0],notes[1]),harm,amp2/4);

		^sig1*sig2;

	}

	signal {|att,rel,note,maxNote,fm,harm,amp1,amp2|
		var sig1,sig2,notes;

		notes=[note,maxNote].midicps;
		sig1=SinOsc.ar(Pulse.kr(fm,0.25,notes[0],notes[1]),0,amp1/4);
		sig2=Blip.ar(Blip.ar(fm,harm/3,notes[0],notes[1]),harm,amp2/4);

		^sig1*sig2;

	}

}
