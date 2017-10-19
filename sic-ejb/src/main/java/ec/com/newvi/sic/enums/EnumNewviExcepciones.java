/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.enums;

/**
 *
 * @author NEWVI
 */
public enum EnumNewviExcepciones {
    // 0 al 100 excepciones comunes
    ERR000("ERR000", "Error desconocido.", "Ocurrió un error desconocido. Concacte con el administrador."),
    ERR001("ERR001", "Error de conversión.", "No se pudo realizar la conversión, debido a incompatibilidades."),
    ERR010("ERR010", "Valor nulo.", "Se intentó acceder a un objeto/valor en nulo. Posiblemente el elemento no ha sido inicializado todavía."),
    ERR011("ERR011", "Valor negativo.", "El valor entregado no es positivo."),
    ERR012("ERR012", "Texto en blanco.", "Los datos enviados están en blanco. Por favor, enviar datos correctos."),
    ERR020("ERR020", "Error al acceder a una función.", "Se ha intentado acceder a una función no existente."),
    ERR021("ERR021", "Host desconocido.", "El host al que se intentó acceder es desconocido."),
    ERR022("ERR022", "Error al acceder a una función.", "Se intentó acceder a una función no permitida."),
    // 101 al 200 excepciones de conexion
    ERR101("ERR101", "No se pudo conectar.", "No se pudo conectar a la URL solicitada."),
    ERR120("ERR120", "No se ha encontrado una transacción.", "Se requiere de una transacción para realizar el registro. No se ha encontrado ninguna."),
    // 201 al 250 excepciones de sistema
    ERR201("ERR201", "Función no soportada.", "La función no está soportada."),
    ERR202("ERR202", "No se han encontrado resultados.", "No se han encontrado resultados. Si cree que esto no es correcto, contacte con el administrador."),
    ERR203("ERR203", "Más de 1 resultado.", "Se ha encontrado más de un resultado. Puede existir un error de consistencia. Contacte con el administrador."),
    ERR204("ERR204", "Entidad inexistente.", "No existe una entidad con el código presentado. Puede deberse a un error en la base de datos. Contacte con el administrador."),
    ERR205("ERR205", "Archivo no encontrado.", "No se ha encontrado el archivo con el nombre indicado, verifique que el archivo se encuentre en la carpeta."),
    ERR206("ERR206", "Archivo no pudo ser leído.", "No se pudo leer el archivo con el nombre indicado, verifique que el archivo no tenga errores."),
    ERR207("ERR207", "Archivo no pudo ser guardado.", "No se pudo guardar el archivo con el nombre indicado, verifique que el archivo no tenga errores o que la ubicación exista dentro del sistema."),
    ERR208("ERR208", "Formato no válido.", "El formato para transformación de valores numéricos a texto no es válido. Por favor verifique en los parámetros del sistema."),
    // 251 al 300 excepciones de validación
    ERR251("ERR251", "Correo electrónico no válido.", "El correo electrónico no es correcto. Registre un correo electrónico con el formato ejemplo@ejemplo.com"),
    
