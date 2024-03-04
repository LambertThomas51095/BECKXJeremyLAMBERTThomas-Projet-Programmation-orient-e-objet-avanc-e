# ProjetJava

### modif à effectuer
- Add le schéma E-A modifié et le schéma BD relationnel dans le git car pas actuel comparé aux screens dans le doc

### Propositions relatives aux requêtes

1) Première Requête

Original : Informations sur les agents d’une cellule

Modifiée : Les langues parlées par les agents au sein d’une cellule 



2) Seconde Requête

Original : Recherche de missions d’un agent

Modifiée : Les pays où se sont déroulées les différentes missions d'un agent

Changer l'entrée du nom-prénom par le matricule d'un agent non ? plus simple en query car identifiant puis QUID si deux jeremy beckx dans la BD ?




3) Troisième Requête

Original : Recherche améliorée des missions d’un agent

Modifiée : Le nombre de contact pour un type de mission donnée

=> Tables : Contact, Coverage, Location, Attribution, Mission, Mission_Type

=> critère de recherche : type de mission (format : texte)

=> Affiché : nombre de contact(Contact)

### Propositions tâche(s) métier
- Intégrer dans l'application un codeur-décodeur de message chiffré (méthode de chiffrement à définir ou à créer : césar, morse, ...)

### propositions thread(s)
- intégration sur une bande supérieure (fond d'écran de menu par exemple), du code qui défile comme si tout était chiffré en mode hacking

- un personnage qui regarde à droite, à gauche lorsque l'utilisateur utilise un formulaire dans l'application pour faire genre il surveille autour ? (il s'arrêterait pas tant qu'on a pas validé l'entrée) (à voir mais pas sur ca soit compté en thread)

