# ProjetJava

### modif à effectuer
- Checker les propositions pour les requêtes SQL dans RequeteSQL.txt : elles ne sont que pour les 3 recherches pour l'instant pas le CRUD

### Thread

a pas oublier ahhaha


### Code Java
Problème lors de la création quand on choisit de ne pas enregistrer de Will

Si il y a un soucis lors de la création d'un agent, mais que le programme sait quand même créé sa Will : il enregistre quand même la will dans la DB ==> ça doit pas être possible => gérer exception lors de création agent si will != null et agent.getEditorial().getCode() != null

Faire une exception dans le cas où la mission entrée pour la recherche 3 n'existe pas  => Checker si arrayList<> != de vide

Recheck aussi chacune des classes au cas ou mais on a un peu de time (je ferai ca dans la semaine chill)

--

Terminer verif des données pour la recherche (jerem s'en occupe tkt)

Reset les tableaux ou les mettre à vide si données entrées éronnées ou que pas de données à montrer (voir commentaire recherche 2 et 3)

Ajouter tooltip sur numéro de tel et date de naissance + changer formant en dd/MM/yyyy nn ?