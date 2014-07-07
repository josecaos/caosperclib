//Written by @joseCao5
//07Jul14
//Part of CaosPercLib 0.1
//Chord generator over a pulse wave
CaosChords {
		*ar{|chord = 'M', att = 0.1, rel = 1, note = 57, iphase = 0.025, width = 0.25, cutf = 120, rq = 0.5, pan = 0, gate = 0, amp = 0.5|
			var sint,filt,env,chords,notes;
				chords=['M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', 'dim', 'aug'];
				if(chord==chords[0],{notes = [note,note+4,note+7,note+12]},
					{if(chord==chords[1],{notes = [note,note+3,note+7,note+12]},
						{if(chord==chords[2],{notes = [note,note+4,note+7,note+10]},
							{if(chord==chords[3],{notes = [note,note+3,note+7,note+10]},
								{if(chord==chords[4],{notes = [note,note+4,note+7,note+11]},
									{if(chord==chords[5],{notes = [note,note+3,note+7,note+11]},
										{if(chord==chords[6],{notes = [note,note+4,note+6,note+12]},										
											 {if(chord==chords[7],{notes = [note,note+4,note+8,note+12]},
					{8.do{"ERR: Use 'M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', 'dim' or 'aug' only as first CaosChord argument".postln}}//if none of above
							 					)};
											)};
										)};
									)};
								)};
							)};
						)};
					);
		sint=LFPulse.ar(notes.midicps,iphase,width,amp);
		filt=RLPF.ar(sint,cutf,rq);
		env=EnvGen.kr(Env.perc(att,rel),gate,doneAction:2)
		  ^Pan2.ar(filt*env,pan);					
		}
}
/*
// chords
'M' = Mix.new(note,note+4,note+7,note+12);
'm' = Mix.new(note,note+3,note+7,note+12);
'M7'=Mix.new(note,note+4,note+7,note+10);
'm7' = Mix.new(note,note+3,note+7,note+10);
'Mmaj7' = Mix.new(note,note+4,note+7,note+11);
'mmaj7' = Mix.new(note,note+3,note+7,note+11);
'dim' = Mix.new(note,note+4,note+6,note+12);
'aug' = Mix.new(note,note+4,note+8,note+12);
*/
