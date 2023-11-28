# bciws

# descargar el proyecto
# levantarlo en intellij idea
# esperar que baje las dependencias
# ejecutar el proyecto
# en el navegador poner la ruta  http://localhost:8181/swagger-ui/index.html
# se han desarrollado 6 metodos de los webservices d elos cuales los mas importantes son /api/v1/register  /api/v1/   /api/v1/login
# primero ejecutar el endpoint post /api/v1/register para eso presione el boton try it out
# en los datos borar ambos ids y poner la data de nombre y password
# puede probar para los casos de correo sin formato, password vacio
# una vez guardado se pude ver la data que se ha guardado
# o puede usar el endpoint get /api/v1/ el cual devuelve todo
# una vez guardado puede hacer login para esto se va al endpoint post /api/v1/login
# una vez inciado sesion con los datos correctos se actualiza la fecha de login y el token el cual los puede visualizar


#tecnologias
# se uso spring security para la encriptacion y desencriptacion del password
# se uso jwt para el token
# se uso lombok para escribir menos codigo
# se uso spring data
# para la documentacion se uso swagger
# se uso metodos genericos para escribir la menor cantidad de codigo
# se uso h2 como base de datos de memoria
# hay un scrip de h2 el cual se ejecuta automaticamente cuando se corre el proyecto
# se usaron algunos validadores de spring pro anotacion

# diagrama

# se creo una carpeta diagrama en el cual se encuentra un documento word con un diagrama simplificado de la solucion 
# tambien se encuentra imagenes del swagger levantado con los endpoint

