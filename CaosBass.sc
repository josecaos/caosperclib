// written by @josecao5
//Simple Bass
//Part of CaosPercLib 1.1
CaosBass {
	*ar {|att = 0.1, rel = 0.5, note = 36, filtminf = 60, filtmaxf = 2600, filtime = 0.2, rq = 0.5,  iphase = 0.25, gate = 0, amp1 = 1, amp2 = 1|
		var bass,env,filtro;
		bass=SinOsc.ar(note.midicps,0,amp1/2)+LFSaw.ar(note.midicps,iphase,amp2/4);
		filtro=RLPF.ar(bass,XLine.kr(filtminf,filtmaxf,filtime),rq);
		env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);
		^Pan2.ar(filtro*env,[1, -0.9])
	}
	*robot {|att = 0.1, rel = 0.5, note = 36, filtminf = 60, filtmaxf = 2600, filtime = 0.2, rq = 0.5,  iphase = 0.25, amp1 = 1, amp2 = 1, t = 1, tp = 0|
		var bass,env,filtro;
		bass=SinOsc.ar(note.midicps,0,amp1/2)+LFSaw.ar(note.midicps,iphase,amp2/4);
		filtro=RLPF.ar(bass,XLine.kr(filtminf,filtmaxf,filtime),rq);
		env=EnvGen.ar(Env.perc(att,rel),Impulse.kr(t,tp),doneAction:0);
		^Pan2.ar(filtro*env,[1, -0.9])
	}
}