# JavaFX_Retrofit_Client

**Programa de ejemplo desarrollado utilizando Javafx, Retrofit, Intellij y Maven.**

El programa permite realizar las operaciones CRUD en la entidad productos de una REST API implementada mediante json-server. Se han implementado:

1. MVC
2. Properties
3. Bindings
4. Retrofit2

El servicio REST API se debe ejecutar previamente mediante un contenedor al que se le pasa el fichero json con las entidades. 
En nuestro caso el fichero es **productos.json** y se encuentra en la carpeta raíz del repositorio.

El comando para ejecutar el contenedor es el siguiente:

>docker run -d -p 8080:80 -v XXXX/productos.json:/data/db.json clue/json-server (es posible que se deba ejecutar con sudo)
> XXXX se debe sustituir por la ruta absoluta en la que se encuentra tras descargar el repositorio.

Aspectos importantes a considerar:

1. Las anotaciones de los modelos son necesarias pues si no se ponen suele dar problemas.
2. Se le cambia el nombre al campo "productCode" porque el servidor necesita que haya un campo "id" para usarlo de clave. Si se usase otro servidor REST API no sería necesario. 


