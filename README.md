 # Mancala 



El juego de Mancala, conocido en algunas regiones como Kalaha, pertenece a una familia de juegos de tablero tradicionales con raíces africanas y una amplia difusión. Estos juegos comparten elementos comunes, como un tablero con hoyos o receptáculos y fichas o "semillas", y su mecanismo de juego se denomina "siembra". La designación "kalaha" podría tener relación con su uso frecuente en comunidades del desierto del Kalahari.

Hallazgos arqueológicos han revelado evidencia del juego en diversas partes del mundo, incluyendo Israel, Eritrea y Etiopía, con dataciones que abarcan desde el siglo II hasta el siglo VII d.C. Además, se ha sugerido que el juego pudo haber sido mencionado en textos antiguos, como "Misterios del Cielo y de la Tierra" del siglo XIV, escrito por Giyorgis de Segla.

El juego de Mancala se popularizó en varias regiones de Europa, como el Báltico, Bosnia, Serbia y Grecia. Sin embargo, en Europa occidental, nunca logró gran aceptación y fue documentado por eruditos como Thomas Hyde de la Universidad de Oxford.

En los Estados Unidos, Mancala ha tenido una presencia más notoria, con versiones tradicionales como "Warra" jugadas en Luisiana a principios del siglo XX. En la década de 1940, una versión comercial conocida como "Kalah" se volvió popular. Además, se menciona que el juego se introdujo en los Estados Unidos a través de comunidades caboverdianas en Nueva Inglaterra.
El juego de Mancala es apreciado por su simplicidad y profundidad estratégica, y se ha adaptado en diversas culturas a lo largo de la historia, manteniendo su atractivo como un pasatiempo intemporal.

![MancalaJuegoPNG](https://github.com/MateoPonti/Mancala/blob/main/mancala.jpg?raw=true)


------------------------------------------------------------------------------------------------

* Reglas del juego de Mancala*



*  Cómo mover las habas*

El jugador al que le toca mover, pulsa sobre un agujero (de la fila más cercana a él) que contenga al menos un haba. Mediante esta acción tomará todas las habas del agujero seleccionado y las colocará una por una en los siguientes agujeros siguiendo el sentido anti horario. En esta diseminación de las habas, la zona "casa" del jugador (al lado derecho del tablero desde su punto de vista) se emplea también y cuando un haba es colocada en ella, el jugador gana un punto. Los jugadores no pueden colocar habas dentro de la zona "casa" del adversario. 
Si la última haba (del movimiento actual) se coloca sobre la zona "casa", el jugador continúa seleccionando otro agujero que no esté vacío. 

* Cómo capturar las habas *

Si la última haba (del movimiento actual) se coloca sobre un agujero vacío (en el lado del jugador), todas las habas de la misma columna de la fila opuesta son capturadas y colocadas en la zona "casa" del jugador. 

Si la última haba (del movimiento actual) se coloca sobre un agujero vacío (en el lado del jugador), todas las habas de la misma columna de la fila opuesta son capturadas y colocadas en la zona "casa" del jugador. 


* Cómo se finaliza la partida *

La partida termina si uno de los jugadores no puede realizar ningún movimiento legal - no hay habas en su fila. Cuando esto ocurre, se suman todas las habas que quedan dentro de los agujeros del rival al marcador del rival. El jugador con el mayor número de puntos es el ganador.

------------------------------------------------------------------------------------------------

# Acerca de la implementación

El juego, que se encuentra implementado en la rama Master del repositorio, fue realizado en Java (jdk 18.0.2) con el editor de código  IntelliJ IDEA.


# Jugar al juego localmente

Si se quiere jugar al juego localmente simplemente debe ejecutar el Main ( se encuentra en src/principal/Main.java). 

# Conexión con RMI
Para poder jugar Mancala con RMI se deberá ejecutar una instancia del AppServidor ( se encuentra en src/server/servidor/AppServidor.java) y previo a ésto dos instancias del AppCliente( se encuentra en src/server/cliente/AppCliente.java).
Para poder ejecutar dos instancias de la misma clase en intellij para probar dos clientes en una maquina, se debe agregar una nueva configuracion en el "Run/Debug Configurattions" con otro nombre. Por ejemplo "AppClienteDos" y configurar el build and run para que ejecute "server.cliente.AppCliente".





-----------------------------------------------------------------------------------------------------------------------------



## Autor


- [@MateoPonti](https://www.github.com/MateoPonti)


  
## 🚀 Acerca de mí

Estudiante de Sistemas de Informacion en la Universidad Nacional de Luján 

  



