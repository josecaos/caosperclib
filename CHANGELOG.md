# CaosPercLib v1.2.3

### TODOs:
1. Pulse.ar(modFreq,modbw,freq1,freq2) - Mismo error que en CaosKick
Problema: Pulse.ar solo acepta 3 argumentos: (freq, width, mul, add)
Estás pasando 4: modFreq, modbw, freq1, freq2
Este error se repite en TODAS las formas de onda del switch
2. sig2 tiene el mismo problema y peor:
Pulse.kr(modFreq,modbw,freq1*0.24,freq2*48) - 4 argumentos a Pulse
Los valores freq1*0.24 y freq2*48 están mal escalados (uno muy bajo, otro muy alto)
3. Falta LeakDC
Debería tener LeakDC antes del filtro: LeakDC.ar(sig + sig2)
4. TODO sin resolver
Si recibes float en lugar de string, hay un bug en cómo llamas al método desde otro lugar (probablemente CaosBox)
5. Inconsistencia de método ar (clase vs instancia)
*ar usa envKR  → debería usar envAR
ar (instancia) usa envKR → debería usar envAR
---

### Changelog

- ## v1.2.3
    - CaosKick2

- ## v1.2.2
    - README.md
        - Add Available Classes section
    - CaosEnv
        - Add Documentation files for Help Browser
        - Update 'customSignal' method to accept Functions or UGENs as non optional input signal
    - CaosKick
        - Revamp 'signal' method for better timbre control
        - Update Documentation files for Help Browser
    - Update version number in all files

- ## v1.2.1
    - CaosEnv
        - Update Compander to avoid DC offset by adding LeakDC.ar to input signal
        - Fixed: Gate ignored in various methods
        - Fixed: envAR method use kr instead of ar
        - Fixed: customSignal method structure to properly use gate and doneAction parameters
        - Fixed: Signal method has wrong Warning message
        - Signal method update evaluations to 'switch' for better performance
        - Fixed: comp method bad Argument tresh breaks API
        - Signal method update to use indexOf(waveform)
        - Revamp LFO Signal for simpler and lighter performance
        - Improved robot method
        - Arguments updated in 'signal' for semantic consistency

- ## v1.2.0
    - General improvements and refactoring of all classes

- ## Previous versions Changelog is lost