s.boot;
s.scope;
z = {LFPulse.ar(60)};
//
x = CaosKick.new;
{x.ar()}.play;
{x.customSignal(z)}.play;
//
y = CaosKick2.new;
{y.ar()}.play;
{y.customSignal(z)}.play;
//
w = CaosChords.new;
{w.ar()}.play;
{w.customSignal(z)}.play;
//
q = CaosGuitChords.new;
(
{q.ar()}.play;
{q.customSignal(z)}.play;
)
