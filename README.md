# 🏥 Sistema de Gestión de Clínica

Sistema backend para la gestión integral de una clínica desarrollado con **Spring Boot 3.5.14** y **Java 21**. Arquitectura completamente persistida en base de datos relacional MySQL usando **JPA/Hibernate**. Listo para consumo externo mediante REST API.

---

## 📋 Características Principales

- ✅ **Gestión de Pacientes** - Crear, editar, buscar y eliminar pacientes con persistencia en BD
- ✅ **Gestión de Médicos** - Administrar médicos, especialidades y asignación a consultorios
- ✅ **Gestión de Administradores** - Control de usuarios administrativos y niveles de acceso
- ✅ **Gestión de Consultorios** - Organizar espacios disponibles y asignar médicos
- ✅ **Gestión de Cuotas** - Registrar pagos de pacientes y seguimiento de afiliación
- ✅ **Gestión de Citas** - Programación de citas médicas con relaciones paciente-médico
- ✅ **Mapeo ORM Completo** - Todas las entidades persistidas con JPA/Hibernate
- ✅ **Operaciones CRUD Funcionales** - Interfaz CLI con todas las operaciones en BD real

---

## 🛠️ Tecnologías Utilizadas

| Tecnología | Versión | Propósito |
|-----------|---------|----------|
| Java | 21 | Lenguaje de programación |
| Spring Boot | 3.5.14 | Framework de aplicación |
| Spring Data JPA | Incluido en Boot | Mapeo Objeto-Relacional |
| Hibernate | Incluido en JPA | ORM y persistencia |
| MySQL | 8.0+ | Base de datos relacional |
| Maven | 3.6+ | Gestor de dependencias |

---

## 📁 Estructura del Proyecto

```
src/main/java/com/Clinica/demo/
├── DemoApplication.java              # Clase principal con menú CLI
├── model/
│   ├── Persona.java                  # Clase base @MappedSuperclass
│   ├── Paciente.java                 # @Entity persistida
│   ├── Medico.java                   # @Entity persistida
│   ├── Administrador.java            # @Entity persistida
│   ├── Consultorio.java              # @Entity persistida
│   ├── Cuota.java                    # @Entity persistida
│   └── Citas.java                    # @Entity persistida
└── repository/
    ├── PacienteRepository.java       # JpaRepository<Paciente, Long>
    ├── MedicoRepository.java         # JpaRepository<Medico, Long>
    ├── AdministradorRepository.java  # JpaRepository<Administrador, Long>
    ├── ConsultorioRepository.java    # JpaRepository<Consultorio, Long>
    ├── CuotaRepository.java          # JpaRepository<Cuota, Long>
    └── CitasRepository.java          # JpaRepository<Citas, Long>

src/main/resources/
└── application.properties            # Configuración de BD y Hibernate
```

---

## 🚀 Instalación y Configuración

### Requisitos Previos

- **Java 21** o superior
- **Maven 3.6+**
- **MySQL 8.0+** (servidor ejecutándose)
- **IDE recomendado:** IntelliJ IDEA, VS Code o Eclipse

---

### ⚙️ PASO 1: Crear la Base de Datos

#### Opción A: Usando MySQL CLI

```bash
# Abre MySQL desde terminal
mysql -u root -p

# En la consola MySQL, ejecuta:
CREATE DATABASE clinica_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE clinica_db;

# Verifica que se creó
SHOW DATABASES;
```

#### Opción B: Usando MySQL Workbench

1. Abre MySQL Workbench
2. Conecta con tu servidor MySQL
3. Haz clic derecho en "Schemas" → "Create Schema..."
4. Nombre: `clinica_db`
5. Character Set: `utf8mb4`
6. Collation: `utf8mb4_unicode_ci`
7. Click "Apply"

#### Opción C: Script SQL Automático (Primera ejecución)

Si ejecutas la aplicación sin la BD, Hibernate intentará crearla automáticamente gracias a la configuración `ddl-auto=update`.

---

### ⚙️ PASO 2: Configurar Credenciales de Base de Datos

**Archivo:** `src/main/resources/application.properties`

