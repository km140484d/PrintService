package printservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class PoljePriznanice {
    public PoljePriznanice() {
        super();
    }
    String vrednost;
    String labela;
    String font;
    String pozicijaTop;
    String pozicijaLeft;

    public static final BigDecimal STAMPA_PRIZNANICE_KONTINUALNA = new BigDecimal(1);
    public static final BigDecimal STAMPA_PRIZNANICE_POJEDINACNA = new BigDecimal(2);
    public static final BigDecimal STAMPA_PRIZNANICE_NA_RACUNU = new BigDecimal(3);
    
    private static Map<BigDecimal, Map<String, PozicijaPoljaPriznanice>> STAMPA_PRIZNANICE_POZICIJE = null;

    public static final String IZNOS = "IZNOS";
    public static final String POTROSACKI_BROJ = "POTROSACKI_BROJ";
    public static final String PRIMALAC = "PRIMALAC";
    public static final String TRANSAKCIJA = "TRANSAKCIJA";
    public static final String DATUM_VALUTE = "DATUM_VALUTE";
    public static final String NAPLATIO = "NAPLATIO";
    public static final String OJ = "OJ";
    public static final String OJ_GRAD = "OJ_GRAD";
    public static final String TELEFON = "TELEFON";
    public static final String IZNOS_SLOVIMA = "IZNOS_SLOVIMA";
    public static final String NACIN_PLACANJA = "NACIN_PLACANJA";
    public static final String POZIV_NA_BROJ_RACUNI = "POZIV_NA_BROJ_RACUNI";
    public static final String POZIV_NA_BROJ_RACUNI_2 = "POZIV_NA_BROJ_RACUNI_2";
    public static final String POZIV_NA_BROJ = "POZIV_NA_BROJ";
    public static final String NAZIV_POTROSACA = "NAZIV_POTROSACA";
    public static final String ID_STAMPE = "ID_STAMPE";
    public static final String BLAGAJNA = "BLAGAJNA";
    public static final String OJ_2 = "OJ_2";
    public static final String IZNOS_2 = "IZNOS_2";
    public static final String DATUM_VALUTE_2 = "DATUM_VALUTE_2";
    public static final String BLAGAJNA_2 = "BLAGAJNA_2";
    public static final String TRANSAKCIJA_2 = "TRANSAKCIJA_2";
    public static final String ID_STAMPE_2 = "ID_STAMPE_2";
    
    static {
        STAMPA_PRIZNANICE_POZICIJE = new HashMap<BigDecimal, Map<String, PozicijaPoljaPriznanice>>();
        
        Map<String, PozicijaPoljaPriznanice> pozicije = new HashMap<String, PozicijaPoljaPriznanice>();
        pozicije.put(OJ, new PozicijaPoljaPriznanice("65", "87"));        
        pozicije.put(TELEFON, new PozicijaPoljaPriznanice("133", "83"));        
        pozicije.put(TRANSAKCIJA, new PozicijaPoljaPriznanice("110", "0"));        
        pozicije.put(POTROSACKI_BROJ, new PozicijaPoljaPriznanice("52", "10"));        
        pozicije.put(IZNOS, new PozicijaPoljaPriznanice("47", "23"));        
        pozicije.put(IZNOS_SLOVIMA, new PozicijaPoljaPriznanice("82", "23"));        
        pozicije.put(PRIMALAC, new PozicijaPoljaPriznanice("57", "36"));        
        pozicije.put(POZIV_NA_BROJ_RACUNI, new PozicijaPoljaPriznanice("35", "43", "3"));
        pozicije.put(POZIV_NA_BROJ_RACUNI_2, new PozicijaPoljaPriznanice("35", "47", "3"));        
        pozicije.put(DATUM_VALUTE, new PozicijaPoljaPriznanice("25", "56"));          
        pozicije.put(NAPLATIO, new PozicijaPoljaPriznanice("95", "56"));          
        pozicije.put(NACIN_PLACANJA, new PozicijaPoljaPriznanice("130", "0")); 
        STAMPA_PRIZNANICE_POZICIJE.put(STAMPA_PRIZNANICE_KONTINUALNA, pozicije);
        
        pozicije = new HashMap<String, PozicijaPoljaPriznanice>();
        pozicije.put(OJ_GRAD, new PozicijaPoljaPriznanice("74", "18", "3"));
        pozicije.put(POTROSACKI_BROJ, new PozicijaPoljaPriznanice("35", "49", "3"));
        pozicije.put(POZIV_NA_BROJ, new PozicijaPoljaPriznanice("95", "49", "3"));       
        pozicije.put(POZIV_NA_BROJ_RACUNI, new PozicijaPoljaPriznanice("1", "71", "2"));
        pozicije.put(POZIV_NA_BROJ_RACUNI_2, new PozicijaPoljaPriznanice("1", "75", "2"));
        pozicije.put(PRIMALAC, new PozicijaPoljaPriznanice("35", "57", "3"));
        pozicije.put(IZNOS, new PozicijaPoljaPriznanice("35", "65", "3"));
        pozicije.put(TRANSAKCIJA, new PozicijaPoljaPriznanice("95", "65", "3"));
        pozicije.put(DATUM_VALUTE, new PozicijaPoljaPriznanice("3", "89", "3"));
        pozicije.put(NAPLATIO, new PozicijaPoljaPriznanice("68", "89", "3"));  
        pozicije.put(ID_STAMPE, new PozicijaPoljaPriznanice("110", "95", "3"));  
        STAMPA_PRIZNANICE_POZICIJE.put(STAMPA_PRIZNANICE_POJEDINACNA, pozicije);        
        
        pozicije = new HashMap<String, PozicijaPoljaPriznanice>();
        pozicije.put(OJ, new PozicijaPoljaPriznanice("104", "104", "2", "OJ "));
        pozicije.put(IZNOS, new PozicijaPoljaPriznanice("104", "108", "2", "Iznos uplate (EUR) : "));
        pozicije.put(DATUM_VALUTE, new PozicijaPoljaPriznanice("104", "112", "2", "Dat. i vrijeme : "));
        pozicije.put(BLAGAJNA, new PozicijaPoljaPriznanice("104", "116", "2", "Šifra blagajne : "));
        pozicije.put(TRANSAKCIJA, new PozicijaPoljaPriznanice("104", "120", "2", "ID prometa blag. : "));
        pozicije.put(ID_STAMPE, new PozicijaPoljaPriznanice("104", "124", "2", "ID potvrde : "));
 
        //pozocije na vrhu treceg dela
        pozicije.put(OJ_2, new PozicijaPoljaPriznanice("104", "248", "2", "OJ "));
        pozicije.put(IZNOS_2, new PozicijaPoljaPriznanice("104", "252", "2", "Iznos uplate (EUR) : "));
        pozicije.put(DATUM_VALUTE_2, new PozicijaPoljaPriznanice("104", "256", "2", "Dat. i vrijeme : "));
        pozicije.put(BLAGAJNA_2, new PozicijaPoljaPriznanice("104", "260", "2", "Šifra blagajne : "));
        pozicije.put(TRANSAKCIJA_2, new PozicijaPoljaPriznanice("104", "264", "2", "ID prometa blag. : "));
        pozicije.put(ID_STAMPE_2, new PozicijaPoljaPriznanice("104", "268", "2", "ID potvrde : "));
        STAMPA_PRIZNANICE_POZICIJE.put(STAMPA_PRIZNANICE_NA_RACUNU, pozicije);
    }
    
    public PoljePriznanice(Number nacinStampe, String id, String vrednost) {
        if (nacinStampe == null) {
            System.out.println("Nacin stampe je obavezan parametar!");
        }
        Map<String, PozicijaPoljaPriznanice> pozicije = STAMPA_PRIZNANICE_POZICIJE.get(nacinStampe);

        if (id != null && pozicije.get(id) != null) {
            PozicijaPoljaPriznanice pozicija = pozicije.get(id);
            if (pozicija.getLabela() != null && vrednost != null) {
                this.setVrednost(pozicija.getLabela() + vrednost);
            } else if (vrednost != null) {
                this.setVrednost(vrednost);
            } else {
                this.setVrednost(" ");
            }
            
            this.setPozicijaLeft(pozicija.getLeft());
            this.setPozicijaTop(pozicija.getTop());
            this.setFont(pozicija.getFont());
        } else {
            this.setPozicijaLeft(null);
            this.setPozicijaTop(null);
            this.setFont(null);
        }
    }

    public void setVrednost(String vrednost) {
        this.vrednost = vrednost;
    }

    public String getVrednost() {
        return vrednost;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getFont() {
        return font;
    }

    public void setPozicijaTop(String pozicijaTop) {
        this.pozicijaTop = pozicijaTop;
    }

    public String getPozicijaTop() {
        return pozicijaTop;
    }

    public void setPozicijaLeft(String pozicijaLeft) {
        this.pozicijaLeft = pozicijaLeft;
    }

    public String getPozicijaLeft() {
        return pozicijaLeft;
    }
}
