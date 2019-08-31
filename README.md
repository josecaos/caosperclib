# CaosPercLib v1.1
================

### CaosPercLib is Collection of instrument and percussion Classes for SuperCollider.
 
#### Use examples:
================
 - ##### Functions or SynthDefs:
   - *{CaosKick.ar()}.play*
   - *{CaosSnare.ar(0.01,0.5)}.play*
   - *(*<br/>
    &nbsp;*SynthDef(\kick,{*<br/>
      &nbsp;&nbsp;&nbsp;*var out,sig;*<br/>
      &nbsp;&nbsp;&nbsp;*sig = CaosKick.ar(0.01,0.5,2,0.1,amp1:0.85,amp2:0.35,pan:0.02);*<br/>
      &nbsp;&nbsp;&nbsp;*out = Out.ar(0,sig);*<br/>
    &nbsp;*});*<br/>
   *)*
- ##### ProxySpace:
   - *~node = {CaosKick.robot(t:2)}*

#### CaosPercLib also has a sequencer: 
================
### (CaosBox)[https://github.com/josecaos/caosbox]
