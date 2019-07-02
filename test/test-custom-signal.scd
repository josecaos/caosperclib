s. boot;
// test
{CaosKick.ar(gate:1)}.play;
{CaosKick.customSignal({Pulse.ar(4)},0.1,10)}.play;//no
{CaosKick2.ar(gate:1)}.play;
{CaosKick2.customSignal({Pulse.ar(8)},0.1,10)}.play;//no
//
a = CaosKick2.new;
{a.ar}.play;
//
s. boot;
(
x = CaosKick.new;//instancia
Tdef(\testCustomInput,{
	var arr = [0,1];
	loop{
		var i = arr.choose;
		var snare = Scale.minor.degrees+12;
		snare = snare*24;
		if(i == 0,{
			{x.ar(gate:1)}.play;
		},{
			{x.customSignal({WhiteNoise.ar(0.5)+LFTri.ar(Mix([160,176]))},0.01,0.1)}.play;//si
		});
		0.125.wait;
	}

}).quant_(4);
);
Tdef(\testCustomInput).play;
Tdef(\testCustomInput).stop;
//
s.boot;
(
~y = CaosKick2.new;
Tdef(\testCustomInput2,{
	var arr = [0,1];
	var snare = Scale.minor.degrees+12;
	snare = snare*24;
	loop{
		var i = arr.choose;
		if(i == 0,{
			{~y.ar(0.01,0.15,gate:1)}.play;
		},{
			{~y.customSignal({WhiteNoise.ar(0.5)+LFTri.ar(Mix(snare.choose,320,snare.choose*2))},0.01,0.1)}.play;//si
		});
		0.125.wait;
	}

}).quant_(4);
);
Tdef(\testCustomInput2).play;
Tdef(\testCustomInput2).stop;
//
value{x.ar}
//
c = CaosBox;
c.enviroment;
c.guiAlpha(0.40);
c.play;
c.bpm(200);
c.freqAnalyzer(true);
k = CaosGear.new;
k.kickCustom({LFTri.ar(Mix.new(60,120,240,360,480,610,860,1240,2880,5240))*LFNoise0.kr(4)},52);
k.kickCustom({LFTri.ar(Mix.new(60,120,240,360,480,610,860,1240,2880,5240,8600,12120,14430))*LFNoise0.kr(4)},52);
k.toTrack(Array.series(16,0,2),true);


c.fx('reverb',0.5,0.95,0.1);
c.fx('master',arg3:0.25,arg4:0.24,arg5:0.95);