```properties
# === CONFIGURACIÓN DE CONEXIÓN A BASE DE DATOS ===
spring.application.name=demo
spring.datasource.url=jdbc:mysql://localhost:3306/clinica_db?serverTimezone=UTC&useSSL=false
spring.datasource.username=root
spring.datasource.password=12345678
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# === CONFIGURACIÓN JPA/HIBERNATE ===
# ddl-auto: update = crea/modifica tablas automáticamente
# Cambiar a 'validate' en producción para evitar cambios accidentales
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.defer-datasource-initialization=true
```

**⚠️ IMPORTANTE - Cambiar contraseña:**
```properties
# Reemplaza "********" con tu contraseña real de MySQL
spring.datasource.password=TU_CONTRASEÑA_AQUI
```

**Ejemplo con otras contraseñas:**
```properties
# Si tu usuario MySQL es diferente:
spring.datasource.username=usuario_clinica
spring.datasource.password=mi_contrasena_123

# Si MySQL está en otro servidor:
spring.datasource.url=jdbc:mysql://192.168.1.100:3306/clinica_db
```

---

### ⚙️ PASO 3: Variables de Entorno (Opcional pero Recomendado)

Para mayor seguridad, usa variables de entorno en lugar de contraseñas en el código:

**En Windows (PowerShell):**
```powershell
$env:DB_URL = "jdbc:mysql://localhost:3306/clinica_db"
$env:DB_USER = "root"
$env:DB_PASSWORD = "12345678"
```

**En Windows (CMD):**
```cmd
set DB_URL=jdbc:mysql://localhost:3306/clinica_db
set DB_USER=root
set DB_PASSWORD=12345678
```

**En Linux/Mac:**
```bash
export DB_URL=jdbc:mysql://localhost:3306/clinica_db
export DB_USER=root
export DB_PASSWORD=12345678
```

**Luego actualiza `application.properties`:**
```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```

---

### ⚙️ PASO 4: Compilar e Instalar Dependencias

```bash
# Navega al directorio del proyecto
cd clinica.demo

# Limpia y compila
mvn clean install

# Descarga todas las dependencias
mvn dependency:resolve
```

---

### ⚙️ PASO 5: Ejecutar la Aplicación

```bash
# Opción 1: Ejecutar directamente con Maven
mvn spring-boot:run

# Opción 2: Compilar a JAR y ejecutar
mvn clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar

# Opción 3: Desde el IDE
# IntelliJ: Click derecho en DemoApplication.java → Run 'DemoApplication.main()'
# VS Code: Debug → Run Java Application
```

**Salida esperada:**
```
===== CLÍNICA - MENÚ PRINCIPAL =====
1. Gestionar Pacientes
2. Gestionar Médicos
3. Gestionar Administradores
4. Gestionar Consultorios
5. Gestionar Cuotas
6. Gestionar Citas
0. Salir
Seleccione una opción:
```

---

### ⚙️ PASO 6: Verificar Conexión a Base de Datos

Para confirmar que la BD se conectó correctamente:

1. Ejecuta la aplicación
2. Verifica en MySQL que se crearon las tablas:

```sql
USE clinica_db;
SHOW TABLES;

-- Debes ver estas tablas:
-- pacientes
-- medicos
-- administradores
-- consultorios
-- cuotas
-- citas
```

3. Describe una tabla para verificar el mapeo:
```sql
DESCRIBE pacientes;
```

---

## 📖 Uso de la Aplicación

La aplicación funciona como una interfaz de línea de comandos (CLI) con un menú interactivo:

```
===== CLÍNICA - MENÚ PRINCIPAL =====
1. Gestionar Pacientes
2. Gestionar Médicos
3. Gestionar Administradores
4. Gestionar Consultorios
5. Gestionar Cuotas
6. Gestionar Citas
0. Salir
```

### Ejemplo: Crear un Paciente

1. Selecciona opción **1** - Gestionar Pacientes
2. Selecciona opción **1** - Crear Paciente
3. Ingresa los datos solicitados:
   - Nombre completo
   - Edad
   - Cédula
   - Teléfono
   - EPS (Entidad Promotora de Salud)

### Ejemplo: Programar una Cita

