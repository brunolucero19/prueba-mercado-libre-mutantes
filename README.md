<h1>Parcial de DSW- Proyecto Detección de Mutantes</h1>
Éste proyecto tiene cómo finalidad resolver una prueba técnica de MercadoLibre que tiene la siguiente premisa: 
<br>
<br>
Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar
contra los X-Mens.
Te ha contratado a ti para que desarrolles un proyecto que detecte si un
humano es mutante basándose en su secuencia de ADN.
Para eso te ha pedido crear un programa con un método o función con la siguiente firma:

boolean isMutant(String[] dna);

En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla
de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las
cuales representa cada base nitrogenada del ADN.

Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras
iguales, de forma oblicua, horizontal o vertical.

Desarrolla el algoritmo de la manera más eficiente posible utilizando el lenguaje Java con SpringBoot.

<h2>Desafíos:</h2>
Nivel 1:
<ul>
  <li>Se desarrolló una solución eficiente al problema utilizando una arquitectura de capas, controladores y servicios.</li>
</ul>

Nivel 2: 
- **API REST**: Se desarrolló una API REST con el servicio `/mutant/` que detecte mutantes a partir de una secuencia de ADN en formato JSON enviada por una solicitud HTTP con el método POST.
  ```json
  POST /mutant/
  {
  "dna": ["ATGC", "CAGT", "TTAT", "AGAA"]
  }
  ```
  - **Respuesta del servicio**: HTTP 200-OK en caso de que se verifique un mutante, 403-Forbidden para no mutantes.
- **Hosting**: Se desplegó la API en **Render**.

Nivel 3: 
- **Base de Datos H2**: Se implementa una base de datos H2, la cuál almacena las secuencias de ADN verificadas, sin duplicados.
- **Estadísticas**: Se expone un servicio `/stats` que como respuesta retorna un JSON con estadísticas de las secuencias de ADN guardadas en la BBDD:
  ```json
  {
      "count_mutant_dna": 35,
      "count_human_dna": 100,
      "ratio": 0.35
  }
  ```
- **Pruebas de Tráfico de Peticiones**: Se utiliza JMeter para probar fluctuaciones agresivas de tráfico (Entre 100 y 1
millón de peticiones por segundo).
- **Test Integrados**: Tests con JUnit que garantizan un code coverage > 80%.

## Guía de ejecución del proyecto:

1. **Descargar el repositorio en formato zip**

2. **Configuración del entorno**:
   - Tener Java y Gradle instalados.
   - Configurar las propiedades de la base de datos H2 en el archivo `application.properties`.

3. **Ejecución del proyecto de manera local**:
   - Una vez configurado el proyecto se ejecuta la aplicación main `ParcialBrunoLucero50073Application`.

4. **Tests**:
   - Para ejecutar los tests y comprobar el code coverage, se ejecutan `IndividuoServiceTest` y `StatsServiceTest`

5. **Acceso a los servicios**:
   - Con un servicio cómo Postman puede interactuar con la API mientras se corre la aplicación usando la siguiente URL: `http://localhost:8080` y acceder a los servicios posibles:
     - Detección de mutantes: `POST /mutant/`
     - Estadísticas: `GET /stats`

## Deploy de la API para interactuar sin ejecutar el proyecto de manera local:

Link de Render: https://prueba-mercado-libre-mutantes.onrender.com/

## Documentación
Puedes acceder a la documentación del proyecto en la carpeta docs. Encontrarás los diagramas de secuencia correspondientes a cada servicio y un documento que incluye varias pruebas realizadas para el servicio `/mutant/` 