    // 301 al 400 excepciones de segurdades 
    ERR301("ERR301", "usuario no válido.", "El usuario ingresado no es válido. Por favor verifique que los datos ingresados son correctos."),
    INF302("INF302", "usuario ingresado.", "El usuario se ha registrado correctamente."),
    ERR303("ERR303", "usuario no registrado.", "No se pudo generar el usuario."),
    INF304("INF304", "usuario eliminado.", "El usuario se ha eliminado correctamente."),
    INF305("INF305", "usuario editado.", "El usuario se ha editado correctamente."),
    ERR306("ERR306", "Rol no válido.", "El rol ingresado no es válido. Por favor verifique que los datos ingresados son correctos."),
    INF307("INF307", "Rol ingresado.", "El rol se ha registrado correctamente."),
    INF308("INF308", "Rol eliminado.", "El rol se ha eliminado correctamente."),
    INF309("INF309", "Rol editado.", "El rol se ha editado correctamente."),
    ERR310("ERR310", "Funcionalidad no válida.", "La funcionalidad ingresada no es válida. Por favor verifique que los datos ingresados son correctos."),
    INF311("INF311", "Funcionalidad ingresada.", "La funcionalidad se ha registrado correctamente."),
    INF312("INF312", "Funcionalidad eliminada.", "La funcionalidad se ha eliminado correctamente."),
    INF313("INF313", "Funcionalidad editada.", "La funcionalidad se ha editado correctamente."),
    INF314("INF314", "asignacion ingresada.", "La asignacion se ha registrado correctamente."),
    INF315("INF315", "asignacion eliminada.", "La asignacion se ha eliminada correctamente."),
    INF316("INF316", "asignacion editada.", "La asignacion se ha editada correctamente."),
    ERR317("ERR317", "No se pudo salir del sistema.", "No se pudo salir del sistema."),
    ERR318("ERR318", "No se ha ingresado datos.", "No se han ingresado un usuario / contraseña."),
    ERR319("ERR319", "No se pudo ingresar al sistema.", "No se pudo registrar en el sistema. Revise que el usuario / contraseña sean correctos. "),
    INF320("INF320", "Usuario registrado.", "Usuario registrado en el sistema. "),
    ERR321("ERR321", "No existe el algoritmo de encriptación", "El algoritmo de encriptación indicado no existe. "),
    ERR322("ERR322", "Dominio no válido.", "El dominio ingresado no es válido. Por favor verifique que los datos ingresados son correctos."),
    INF323("INF323", "Dominio ingresado.", "El dominio se ha registrado correctamente."),
    INF324("INF324", "Dominio editado.", "El dominio se ha editado correctamente."),
    INF325("INF325", "Dominio eliminado.", "El dominio se ha eliminado correctamente."),
    ERR326("ERR326", "Usuario no registrado.", "El usuario no se encuentra registrado en el sistema."),
    ERR327("ERR327", "Usuario registrado.", "El usuario ya se encuentra registrado en el sistema."),
    ERR328("ERR328", "Correo electrónico registrado.", "El correo electrónico ya se encuentra registrado en el sistema."),
    ERR329("ERR329", "Contribuyente no válido.", "El contribuyente ingresado no es válido. Por favor verifique que los datos ingresados son correctos."),
    INF330("INF330", "Contribuyente ingresado.", "El contribuyente se ha registrado correctamente."),
    INF331("INF331", "Contribuyente editado.", "El contribuyente se ha editado correctamente."),
    INF332("INF332", "Contribuyente eliminado.", "El contribuyente se ha eliminado correctamente."),
    INF333("INF333", "Contribucion Mejora ingresada.", "La contribución se ha registrado correctamente."),
    INF334("INF334", "Contribucion Mejora ingresada.", "La contribución se ha editado correctamente."),
    INF335("INF335", "Contribucion Mejora eliminada.", "La contribución se ha eliminado correctamente."),
    ERR336("ERR336", "Contribucion Mejora no válido.", "La contribución ingresada no es válida. Por favor verifique que los datos ingresados son correctos."),
    ERR337("ERR337", "Detalle de obra no válido.", "El Detalle de obra ingresada no es válida. Por favor verifique que los datos ingresados son correctos."),
    ERR338("ERR338", "Predio no válido.", "El predio ingresado no es válido. Por favor verifique que los datos ingresados son correctos."),
    ERR339("ERR339", "Bloque no válido.", "El bloque ingresado no es válido. Por favor verifique que los datos ingresados son correctos."),
    INF340("INF340", "Bloque ingresado.", "El bloque se ha registrado correctamente."),
    INF341("INF341", "Bloque editado.", "El bloque se ha editado correctamente."),
    INF342("INF342", "Bloque eliminado.", "El bloque se ha eliminado correctamente."),
    ERR343("ERR343", "Piso no válido.", "El piso ingresado no es válido. Por favor verifique que los datos ingresados son correctos."),
    INF344("INF344", "Piso ingresado.", "El piso se ha registrado correctamente."),
    INF345("INF345", "Piso editado.", "El piso se ha editado correctamente."),
    INF346("INF346", "Piso eliminado.", "El piso se ha eliminado correctamente."),
    ERR347("ERR347", "Propietario no válido.", "El propietario ingresado no es válido. Por favor verifique que los datos ingresados son correctos."),
    INF348("INF348", "Propietario ingresado.", "El propietario se ha registrado correctamente."),
    INF349("INF349", "Propietario editado.", "El propietario se ha editado correctamente."),
    INF350("INF350", "Propietario eliminado.", "El propietario se ha eliminado correctamente."),
    INF351("INF351", "Predio ingresado.", "El predio se ha registrado correctamente."),
    INF352("INF352", "Predio editado.", "El predio se ha editado correctamente."),
    INF353("INF353", "Predio eliminado.", "El predio se ha eliminado correctamente."),
    INF354("INF353", "Bloque agregado.", "El bloque se ha agregado correctamente."),
    INF355("INF353", "Piso agregado.", "El piso se ha agregado correctamente."),
    INF356("INF356", "Detalle piso agregado.", "El detalle del piso se ha agregado correctamente."),
    INF357("INF357", "Detalle piso actualizado.", "El detalle del piso se ha actualizado correctamente."),
    INF358("INF358", "Servicio agregado.", "El servicio se ha agregado correctamente."),
    INF359("INF359", "Propiedad actualizada.", "La propiedad se ha actualizado correctamente."),
    INF360("INF360", "Descripción  de terreno agregado.", "La descripcion de terreno se ha agregado correctamente."),
    INF361("INF361", "Servicio actualizada.", "El servicio se ha actualizado correctamente."),
    INF362("INF362", "Descripción  de terreno eliminada.", "La descripcion de terreno se ha eliminado correctamente."),
    ERR362("ERR362", "Avaluo no válido.", "El avaluo ingresado no es válido. Por favor verifique que los datos ingresados son correctos."),
    ERR363("ERR363", "Fecha Avaluo no válida.", "la fecha avaluo ingresada no es válida. Por favor verifique que los datos ingresados son correctos."),
    ERR364("ERR364", "Constante impuestos no válida.", "La Constante impuestos ingresada no es válida. Por favor verifique que los datos ingresados son correctos."),
    ERR365("ERR365", "Servicio no válido.", "El servicio ingresado no es válido. Por favor verifique que los datos ingresados son correctos."),
    INF365("INF365", "Parametro ingresado.", "El parametro se ha registrado correctamente."),
    INF366("INF366", "Parametro editado.", "El parametro se ha editado correctamente."),
    INF367("INF367", "Parametro eliminado.", "El parametro se ha eliminado correctamente."),
    INF368("INF368", "Predio registrado.", "El predio se ha registrado correctamente en la contribución."),
    INF369("INF369", "Predio eliminado.", "El predio se ha eliminado correctamente de la contribución."),
    INF370("INF370", "Predios eliminados.", "Los predios se ha eliminado correctamente de la contribución."),
    INF371("INF371", "Servicio eliminado.", "El servicio se ha eliminado correctamente."),
    INF372("INF372", "Bloque eliminado.", "El bloque se ha eliminado correctamente."),
    INF373("INF373", "Piso eliminado.", "El piso se ha eliminado correctamente."),
    INF374("INF374", "Detalle Piso eliminado.", "El detalle del piso se ha eliminado correctamente."),
    // 400 al 450 excepciones de impresión
    ERR400("ERR400", "Error al acceder al reporte", "No se pudo acceder al archivo de reporte. Por favor, compruebe que existe en la carpeta del servidor."),
    ERR401("ERR401", "Error al interpretar el reporte", "El reporte generado no está correctamente formado. Revise los archivos de LOG o contacte con el administrador."),
    ERR402("ERR402", "Error al generar la impresión", "No se pudo generar el reporte Jasper. Posiblemente no se encuentre el servidor correctamente configurado. Revise los archivos de LOG o contacte con el administrador."),
    ERR403("ERR403", "No existe el tipo de letra de la impresión", "El tipo de letra que se utiliza para el reporte no se encuentra instalado en el servidor. Revise los LOGs del sistema o contacte con el administrador."),
    ERR404("ERR404", "Error al intentar procesar el flujo del reporte", "El sistema intentó acceder a los contenidos del reporte generado, y no se pudo obtener la información. Contacte con el administrador."),
    // 451 al 500 excepciones de parámetros
    ERR451("ERR451", "Parámetro no válido.", "El parámetro ingresado no es válido. Por favor verifique que los datos ingresados no contengan valores nulos."),
    INF452("INF452", "Parámetro ingresado.", "El parámetro se ha registrado correctamente."),
    ERR453("ERR453", "Parámetro no registrado.", "No se pudo generar el parámetro."),
    INF454("INF454", "Parámetro editado.", "El parámetro se ha editado correctamente."),
    ERR455("ERR455", "Parámetro no encontrado.", "El parámetro [parametro] no se ha encontrado. Compruebe que este parámetro haya sido registrado."),
    // 501 al 600 excepciones de catastro
    ERR501("ERR501", "Propietario no encontrado.", "No se pudo encontrar un propietario al predio seleccionado. Registre la propiedad correspondiente."),
    ;
    
    private final String codigoExcepcion;
    private final String nombreExcepcion;
    private final String mensajeExcepcion;
    
    private EnumNewviExcepciones(String codigoExcepcion, String nombreExcepcion, String mensajeExcepcion) {
        this.codigoExcepcion = codigoExcepcion;
        this.nombreExcepcion = nombreExcepcion;
        this.mensajeExcepcion = mensajeExcepcion;
    }

    public String getCodigoExcepcion() {
        return codigoExcepcion;
    }

    public String getNombreExcepcion() {
        return nombreExcepcion;
    }

    public String getMensajeExcepcion() {
        return mensajeExcepcion;
    }
    
    public String presentarExcepcion() {
        return this.getNombreExcepcion();
    }
    
    public String presentarMensaje() {
        return this.getMensajeExcepcion();
    }
    
    public String presentarMensajeCodigo() {
        return this.getMensajeExcepcion().concat(" (").concat(this.codigoExcepcion).concat(")");
    }
}
