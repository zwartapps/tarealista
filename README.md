# Tarea 3 Lista de Nombres
## Tarea UT3 de Programación Multimedia y Dispositivos Móviles

### Unidad de Trabajo 03
#### Diseño de la Interfaz de Usuario
#### Práctica a Entregar

Crear un proyecto Android que muestre el listado los nombres de personas en una lista. Además, la aplicación
deberá ser capaz de añadir nuevos nombres a la lista y de borrar un elemento de la lista:
- El menú del Sidebar tendrá una opción que permitirá insertar un nuevo elemento en el ListView.
- Cuando pulsemos sobre un elemento del ListView, mostrará la opción de borrar dicho elemento.

Para ver los detalles, ver el PDF: 
- [PDF Tarea 3](../master/0489_PDMP_UT03_Practica_2018_v1.0.pdf)

Creamos el proyecto en Android Studio. El codigo está comentado para entender que hacemos.
Extras añadido por mi:
Enseñar un mensaje Toast cuando añadimos o borramos un nombre indicando el nombre en cuestion:

``` buttonAdd.setOnClickListener(new View.OnClickListener() {

            //Creamos un String temporal para añadirlo a la lista y para
            // concatenarlo con el mensaje Toast que hemos añadido ese nombre
            @Override
            public void onClick(View v) {
                String temp = nombre.getText().toString();
                nombres.add(temp);
                Toast.makeText(getApplicationContext(), "Añadido " + temp, Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                dialogBuilder.dismiss();
            }
        });
        
         //Creamos String temporal para eliminar el elemento y indicarlo con el toast que fue eliminado
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.Delete:
                String temp = (nombres.get((int) info.id));
                nombres.remove(temp);
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Borrado " + temp, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
```

La aplicacion ha quedado así:
Pantall principal con la lista Inicial:

<img src="http://i68.tinypic.com/i6ywx5.jpg" >

Al elegir la opcion **Nuevo...** en el menu aparece un cuadro de dialogo para añadir un nombre nuevo:

<img src="http://i66.tinypic.com/s45fo9.jpg">

Introducimos un nombre nuevo y damos al boton **Añadir**:

<img src="http://i68.tinypic.com/344y652.jpg">

Vemos que se añade el nuevo nombre y el Toast que lo indica:

<img src="http://i65.tinypic.com/2lsi3jt.jpg">

Al mantener pulsado un nombre de la lista, se abre un menu contextual para borrar ese nombre (o cancelar):

<img src="http://i66.tinypic.com/25i86cl.jpg">

Pulsamos en **Borrar**, vemos que se ha borrado de la lista y se muestra un mensaje toast que ese nombre ha sido borrado:

<img src="http://i64.tinypic.com/28hlcvb.jpg">

### Todo funciona correctamente!










