# applicazione contatti 

### design dell'architettura + sviluppo del prototipo

Prova di applicazione per il corso di ing. del software per gestione dei contatti. 

Template Vaading tratto da https://github.com/vaadin/skeleton-starter-flow/tree/v22

Per far girare il codice devi aprire eclipse usando come workspace la cartella che contiene i tre progetti.
Poi fare "import ->existing maven project" e selezionare i tre progetti.
Bisognare poi fare "run as -> maven install" nel seguente ordine: db_sqlite, business_logic e poi webapp.
Se i maven install funzionano, per far girare l'app bisogna fare "run as maven build" sul progetto webapp, e mettere come goals: "jetty:run", poi applica e chiudi.
Una volta avviato basta aprire il browser e digitare localhost:8080.

Per ulteriori informazioni consulatre: https://github.com/garganti/applicazione_contatti/wiki


