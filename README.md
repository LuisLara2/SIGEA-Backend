# SIGEA

# Tabla de Contenido

- [SIGEA](#sigea)
- [Tabla de Contenido](#tabla-de-contenido)
- [I. Descripcion](#i-descripcion)
- [II. Arquitectura](#ii-arquitectura)
- [III. Patrones de Diseño](#iii-patrones-de-diseño)
- [IV. Tecnologias](#iv-tecnologias)
- [V. Estructura de Carpetas del Proyecto](#v-estructura-de-carpetas-del-proyecto)
- [VI. Instalacion y Ejecucion del Codigo Fuente](#vi-instalacion-y-ejecucion-del-codigo-fuente)
  - [6.1. Clonar Repositorio](#61-clonar-repositorio)
  - [6.2. Antes de Ejecutar el Proyecto](#62-antes-de-ejecutar-el-proyecto)
    - [6.2.1. Configuracion de Archivo `.env`](#621-configuracion-de-archivo-env)
    - [6.2.2. Configuracion de `psql`](#622-configuracion-de-psql)
      - [6.2.2.1. En Windows](#6221-en-windows)
      - [6.2.2.2. En Linux (Ubuntu, Debian, etc.)](#6222-en-linux-ubuntu-debian-etc)
      - [6.2.2.3. En macOS](#6223-en-macos)
  - [6.3. Ejecutar el proyecto](#63-ejecutar-el-proyecto)
- [VII. Colaboradores](#vii-colaboradores)
- [VIII. Licencia](#viii-licencia)


# I. Descripcion

# II. Arquitectura

# III. Patrones de Diseño

# IV. Tecnologias

# V. Estructura de Carpetas del Proyecto

# VI. Instalacion y Ejecucion del Codigo Fuente

## 6.1. Clonar Repositorio

Para clonar con `GIT` ejecuta en tu terminal:

```bash
git clone https://github.com/PAULTB4/SIGEA-backend.git
```

Las ramas principales son:

- `main` (estable / produccion):
- `develop` (menos estable / desarrollo):

## 6.2. Antes de Ejecutar el Proyecto

Antes de poder ejecutar el proyecto debes realizar unas configuraciones iniciales.

### 6.2.1. Configuracion de Archivo `.env`

Antes de ejecutar el proyecto, crea un archivo `.env` junto al archivo `README.md` y `pom.xml`.

Dentro debes especificar lo siguiente:

```ini
# Miscelaneous Variables
APPLICATION_NAME=sigea
SERVER_PORT={Puerto del servidor. def: 16001}

# Hibernate Variables
HIBERNATE_DDL_AUTO={Puedes colocar: update, create-drop}

# PostgreSQL Variables
DB_URL=jdbc:postgresql://{database host}:{database port: def: 5432}/{Nombre de la base de datos}
DB_HOST={database host}
DB_PORT={database port: def: 5432}
DB_NAME={Nombre de la base de datos}
DB_USERNAME={Nombre del usuario de la base de datos}
DB_PASSWORD={Contraseña del usuario de la base de datos}
DB_ADMIN_USERNAME={Nombre de un usuario sysdba en postgreSQL. def: postgres}
DB_ADMIN_USER_PASSWORD={Contraseña del usuario sysdba de postgreSQL}

# OpenAPI Variables
API_DOCS_PATH={def: /v3/api-docs}
API_UI_PATH={Ruta para la documentacion OpenAPI. def: /swagger-ui/index.html}
```

Recuerda reemplazar con tus variables entre los  `{}`  .

### 6.2.2. Configuracion de `psql`

#### 6.2.2.1. En Windows

1. Ubica el directorio donde está psql. Si instalaste PostgreSQL con el instalador oficial, suele estar en:

```bash
C:\Program Files\PostgreSQL\<version>\bin
```

Ejemplo:

```bash
C:\Program Files\PostgreSQL\16\bin
```

2. Abre Panel de control → Sistema → Configuración avanzada del sistema
3. Clic en Variables de entorno
4. En Variables del sistema, selecciona Path → Editar
5. Agrega la ruta:

```bash
C:\Program Files\PostgreSQL\<version>\bin
```

6. Acepta todo y abre una nueva terminal (`cmd` o `PowerShell`).

7. Verificar dentro de la terminal (si no funciona en VSCode cierra y vuelve a abrir):

```bash
psql --version
```

Deberia mostrarte:

```bash
psql (PostgreSQL) <version>
```

#### 6.2.2.2. En Linux (Ubuntu, Debian, etc.)

Cuando instalas PostgreSQL vía apt, psql suele quedar ya en el PATH.
Si no lo está, puedes hacerlo manualmente.

1. Encontrar psql:

```bash
which psql
```

Si no aparece, busca:

```bash
sudo find / -name psql 2>/dev/null
```

Suele estar en:

```bash
/usr/bin/psql
```

2. Añadir al PATH (si fuera necesario)

Edita tu archivo `~/.bashrc` o `~/.zshrc`, ejecutando:

```bash
nano ~/.bashrc
```

Agrega (ajusta la ruta si es distinta):

```bash
export PATH="$PATH:/usr/bin"
```

Luego recarga:

```bash
source ~/.bashrc
```

3. Verifica:

```bash
psql --version
```

#### 6.2.2.3. En macOS

1. Instalado con Homebrew. Si usas Homebrew, generalmente ya queda en el PATH, pero si no:

Ubicación clásica:

```bash
/usr/local/bin/psql        ← Intel Macs
/opt/homebrew/bin/psql     ← Apple Silicon (M1/M2/M3)
```

2. Añadir al PATH

```bash
Edita ~/.zshrc (zsh es el shell por defecto):
```

```bash
nano ~/.zshrc
```

3. Agrega según tu arquitectura:

    3.1. Apple Silicon (M1/M2/M3):

    ```bash
    export PATH="/opt/homebrew/bin:$PATH"
    ```

    3.2. Intel:

    ```bash
    export PATH="/usr/local/bin:$PATH"
    ```

4. Recarga:

```bash
source ~/.zshrc
```

5. Verifica:

```bash
psql --version
```

## 6.3. Ejecutar el proyecto

Para ejecutar el proyecto debes usar los siguientes comandos dependiendo de tu Sistema Operativo:

- `Windows`:
  
```bash
.\scripts\run_sigea_windows.bat
```

- `Linux / MacOS`: 

    1. Asegúrate de que el script sea ejecutable:

    ```bash
    chmod +x run.sh
    ```

    2. Ejecuta:

    ```bash
    ./scripts/run_sigea_linux_mac.sh
    ```

# VII. Colaboradores

# VIII. Licencia

Este proyecto esta bajo la licencia del 