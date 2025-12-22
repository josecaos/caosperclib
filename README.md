# CaosPercLib v1.2.2

### CaosPercLib is a Collection of percussion Classes for SuperCollider.

**Available Classes:**
- **CaosEnv** - Multi-shape envelope with tremolo helper
- **CaosKick** - Modular two-amp kick drum
- **CaosKick2** - Rough kick with wave oscillator selection
- **CaosSnare** - Simple snare drum synthesis
- **CaosSnare2** - Sharper snare drum variation
- **CaosHats** - Simple hi-hats
- **CaosHats2** - Rougher hi-hats and shakes
- **CaosBass** - Simple bass synthesis
- **CaosBass2** - Hard bass with filter sweep
- **CaosPad** - Simple ambience pad with waveform selection
- **CaosPad2** - Trippy ambience pad with FM synthesis
- **CaosChords** - Chord generator over LFPulse wave
- **CaosGuitChords** - Guitar-style chord voicing generator

--------
 - Class:
    ```
    CaosKick.ar(...args)
    ```
 - Instance:
    ```
    x = CaosKick.new()
    x.ar(...args)
    ```

 - Declare it + change it's parameters

### Use examples:
 - #### Function:
    ```
    {CaosKick.ar()}.play

    {CaosSnare.ar(0.01,0.5)}.play
    ```
 - #### SynthDef:
    ```
    (
    SynthDef(\kick,{
      var out,sig;
      sig = CaosKick.ar(0.01,0.5,2,0.1,amp1:0.85,amp2:0.35,pan:0.02);
      out = Out.ar(0,sig);
    }).add;
   )
   ```
- #### ProxySpace support with *.robot* method:
  ```
    ~node = {CaosKick.robot(t:2)}
  ```

- #### You can also use it as an envelope wrapper for custom signals, in two ways:
  - With CaosEnv:
     ```
    {LFTri.ar() * CaosEnv.ar('saw')}.play
    ```
  - With CustomSignal Method:
    ```
    var x;
    x = {LFTri.ar()}
    {CaosKick.customSignal(x,0.01,1)}.play
    ```
---------
#### Reference to Default [Timbres](test/timbres.scd)

#### CaosPercLib also has a sequencer: [CaosBox](https://github.com/josecaos/caosbox)