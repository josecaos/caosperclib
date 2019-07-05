s. boot;
// test
{CaosKick.ar(gate:1)}.play;
{CaosKick.customSignal({Pulse.ar(4)},0.1,10)}.play;
{CaosKick2.ar(gate:1)}.play;
{CaosKick2.customSignal({Pulse.ar(8)},0.1,10)}.play;
//
a = CaosKick2.new;
{a.ar}.play;
// Kick
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
//kick2
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
//snare
s.boot;
(
~y = CaosSnare.new;
Tdef(\testCustomInput3,{
	var arr = [0,0];
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
Tdef(\testCustomInput3).play;
Tdef(\testCustomInput3).stop;
//
//snare2
s.boot;
(
~y = CaosSnare2.new;
Tdef(\testCustomInput4,{
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
Tdef(\testCustomInput4).play;
Tdef(\testCustomInput4).stop;
//
s.boot;
{CaosHats.ar}.play;
{CaosHats2.ar}.play;
(
~y = CaosHats.new;
Tdef(\testCustomInput5,{
	var arr = [0,1];
	var q = Scale.minor.degrees+12;
	q = q*24;
	loop{
		var i = arr.choose;
		if(i == 0,{
			{~y.ar(0.01,0.15)}.play;
		},{
			// {~y.customSignal({WhiteNoise.ar(0.15)+LFTri.ar(Mix([q.choose,q.choose*2,q.choose*4]),0,0.25)},0.01,0.1)}.play;//si
			{CaosHats2.ar(0.01,0.15)}.play;
		});
		0.125.wait;
	}
}).quant_(1);
);
Tdef(\testCustomInput5).play;
Tdef(\testCustomInput5).stop;
value{x.ar}
//CaosBox update test
c = CaosBox;
c.enviroment;
c.guiAlpha(0.40);
c.play;
c.bpm(200);
c.freqAnalyzer(true);

b = CaosGear.new;
b.kick(50,0.01,0.15,amp1:0.85,amp2:0.35);
b.toTrack(0);
b.toTrack(Array.series(8,0,4),false);
b.toTrack(Array.series(8,2,4),true);

k = CaosGear.new;
k.kickCustom({LFTri.ar(Mix.new([60,120,240,360,480,610,860,1240,2880,5240]))*LFNoise0.kr(4)},[52,56,57,64,65]);
k.kickCustom({LFTri.ar([60,120,240,360,480,610,860,1240,2880,5240,8600,12120,14430].choose,0,0.1)*LFNoise0.kr(1)},52);
k.toTrack(Array.series(16,0,2));
k.toTrack(Array.series(8,2,4));
k.toTrack(Array.series(4,2,8));
l = CaosGear.new;
l.kick2Custom({LFTri.ar(Mix.new([60,120,240,360,480,610,860,1240,2880,5240]))*LFNoise0.kr(4)},[52,54,64,65]);
l.kick2Custom({LFTri.ar(Mix.new(60,120,240,360,480,610,860,1240,2880,5240,8600,12120,14430))*LFNoise0.kr(8)},52);
l.toTrack(Array.series(7,4,4));
l.toTrack(Array.series(4,2,8));
loff
//
t = CaosGear.new;
t.snare();
t.toTrack(24);
t.toTrack([8, 24],true);
t.toTrack([18,22,24]);

o = CaosGear.new;
o.snare2();
t.toTrack(20);
o.toTrack([8, 24]-2,true);
o.toTrack([8, 24]-6,false);

x = CaosGear.new;
x.snareCustom({LFTri.ar(Mix.new([60,120,240,360,480,610,860,1240,2880,5240]))*LFNoise0.kr(4)});
x.toTrack(Array.series(7,4,4));
//
c.fx('reverb',0.5,0.95,0.1);
c.fx('pitch',1.75);
c.autoFx(\reverb,true,\normal,Array.series(10,0.1,0.1),0.5,0.15);
c.autoFx(\pitch,true,\normal,[1,1.2,1.4,1.8,2,2.5],0,0);
c.autoFx(\delay,true,\normal,Array.series(12,0.01,0.02),0.075);
c.autoFx(\grains,true,\normal,Array.series(10,0.1,0.1).scramble,Array.series(10,0.1,0.1).scramble);
c.fx('master',1,1,0.25,0.24,0.95);
c.record;
c.openDefaults;
c.toggleMetric;
c.randomTime(true,true,[200/2,200,200*0.5,200*0.25]);
//
CaosGear.browse;