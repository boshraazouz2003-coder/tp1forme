import java.math.*;
abstract class Forme {
    String nom;
    int couleur;
    abstract float surf();
    protected static int nb0bj=0;

public Forme() {
    this.nom="Forme";
    this.couleur=0;
    nb0bj++;

}

public Forme ( String nom, int couleur){
    this.nom = nom;
    this.couleur = couleur;
    nb0bj++;
}
  @Override
    public String toString() {
        return nom + '\t' + couleur;
}
}
class Rectangle extends Forme {
    float L;
    float l;
    public Rectangle (String nom, int couleur, float L, float l) {
        super(nom, couleur);
        this.L = L;
        this.l = l;
}
 @Override
    public String toString() {
        return "Rectangle : " + super.toString() + "\t" + L + l ;
    }
float surf(){
    return (L*l);
    }
}
class Cercle extends Forme {
    float r;

    public Cercle (String nom, int couleur, float r) {
        super(nom, couleur);
        this.r = r;
}
 @Override
    public String toString() {
        return "Cercle : " + super.toString() + "\t" + r ;
    }
    float surf(){
    return (float)(Math.PI*r*r);
}
}

class Triangle extends Forme {
    float b;
    float h;

    public Triangle (String nom, int couleur, float b, float h) {
        super(nom, couleur);
        this.b = b;
        this.h = h;
}
 @Override
    public String toString() {
        return "Triangle : " + super.toString() + "\t" + b + h;
    }

    float surf() {
       return (b*h) / 2;
    }
}
public class Main2