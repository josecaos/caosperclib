p.pop;
s.boot;
// function
play{CaosSnare.ar}//class
play{CaosSnare.ar(0.01,1)};
play{CaosSnare.ar(0.01,4,amp2:0.5)};
play{CaosSnare.ar(amp1:1,amp2:0)};
play{CaosSnare.ar(amp1:0,amp2:1)};
play{CaosSnare.ar(0.01,1,amp1:0,amp2:1)};
play{CaosSnare.ar(0.01,1,amp1:1,amp2:0)};
play{CaosSnare.ar(fund:60)};
play{CaosSnare.ar(fund:666)};
play{CaosSnare.ar(rq:1,amp2:0.25,fund:666)};
play{CaosSnare.ar(0.01,2,rq:1,amp2:0.25,fund:666,fundGlideTime:0.01)};
play{CaosSnare.ar(0.01,2,rq:1,amp2:0.25,fund:333,fundGlideTime:0.25)};
play{CaosSnare.ar(0.01,2,rq:1,amp2:0.25,fund:333,fundGlideTime:2)};

(// test fundGlideTime arg
    Tdef(\test,{
        o = Prand([0.125,0.25,0.5,0.75,1,1.5,2],inf).asStream;
        
        inf.do({
            play{CaosSnare.ar(0.01,o.next,rq:0.9,amp1:1,amp2:0.15,fund:170,fundGlideTime:o.next)};
            0.5.wait;
        });
        
    });
)
Tdef(\test).play;
Tdef(\test).stop;
// ///////////////
// robot method test
//ProxySpace
p = ProxySpace.push(s);
~t = 1;
~out.play;

o = Prand([0.125,0.25,0.5,0.75,1,1.5,2],inf).asStream;

~snare = {CaosSnare.robot(t:~t)};
~snare = {CaosSnare.robot(0.01,0.15,t:~t*2)};
~snare = {CaosSnare.robot(0.01,0.15,rq:0.2,t:~t*2)};
~snare = {CaosSnare.robot(0.01,0.25,rq:0.9,amp1:1,amp2:0,t:~t*2,fund:180,fundGlideTime:0.25)};
~snare = {CaosSnare.robot(0.01,o.next,rq:0.9,amp1:1,amp2:0.15,t:~t,fund:180,fundGlideTime:o.next)};

~out = ~snare;
~out = 0;
p.pop;