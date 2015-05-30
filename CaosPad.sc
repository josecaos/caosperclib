//written by @joseCao5
//Pad of simple ambience
//Part of CaosPercLib 1.1
CaosPad {
			*ar {|waveform = 'sin', att = 0.5, rel = 1, note = 57, trem = 0, vrel = 1, iphase = 0,
						cutf = 920, rq = 0.7, gate = 0, amp = 0.5|
					var sig,filt,pad,env,osc,tag,waveindex;
						osc=[SinOsc,LFTri,Pulse];
						tag=['sin','tri','pulse'];
						if(waveform==tag[0],{waveindex=0},
								{if(waveform==tag[1],{waveindex=1},
										 {if(waveform==tag[2],{waveindex=2},
												 {8.do{"Use 'sin', 'tri' or 'pulse' only as first CaosPad argument".postln};nil})
											})
									}
							);
						if(waveform==tag[2],{iphase=0.25},{iphase=0});
						sig=osc[waveindex].ar([note.midicps,(note+trem).midicps],iphase,amp);
						filt=RLPF.ar(sig,cutf,rq);
						env=EnvGen.kr(Env.perc(att+0.5,rel+1),gate,doneAction:2)
					   ^pad=AllpassN.ar(filt*env,0.25,0.25,vrel);
			}
}
