//written by @joseCao5
//Pad of trippy ambience
//Part of CaosPercLib 1.1
CaosPad2 :CaosEnv {

	*new {

		^super.new;

	}

	*ar {|att=0.5,rel=2,note=60,maxNote=1,fm=0.125,harm=1,rmix=0.5,rroom=0.75,rfilt=0.25,gate=1,amp1=0.5,amp2=0.5,pan=0|
		var sig,env;

		sig = this.signal(att,rel,note,maxNote,fm,harm,rmix,rroom,rfilt,gate,amp1/3,amp2/3);
		sig = this.comp(sig);
		env = this.envKR(att,rel,gate);

		^Pan2.ar(sig,pan);

	}

	ar {|att=0.5,rel=2,note=60,maxNote=1,fm=0.125,harm=1,rmix=0.5,rroom=0.75,rfilt=0.25,gate=1,amp1=0.5,amp2=0.5,pan=0|
		var sig,env;

		sig = this.signal(att,rel,note,maxNote,fm,harm,rmix,rroom,rfilt,gate,amp1/3,amp2/3);
		sig = this.comp(sig);
		env = this.envKR(att,rel,gate);

		^Pan2.ar(sig*env,pan);

	}

	*robot {|att=0.5,rel=2,note=220,maxNote=1,fm=0.125,harm=1,rmix=0.75,rroom=0.5,rfilt=0.1,amp1=0.5,amp2=0.5,pan=0,t=0.25,tp=0|
		var sig,env;

		sig = this.signal(att,rel,note,maxNote,fm,harm,rmix,rroom,rfilt,amp1/3,amp2/3);
		sig = this.comp(sig);
		env = this.envKR(att,rel,Impulse.kr(t,tp),0);

		^Pan2.ar(sig*env,pan);
	}

	*signal {|att,rel,note,maxNote,fm,harm,amp1,amp2|
		var sig1,sig2,pad,notes,env;

		notes=[note,maxNote].midicps;
		sig1=SinOsc.ar(Pulse.kr(fm,0.25,notes[0],notes[1]),0,amp1);
		sig2=Blip.ar(Blip.kr(fm,harm/3,notes[0],notes[1]),harm,amp2);

		^sig1+sig2;

	}

	signal {|att,rel,note,maxNote,fm,harm,amp1,amp2|
		var sig1,sig2,pad,notes,env;

		notes=[note,maxNote].midicps;
		sig1=SinOsc.ar(Pulse.kr(fm,0.25,notes[0],notes[1]),0,amp1);
		sig2=Blip.ar(Blip.kr(fm,harm/3,notes[0],notes[1]),harm,amp2);

		^sig1+sig2;

	}

}
