/**
 * @author Lorenz Rasch
 */
public enum OWMCity {
    BASEL(2661604),
    BERN(2661552),
    BIEL(2661513),
    CHUR(2661169),
    ZURICH(6295546);

    private final int id;

    OWMCity(int i){
        id = i;
    }
}
