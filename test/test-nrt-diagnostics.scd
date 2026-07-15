// NRT (non-realtime) diagnostic tools for CaosPercLib signals.
// Renders a UGen graph offline (no audio hardware/server boot needed) and
// inspects peak amplitude over time — useful to compare envelope/modulation
// behavior between two versions of a `signal` method without trusting your ears alone.
//
// Usage: edit the SynthDef function in `renderNRT` below, then run the two
// blocks in order (render, then analyze).

(
~nrtOutPath = "tmp_nrt_render.wav".resolveRelative; // resolves next to this script, i.e. test/tmp_nrt_render.wav
~nrtDur = 4;

// Renders `defFunc` (a Function taking no args, building the UGen graph and
// calling Out.ar) to ~nrtOutPath. `synthArgs` is an array like [\modFreq, 2, \t, 1]
// passed to the synth at creation time.
~renderNRT = { |defFunc, synthArgs = #[], dur = 4|
	var def, score;
	~nrtDur = dur;

	def = SynthDef(\nrtTest, defFunc);

	score = Score([
		[0.0, ['/d_recv', def.asBytes]],
		[0.01, ['/s_new', \nrtTest, 1000, 0, 0] ++ synthArgs],
		[dur, [0, 0]]
	]);

	score.recordNRT(
		outputFilePath: ~nrtOutPath,
		sampleRate: 44100,
		headerFormat: "WAV",
		sampleFormat: "float",
		options: ServerOptions.new,
		duration: dur + 0.2,
		action: {
			"NRT render done -> %".format(~nrtOutPath).postln;
			0.exit;
		}
	);
};
)

// Example: render CaosKick.robot with default args
(
~renderNRT.(
	{ |out=0, modFreq=2, t=1|
		var sig = CaosKick.robot(t:t, modFreq:modFreq, modbw:0.5, freq1:60, freq2:60, lowcutfreq:58, amp1:0.75, amp2:0.25, pan:0);
		Out.ar(out, sig);
	},
	[\modFreq, 2, \t, 1],
	4
);
)

// Prints peak amplitude per time window (default 20ms) for the last render.
// Run this in a SEPARATE sclang invocation after the render above finishes
// (the render block calls 0.exit when done).
(
~analyzeNRT = { |path = nil, windowSec = 0.02, maxSec = nil|
	var sf, data, n, sr, chunkSize, chunks;
	path = path ?? { ~nrtOutPath };

	sf = SoundFile.openRead(path);
	n = sf.numFrames;
	sr = sf.sampleRate;
	data = FloatArray.newClear(n * sf.numChannels);
	sf.readData(data);
	sf.close;

	chunkSize = (sr * windowSec).asInteger;
	chunks = (n / chunkSize).asInteger;

	chunks.do{ |i|
		var startF = i * chunkSize;
		var endF = min(startF + chunkSize, n);
		var maxV = 0;
		(startF..(endF-1)).do{ |f|
			var v = data[f * sf.numChannels].abs;
			if(v > maxV) { maxV = v };
		};
		if(maxSec.isNil or: { (startF/sr) < maxSec }) {
			("t=% s  peak=%".format((startF/sr).round(0.001), maxV.round(0.001))).postln;
		};
	};

	("max abs sample overall: %".format(data.abs.maxItem)).postln;
	0.exit;
};

~analyzeNRT.(nil, 0.02, 1.35);
)