1. Selecciona opción **6** - Gestionar Citas
2. Selecciona opción **1** - Crear Cita
3. Proporciona:
   - ID del paciente
   - ID del médico
   - Fecha y hora (formato: YYYY-MM-DD HH:MM)
   - Estado de la cita

## 🗄️ Modelo de Datos

### Relaciones Entre Entidades

- **Paciente** → puede tener múltiples **Cuotas** y **Citas**
- **Médico** → puede atender múltiples **Citas**
- **Citas** → vincula **Pacientes** y **Médicos**
- **Cuota** → asociada a un **Paciente**
- **Persona** → clase base para Paciente, Médico y Administrador

## 🔧 Operaciones CRUD Disponibles

Cada módulo permite:

- **Create (C)** - Crear nuevos registros
- **Read (R)** - Visualizar todos los registros o buscar por ID
- **Update (U)** - Modificar registros existentes
- **Delete (D)** - Eliminar registros

## ⚙️ Configuración Adicional

### Variables de Entorno (Opcional)

```bash
export DB_URL=jdbc:mysql://localhost:3306/clinica_db
export DB_USER=root
export DB_PASSWORD=contraseña
```

### Perfil de Desarrollo

Para ejecutar con logs detallados:
```bash
mvn spring-boot:run -Dspring.profiles.active=dev
```

## 📝 Dependencias Maven

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
    </dependency>
</dependencies>
```

## 🚀 Próximas Mejoras (Road Map)

- [ ] Implementar REST API HTTP
- [ ] Agregar autenticación y autorización
- [ ] Crear interfaz gráfica (UI)
- [ ] Añadir validaciones más robustas
- [ ] Implementar paginación en listados
- [ ] Agregar reportes y estadísticas
- [ ] Integrar notificaciones por email/SMS

## 🌐 Guía para Desarrolladores Frontend

### Estado Actual de la Aplicación

Actualmente, la aplicación es una **interfaz CLI (Command Line Interface)**. Para que un frontend web pueda conectarse con este código Java, es necesario crear **Controllers REST** que expongan los datos y operaciones a través de endpoints HTTP.

### Clases y Métodos Principales a Utilizar

Los desarrolladores frontend necesitarán llamar a los **Repositorios** (que heredan de `JpaRepository`). Aquí están los principales:

#### 1. **PacienteRepository**
```java
// Métodos disponibles (heredados de JpaRepository)
List<Paciente> findAll();                    // Obtener todos los pacientes
Optional<Paciente> findById(Long id);        // Buscar paciente por ID
Paciente save(Paciente paciente);            // Crear o actualizar paciente
void deleteById(Long id);                    // Eliminar paciente
boolean existsById(Long id);                 // Verificar si existe
```

#### 2. **MedicoRepository**
```java
List<Medico> findAll();                      // Obtener todos los médicos
Optional<Medico> findById(Long id);          // Buscar médico por ID
Medico save(Medico medico);                  // Crear o actualizar médico
void deleteById(Long id);                    // Eliminar médico
boolean existsById(Long id);                 // Verificar si existe
```

#### 3. **CitasRepository**
```java
List<Citas> findAll();                       // Obtener todas las citas
Optional<Citas> findById(Long id);           // Buscar cita por ID
Citas save(Citas cita);                      // Crear o actualizar cita
void deleteById(Long id);                    // Eliminar cita
```

#### 4. **CuotaRepository**
```java
List<Cuota> findAll();                       // Obtener todas las cuotas
Optional<Cuota> findById(Long id);           // Buscar cuota por ID
Cuota save(Cuota cuota);                     // Crear o actualizar cuota
void deleteById(Long id);                    // Eliminar cuota
```

#### 5. **ConsultorioRepository** y **AdministradorRepository**
Utilizan los mismos métodos CRUD disponibles en `JpaRepository`.

### Estructura Recomendada de Controllers REST

Para que el frontend pueda conectarse, se deben crear Controllers como el siguiente ejemplo:

```java
@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "http://localhost:3000") // Ajusta según tu dominio frontend
public class PacienteController {
    
    @Autowired
    private PacienteRepository pacienteRepository;
    
