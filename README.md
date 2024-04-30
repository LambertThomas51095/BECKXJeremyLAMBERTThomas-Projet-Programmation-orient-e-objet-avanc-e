# ProjetJava

### modif à effectuer
- Checker les propositions pour les requêtes SQL dans RequeteSQL.txt : elles ne sont que pour les 3 recherches pour l'instant pas le CRUD

### Thread

a pas oublier ahhaha


### Code Java
Problème lors de la création quand on choisit de ne pas enregistrer de Will

Problème lors de la suppression d'un agent ==> doit supprimer la Will s'il y en a une

Si il y a un soucis lors de la création d'un agent, mais que le programme sait quand même créé sa Will : il enregistre quand même la will dans la DB ==> ça doit pas être possible => gérer exception lors de création agent si will != null et agent.getEditorial().getCode() != null

Faire une exception dans le cas où la mission entrée pour la recherche 3 n'existe pas  => Checker si arrayList<> != de vide

Recheck aussi chacune des classes au cas ou mais on a un peu de time (je ferai ca dans la semaine chill)