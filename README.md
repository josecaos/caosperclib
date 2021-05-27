# CaosPercLib v1.1

### CaosPercLib is Collection of instrument and percussion Classes for SuperCollider.
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
   
 - Declare it + change it's arguments (parameters)
 
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
