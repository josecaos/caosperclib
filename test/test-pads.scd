s.boot;
//
{CaosPad.ar(\sin,2,4,57,0)}.play;
{CaosPad.ar(\tri,2,4,57,7)}.play;
{CaosPad.ar(\pulse,2,4,57,2,0)}.play;//sonido +  warning: \pulse con fase 0
{CaosPad.ar(\x,2,4,57,2,0.99)}.play;// Error de waveform, sin sonido
(//custom
var x = {LFNoise0.ar(8)};
{CaosPad.customSignal(x)}.play;
);
(//custom
var x = {LFNoise0.ar(32)};
{CaosPad2.customSignal(x)}.play;
);
//
s.boot;
{CaosPad2.ar(0.01,18,36,82,0.1,1,1,1,0.1,1,1,0.25,0.1)}.play;
{CaosPad2.ar(1,18,26,94,0.1,1,1,1,0.1,1,1,0.25,0.1)}.play;
(//custom
var x = {LFNoise0.ar(440)};
{CaosPad2.customSignal(x)}.play;
);
