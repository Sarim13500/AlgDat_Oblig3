# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Sarim Asher Saeed, S362123, s362123@oslomet.no


# Oppgavebeskrivelse

Oppgave 5 og 6 er ikke gjort for obligen pga kort frist. Jeg skal derfor gjøre de litt senere for å 
forberede meg til eksamen.

I oppgave 1 så gikk jeg frem ved å først finne veien til der den nye noden skal legges til. Når denne plassen er funnet så 
lages det en ny node med denne verdien. Denne nye noden vil få riktig foreldernode og være riktig høyre- eller venstrenode

I oppgave 2 så brukte jeg inneholder() metoden til å se om verdien jeg vil finne eksisterer i treet. Dersom denne eksisterer
brukes samme måte i oppgave 1 til å gå gjennom treet og finne hvor mange av disse verdiene det er.

I oppgave 3 brukte jeg en while løkke som flytter ned pekeren i treet dersom if setningene er true, og til slutt vil p 
returneres. nestePostorden metoden løses ved å sjekke om forelder noeden er null, hvis den er null vil ikke metoden kjøre.
Dersom den ikke er null vil metoden gå videre og sjekke om p er høyrebarnet til forelder eller om forelder ikke har barn. Hvis
dette er sant så vil førstePostorden metoden kjøre med forelder sitt høyrebarn som rotnoden.


I oppgave 4 utførte jeg metoden ved å bruke en peker q som går gjennom treet i postorden ved hjelp av de to metodene
frs oppgave 3. Når q flyttes til neste, utføres en oppgave på q sin verdi hvis den ikke er null. postordenRecursive er en 
rekursiv metode som har basistilfelle for å stoppe det rekursivet kallet når if(p==null) er true.Når denne er false så kaller
vi på metoden med p.høyre og p.venstre. Etter hvert vil det ikke gå ann å kalle på p sine barn fordi p=null og da vil metoden
stoppe opp

