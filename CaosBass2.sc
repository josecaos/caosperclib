// written by @josecao5
//Simple hard Bass
//Part of CaosPercLib 1.1
CaosBass2 : CaosKick {

	*new {

		^super.new;

	}

	*ar {|att=0.1,rel=0.5,note=36,trig=1,filtminf=60,filtmaxf=2600,filtime=1,rq=0.5,bandw=0.5,iphase=0.25,gate=0,amp1=1,amp2=1,pan=#[-0.95,0.95]|
		var bass,env;

		bass = this.signal(note,trig,filtminf,filtmaxf,filtime,rq,bandw,iphase,amp1,amp2);
		env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);
		^Pan2.ar(bass*env,pan)
	}

	ar {|att=0.1,rel=0.5,note=36,trig=1,filtminf=60,filtmaxf=2600,filtime=1,rq=0.5,bandw=0.5,iphase=0.25,gate=0,amp1=1,amp2=1,pan=#[-0.95,0.95]|
		var bass,env;

		bass = this.signal(note,trig,filtminf,filtmaxf,filtime,rq,bandw,iphase,amp1,amp2);
		env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);
		^Pan2.ar(bass*env,pan)
	}

	*robot {|att=0.1,rel=0.5,note=36,trig=1,filtminf=60,filtmaxf=2600,filtime=1,rq=0.5,bandw=0.5,iphase=0.25,amp1=1,amp2=1,t=1,tp=0,pan=#[-0.95,0.95]|
		var bass,env;

		bass = this.signal(note,trig,filtminf,filtmaxf,filtime,rq,bandw,iphase,amp1,amp2);
		env=EnvGen.ar(Env.perc(att,rel),Impulse.kr(t,tp),doneAction:0);
		^Pan2.ar(bass*env,pan);

	}

	*signal {|note,trig,filtminf,filtmaxf,filtime,rq,bandw,iphase,amp1,amp2|
		var bass,filtro;

		bass=LFTri.ar(note.midicps,0,amp1/2)+LFPulse.ar(note.midicps,iphase,bandw,amp2/4);
		filtro=RLPF.ar(bass,Phasor.kr(Impulse.kr(trig),filtime,filtminf,filtmaxf),rq);
		^filtro;

	}

	signal {|note,trig,filtminf,filtmaxf,filtime,rq,bandw,iphase,amp1,amp2|
		var bass,filtro;

		bass=LFTri.ar(note.midicps,0,amp1/2)+LFPulse.ar(note.midicps,iphase,bandw,amp2/4);
		filtro=RLPF.ar(bass,Phasor.kr(Impulse.kr(trig),filtime,filtminf,filtmaxf),rq);
		^filtro;

	}

}
