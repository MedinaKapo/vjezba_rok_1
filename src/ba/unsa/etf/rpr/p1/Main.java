package ba.unsa.etf.rpr.p1;

public class Main {

    public static void main(String[] args) {
        System.out.println("Nema maina, pokretni testove!");
    }
    /*
    Potrebno je razviti aplikaciju koja omogućuje praćenje performansi rada neke fabrike. Fabrika sadrži
određeni broj mašina, pri čemu svaka mašina može proizvesti jedan od nekoliko vrsta materijala
određenom brzinom. Osnovni podaci o mašini su naziv i serijski broj, a zatim spisak svih materijala
koje mašina može proizvesti (ovdje treba uzeti u vidu da nemaju sve mašine isti broj materijala koje
mogu proizvesti). Za svaki materijal navodi se koliko sati treba datoj mašini da ga proizvede, što
smatramo za cijenu tog materijala (jer vrijeme.equals(novac)).
Fabrika koristi mašine strane proizvodnje (kupljene), i mašine koje su u fabrici proizveli za svoje
potrebe, koje rade brže. Razliku između domaćih i stranih mašina trebate predstaviti nasljeđivanjem,
pri čemu naziv klase nije bitan (ne testira se).
Naziv mašine se sastoji isključivo od velikih i malih slova abecede, sa ograničenjem na dužinu koja
mora biti minimalno 2 karaktera. Serijski broj mašine je pozitivan cijeli broj sa najviše 5 cifara (0 <
serijski < 100000). Sve mašine su ograničene od strane proizvođača na 8 radnih sati dnevno.
Mašine podržavaju sljedeće operacije:
• int getSerijski() - daje serijski broj.
• void upali() - metoda za paljenje mašine, baca izuzetak (korisnički definisani) tipa
WrongMachineState ako je već upaljena. Pri paljenju mašina dobiva 8 radnih sati za
proizvodnju.
• void ugasi() - gašenje mašine, baca izuzetak tipa WrongMachineState ako je već ugašena.
• void resetuj() - gašenje i paljenje mašine, resetuje broj sati na 8, baca izuzetak tipa
WrongMachineState ako je ugašena.
• int cijena(String materijal) – vraća broj sati koliko košta proizvodnja datog materijala,
baca izuzetak tipa IllegalArgumentException ako se materijal ne može proizvesti.
• int proizvedi(String materijal) – proizvodi navedeni materijal, vraća broj sati koliko je
proizvodnja trajala, baca izuzetak tipa IllegalArgumentException ako mašina ne može
proizvesti navedeni materijal ili ako nema dovoljno sati na raspolaganju da se dati materijal
proizvede.
• int preostaloSati() - vraća broj sati koliko ova mašina još može raditi, vraća 0 ako je
mašina ugašena.
• void registrujMaterijal(String naziv, int cijena) - registruje da data mašina može
proizvesti navedeni materijal pod određenom cijenom datom u satima.
• Set<String> dajMaterijaleMoguceZaProizvesti() - vraća nazive svih materijala koje ova
mašina može proizvesti u preostalom vremenu, abecedno sortirano.
• Map<String, Integer> dajMogucnostProizvodnje() - vraća novu mapu čiji je ključ tipa
string i predstavlja ime materijala, a vrijednost je broj komada koliko se može proizvesti ako se
ostatak vremena posveti samo proizvodnji ovog materijala.
Mašina domaće proizvodnje pored navedenih operacija ima još sljedeće operacije:
• void pocniBrziRad() - prebacuje mašinu u režim brzog rada, baca izuzetak tipa
WrongMachineState ukoliko već jeste u tom režimu. U ovom režimu se sve vremenske cijene
smanjuju za 2, s tim da ne mogu ići ispod 1
• void zaustaviBrziRad() - vraća mašinu u standardni režim rada, baca izuzetak tipa
WrongMachineState ukoliko nije bila u režimu brzog rada
Kod domaćih mašina se, kod svih metoda koje uzimaju u obzir trajanje, uzima modificirana vrijednost
u zavisnosti od režima rada u kojem se nalazi.
Materijal predstavljaju dva podatka: naziv (String) materijala i cijena (u satima, od 1 do 5).
Pri registraciji mašina i materijala trebaju se automatski izbaciti duplikati. Kao duplikat smatramo
mašinu sa istim serijskim brojem, odnosno mašine sa istim serijskim, neovisno o nazivu i ostalim
atributima. Ako unesena mašina već postoji, podrazumijeva se da se prethodni unos "pregazi", čime
posljednje unesene vrijednosti postaju aktuelne. Međutim, ako uneseni materijal već postoji, odbacuje
se proslijeđeni podatak i ostaje ranije pohranjeni podatak!
Kada se mašina ispiše (npr. System.out.println(m) gdje je m instanca klase Masina), potrebno je na
izlazu da se ispiše:
"Masina <naziv> je upaljena (preostalo <N> sati)" ili "Masina <naziv> je ugasena",
zavisno da li je upaljena ili ne. Nakon toga slijed ispis:
“Ona moze proizvesti materijale <spisak>”, gdje je <spisak> materijala u obliku "naziv
(cijena)", i abecedno su sortirani po nazivu, a razdvojeni su znakom zarez (na kraju se nalazi tačka).
Kada se fabrika ispiše (npr System.out.println(f) gdje je f instanca klase Fabrika), potrebno je da se
ispište spisak svih mašina, uz redni broj ispred, npr.
1. Masina masina1 je ugasena. Ona moze proizvesti materijale m1 (1), m5 (5).
2. Masina masina2 je upaljena (preostalo 5 sati). Ona moze proizvesti.... (itd.)
Konačno, potrebno je u klasi Fabrika implementirati sljedeće metode:
• Map<Masina, String> najviseProizvoda() - vraća mapu gdje je ključ mašina, a vrijednost
naziv materijala kojeg ta mašina može najviše proizvesti danas. U mapi se izostavljaju ugašene
mašine. Mašine koje nemaju registrovan nijedan materijal treba također izostaviti. Ukoliko ima
više materijala koje za neku mašinu ispunjavaju navedene uslove, uzima se onaj materijal koji
dolazi prvi po abecedi. (npr ako mašina M moze proizvesti i materijal m1 i m2 8 puta danas,
uzima se m1).
• Masina dodajDomacuMasinu(String naziv, int serijski) - registruje datu mašinu u
fabriku i vraća kreiranu instancu mašine. Pri registraciji mašina je ugašena.
• Masina dodajKupljenuMasinu(String naziv, int serijski) - registruje datu mašinu u
fabriku i vraća kreiranu instancu mašine. Pri registraciji mašina je ugašena.
• void dodajMaterijal(String nazivMasine, String nazivMaterijala, int cijena) -
registruje dati materijal u mašinu sa prosljeđenim nazivom. Baca izuzetak tipa
IllegalArgumentException ukoliko ne postoji. Ukoliko ima više mašina sa istim nazivom,
materijal se dodaje u sve mašine sa navedenim nazivom, neovisno o tome da li je upaljena ili
ne.
• Map<Masina, Integer> cijenaZaMaterijal(String naziv) – vraća mapu gdje je ključ
mašina a vrijednost cijena navedenog materijala, ukoliko se dati materijal proizvodi na toj
mašini. Ukoliko mašina nije u stanju da proizvede dati materijal mapa će sadržati -1, a ukoliko
je mašina ugašena neće se pojaviti unutar ove mape.
• Set<Masina> dajMasine() - vraća set mašina sortiran po upotrebljivosti mašine za
proizvodnju. Kriterij za upotrebljivost, tj. da je mašina A korisnija od mašine B, je da ima veći
izbor materijala koji se mogu proizvesti sa preostalim satima. Ukoliko ovaj kriterij zaključuje
da su dvije mašine jednake, vrši se dalji poredak po nazivu (abecedno, sto je i defaultni poredak
za String klasu). Sortira se od najmanje vrijednosti do najveće (najmanji broj materijala na
početak, odnosno rastući poredak).
Funkcija ima i jedan argument filter koji predstavlja pokazivač na funkciju. Ukoliko je
vrijednost ovog argumenta null, uzimaju se sve mašine u obzir. Ukoliko vrijednost ovog
argumenta nije null, uzimaju se samo one mašine u obzir za koji poziv prosljeđene funkcije
vraća vrijednost true.
     */
}
