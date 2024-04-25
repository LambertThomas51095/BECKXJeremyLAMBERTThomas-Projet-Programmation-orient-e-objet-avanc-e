INSERT INTO cell (name,address,phone_number)
VALUES ("Informatique","1 Rue Morte","081/123.456"),
("Mec balèze","47 Rue Pas là","081/153.546"),
("Meuf stupide","325 Rue de la joute","081/86.26.57"),
("Choses"," 5 Rue inconnu","081/568.424");

INSERT INTO mission_type (code, name)
VALUES (1,"sabotage"),
(2,"infiltration"),
(3,"assassinat"),
(4,"libération d'otages"),
(5,"récolte d'informations");

INSERT INTO language (code,name,english_name,percent_world)
VALUES (1,"français","french",3.43),
(2,"anglais","english",31.25),
(3,"mandarin","mandarin",11.94),
(4,"hindi","hindi",7.53),
(5,"espagnol","spanish",6.85),
(6,"arabe","arabic",3.43),
(7,"russe","russian",3.23),
(8,"portugais","portuguese",3.22),
(9,"japonnais","japanese",1.63),
(10,"allemand","german",1.57);

INSERT INTO country (name,leader,currency,government_type)
VALUES ("Belgique","Philipe","euro","monarchie"),
("Russie","Vladimir Poutine","rouble russe","état fédéral"),
("Portugal","Marcelo Rebelo de Sousa","euro","république"),
("France","Emmanuel Macron","euro","république"),
("Angleterre","Charles III","livre sterling","démocratie"),
("Japon","Naruhito","yen","monarchie"),
("Allemagne","Frank-Walter Steinmeier","euro","république");

INSERT INTO location (code,name,postal_code,inhabitants_nb,position)
VALUES (1,"Namur",5100,112065,"Belgique"),
(2,"Bertrix",6880,8282,"Belgique"),
(3,"Marseille",13000,873076,"France"),
(4,"Paris",75000,1133111,"France"),
(5,"Moscou",101035,13100000,"Russie"),
(6,"Birmingham",2,970892,"Angleterre"),
(7,"Lisbonne",1000001,545920,"Portugal"),
(8,"Londres",1,7172091,"Angleterre"),
(9,"Kyoto",5200461,1460000,"Japon"),
(10,"Berlin",10117,3760000,"Allemagne");

