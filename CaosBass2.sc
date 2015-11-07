// written by @josecao5
//Simple hard Bass
//Part of CaosPercLib 1.1
CaosBass2 {
		*ar {|att = 0.1, rel = 0.5, note = 36, trig = 1, filtminf = 60, filtmaxf = 2600, filtime = 1, rq = 0.5,
					bandw = 0.5, iphase = 0.25, gate = 0, amp1 = 1, amp2 = 1|
						var bass,env,filtro;
							bass=LFTri.ar(note.midicps,0,amp1/2)+LFPulse.ar(note.midicps,iphase,bandw,amp2/4);
							filtro=RLPF.ar(bass,Phasor.kr(Impulse.kr(trig),filtime,filtminf,filtmaxf),rq);
							env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);
						^Pan2.ar(filtro*env,[1, -0.9])
				}
			*robot {|att = 0.1, rel = 0.5, note = 36, trig = 1, filtminf = 60, filtmaxf = 2600, filtime = 1, rq = 0.5,
					bandw = 0.5, iphase = 0.25, gate = 0, amp1 = 1, amp2 = 1, t = 1, tp = 0|
						var bass,env,filtro;
							bass=LFTri.ar(note.midicps,0,amp1/2)+LFPulse.ar(note.midicps,iphase,bandw,amp2/4);
							filtro=RLPF.ar(bass,Phasor.kr(Impulse.kr(trig),filtime,filtminf,filtmaxf),rq);
		env=EnvGen.ar(Env.perc(att,rel),Impulse.kr(t,tp),doneAction:0);
						^Pan2.ar(filtro*env,[1, -0.9])
				}
}