    // GET: Obtener todos los pacientes
    @GetMapping
    public ResponseEntity<List<Paciente>> obtenerTodos() {
        return ResponseEntity.ok(pacienteRepository.findAll());
    }
    
    // GET: Obtener paciente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtenerPorId(@PathVariable Long id) {
        return pacienteRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    // POST: Crear nuevo paciente
    @PostMapping
    public ResponseEntity<Paciente> crear(@RequestBody Paciente paciente) {
        Paciente guardado = pacienteRepository.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }
    
    // PUT: Actualizar paciente
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(
        @PathVariable Long id,
        @RequestBody Paciente pacienteActualizado) {
        
        return pacienteRepository.findById(id).map(paciente -> {
            paciente.setNombreCompleto(pacienteActualizado.getNombreCompleto());
            paciente.setEPS(pacienteActualizado.getEPS());
            // ... actualizar otros campos
            return ResponseEntity.ok(pacienteRepository.save(paciente));
        }).orElse(ResponseEntity.notFound().build());
    }
    
    // DELETE: Eliminar paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
```

### Ejemplos de Llamadas desde Frontend

#### Obtener todos los pacientes (Fetch API)
```javascript
fetch('http://localhost:8080/api/pacientes')
    .then(response => response.json())
    .then(data => console.log(data));
```

#### Crear un nuevo paciente
```javascript
const nuevoPaciente = {
    nombreCompleto: "Juan Pérez",
    edad: 35,
    cedula: "1234567890",
    telefono: "3001234567",
    eps: "EPS Salud"
};

fetch('http://localhost:8080/api/pacientes', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(nuevoPaciente)
})
.then(response => response.json())
.then(data => console.log('Paciente creado:', data));
```

#### Actualizar un paciente
```javascript
const pacienteActualizado = {
    nombreCompleto: "Juan Pérez",
    edad: 36,
    cedula: "1234567890",
    telefono: "3001234568",
    eps: "EPS Salud Plus"
};

fetch('http://localhost:8080/api/pacientes/1', {
    method: 'PUT',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(pacienteActualizado)
})
.then(response => response.json())
.then(data => console.log('Paciente actualizado:', data));
```

#### Eliminar un paciente
```javascript
fetch('http://localhost:8080/api/pacientes/1', {
    method: 'DELETE'
})
.then(response => {
    if (response.ok) {
        console.log('Paciente eliminado');
    }
});
```

### Pasos para Integrar Frontend

1. **Crear los Controllers REST** - Implementar controllers similar al ejemplo anterior para cada entidad
2. **Configurar CORS** - Habilitar acceso desde tu dominio frontend
3. **Definir DTOs** (opcional pero recomendado) - Para validaciones y seguridad
4. **Documentar con Swagger** - Añadir `springdoc-openapi` para documentación automática
5. **Configurar errores globales** - Implementar `@ControllerAdvice` para manejo de excepciones

### Configuración CORS Recomendada

Añade esta configuración en `application.properties`:
```properties
# CORS Configuration
server.servlet.context-path=/api
spring.web.cors.allowed-origins=http://localhost:3000,http://localhost:4200
spring.web.cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
spring.web.cors.allowed-headers=*
spring.web.cors.allow-credentials=true
```

### Próximas Acciones Necesarias

- ✅ Crear clases Controller REST para cada entidad
- ✅ Implementar DTOs para transferencia de datos
- ✅ Añadir validaciones con `@Valid` y `@Validated`
- ✅ Integrar Swagger para documentación interactiva
- ✅ Implementar manejo global de excepciones
- ✅ Configurar seguridad con Spring Security (opcional)

---

## � Integración Conceptual Frontend-Backend

### Arquitectura General del Sistema

El sistema de gestión de clínica está dividido en dos capas claramente definidas:

```
┌─────────────────────────────────────────────────────────┐
│                    CAPA FRONTEND                         │
│         (Angular, React, Vue, o similar)                 │
│  - Interfaz gráfica web/móvil                            │
│  - Interacción del usuario                               │
│  - Validaciones en cliente                               │
└─────────────────────────────────────────────────────────┘
                         ↓ HTTP/REST ↓
