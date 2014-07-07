//Written by @joseCao5
//07Jul14
//Part of CaosPercLib 0.1
//Chord generator as common Guitar disposition over 2 triangular waves
CaosGuitChords {
		*ar{|chord = 'mmaj7', att = 0.1, rel = 1, note = 57, cutf = 120, rq = 0.5, pan = 0, gate = 0, amp = 0.5|
			var sint,filt,env,chords,notes;
			chords=['M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', 'M9', 'M9m', 'm9', 'm9m'];
			if(chord==chords[0],{notes = Mix.new(note,note+7,note+12,note+16,note+19,note+24);},
				{if(chord==chords[1],{notes = Mix.new(note,note+7,note+12,note+15,note+19,note+24);},
					{if(chord==chords[2],{notes = Mix.new(note,note+7,note+10,note+16,note+19,note+24);},
						{if(chord==chords[3],{notes = Mix.new(note,note+7,note+10,note+15,note+19,note+24);},
							{if(chord==chords[4],{notes = Mix.new(note,note+7,note+11,note+16,note+19,note+24);},
								{if(chord==chords[5],{notes = Mix.new(note,note+7,note+11,note+15,note+19,note+24);},
									{if(chord==chords[6],{notes = Mix.new(note,note+7,note+14,note+16,note+19,note+24);},										
										 {if(chord==chords[7],{notes = Mix.new(note,note+7,note+13,note+16,note+19,note+24);},
											{if(chord==chords[8],{notes = Mix.new(note,note+7,note+14,note+15,note+19,note+24);},										
										 		{if(chord==chords[9],{notes = Mix.new(note,note+7,note+13,note+15,note+19,note+24);},											 
					{8.do{"Please Use 'M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', 'dim' or 'aug' only as first CaosChord argument".postln}}//if none of above
							 					)};
											)};
										)};
									)};
								)};
							)};
						)};			
					)};
				)};
			);
		sint=LFTri.ar(note.midicps,0,amp)+LFTri.ar(notes,0,amp/1.5);
		filt=RLPF.ar(sint,cutf,rq);
		env=EnvGen.kr(Env.perc(att,rel),gate,doneAction:2)
		  ^Pan2.ar(filt*env,pan);					
		}
}
/*
// chords as Guitar disposition
'M' = Mix.new(note,note+7,note+12,note+16,note+19,note+24);//mayor third on second octave
'm' = Mix.new(note,note+7,note+12,note+15,note+19,note+24);//minor third on second octave
'M7'= Mix.new(note,note+7,note+10,note+16,note+19,note+24);
'm7' = Mix.new(note,note+7,note+10,note+15,note+19,note+24);
'Mmaj7' = Mix.new(note,note+7,note+11,note+16,note+19,note+24);
'mmaj7' = Mix.new(note,note+7,note+11,note+15,note+19,note+24);
'M9' = Mix.new(note,note+7,note+14,note+16,note+19,note+24);//Mayor M9th
'M9m' = Mix.new(note,note+7,note+13,note+16,note+19,note+24);//Mayor m9th
'm9' = Mix.new(note,note+7,note+14,note+15,note+19,note+24);//minor M9th
'm9m' = Mix.new(note,note+7,note+13,note+15,note+19,note+24);//minor m9th
*/
