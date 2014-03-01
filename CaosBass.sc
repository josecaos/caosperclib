// written @josecao5
//28_feb_14
//simple percussive bass line
// bass note are set in MIDI notes
CaosBass {
	*ar {|att = 0.1, rel = 0.5, nota = 36, filtminf = 60, filtmaxf = 2600, filtime = 0.2, rq = 0.5,  iphase = 0.25, gate = 0, amp1 = 1, amp2 = 1|
		var bass,env,filtro;
			bass=SinOsc.ar(nota.midicps,0,amp1/2) + LFSaw.ar(nota.midicps,iphase,amp2/4);
			filtro=RLPF.ar(bass,XLine.kr(filtminf,filtmaxf,filtime),rq);
			env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);
		^Pan2.ar(filtro*env,[1, -0.9])
				}
		// control rate bass aun experimental		
	*kr {|att = 0.1, rel = 0.5, freq = 4, filtminf = 60, filtmaxf = 2600, filtime = 0.25, gate = 1, modfreq1 = 10, modfreq2 = 20 |
		var bass,env,filtro;
			bass=LFSaw.kr(freq,0.5,modfreq1,modfreq2);
			filtro=LPF.kr(bass,XLine.kr(filtminf,filtmaxf,filtime),0.3);
			env=EnvGen.kr(Env.perc(att,rel),gate,doneAction:2);
		^filtro*env
				}
}