┌─────────────────────────────────────────────────────────┐
│                    CAPA BACKEND (Este proyecto)          │
│         Spring Boot + JPA + MySQL                        │
│  - Lógica de negocio                                     │
│  - Validaciones en servidor                              │
│  - Persistencia en base de datos                         │
└─────────────────────────────────────────────────────────┘
                         ↓ SQL ↓
┌─────────────────────────────────────────────────────────┐
│                    BASE DE DATOS MySQL                   │
│  - Almacenamiento persistente de datos                   │
│  - 6 tablas relacionadas (pacientes, médicos, etc.)      │
└─────────────────────────────────────────────────────────┘
```

### Flujo de Datos: Ejemplo Crear un Paciente

#### 1. **Usuario Ingresa Datos en el Frontend**
El usuario completa un formulario en la interfaz web:
```json
{
  "nombreCompleto": "Carlos García",
  "edad": 45,
  "cedula": "1234567890",
  "telefono": "3101234567",
  "eps": "EPS Salud Total"
}
```

#### 2. **Frontend Envía Petición HTTP POST**
```javascript
// JavaScript en el Frontend (React, Angular, Vue, etc.)
fetch('http://localhost:8080/api/pacientes', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify(datosPaciente)
})
.then(response => response.json())
.then(data => {
  console.log('Paciente creado con ID:', data.id);
  // Actualizar la lista en la interfaz
});
```

#### 3. **Backend Recibe y Procesa**
El Controller REST recibe la petición:
```
POST /api/pacientes
Content-Type: application/json

{
  "nombreCompleto": "Carlos García",
  "edad": 45,
  ...
}
```

#### 4. **Mapeo ORM: De JSON a Objeto Java**
Spring Boot automáticamente:
- Deserializa el JSON a objeto `Paciente`
- Valida los datos
- Invoca `pacienteRepository.save(paciente)`

#### 5. **Persistencia en Base de Datos**
JPA/Hibernate ejecuta en SQL:
```sql
INSERT INTO pacientes (
  nombre_completo, edad, cedula, telefono, eps
) VALUES (
  'Carlos García', 45, '1234567890', '3101234567', 'EPS Salud Total'
);
```

#### 6. **Respuesta al Frontend**
El servidor devuelve:
```json
{
  "id": 1,
  "nombreCompleto": "Carlos García",
  "edad": 45,
  "cedula": "1234567890",
  "telefono": "3101234567",
  "eps": "EPS Salud Total"
}
```

#### 7. **Frontend Actualiza la Interfaz**
El navegador muestra el nuevo paciente en la lista.

---

### Relaciones de Datos en el Backend

Las siguientes entidades están relacionadas en la base de datos:

```
┌─────────────────────────────────────────────────────────┐
│                    PACIENTE                              │
│ - id (PK)                                               │
│ - nombreCompleto, edad, cedula, telefono               │
│ - eps                                                    │
└─────────────────────────────────────────────────────────┘
         ↓ 1:N (Un paciente muchas citas/cuotas)
         ├──→ CITAS (N:1 relación inversa con MÉDICO)
         │    - id, fechaHora, estado
         │    - paciente_id (FK), medico_id (FK)
         │
         └──→ CUOTAS (pagos y afiliación)
              - id, nombrePaciente, cedulaPaciente
              - afiliacion, totalPagar

┌─────────────────────────────────────────────────────────┐
│                    MÉDICO                                │
│ - id (PK)                                               │
│ - nombreCompleto, edad, cedula, telefono               │
│ - especialidad                                           │
│ - consultorio_id (FK)                                   │
└─────────────────────────────────────────────────────────┘
         ↓ 1:N (Un médico muchas citas)
         └──→ CITAS (N:1 relación inversa con PACIENTE)

┌─────────────────────────────────────────────────────────┐
│                 CONSULTORIO                              │
│ - id (PK)                                               │
│ - numConsultorio, pisoConsultorio, estado              │
└─────────────────────────────────────────────────────────┘
         ↓ 1:N (Un consultorio muchos médicos)
         └──→ MÉDICOS
