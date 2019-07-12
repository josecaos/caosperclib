// written by @Ill_Slide
//Simple Bass
//Part of CaosPercLib 2.0
CaosBass : CaosKick {

	*new {

		^super.new;

	}

	*ar {|att=0.1,rel=0.5,note=36,filtminf=60,filtmaxf=2600,filtime=0.2,rq=0.5,iphase=0.25,gate=0,amp1=1,amp2=1,pan=#[0.98,-0.98]|
		var bass,env;
		bass = this.signal(note,filtminf,filtmaxf,filtime,rq,iphase,amp1,amp2);
		env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);
		^Pan2.ar(bass*env,pan);
	}

	ar {|att=0.1,rel=0.5,note=36,filtminf=60,filtmaxf=2600,filtime=0.2,rq=0.5,iphase=0.25,gate=0,amp1=1,amp2=1,pan=#[0.98,-0.98]|
		var bass,env;
		bass = this.signal(note,filtminf,filtmaxf,filtime,rq,iphase,amp1,amp2);
		env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);
		^Pan2.ar(bass*env,pan);
	}

	*robot {|att=0.1,rel=0.5,note=36,filtminf=60,filtmaxf=2600,filtime=0.2,rq=0.5, iphase=0.25,amp1=1,amp2=1,t=1,tp=0,pan=#[0.98,-0.98]|
		var bass,env;

		bass = this.signal(note,filtminf,filtmaxf,filtime,rq,iphase,amp1,amp2);
		env=EnvGen.ar(Env.perc(att,rel),Impulse.kr(t,tp),doneAction:0);
		^Pan2.ar(bass*env,pan);

	}

	*signal {|note,filtminf,filtmaxf,filtime,rq,iphase,amp1,amp2|
		var bass, filtro;

		bass=SinOsc.ar(note.midicps,0,amp1/2)+LFSaw.ar(note.midicps,iphase,amp2/4);
		filtro=RLPF.ar(bass,XLine.kr(filtminf,filtmaxf,filtime),rq);
		^filtro;

	}

	signal {|note,filtminf,filtmaxf,filtime,rq,iphase,amp1,amp2|
		var bass, filtro;

		bass=SinOsc.ar(note.midicps,0,amp1/2)+LFSaw.ar(note.midicps,iphase,amp2/4);
		filtro=RLPF.ar(bass,XLine.kr(filtminf,filtmaxf,filtime),rq);
		^filtro;

	}

}