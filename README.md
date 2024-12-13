# My Ghisa Gym Backend 💪🏋️‍♂️

Il backend di **My Ghisa Gym** è un'applicazione basata su **Spring Boot** che gestisce i dati degli utenti, i piani di allenamento, gli esercizi e la sicurezza tramite autenticazione JWT. Utilizza un database **PostgreSQL** per la memorizzazione dei dati e **Cloudinary** per la gestione delle immagini caricate dagli utenti.

## Funzionalità ⚡

- **Registrazione e Login** 🔑: Gli utenti possono registrarsi, accedere e gestire il proprio account.
- **Gestione Account** 👤: Gli utenti possono modificare i propri dati personali e caricare un avatar.
- **Gestione Esercizi e Piani di Allenamento** 🏋️‍♀️: Creazione, gestione e personalizzazione dei piani di allenamento e degli esercizi.
- **Autenticazione tramite JWT** 🔒: Protezione delle risorse tramite token JWT per garantire l'accesso sicuro.
- **File Upload** 📸: Caricamento di immagini tramite **Cloudinary**.
- **Gestione della Sicurezza** 🛡️: Autenticazione e autorizzazione basate su Spring Security.

## Tecnologie Utilizzate ⚙️

- **Spring Boot** 🚀: Framework principale per la creazione di applicazioni Java. Utilizzato per costruire il backend in modo rapido ed efficiente.
- **Spring Security** 🔐: Gestione della sicurezza, per garantire l'autenticazione e l'autorizzazione tramite JWT.
- **JWT (JSON Web Tokens)** 🛡️: Per l'autenticazione degli utenti tramite token sicuri.
- **Spring Data JPA** 📝: Per l'interazione con il database in modo dichiarativo, utilizzando JPA (Java Persistence API).
- **PostgreSQL** 🗄️: Database relazionale utilizzato per la gestione dei dati.
- **Lombok** 📦: Libreria per ridurre il boilerplate del codice (es. getter, setter, costruttori, ecc.).
- **JUnit** 🧪: Utilizzato per il testing delle funzionalità dell'applicazione (tramite **spring-boot-starter-test**).
- **Cloudinary** ☁️: Servizio di gestione delle immagini e dei file media (per esempio, per caricare avatar utente o immagini degli esercizi).
- **Unirest Java** 🌐: Libreria per effettuare richieste HTTP (utile per comunicare con altri servizi esterni se necessario).

### Dipendenze aggiuntive 📥

- **PostgreSQL Driver**: `org.postgresql:postgresql` per connettersi al database PostgreSQL.
- **jjwt**: Libreria per lavorare con JSON Web Tokens, in particolare `jjwt-api`, `jjwt-impl` e `jjwt-jackson` per la gestione dei JWT nel progetto.
- **Spring Boot DevTools** 🔄: Strumenti di sviluppo che migliorano l'esperienza di sviluppo (ad esempio, hot reload).
- **Spring Boot Starter Web** 🌐: Per costruire applicazioni web basate su Spring Boot, inclusi i controller REST.
- **Spring Boot Starter Security** 🛡️: Per gestire la sicurezza dell'applicazione, comprese le funzioni di login e autorizzazione con JWT.
- **Spring Boot Starter Validation** ✅: Per validare i dati in entrata (ad esempio, nei DTO).

- 
# Collegamento al Front-end del mio progetto 🌍
Visita il Front-end del mio progetto **My Ghisa Gym**
👉 (https://github.com/Pietroro98/FRONTEND-CapstoneProject) 👈


## Prerequisiti ⚙️

Prima di iniziare, assicurati di avere i seguenti strumenti installati:

- **Java 17 o superiore** ☕: È necessario per eseguire l'applicazione.
- **PostgreSQL** 🗄️: Per la gestione del database.
- **Maven** 📦: Per la gestione delle dipendenze e la costruzione del progetto (se non usi un IDE come IntelliJ, che gestisce Maven automaticamente).
- **IDE** 💻: Come **IntelliJ IDEA** o **Eclipse** per lo sviluppo.

## Installazione 🛠️

1. **Clona il repository** 📂:
   - Esegui il comando per clonare il repository nel tuo ambiente locale:
     ```bash
     git clone https://github.com/Pietroro98/BACKEND-CapstoneProject.git
     ```

2. **Configura il database PostgreSQL** 🗄️:
   - Crea un database **PostgreSQL** locale o remoto. Assicurati che il tuo database abbia la configurazione corretta.

3. **Configura il file `application.properties`** ⚙️:
   - Modifica il file `src/main/resources/application.properties` per configurare la connessione al database PostgreSQL:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     spring.jpa.hibernate.ddl-auto=update
     spring.datasource.driver-class-name=org.postgresql.Driver
     spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
     ```

4. **Costruisci il progetto** 🔨:
   - Se non stai usando un IDE, puoi eseguire Maven dal terminale per costruire il progetto:
     ```bash
     mvn clean install
     ```

5. **Esegui l'applicazione** 🚀:
   - Se utilizzi IntelliJ, puoi avviare l'applicazione direttamente dal tuo IDE premendo il tasto **Run** o eseguendo il comando:
     ```bash
     mvn spring-boot:run
     ```

6. **Accedi all'applicazione** 🌍:
   - Una volta avviata, l'app sarà disponibile all'indirizzo `http://localhost:8080`.

## Endpoint API 📡:

### - Autenticazione 🔑
### - Gestione degli Utenti 👤
### - Gestione dei Piani di Allenamento 🏋️‍♀️
### - Gestione degli Esercizi 🏋️‍♂️
### - Gestione dei BodyParts
### - Gestione degli ExerciseWorkout
### Gestione delle Immagini (File Upload-cloudinary) 📸
## Testing 🧪

Puoi eseguire i test dell'applicazione utilizzando Maven:

```bash
mvn test