```

---

### Responsabilidades Divididas

| **Componente** | **Responsabilidad** |
|---|---|
| **Frontend** | Captura datos del usuario, validación básica, visualización, interacción UX |
| **Backend (Este Proyecto)** | Validación completa, lógica de negocio, persistencia, integridad referencial |
| **Base de Datos** | Almacenamiento seguro, cumplimiento de restricciones, transacciones |

---

### Ejemplo: Crear una Cita (Caso más complejo)

**Requisitos:**
- La cita vincula un Paciente existente con un Médico existente
- Solo se puede crear si ambos existen

**Flujo:**

1. **Frontend obtiene listas de pacientes y médicos**
   ```javascript
   // GET /api/pacientes
   // GET /api/medicos
   ```

2. **Usuario selecciona paciente y médico, define fecha y hora**

3. **Frontend envía POST**
   ```json
   {
     "paciente_id": 5,
     "medico_id": 2,
     "fechaHora": "2024-06-15 14:30",
     "estado": "programada"
   }
   ```

4. **Backend valida:**
   - ¿Existe paciente con ID 5? → `pacienteRepository.findById(5)`
   - ¿Existe médico con ID 2? → `medicoRepository.findById(2)`
   - Si ambos existen → Crear cita
   - Si no → Retornar error 404

5. **Resultado:**
   - Base de datos crea registro en tabla `citas`
   - Las claves foráneas garantizan integridad
   - Frontend recibe la cita creada con ID

---

## 🎯 Manual Específico: Endpoints del Backend Disponibles

Aunque la CLI actual NO expone REST API, aquí están los **Repositorios disponibles** que un desarrollo futuro puede usar:

### Operaciones sobre PACIENTES
```java
// Obtener todos
GET /api/pacientes → List<Paciente>

// Obtener uno
GET /api/pacientes/{id} → Paciente

// Crear
POST /api/pacientes → Paciente

// Actualizar
PUT /api/pacientes/{id} → Paciente

// Eliminar
DELETE /api/pacientes/{id} → void
```

### Operaciones sobre MÉDICOS
```java
GET /api/medicos → List<Medico>
GET /api/medicos/{id} → Medico
POST /api/medicos → Medico
PUT /api/medicos/{id} → Medico
DELETE /api/medicos/{id} → void
```

### Operaciones sobre CITAS
```java
GET /api/citas → List<Citas>
GET /api/citas/{id} → Citas
POST /api/citas → Citas
PUT /api/citas/{id} → Citas
DELETE /api/citas/{id} → void
```

(Igual para CUOTAS, CONSULTORIOS y ADMINISTRADORES)

---

## ✨ Estado Actual vs. Futuro

| Aspecto | Estado Actual | Siguiente Paso |
|--------|--------------|----------------|
| **Interfaz** | CLI (Consola) | REST API + Frontend Web |
| **Persistencia** | MySQL ✅ | MySQL (sin cambios) |
| **Validaciones** | Básicas en código | Decoradores (@Valid, @Validated) |
| **Documentación API** | Manual en README | Swagger/OpenAPI |
| **Seguridad** | Ninguna | Spring Security |
| **Testing** | Manual | Unit + Integration Tests |

---

## 🚀 Próximas Mejoras (Road Map)

- [ ] **Implementar REST API HTTP completa** - Controllers REST para cada entidad
- [ ] **Crear interfaz gráfica (UI)** - Frontend con Angular, React o Vue
- [ ] **Agregar autenticación y autorización** - Spring Security + JWT
- [ ] **Integrar Swagger/OpenAPI** - Documentación automática de endpoints
- [ ] **Añadir validaciones robustas** - @Valid, @NotNull, @Email, etc.
- [ ] **Implementar paginación** - Para listados con muchos registros
- [ ] **Agregar reportes y estadísticas** - Consultas analíticas avanzadas
- [ ] **Integrar notificaciones** - Email/SMS para confirmación de citas
- [ ] **Implementar tests automáticos** - JUnit + Mockito

---

## 📧 Contacto y Soporte

Para preguntas o problemas, contacta con el equipo de desarrollo.

---

**Nota**: Se recomienda revisar regularmente dado que está sujeto a actualizaciones y cambios constantes.