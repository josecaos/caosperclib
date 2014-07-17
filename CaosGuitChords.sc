//Written by @joseCao5
//07Jul14
//Part of CaosPercLib 1.0
//Chord generator as common Guitar disposition
CaosGuitChords {
		*ar{|chord = 'M', att = 0.1, rel = 1, note = 57, cutf = 1200, rq = 0.5, pan = 0, gate = 0, amp = 0.5|
			var sint,filt,env;
			var notes,chords,ton,third,fifth,seventh,oct,octfifth;
			chords=['M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', 'M9', 'M9m', 'm9', 'm9m'];
			if(chord==chords[0],{notes = [note,note+7,note+12,note+16,note+19,note+24]},
				{if(chord==chords[1],{notes = [note,note+7,note+12,note+15,note+19,note+24]},
					{if(chord==chords[2],{notes = [note,note+7,note+10,note+16,note+19,note+24]},
						{if(chord==chords[3],{notes = [note,note+7,note+10,note+15,note+19,note+24]},
							{if(chord==chords[4],{notes = [note,note+7,note+11,note+16,note+19,note+24]},
								{if(chord==chords[5],{notes = [note,note+7,note+11,note+15,note+19,note+24]},
									{if(chord==chords[6],{notes = [note,note+7,note+14,note+16,note+19,note+24]},
										 {if(chord==chords[7],{notes = [note,note+7,note+13,note+16,note+19,note+24]},
											{if(chord==chords[8],{notes = [note,note+7,note+14,note+15,note+19,note+24]},
										 		{if(chord==chords[9],{notes = [note,note+7,note+13,note+15,note+19,note+24]},
					{10.do{"ERR: Use 'M', 'm', 'M7', 'm7', 'Mmaj7', 'mmaj7', 'M9',  'M9m', 'm9' or 'm9m' only as first CaosChord.ar argument".postln}}//if none of above
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
		ton=notes[0];
		third=notes[3];
		fifth=notes[1];
		seventh=notes[2];
		octfifth=notes[4];
		oct=notes[5];
		sint=LFTri.ar(notes.midicps,0,amp/2.3)+Pulse.ar(fifth.midicps,0.1,amp/2.4)+Pulse.ar(seventh.midicps,0.1,amp/2.3)+
							Pulse.ar(third.midicps,0.3,amp/2.4)+Pulse.ar(octfifth.midicps,0.2,amp/2.6)+Pulse.ar(oct.midicps,0.1,amp/2.6);
		filt=LPF.ar(sint,cutf,rq);
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
