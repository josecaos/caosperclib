//written by @joseCao5
//Pad of trippy ambience
//Part of CaosPercLib 1.1
CaosPad2 :CaosKick {

	*new {

		^super.new;

	}

	*ar {|att=0.5,rel=1,note=60,maxNote=1,fm=0.125,harm=1,rmix=0.5,rroom=0.75,rfilt=0.25,gate=1,amp1=0.5,amp2=0.5,pan=#[1, -0.976]|
		var sig;

		sig = this.signal(att,rel,note,maxNote,fm,harm,rmix,rroom,rfilt,gate,amp1/3,amp2/3);
		sig = this.comp(sig);
		^Pan2.ar(sig,pan);

	}

	ar {|att=0.5,rel=1,note=60,maxNote=1,fm=0.125,harm=1,rmix=0.5,rroom=0.75,rfilt=0.25,gate=1,amp1=0.5,amp2=0.5,pan=#[1, -0.976]|
		var sig;

		sig = this.signal(att,rel,note,maxNote,fm,harm,rmix,rroom,rfilt,gate,amp1/3,amp2/3);
		sig = this.comp(sig);
		^Pan2.ar(sig,pan);

	}

	*robot {|att=0.5,rel=1,note=220,maxNote=1,fm=0.125,harm=1,rmix=0.75,rroom=0.5,rfilt=0.1,amp1=0.5,amp2=0.5,pan=#[-0.5,0.5],t=0.25,tp=0|
		var sig,env;

		sig = this.signal(att,rel,note,maxNote,fm,harm,rmix,rroom,rfilt,amp1/3,amp2/3);
		sig = this.comp(sig);
		env=EnvGen.kr(Env.perc(att,rel),Impulse.kr(t,tp),doneAction:0);

		^Pan2.ar(sig*env,[1, -0.976]);
	}

	*signal {|att,rel,note,maxNote,fm,harm,rmix,rroom,rfilt,gate,amp1,amp2|
		var sig1,sig2,pad,notes,env;

		notes=[note,maxNote].midicps;
		sig1=SinOsc.ar(Pulse.kr(fm,0.25,notes[0],notes[1]),0,amp1);
		sig2=Blip.ar(Blip.kr(fm,harm/3,notes[0],notes[1]),harm,amp2);
		env=EnvGen.kr(Env.perc(att,rel+1),gate,doneAction:2);
		pad=FreeVerb2.ar(sig1*env,sig2*env,rmix,rroom,rfilt);
		^Mix([pad]);

	}

	signal {|att,rel,note,maxNote,fm,harm,rmix,rroom,rfilt,gate,amp1,amp2|
		var sig1,sig2,pad,notes,env;

		notes=[note,maxNote].midicps;
		sig1=SinOsc.ar(Pulse.kr(fm,0.25,notes[0],notes[1]),0,amp1);
		sig2=Blip.ar(Blip.kr(fm,harm/3,notes[0],notes[1]),harm,amp2);
		env=EnvGen.kr(Env.perc(att,rel+1),gate,doneAction:2);
		pad=FreeVerb2.ar(sig1*env,sig2*env,rmix,rroom,rfilt);
		^Mix([pad]);

	}

}
