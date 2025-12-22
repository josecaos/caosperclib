// written by @Ill_Slide
//Simple Bass
//Part of CaosPercLib v1.2.1
CaosBass : CaosEnv {

	*new {

		^super.new;

	}

	*ar {|att=0.1,rel=0.5,note=36,filtminf=60,filtmaxf=2600,filtime=0.2,rq=0.5,iphase=0.25,gate=1,amp1=1,amp2=1,pan=0,doneAction=2|
		var bass,env;
		bass = this.signal(note,filtminf,filtmaxf,filtime,rq,iphase,amp1,amp2);
		env = this.envKR(att,rel,gate,doneAction);

		^Pan2.ar(bass*env,pan);
	}

	ar {|att=0.1,rel=0.5,note=36,filtminf=60,filtmaxf=2600,filtime=0.2,rq=0.5,iphase=0.25,gate=1,amp1=1,amp2=1,pan=0,doneAction=2|
		var bass,env;
		bass = this.signal(note,filtminf,filtmaxf,filtime,rq,iphase,amp1,amp2);
		env = this.envKR(att,rel,gate,doneAction);

		^Pan2.ar(bass*env,pan);
	}

	*robot {|att=0.1,rel=0.5,note=36,filtminf=60,filtmaxf=2600,filtime=0.2,rq=0.5, iphase=0.25,amp1=1,amp2=1,t=1,tp=0,pan=0,doneAction=0|
		var bass,env;

		bass = this.signal(note,filtminf,filtmaxf,filtime,rq,iphase,amp1,amp2);
		env = this.envKR(att,rel,Impulse.kr(t,tp),doneAction);

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