# Documentación Técnica del Proyecto de Juego de Batalla

## 1. Detalle de los Módulos Desarrollados

### 1.1 Módulo: Player (Jugador)
Descripción: Representa al jugador en el juego, incluyendo atributos básicos como puntos de vida (HP), puntos de ataque, defensa y la capacidad de usar habilidades especiales.
Métodos Principales:
attack(): Realiza un ataque básico contra el enemigo.
useSpecialAbility(): Activa una habilidad especial, consumiendo puntos o turnos específicos.
defend(): Aumenta temporalmente la defensa contra el próximo ataque enemigo.
transform(): Cambia al estado transformado del jugador, aumentando sus estadísticas.
Detalles Técnicos: Este módulo utiliza encapsulamiento para mantener la integridad de los datos del jugador.

### 1.2 Módulo: Enemy (Enemigo)
Descripción: Define las características y comportamientos del enemigo que el jugador enfrentará.
Métodos Principales:
attack(): Realiza un ataque básico contra el jugador.
useDepletionSpell(): Reduce temporalmente las estadísticas del jugador.
Detalles Técnicos: La lógica del enemigo se adapta dinámicamente a la situación, utilizando probabilidad para decidir sus acciones.

### 1.3 Módulo: Item (Ítems)
Descripción: Representa objetos que el jugador puede recolectar y usar durante las batallas para mejorar su rendimiento.
Atributos:
type: Tipo de ítem (ataque, defensa, salud).
value: Valor numérico del efecto del ítem.
Métodos Principales:
generateRandomItem(): Genera ítems aleatorios para las batallas.
Detalles Técnicos: Se utilizó una enumeración (enum) para definir los tipos de ítems.
### 1.4 Módulo: BattleController (Controlador de Batallas)
Descripción: Gestiona la lógica de las batallas, incluyendo los turnos, las acciones permitidas y las condiciones de victoria/derrota.
Métodos Principales:
startBattle(): Inicializa la batalla entre el jugador y el enemigo.
processTurn(): Controla las acciones de cada turno.
calculateDamage(): Determina el daño infligido basado en las estadísticas actuales.
Detalles Técnicos: Se implementó un bucle principal que gestiona los turnos hasta que se cumple una condición de finalización (ganar/perder).
### 1.5 Módulo: GameController (Controlador del Juego)
Descripción: Gestiona el flujo principal del juego, desde la selección del personaje hasta el registro de puntuaciones.
Métodos Principales:
selectCharacter(): Permite al jugador elegir un personaje.
manageGameFlow(): Controla las transiciones entre las diferentes fases del juego (inicio, batalla, final).
endGame(): Finaliza el juego y registra la puntuación.
Detalles Técnicos: Este módulo integra todos los demás módulos y sirve como punto de entrada para el programa.

### 1.6 Módulo: GameRecordManager (Gestor de Puntuaciones)
Descripción: Maneja el almacenamiento y recuperación de las puntuaciones de los jugadores.
Métodos Principales:
saveScore(): Guarda la puntuación del jugador en un archivo.
getTopScores(): Recupera las mejores puntuaciones registradas.
Detalles Técnicos: Se empleó la clase Files del paquete java.nio.file para operaciones con archivos, asegurando compatibilidad y eficiencia.

## 2. Tecnologías Empleadas

- Lenguaje de Programación: Java.
 Justificación: Java ofrece portabilidad, es orientado a objetos y tiene un ecosistema robusto para desarrollar juegos simples.
- Entorno de Desarrollo: Visual Studio Code.
 Justificación: Es un editor de código ligero, extensible y con soporte para depuración, integración con Git, y herramientas específicas para Java mediante extensiones como Language Support for Java y Debugger for Java.
- Arquitectura del Software: Modelo-Vista-Controlador (MVC).
 Justificación: MVC permite separar las responsabilidades del juego, facilitando la escalabilidad y el mantenimiento del código. En este caso:
- Modelo: Incluye la lógica de negocio, como los módulos Player, Enemy, y Item.
- Vista: Representa la interfaz del juego que interactúa con el usuario.
- Controlador: Maneja la interacción entre el modelo y la vista, como BattleController y GameController.
- Bibliotecas Utilizadas:
 java.util.Random: Para generar elementos aleatorios como ítems y decisiones del enemigo.
 java.nio.file.Files: Para manejo de archivos de puntuaciones.
- Control de Versiones: Git.
 Justificación: Permite un seguimiento detallado de los cambios y facilita la colaboración entre desarrolladores.
- Estrategias de Diseño:
- Encapsulación: Todos los atributos son privados, con acceso mediante getters y setters.
- Modularidad: Cada módulo tiene una responsabilidad específica, lo que facilita la mantenibilidad.

## 3. Justificación de Decisiones Técnicas

### 3.1 Uso de Orientación a Objetos
Se optó por un diseño orientado a objetos para:

Garantizar la reutilización del código.
Facilitar la extensión del juego mediante la adición de nuevos personajes, enemigos o ítems.
Mejorar la legibilidad y el mantenimiento del código.
### 3.2 Selección del Lenguaje
Java fue elegido por su portabilidad y la facilidad para implementar estructuras modulares, así como por sus capacidades para manejar errores de manera robusta.

### 3.3 Manejo de Archivos
Se utilizó java.nio.file.Files en lugar de java.io debido a su rendimiento mejorado y soporte para operaciones modernas, como el manejo de flujos y directorios.

### 3.4 Gestión de la Lógica del Juego
La lógica del juego se separó en varios controladores (BattleController, GameController) para garantizar un flujo bien estructurado y evitar dependencias innecesarias entre clases.

### 3.5 Arquitectura MVC
Se adoptó la arquitectura Modelo-Vista-Controlador para separar las responsabilidades en tres capas bien definidas:

Modelo: Gestión de datos y lógica de negocio.
Vista: Representación de la salida del juego.
Controlador: Intermediario entre el modelo y la vista.

### 3.6 Decisiones sobre Interactividad
Se priorizó una experiencia de usuario fluida mediante un menú interactivo, ofreciendo retroalimentación clara sobre las decisiones del jugador y el estado del juego.